package org.bluesky.mybatis.dao.function;

import java.util.Map;

/**
 * 数据库函数和存储过程操作类接口
 * @author: liuyuefeng
 * @date: 2015-6-11 下午3:33:11
 * @version: V1.0
 *
 */
public interface FunctionDao {
	
	/**
	 * 调用数据库函数查询符合条件的记录数
	 * @param paramMap
	 * @throws Exception
	 * @return: void
	 */
	public void getStudentCount(Map<String, Object> paramMap) throws Exception;

}
