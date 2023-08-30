create table USERS (
   DISCORD_ID BIGINT not null,
   PSEUDO varchar(32) null,
   COMMING_DATE timestamp not null,
   TIME_SPEND BIGINT not null,
   LAST_LOGIN_DATE timestamp,
   constraint PK_USERS primary key (DISCORD_ID)
);