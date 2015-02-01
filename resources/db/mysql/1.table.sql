/*==============================================================*/
/* Table: am_menu                                               */
/*==============================================================*/
create table am_menu
(
   menu_id              char(32) not null,
   menu_cd              varchar(32),
   menu_name            varchar(64),
   url                  varchar(256),
   image_url            varchar(256),
   display_no           numeric(9,0),
   display_level        numeric(9,0),
   end_flag             char(1) comment '0:no, 1:yes',
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (menu_id)
);

/*==============================================================*/
/* Table: am_org                                                */
/*==============================================================*/
create table am_org
(
   org_id               char(32) not null,
   org_cd               varchar(32),
   org_name             varchar(128),
   description          text,
   display_no           numeric(9,0),
   display_level        numeric(9,0),
   end_flag             char(1) comment '0:no, 1:yes',
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (org_id)
);

/*==============================================================*/
/* Table: am_role                                               */
/*==============================================================*/
create table am_role
(
   role_id              char(32) not null,
   role_cd              varchar(32),
   role_name            varchar(64),
   display_no           numeric(9,0),
   description          text,
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (role_id)
);

/*==============================================================*/
/* Table: am_role_menu                                          */
/*==============================================================*/
create table am_role_menu
(
   role_menu_id         char(32) not null,
   role_id              char(32),
   menu_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (role_menu_id)
);

/*==============================================================*/
/* Table: am_role_subsystem                                     */
/*==============================================================*/
create table am_role_subsystem
(
   role_subsystem_id    char(32) not null,
   subsystem_id         char(32),
   role_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (am_role_susbsystem_id)
);

/*==============================================================*/
/* Table: am_subsystem                                          */
/*==============================================================*/
create table am_subsystem
(
   subsystem_id         char(32) not null,
   subsystem_cd         varchar(32),
   subsystem_name       varchar(128),
   display_no           numeric(9,0),
   description          text,
   operator_id          char(32),
   operator_name        varchar(128),
   lang                 varchar(10),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (subsystem_id)
);

/*==============================================================*/
/* Table: am_subsystem_menu                                     */
/*==============================================================*/
create table am_subsystem_menu
(
   subsystem_menu_id    char(32) not null,
   subsystem_id         char(32),
   menu_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (subsystem_menu_id)
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
   default_subsystem_id char(32),
   description          text,
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: am_user_org                                           */
/*==============================================================*/
create table am_user_org
(
   user_org_id          char(32) not null,
   org_id               char(32),
   user_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_org_id)
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
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_role_id)
);

/*==============================================================*/
/* Table: am_user_subsystem                                     */
/*==============================================================*/
create table am_user_subsystem
(
   user_subsystem_id    char(32) not null,
   subsystem_id         char(32),
   user_id              char(32),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (user_subsystem_id)
);

/*==============================================================*/
/* Table: bm_config                                             */
/*==============================================================*/
create table bm_config
(
   config_id            char(32) not null,
   subsystem_id         char(32),
   config_cd            varchar(32),
   config_name          varchar(64),
   config_value         text,
   display_no           numeric(9,0),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   receive_date_time    datetime,
   send_date_time       datetime,
   primary key (config_id)
);

/*==============================================================*/
/* Table: bm_param                                              */
/*==============================================================*/
create table bm_param
(
   param_id             char(32) not null,
   subsystem_id         char(32),
   param_cd             varchar(32),
   param_name           varchar(128),
   param_type_cd        varchar(32),
   param_type_name      varchar(64),
   display_no           numeric(9,0),
   lang                 varchar(10),
   operator_id          char(32),
   operator_name        varchar(128),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_status          char(1),
   send_status          varchar(1) comment '0��don''t sent, 1��has sent',
   send_date_time       datetime,
   receive_date_time    datetime,
   primary key (param_id)
);