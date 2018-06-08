package com.example.mybatisDemo.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.mybatisDemo.pojo.Student;

/**
 * 
 * The class StudentMapper.
 *
 * Description:学生mapper，dao层
 *
 * @author: huangjiawei
 * @since: 2018年6月7日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public interface StudentDao {

	@Select("select * from Student where id=#{id}")
	public Student get(@Param("id") int id);

}
