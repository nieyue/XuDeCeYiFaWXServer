package com.nieyue.service.impl.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.nieyue.bean.Admin;
import com.nieyue.service.AdminService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class AdminServiceImplTest {
	@Resource
	 AdminService  AdminService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAdmin() {
		Admin a = new Admin();
		a.setPassword("xxxx");
		System.out.println("插入前主键为："+a.getAdminId());
		AdminService.addAdmin(a);
		System.out.println("插入后主键为："+a.getAdminId());
	}
	

	@Test
	public void testDelAdmin() {
		AdminService.delAdmin(1000);
	}

	@Test
	public void testUpdateAdmin() {
		Admin p = AdminService.loadAdmin(1000);
		AdminService.updateAdmin(p);
	}

	@Test
	public void testLoadAdmin() {
		Admin n = AdminService.loadAdmin(1011);
		System.out.println(n);
	}

	@Test
	public void testCountAll() {
		int n = AdminService.countAll();
		System.out.println(n);
	}


	@Test
	public void testBrowsePagingAdmin() {
	}

}
