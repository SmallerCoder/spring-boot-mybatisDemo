package com.example.mybatisDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybatisDemo.dao.StudentDao;
import com.example.mybatisDemo.pojo.Student;

/**
 * 
 * The class StudentService.
 *
 * Description:service层
 *
 * @author: huangjiawei
 * @since: 2018年6月8日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Service
public class StudentService {

	/**
	 * 针对接口编程，直接自动注入
	 */
	@Autowired
	private StudentDao dao;

	public Student getStudent(int id) {
		return dao.get(id);
	}
}
