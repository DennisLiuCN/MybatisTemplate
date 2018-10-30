package org.bluesky.mybatis.cascade;

import org.bluesky.mybatis.dao.TeacherDao;
import org.bluesky.mybatis.entity.Student;
import org.bluesky.mybatis.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 教师级联基本测试类
 * @author: liuyuefeng
 * @date: 2015-4-16 下午7:21:36
 * @version: V1.0
 * 
 */
public class TeacherCascadeTest {

	private static ApplicationContext ctx;
	static {
		// 在类路径下寻找applicationContext.xml文件
		ctx = new ClassPathXmlApplicationContext("applicationContext_mybatis.xml");
	}
	
	/**
	 * @param args
	 * @return: void
	 * @throws:
	 */
	public static void main(String[] args) {
		// 从Spring容器中请求映射器
		TeacherDao teacherDao = (TeacherDao) ctx.getBean("teacherDao");
		
		System.out.println("\n级联查询： ");
		try {
			queryTeacherAssoStudent(teacherDao);
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		}
	}

	/**
	 * 查询老师同时查询老师关联的学生
	 * @param teacherDao
	 * @return: void
	 * @throws:
	 */
	private static void queryTeacherAssoStudent(TeacherDao teacherDao) throws Exception {
		// 查询id为1的教师
		Teacher teacher = teacherDao.getByIdColl(1);
		if (teacher == null) {
			System.out.println("未找到相关教师信息。");
		} else {
			// 教师信息
			System.out.println("**********************************************");
			System.out.println("教师姓名：" + "  " + teacher.getName());
			System.out.println("教师职称：" + "  " + teacher.getTitle());
			System.out.println("**********************************************");
			System.out.println("指导学生信息：");
			// 遍历指导的学生
			for (Student s : teacher.getSupervisees()) {
				System.out.println("**********************************************");
				System.out.println(s.getName() + "  " + s.getGender() + "  " + s.getGrade() + "  " + s.getMajor());
			}
			System.out.println("**********************************************");
		}
	}

}
