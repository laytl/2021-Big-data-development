-- 建表语句

DROP TABLE IF EXISTS "data_yys"."info";
CREATE TABLE "data_yys"."info" (
  "name" varchar(255) primary key COLLATE "pg_catalog"."default",
  "age" varchar(255) COLLATE "pg_catalog"."default",
  "gender" varchar(255) COLLATE "pg_catalog"."default",
  "phone_number" varchar(255) COLLATE "pg_catalog"."default",
  "mail" varchar(255) COLLATE "pg_catalog"."default",
    "register" timestamp
)
;
COMMENT ON COLUMN "data_yys"."info"."name" IS '姓名';
COMMENT ON COLUMN "data_yys"."info"."age" IS '年龄';
COMMENT ON COLUMN "data_yys"."info"."gender" IS '性别';
COMMENT ON COLUMN "data_yys"."info"."phone_number" IS '手机号';
COMMENT ON COLUMN "data_yys"."info"."mail" IS '邮箱';
COMMENT ON COLUMN "data_yys"."info"."register" IS '注册时间';
COMMENT ON TABLE "data_yys"."info" IS '个人信息';

DROP TABLE IF EXISTS "data_yys"."shopping";
CREATE TABLE "data_yys"."shopping" (
  "log"  varchar(255) primary key COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "city" varchar(255) COLLATE "pg_catalog"."default",
  "kind" varchar(255) COLLATE "pg_catalog"."default",
  "time" timestamp ,
  "price" int4 COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "data_yys"."shopping"."log" IS '编号';
COMMENT ON COLUMN "data_yys"."shopping"."name" IS '姓名';
COMMENT ON COLUMN "data_yys"."shopping"."city" IS '城市';
COMMENT ON COLUMN "data_yys"."shopping"."kind" IS '商品类别';
COMMENT ON COLUMN "data_yys"."shopping"."time" IS '购买时间';
COMMENT ON COLUMN "data_yys"."shopping"."price" IS '价格';
COMMENT ON TABLE "data_yys"."shopping" IS '购买记录';


INSERT INTO "data_yys"."info" VALUES ('李文','19', '女', '13306091892', '781369524@qq.com','20200202');
INSERT INTO "data_yys"."info" VALUES ('李武', '20','男', '18965510188', '785438878@qq.com','20200202');
INSERT INTO "data_yys"."shopping" VALUES ('0','李武', '广州', '谢特', '20210630','12');
COMMIT;