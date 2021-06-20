package wo;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class count {
    public  Map<String, Integer> map = new HashMap<String, Integer>();
    public void cnt(String str){//根据城市存出现次数
        map.put("天堂",0);
        int tag=0;
        for(String key :map.keySet()){
            if(str.equals(key)){
                int val=map.get(key);
                map.replace(key,val+1);
                tag=1;
            }
        }
        if(tag==0){
            map.put(str,1);
        }

    }
    public void sum(){//进行最高到达数城市的统计
        Map<String, Integer> map1 = new HashMap<>();
        String maxstring="";
        int max=0;
        for(String key :map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                maxstring=key;
            }
        }
        map1.put(maxstring,max);//1
        map.remove(maxstring);
        maxstring="";
        max=0;
        for(String key :map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                maxstring=key;
            }
        }
        map1.put(maxstring,max);//2
        map.remove(maxstring);
        maxstring="";
        max=0;
        for(String key :map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                maxstring=key;
            }
        }
        map1.put(maxstring,max);//3
        map.remove(maxstring);
        maxstring="";
        max=0;
        for(String key :map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                maxstring=key;
            }
        }
        map1.put(maxstring,max);//4
        map.remove(maxstring);
        maxstring="";
        max=0;
        for(String key :map.keySet()){
            if(map.get(key)>max){
                max=map.get(key);
                maxstring=key;
            }
        }
        map1.put(maxstring,max);//5
        map.remove(maxstring);

        for(String key :map1.keySet()){

            System.out.println(key);
            System.out.println(map1.get(key));
        }
    }
}
