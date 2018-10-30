package org.bluesky.mybatis.base;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bluesky.mybatis.dao.TeacherDao;
import org.bluesky.mybatis.entity.Teacher;

/**
 * 教师基本测试类
 * @author: liuyuefeng
 * @date: 2015-4-14 下午3:37:56
 * @version: V1.0
 * 
 */
public class TeacherTest {

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
		
		System.out.println("按主键查询：");
		query(sqlSessionFactory);
		System.out.println("\n新增： ");
		add(sqlSessionFactory);
		System.out.println("\n按名称查询： ");
		query2(sqlSessionFactory);
		System.out.println("\n更新： ");
		update(sqlSessionFactory);
		System.out.println("\n删除： ");
		delete(sqlSessionFactory);
	}

	/**
	 * 查询示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void query(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
			Teacher teacher = teacherDao.getById(1);
			if (teacher != null) {
				System.out.println("姓名： " + teacher.getName() + "\n研究方向： " + teacher.getResearchArea());
			} else {
				System.out.println("没有找到。");
			}
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 查询示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void query2(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			TeacherDao TeacherDao = sqlSession.getMapper(TeacherDao.class);
			List<Teacher> teachers = TeacherDao.getByName("张伟");
			if (teachers != null) {
				for (Teacher teacher : teachers)
					System.out.println("姓名： " + teacher.getName() + "\n研究方向： " + teacher.getResearchArea());
			} else {
				System.out.println("没有找到。");
			}
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 插入示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void add(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		Teacher teacher = new Teacher();
		teacher.setName("徐渭");
		teacher.setGender("男");
		teacher.setResearchArea("语言学");
		teacher.setTitle("教授");
		try {
			TeacherDao TeacherDao = sqlSession.getMapper(TeacherDao.class);
			TeacherDao.add(teacher);
			
			//提交事务，否则不会实际添加到数据库中
			sqlSession.commit();
			
			System.out.println("数据库生成的ID： " + teacher.getId());
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 更新示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void update(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			TeacherDao TeacherDao = sqlSession.getMapper(TeacherDao.class);
			// 获取id为1的教师
			Teacher teacher = TeacherDao.getById(3);
			System.out.println("修改前的研究方向：" + teacher.getResearchArea());
			
			// 修改其研究方向
			teacher.setResearchArea("统计学工程");
			TeacherDao.update(teacher);
			
			// 提交事务，否则不会实际修改到数据库中
			sqlSession.commit();
			
			teacher = TeacherDao.getById(1);
			System.out.println("修改后的研究方向：" + teacher.getResearchArea());
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 删除示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void delete(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			TeacherDao TeacherDao = sqlSession.getMapper(TeacherDao.class);
			
			// 删除id为1的教师
			TeacherDao.delete(1);
			
			// 提交事务，否则不会实际删除数据库中的记录
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
}
