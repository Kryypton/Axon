create table USERS (
   DISCORD_ID BIGINT not null,
   PSEUDO varchar(32) null,
   COMMING_DATE timestamp not null,
   TIME_SPEND BIGINT not null,
   LAST_LOGIN_DATE timestamp,
   constraint PK_USERS primary key (DISCORD_ID)
);

create table characters (
    CHARACTER_ID BIGINT UNIQUE not null,
    DISCORD_ID BIGINT not null,
    NAME varchar(32) not null,
    IS_MAIN boolean,
    ROLE int,
    constraint PK_CHARACTERS primary key (CHARACTER_ID, DISCORD_ID)
);

create table recruitements (
    RECRUITER_DISCORD_ID BIGINT not null,
    CHARACTER_ID BIGINT not null,
    CONVOCATION_DATE timestamp,
    ENTRETIEN_DATE timestamp,
    constraint PK_RECRUTEMENTS primary key (RECRUITER_DISCORD_ID, CHARACTER_ID)
);
