-- �������

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
COMMENT ON COLUMN "data_yys"."info"."name" IS '����';
COMMENT ON COLUMN "data_yys"."info"."age" IS '����';
COMMENT ON COLUMN "data_yys"."info"."gender" IS '�Ա�';
COMMENT ON COLUMN "data_yys"."info"."phone_number" IS '�ֻ���';
COMMENT ON COLUMN "data_yys"."info"."mail" IS '����';
COMMENT ON COLUMN "data_yys"."info"."register" IS 'ע��ʱ��';
COMMENT ON TABLE "data_yys"."info" IS '������Ϣ';

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
COMMENT ON COLUMN "data_yys"."shopping"."log" IS '���';
COMMENT ON COLUMN "data_yys"."shopping"."name" IS '����';
COMMENT ON COLUMN "data_yys"."shopping"."city" IS '����';
COMMENT ON COLUMN "data_yys"."shopping"."kind" IS '��Ʒ���';
COMMENT ON COLUMN "data_yys"."shopping"."time" IS '����ʱ��';
COMMENT ON COLUMN "data_yys"."shopping"."price" IS '�۸�';
COMMENT ON TABLE "data_yys"."shopping" IS '�����¼';


INSERT INTO "data_yys"."info" VALUES ('����','19', 'Ů', '13306091892', '781369524@qq.com','20200202');
INSERT INTO "data_yys"."info" VALUES ('����', '20','��', '18965510188', '785438878@qq.com','20200202');
INSERT INTO "data_yys"."shopping" VALUES ('0','����', '����', 'л��', '20210630','12');
COMMIT;