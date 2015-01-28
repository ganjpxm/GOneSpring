/*==============================================================*/
/* Table: am_role                                               */
/*==============================================================*/
create table am_role
(
   role_id              char(32) not null,
   role_cd              varchar(32),
   role_name            varchar(64),
   description          text,
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0£∫don''t sent, 1£∫has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (role_id)
);

/*==============================================================*/
/* Table: am_user                                               */
/*==============================================================*/
create table am_user
(
   user_id              char(32) not null,
   user_cd              varchar(32),
   first_name           varchar(64),
   last_name            varchar(64),
   gender               varchar(6),
   birthday             date,
   mobile_number        varchar(20),
   email                varchar(64),
   password             varchar(64),
   photo_url            varchar(256),
   login_count          numeric(9,0),
   description          text,
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0£∫don''t sent, 1£∫has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: am_user_role                                          */
/*==============================================================*/
create table am_user_role
(
   user_role_id         char(32) not null,
   user_id              char(32),
   role_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0£∫don''t sent, 1£∫has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_role_id)
);

