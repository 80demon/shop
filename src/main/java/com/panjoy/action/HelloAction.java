package com.panjoy.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	public String update() {
		System.out.println("update-----------------------------");
		return "update";

	}

	public String add() {
		System.out.println("add-------------------------------");
		return "add";

	}

	public String delete() {
		System.out.println("delete----------------------------");
		return "delete";

	}

	public String query() {
		System.out.println("query----------------------------");
		return "query";
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "index";
	}
}
