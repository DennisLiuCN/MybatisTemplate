/*数据库编码UTF8，以下命令是为了在脚本和命令行中支持中文*/
set names utf-8;

/*创建school数据库*/
drop database if exists school;
create database school;

/*切换到school数据库*/
use school;

/*授予hello用户访问school数据库的全部权限。该用户若不存在则被创建，密码为111111*/
grant all privileges on school.* to hello@'%'
identified by '111111';
flush privileges;

/***************************************************************************/
/*创建学生表*/
drop table if exists student;
CREATE TABLE student(
id int NOT NULL AUTO_INCREMENT,
name varchar(10) NOT NULL,/*姓名*/
gender char(1) NOT NULL,/*性别*/
major varchar(20) NOT NULL,/*专业*/
grade char(4) NOT NULL,/*年级*/
PRIMARY KEY (`id`)
);

/*添加第一条记录*/
insert into student(name, gender, major, grade)
values('李四','男','计算机科学与技术','2011');

/*创建教师表*/
drop table if exists teacher;
CREATE TABLE teacher(
id int NOT NULL AUTO_INCREMENT,
name varchar(10) NOT NULL,/*姓名*/
gender char(1) NOT NULL,/*性别*/
research_area varchar(20) NOT NULL,/*研究方向*/
title varchar(6) NOT NULL,/*职称*/
PRIMARY KEY (`id`)
);

/*添加第一条记录，自动生成的ID为1*/
insert into teacher(name,gender,research_area, title)
values('张三','男','软件工程','讲师');
/***************************************************************************/


/*已下脚本用于一对多关联测试*/
/***************************************************************************/
/*为学生表添加指导老师ID列*/
alter TABLE student add supervisor_id int not null
references teacher(id);
/*把上面新增的教师作为目前学生的指导教师*/
update student set supervisor_id=1;
/***************************************************************************/


/*已下脚本用于多对多关联测试*/
/***************************************************************************/
/*创建课程表*/
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`course_code` varchar(20) NOT NULL COMMENT '课程编号',
`course_name` varchar(50) NOT NULL COMMENT '课程名称',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `course` VALUES ('1', 'zj01', '数据结构');

/*创建选课信息表*/
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`student_id` int(11) NOT NULL COMMENT '选课学生id',
`course_id` int(11) NOT NULL COMMENT '所选课程的id',
PRIMARY KEY (`id`),
KEY `student_id` (`student_id`),
KEY `course_id` (`course_id`),
CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/***************************************************************************/


/*已下脚本用于数据库函数和存储过程测试*/
/***************************************************************************/
/*创建函数*/
DROP PROCEDURE IF EXISTS `P_GET_STUDENT`;
CREATE DEFINER = `root`@`localhost` PROCEDURE `P_GET_STUDENT`(IN `p_name` varchar(10),IN `p_sex` int,OUT `p_result` int)
    COMMENT '根据姓名和性别查询学生数量, 如果传入的是0就女性否则是男性'
BEGIN
	DECLARE num INT DEFAULT 0;

	IF p_sex=0 THEN
		SELECT COUNT(*) INTO num FROM student WHERE name like concat('%',p_name,'%') and gender='女';
	ELSE
		SELECT COUNT(*) INTO num FROM student WHERE name like concat('%',p_name,'%') and gender='男';
	END IF;

	SET p_result = num;
END;

GRANT EXECUTE ON PROCEDURE `school`.`P_GET_STUDENT` TO 'hello'@'%';


/*创建存储过程*/

/***************************************************************************/