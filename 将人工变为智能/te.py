import os

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import Ridge
from sklearn.model_selection import cross_val_score
from sklearn.ensemble import RandomForestRegressor

# 对缺省值进行填充
def fill_na(df, feature):
    s = df[feature].value_counts(normalize=True)
    missing = df[feature].isnull()
    df.loc[missing, feature] = np.random.choice(s.index, size=len(df[missing]), p=s.values)


def remod(df):
    if df['YearBuilt'] == df['YearRemodAdd']:
        res = 0
    else:
        res = 1
    return res

# 数据处理，特征替换和缺失值补充
def prep(df):

    filling_NA = ["BsmtQual", "BsmtCond", "BsmtExposure", "BsmtFinType1", "BsmtFinType2", "FireplaceQu", "GarageType",
                  "GarageFinish", "GarageQual", "Fence", "MiscFeature"]
    for x in filling_NA:
        df[x].fillna('NA', inplace=True)  # 表示数据缺省
    for i in df.columns:
        fill_na(df, i)
    df['Bath'] = df['FullBath'] + df['HalfBath'] + df['BsmtFullBath'] + df['BsmtHalfBath']
    df['Porch'] = df['OpenPorchSF'] + df['3SsnPorch'] + df['ScreenPorch'] + df['EnclosedPorch'] + df['WoodDeckSF']
    df['GarageYear'] = pd.cut(x=df['GarageYrBlt'], bins=[1900, 1933, 1946, 1969, 1981, 1990, 1993, 2002, 2010],
                              labels=[0, 1, 2, 3, 4, 5, 6, 7])
    df['HouseYear'] = pd.cut(x=df['YearBuilt'], bins=[1872, 1915, 1931, 1941, 1956, 1972, 1980, 1991, 2002, 2010],
                             labels=[0, 1, 2, 3, 4, 5, 6, 7, 8])
    dropin = ["MiscFeature", 'FullBath', 'HalfBath', 'BsmtFullBath', 'BsmtHalfBath', 'GarageYrBlt', 'Heating',
              'GarageArea', 'YearBuilt', 'Alley', 'PoolArea', "PoolQC", "Functional", "GarageCond", "GarageQual",
              "PavedDrive", "SaleType", "Utilities", 'BsmtFinSF2', 'BsmtFinSF1', 'OpenPorchSF', '3SsnPorch',
              'ScreenPorch', 'BsmtFinType2', 'WoodDeckSF', 'EnclosedPorch']
    df.drop(dropin, axis=1, inplace=True)
    return df


if __name__ == '__main__':
    # 从相应目录读入文件
    train_data_path=os.path.join('../data','train.csv')
    test_data_path=os.path.join('../data','test.csv')
    train_data = pd.read_csv(train_data_path, index_col='Id')
    test_data = pd.read_csv(test_data_path, index_col='Id')

#                 数据预处理
# 查看数据跨度太大，对其进行取对数操作。
    prices = pd.DataFrame({"price": train_data["SalePrice"], "log(price + 1)": np.log1p(train_data["SalePrice"])})
    prices.hist()
    plt.show()
    plt.close()

#        进行特征处理
    labels = ["MSZoning", "Street", "LotShape", "LandContour", "LotConfig", "Neighborhood",
              "Condition1", "Condition2", "RoofStyle", "RoofMatl", "Exterior1st", "Exterior2nd", "MasVnrType", "Foundation", "GarageType", "SaleCondition"]
    ordinals = ["LandSlope", "BldgType", "HouseStyle", "ExterQual", "ExterCond", "BsmtQual",
                "BsmtCond", "BsmtExposure", "BsmtFinType1", "HeatingQC", "Heating", "CentralAir", "Electrical", "KitchenQual", "FireplaceQu", "GarageFinish","Fence"]

    train_data=prep(train_data)
    test_data=prep(test_data)

    # 将train_data和test_data拼合起来一起处理其他特征，SalePrice进行取对数操作减小维度作为待预测值，并删掉待预测值
    y_train = np.log1p(train_data.pop("SalePrice"))
    all_data = pd.concat((train_data, test_data), axis=0)

    # 类型转化，便于独热编码处理
    all_data["MSSubClass"] = all_data["MSSubClass"].astype(str)
    all_data["GarageYear"] = all_data["GarageYear"].astype(str)
    all_data["HouseYear"] = all_data["HouseYear"].astype(str)

    #  求均值进行再次缺省值填补
    all_dummy_data = pd.get_dummies(all_data)
    mean_cols = all_dummy_data.mean()
    all_dummy_data = all_dummy_data.fillna(mean_cols)

    # 对于数字化特征，进行正则化处理
    numeric_cols = all_data.columns[all_data.dtypes != "object"]
    numeric_col_means = all_dummy_data.loc[:, numeric_cols].mean()
    numeric_col_std = all_dummy_data.loc[:, numeric_cols].std()
    all_dummy_data.loc[:, numeric_cols] = (all_dummy_data.loc[:, numeric_cols] - numeric_col_means) / numeric_col_std

    # 数据重新分回训练集和测试集
    dummy_train_data = all_dummy_data.loc[train_data.index]
    dummy_test_data = all_dummy_data.loc[test_data.index]
    X_train = dummy_train_data.values
    X_test = dummy_test_data.values

#  模型参数筛选
    # np.logspace创建等比数列一共取50个数，用来进行最佳参数搜索
    alphas = np.logspace(-3, 2, 50)
    # 网格搜索来寻找最佳alpha
    # 使用ridge regression岭回归方法做预测
    test_scores = []
    for alpha in alphas:
       clf = Ridge(alpha)
       test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring="neg_mean_squared_error"))
       test_scores.append(np.mean(test_score))


# 将过程可视化出来，便于参数挑选
    plt.plot(alphas, test_scores)
    plt.xlabel("alphas")
    plt.ylabel("test scores")
    plt.show()
    plt.close()

# 特征筛选
    # 设定随机森林中的决策树使用的特征占比
    max_features = [.1, .3, .5, .7, .9, .99]
    test_scores = []
    # 网格搜索来寻找最佳max_feat
    # 使用随机森林模型预测
    for max_feat in max_features:
       clf = RandomForestRegressor(n_estimators=200, max_features=max_feat)
       print(max_feat)
       test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=5, scoring="neg_mean_squared_error"))
       test_scores.append(np.mean(test_score))

# 将过程可视化出来，便于参数挑选
    plt.plot(max_features, test_scores)
    plt.xlabel("max_features")
    plt.ylabel("test_scores")
    plt.show()
    plt.close()

    # 根据上面网格搜索,使用岭回归时最佳alpha为15,使用随机森林时最佳max_features=0.3
    ridge = Ridge(alpha=15)
    rf = RandomForestRegressor(n_estimators=500, max_features=.3)

    ridge.fit(X_train, y_train)
    rf.fit(X_train, y_train)
    # 对预测结果做对数的反操作
    y_ridge = np.expm1(ridge.predict(X_test))
    y_rf = np.expm1(rf.predict(X_test))

    # 一个简单的模型融合，最后取两个学习器(岭回归和随机森林)的算术平均值作为预测值
    y_final = (y_ridge + y_rf) / 2
    submission_data = pd.DataFrame(data={"Id": test_data.index, "SalePrice": y_final})

    # 上传结果到目录
    submission_data.to_csv("sub.csv", index=False)
    print("ok")