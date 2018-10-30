package org.bluesky.mybatis.function;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bluesky.mybatis.dao.function.FunctionDao;

/**
 * 数据库函数和存储过程测试类
 * @author: liuyuefeng
 * @date: 2015-6-11 下午3:38:11
 * @version: V1.0
 *
 */
public class FunctionTest {

	public static void main(String[] args) {
		String resource = "mybatis-configuration_base.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 创建SqlSessionFactory实例。没有指定要用到的environment，则使用默认的environment
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		System.out.println("数据库函数调用：");
		callFunction(sqlSessionFactory);

	}

	/**
	 * 数据库函数调用示例
	 * @param factory
	 * @return: void
	 * @throws:
	 */
	private static void callFunction(SqlSessionFactory factory) {
		SqlSession sqlSession = factory.openSession();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "王");
		paramMap.put("gender", 0);
		paramMap.put("count", null);
		
		try {
			FunctionDao functionDao = sqlSession.getMapper(FunctionDao.class);
			functionDao.getStudentCount(paramMap);
			
			if (paramMap.get("count") != null) {
				int count = Integer.parseInt(paramMap.get("count").toString());
				System.out.println("数据库函数返回结果数： " + count);
			} else {
				System.out.println("数据库函数执行发生错误。");
			}
		} catch (Exception e) {
			System.out.println("程序发生错误：" + e.getMessage());
		} finally {
			sqlSession.close();
		}
	}

}
