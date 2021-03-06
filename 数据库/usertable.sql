--------------------------------------------------------
--  文件已创建 - 星期一-十月-06-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USERTABLE
--------------------------------------------------------

  CREATE TABLE "SALIERI"."USERTABLE" 
   (	"ID" NUMBER, 
	"USERNAME" VARCHAR2(32 BYTE), 
	"PASSWORD" VARCHAR2(32 BYTE), 
	"ISMANAGER" NUMBER
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "PRACTICE" ;
REM INSERTING into SALIERI.USERTABLE
SET DEFINE OFF;
Insert into SALIERI.USERTABLE (ID,USERNAME,PASSWORD,ISMANAGER) values (2,'1001','1002',2);
Insert into SALIERI.USERTABLE (ID,USERNAME,PASSWORD,ISMANAGER) values (1,'admin','admin',1);
Insert into SALIERI.USERTABLE (ID,USERNAME,PASSWORD,ISMANAGER) values (3,'normal','normal',2);
Insert into SALIERI.USERTABLE (ID,USERNAME,PASSWORD,ISMANAGER) values (4,'1002','1006',2);
--------------------------------------------------------
--  DDL for Index USERTABLE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SALIERI"."USERTABLE_UK1" ON "SALIERI"."USERTABLE" ("USERNAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "PRACTICE" ;
--------------------------------------------------------
--  DDL for Index USERTABLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SALIERI"."USERTABLE_PK" ON "SALIERI"."USERTABLE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "PRACTICE" ;
--------------------------------------------------------
--  Constraints for Table USERTABLE
--------------------------------------------------------

  ALTER TABLE "SALIERI"."USERTABLE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "SALIERI"."USERTABLE" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "SALIERI"."USERTABLE" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "SALIERI"."USERTABLE" MODIFY ("ISMANAGER" NOT NULL ENABLE);
 
  ALTER TABLE "SALIERI"."USERTABLE" ADD CONSTRAINT "USERTABLE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "PRACTICE"  ENABLE;
 
  ALTER TABLE "SALIERI"."USERTABLE" ADD CONSTRAINT "USERTABLE_UK1" UNIQUE ("USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "PRACTICE"  ENABLE;
