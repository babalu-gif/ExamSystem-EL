drop table if exists t_question;

/*==============================================================*/
/* Table: t_question                                            */
/*==============================================================*/
create table t_question
(
   questionId           int auto_increment,
   title                varchar(255),
   optionA              varchar(255),
   optionB              varchar(255),
   optionC              varchar(255),
   optionD              varchar(255),
   answer               char(1),
   primary key (questionId)
);
