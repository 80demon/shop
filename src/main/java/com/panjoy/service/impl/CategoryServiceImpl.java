package com.panjoy.service.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.panjoy.entity.Category;
import com.panjoy.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		System.out.println(sessionFactory);
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		getSession().save(category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		getSession().update(category);
	}

}
