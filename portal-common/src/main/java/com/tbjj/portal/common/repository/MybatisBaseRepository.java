package com.tbjj.portal.common.repository;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * mybatis基础接口，所有repository接口均继承该接口
 * @link http://stackoverflow.com/questions/19720102/mybatis-generator-whats-the-best-way-to-separate-out-auto-generated-and-han
 * @author
 */
public interface MybatisBaseRepository<T, PK extends Serializable, E> {
	long countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(PK id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(E example);

	T selectByPrimaryKey(PK id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	int updateByExample(@Param("record") T record, @Param("example") E example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
