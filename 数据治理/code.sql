--按月查看总体销售额
select extract (year from k.time),extract (month from k.time),sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time)
;

--按月查看新增注册人数
select extract (year from k.register),extract (month from k.register),count(k) 
from "data_yys"."info"as k
group by extract (year from k.register),extract (month from k.register)
;

--按月查看城市销售额
select k.city,extract (year from k.time),extract (month from k.time),sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city
;

--按月查看城市、商品类别销售额
select k.city,extract (year from k.time),extract (month from k.time),k.kind,sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city,k.kind
;

--按月查看性别、商品类别销售额
select k.city,extract (year from k.time),extract (month from k.time),k.gender,sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city,k.gender
;
