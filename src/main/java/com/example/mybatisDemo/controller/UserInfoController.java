package com.example.mybatisDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatisDemo.service.StudentService;

/**
 * 
 * The class UserInfoController.
 *
 * Description:controller接口层
 *
 * @author: huangjiawei
 * @since: 2018年6月8日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@RestController
public class UserInfoController {

	@Autowired
	private StudentService service;

	@RequestMapping(value = "/get.json")
	public String getUserInfo(@RequestParam int id) {
		return service.getStudent(id).toString();
	}
}
