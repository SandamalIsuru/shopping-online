package com.example.shoppingcart.dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.shoppingcart.entity.Account;
import com.example.shoppingcart.form.CustomerValidateForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Account findAccount(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(Account.class, userName);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(CustomerValidateForm customerValidateForm) throws ClassNotFoundException, SQLException {
		Session session = this.sessionFactory.getCurrentSession();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(customerValidateForm.getPassword());
		Account acount = new Account();
		acount.setUserName(customerValidateForm.getUsername());
		acount.setEncrytedPassword(hashedPassword);
		acount.setActive(true);
		acount.setUserRole("CUSTOMER");

		session.persist(acount);
		session.flush();
	}

}