create table USERS (
   DISCORD_ID BIGINT not null,
   PSEUDO varchar(32) null,
   COMMING_DATE timestamp not null,
   constraint PK_USERS primary key (DISCORD_ID)
);