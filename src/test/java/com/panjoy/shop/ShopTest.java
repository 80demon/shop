package com.panjoy.shop;

import javax.annotation.Resource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panjoy.entity.Category;
import com.panjoy.service.CategoryService;
import com.panjoy.service.impl.CategoryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-*.xml" })
public class ShopTest {
	@Resource
	public CategoryService  categoryService;
	@Test
	public void TestHibernate(){
		categoryService=new CategoryServiceImpl();
		categoryService.save(new Category("dk≤‚ ‘0",true));
		categoryService.save(new Category("dk≤‚ ‘1",true));
		categoryService.save(new Category("dk≤‚ ‘2",true));
		categoryService.save(new Category("dk≤‚ ‘3",true));
	}
}
