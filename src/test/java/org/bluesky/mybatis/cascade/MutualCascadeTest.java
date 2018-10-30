package org.bluesky.mybatis.cascade;

import java.util.List;

import org.bluesky.mybatis.dao.StudentDao;
import org.bluesky.mybatis.dao.TeacherDao;
import org.bluesky.mybatis.entity.Student;
import org.bluesky.mybatis.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 学生教师相互级联复杂模式测试类
 * @author: liuyuefeng
 * @date: 2015-4-17 上午11:15:51
 * @version: V1.0
 * 
 */
public class MutualCascadeTest {

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
		StudentDao studentDao = (StudentDao) ctx.getBean("studentDao");

		try {
			System.out.println("教师级联查询： ");
			queryTeacherAssoStudent(teacherDao);
			
			System.out.println("\n学生级联查询： ");
			queryStudentAssoTeacher(studentDao);
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		}
	}

	/**
	 * @param teacherDao
	 * @return: void
	 * @throws:
	 */
	private static void queryTeacherAssoStudent(TeacherDao teacherDao) throws Exception {
		// 查询id为1的教师
		Teacher teacher = teacherDao.getByIdAssoColl(1);
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
				// 从学生端访问教师
				System.out.println("指导教师研究方向：" + s.getSupervisor().getResearchArea());
			}
			System.out.println("**********************************************");
		}
	}

	/**
	 * @param studentDao
	 * @return: void
	 * @throws:
	 */
	private static void queryStudentAssoTeacher(StudentDao studentDao) throws Exception {
		// 查询id为2的学生
		List<Student> students = studentDao.getByIdAssoColl(2);
		if (students == null || students.size() == 0) {
			System.out.println("未找到相关学生信息。");
		} else {
			// 遍历学生信息
			for (Student student : students) {
				// 学生信息
				System.out.println("**********************************************");
				System.out.println("学生姓名：" + "  " + student.getName());
				System.out.println("入学时间：" + "  " + student.getGrade());
				System.out.println("**********************************************");
				System.out.println("指导教师信息：");
				// 教师信息
				System.out.println("**********************************************");
				Teacher t = student.getSupervisor();
				System.out.println(t.getName() + "  " + t.getGender() + "  " + t.getResearchArea() + "  " + t.getTitle());
				// 从教师端访问学生
				// 遍历指导教师指导的学生
				System.out.print("指导学生姓名：");
				for (Student s : t.getSupervisees()) {
					System.out.print(s.getName() + "  ");
				}
				System.out.println("\n**********************************************");
			}
		}
	}

}
