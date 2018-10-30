package org.bluesky.mybatis.base;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bluesky.mybatis.dao.CourseDao;
import org.bluesky.mybatis.dao.StudentDao;
import org.bluesky.mybatis.entity.Course;
import org.bluesky.mybatis.entity.Student;

/**
 * 学生选课测试类
 * @author: liuyuefeng
 * @date: 2015-4-18 下午10:36:27
 * @version: V1.0
 * 
 */
public class StudentCourseTest {

	/**
	 * @param args
	 * @return: void
	 * @throws:
	 */
	public static void main(String[] args) {
		// 与Mybatis-Configuration.xml中的mapper配置类似，告诉MyBatis应读取的核心配置文件
		String resource = "mybatis-configuration_base.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 创建SqlSessionFactory实例。没有指定要用到的environment，则使用默认的environment
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		System.out.println("增加学生选课：");
		save(sqlSessionFactory);
	}
	
	/**
	 * 保存示例
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void save(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
		
			Student student = studentDao.getById(8);
			Course course = courseDao.getById(1);
			studentDao.addElecCourse(student, course);
		
			// 提交事务，否则不会实际添加到数据库中
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

}
