--���²鿴�������۶�
select extract (year from k.time),extract (month from k.time),sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time)
;

--���²鿴����ע������
select extract (year from k.register),extract (month from k.register),count(k) 
from "data_yys"."info"as k
group by extract (year from k.register),extract (month from k.register)
;

--���²鿴�������۶�
select k.city,extract (year from k.time),extract (month from k.time),sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city
;

--���²鿴���С���Ʒ������۶�
select k.city,extract (year from k.time),extract (month from k.time),k.kind,sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city,k.kind
;

--���²鿴�Ա���Ʒ������۶�
select k.city,extract (year from k.time),extract (month from k.time),k.gender,sum(k.price) 
from("data_yys"."info"as i right join "data_yys"."shopping"as s on i.name=s.name)as k
group by extract (year from k.time),extract (month from k.time),k.city,k.gender
;
