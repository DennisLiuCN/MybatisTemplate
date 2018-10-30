package org.bluesky.mybatis.cascade;

import org.bluesky.mybatis.dao.StudentDao;
import org.bluesky.mybatis.entity.Student;
import org.bluesky.mybatis.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 学生级联基本测试类
 * @author: liuyuefeng
 * @date: 2015-4-16 下午4:23:53
 * @version: V1.0
 * 
 */
public class StudentCascadeTest {

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
		StudentDao studentDao = (StudentDao) ctx.getBean("studentDao");
		
		try {
			System.out.println("\n级联查询： ");
			queryStudentAssoTeacher(studentDao);
		
			System.out.println("\n级联插入： ");
			addStudentAssoTeacher(studentDao);
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		}
	}

	/**
	 * 查询学生同时查询学生关联的老师
	 * @param studentDao
	 * @return: void
	 * @throws:
	 */
	private static void queryStudentAssoTeacher(StudentDao studentDao) throws Exception {
		// 需要保证数据库中存在ID为4的学生。否则报空指针异常
		Student student = studentDao.getByIdAsso(4);
		// 使用StringBuilder的append操作代替字符串的“+”操作可提高执行效率
		StringBuilder sb = new StringBuilder("学生信息：\n");
		sb.append("姓名：");
		sb.append(student.getName());
		sb.append(" ");
		sb.append("专业：");
		sb.append(student.getMajor());
		sb.append(" 年级：");
		sb.append(student.getGrade());
		sb.append("\n");
		sb.append("指导教师信息：\n");
		sb.append("姓名：");
		sb.append(student.getSupervisor().getName());
		sb.append(" ");
		sb.append("职称：");
		sb.append(student.getSupervisor().getTitle());
		sb.append(" ");
		sb.append("研究方向：");
		sb.append(student.getSupervisor().getResearchArea());
		System.out.println(sb.toString());
	}

	/**
	 * 插入示例，级联教师信息
	 * @param studentDao
	 * @return: void
	 * @throws:
	 */
	private static void addStudentAssoTeacher(StudentDao studentDao) throws Exception {
		//从Spring容器中请求事务管理器，用PlatformTransactionManager类型的引用指向它
		PlatformTransactionManager tm = (PlatformTransactionManager) ctx.getBean("transactionManager");
		Student student = new Student();
		student.setName("王芳");
		student.setGender("女");
		student.setMajor("计算机科学与技术");
		student.setGrade("2011");
		Teacher supervisor = new Teacher();
		supervisor.setId(1);
		student.setSupervisor(supervisor);
		// TransactionDefinition对象代表着事务的定义，即事务的传播行为、隔离级别和是否可读等属性。
		// DefaultTransactionDefinition是此接口的默认实现，给上述属性指定了默认值。如传播行为是PROPAGATION_REQUIRED、只读为false等（可参见Spring api文档）
		TransactionDefinition def = new DefaultTransactionDefinition();
		// TransactionStatus对象代表着事务的状态。以下代码根据传入的事务定义对象返回事务并启动事务
		TransactionStatus status = (TransactionStatus) tm.getTransaction(def);
		try {
			studentDao.addAsso(student);
			// 若执行下述语句，则事务回滚。
			// int a = 1/0;
			// 提交事务
			tm.commit(status);
			System.out.println("数据库生成的ID： " + student.getId());
		} catch (Exception e) {
			// 回滚事务
			tm.rollback(status);
			e.printStackTrace();
		}
	}

}
