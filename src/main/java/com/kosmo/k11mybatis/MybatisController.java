package com.kosmo.k11mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MybatisController
{
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/mybatis/list.do")
	public String list()
	{
		return "07Mybatis/list";
	}
}
