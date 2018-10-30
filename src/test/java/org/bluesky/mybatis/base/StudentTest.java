package org.bluesky.mybatis.base;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bluesky.mybatis.dao.StudentDao;
import org.bluesky.mybatis.entity.Student;

/**
 * 学生基本测试类
 * @author: liuyuefeng
 * @date: 2015-4-9 下午6:37:33
 * @version: V1.0
 * 
 */
public class StudentTest {

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
		
		// 动态SQL测试
		System.out.println("按学生对象查询：");
		queryByStudentDynamicSQL(sqlSessionFactory);
		System.out.println("按学生对象List查询：");
		queryByListDynamicSQL(sqlSessionFactory);
		System.out.println("按学生对象Array查询：");
		queryByArrayDynamicSQL(sqlSessionFactory);
		System.out.println("按学生对象更新：");
		updateDynamicSQL(sqlSessionFactory);

	}

	/**
	 * 查询示例
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void query(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			Student student = studentDao.getById(2);
			if (student != null) {
				System.out.println("姓名： " + student.getName() + "\n专业： " + student.getMajor());
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
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void query2(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			List<Student> students = studentDao.getByName("陈一斌");
			if (students != null) {
				for (Student student : students)
					System.out.println("姓名： " + student.getName() + "\n专业： " + student.getMajor());
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
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void add(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		
		Student student = new Student();
		student.setName("陈一斌");
		student.setGender("男");
		student.setMajor("计算机科学与技术");
		student.setGrade("2015");
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			studentDao.add(student);
			
			// 提交事务，否则不会实际添加到数据库中
			sqlSession.commit();
			
			System.out.println("数据库生成的ID： " + student.getId());
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 更新示例
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void update(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			
			// 获取id为1的学生
			Student student = studentDao.getById(3);
			System.out.println("修改前的专业：" + student.getMajor());
			
			// 修改其专业
			student.setMajor("电子信息工程");
			studentDao.update(student);
			
			// 提交事务，否则不会实际修改到数据库中
			sqlSession.commit();
			
			student = studentDao.getById(1);
			System.out.println("修改后的专业：" + student.getMajor());
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 删除示例
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void delete(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			
			// 删除id为1的学生
			studentDao.delete(1);
			
			// 提交事务，否则不会实际删除数据库中的记录
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 查询示例（动态SQL）
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void queryByStudentDynamicSQL(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		
		Student studentParm = new Student();
		studentParm.setName("陈一斌");
		studentParm.setGrade("2014");
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			List<Student> students = studentDao.getByStudentDynamicSQL(studentParm);
			if (students != null) {
				for (Student student : students) {
					System.out.println("姓名： " + student.getName() + "\n专业： " + student.getMajor() + "\n入学年份： " + student.getGrade());
					System.out.println("----------------------");
				}
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
	 * 查询示例（动态SQL）
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void queryByListDynamicSQL(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		
		List<Student> studentList = new ArrayList<Student>();
		Student studentParm1 = new Student();
		studentParm1.setId(2);
		studentList.add(studentParm1);
		Student studentParm2 = new Student();
		studentParm2.setId(3);
		studentList.add(studentParm2);
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			List<Student> students = studentDao.getByListDynamicSQL(studentList);
			if (students != null) {
				for (Student student : students) {
					System.out.println("姓名： " + student.getName() + "\n专业： " + student.getMajor() + "\n入学年份： " + student.getGrade());
					System.out.println("----------------------");
				}
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
	 * 查询示例（动态SQL）
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void queryByArrayDynamicSQL(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		
		Student[] studentArray = new Student[2];
		Student studentParm0 = new Student();
		studentParm0.setId(2);
		studentArray[0] = studentParm0;
		Student studentParm1 = new Student();
		studentParm1.setId(4);
		studentArray[1] = studentParm1;
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			List<Student> students = studentDao.getByArrayDynamicSQL(studentArray);
			if (students != null) {
				for (Student student : students) {
					System.out.println("姓名： " + student.getName() + "\n专业： " + student.getMajor() + "\n入学年份： " + student.getGrade());
					System.out.println("----------------------");
				}
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
	 * 更新示例（动态SQL）
	 * 
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void updateDynamicSQL(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		try {
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			
			// 学生对象
			Student student = new Student();
			student.setId(4);
			
			// 修改其专业
			student.setMajor("人工识别");
			studentDao.updateDynamicSQL(student);
			
			// 提交事务，否则不会实际修改到数据库中
			sqlSession.commit();
			
			student = studentDao.getById(3);
			System.out.println("修改后的专业：" + student.getMajor());
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

}
