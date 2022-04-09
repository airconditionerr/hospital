/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
drop table if exists user;
create table user
(
   id                   int(11) not null auto_increment comment '自增主键',
   user_id              varchar(255) default NULL comment '用户id',
   user_name            char(16) default NULL comment '用户名',
   user_sex             char(4) default '男' comment '用户性别',
   user_password        char(32) not null comment '密码',
   user_email           char(32) default NULL comment '用户邮箱',
   user_mobile          char(15) default NULL comment '用户电话',
   user_register_time   timestamp NULL default CURRENT_TIMESTAMP comment '用户注册时间',
   user_register_ip     char(20) default NULL comment '用户注册ip',
   last_login_time      datetime default NULL comment '用户最后登录时间',
   last_login_ip        char(20) default NULL comment '用户最后登录ip',
   update_time          datetime default NULL comment '更新时间（激活邮件时间计时用）',
   verification_code    int(4) default NULL comment '验证码',
   status               tinyint(4) default 1 comment '用户是否激活',
   is_delete            int(4) default 0 comment '用户是否存在',
   primary key (id)
) charset = UTF8;



/*==============================================================*/
/* Table: doctor                                                */
/*==============================================================*/
drop table if exists doctor;
create table doctor
(
   id                   int(11) not null auto_increment comment '医生id',
   doctor_name          varchar(255) default 'UNKNOWN' comment '医生姓名',
   doctor_department    varchar(255) default 'UNKNOWN' comment '医生科室',
   doctor_introduction  varchar(255) default 'UNKNOWN' comment '医生相关',
   doctor_sex           varchar(10) default 'UNKNOWN' comment '医生性别',
   doctor_img           varchar(255) default 'UNKNOWN' comment '医生照片',
   doctor_position      varchar(255) default 'UNKNOWN' comment '医生职称',
   doctor_degree        varchar(255) default 'UNKNOWN' comment '医生学位',
   doctor_hospital      varchar(255) default 'UNKNOWN' comment '医生所属医院',
   doctor_forte         varchar(255) default 'UNKNOWN' comment '医生特长',
   teacher_title        varchar(255) default 'UNKNOWN' comment '医生教学职称',
   primary key (id)
) charset = UTF8;


/*==============================================================*/
/* Table: hospital                                              */
/*==============================================================*/
drop table if exists hospital;
create table hospital
(
   id                   int(11) not null auto_increment comment '医院id',
   hospital_name        varchar(255) default NULL comment '医院名称',
   hospital_area        varchar(255) default NULL comment '医院所在地区',
   hospital_img         varchar(255) default NULL comment '医院照片',
   hospital_dean_name   varchar(255) default NULL comment '医院院长姓名',
   hospital_create_year varchar(255) default NULL comment '医院建院年份',
   hospital_nature      varchar(255) default NULL comment '医院性质',
   hospital_grade       varchar(255) default NULL comment '医院等级',
   hospital_department_num int(11) default NULL comment '医院科室数量',
   hospital_bed_num     int(11) default NULL comment '医院病床数量',
   hospital_annua_outpatient_volume int(11) default NULL comment '医院年门诊量',
   hospital_is_medical_insurance varchar(255) default NULL comment '医院是否支持医保',
   hospital_equipment   text comment '医院设备介绍',
   hospital_introduction text comment '医院介绍',
   hospital_honor       text comment '医院荣誉',
   hospital_url         varchar(255) default NULL comment '医院网址',
   hospital_phone_number varchar(255) default NULL comment '医院咨询电话',
   hospital_address     varchar(255) default NULL comment '医院地址',
   hospital_zip_code    varchar(255) default NULL comment '医院邮编',
   hospital_bus_route   varchar(255) default NULL comment '医院交通路线',
   hospital_is_open     int(11) default 0 comment '医院是否开启挂号',
   primary key (id)
) charset = UTF8;


/*==============================================================*/
/* Table: hospital_department                                   */
/*==============================================================*/
drop table if exists hospital_department;
create table hospital_department
(
   id                   int(11) not null auto_increment comment '科室id',
   department_name      varchar(255) default 'UNKNOWN' comment '科室名字',
   hospital_name        varchar(255) default 'UNKNOWN' comment '医院名字',
   doctor_num           varchar(255) default 'UNKNOWN' comment '医生数量',
   department_honor         text comment '科室荣誉',
   department_equipment     varchar(255) default 'UNKNOWN' comment '科室设备',
   department_introduction  text comment '科室介绍',
   department_diagnosis_scope varchar(255) default 'UNKNOWN' comment '科室诊疗范围',
   primary key (id)
) charset = UTF8;


/*==============================================================*/
/* Table: order_record                                          */
/*==============================================================*/
drop table if exists order_record;
create table order_record
(
   order_id             int(11) not null auto_increment comment '预约id',
   user_id              int(11) default NULL comment '用户id',
   hospital_name        varchar(255) default NULL comment '医院名称',
   department_name          varchar(255) default NULL comment '科室名称',
   doctor_name          varchar(255) default NULL comment '医生姓名',
   transact_date        varchar(255) default NULL comment '预约办理日期',
   transact_time        varchar(255) default NULL comment '预约办理时间段',
   disease_info         varchar(500) default NULL comment '疾病信息',
   is_success           int(5) default 0 comment '是否预约成功',
   is_send              int(5) default 0 comment '是否发送通知成功',
   is_cancel            int(5) default 0 comment '是否取消',
   create_time          timestamp NULL default CURRENT_TIMESTAMP,
   order_ver            int(11) default NULL comment '预约识别码',
   is_finish            int(5) default 0 comment '是否完成',
   primary key (order_id)
) charset = UTF8;
