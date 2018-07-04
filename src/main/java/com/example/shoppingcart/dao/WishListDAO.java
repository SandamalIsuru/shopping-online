package com.example.shoppingcart.dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingcart.entity.WishList;

@Transactional
@Repository
public class WishListDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(WishList wishList) throws ClassNotFoundException, SQLException {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("---------- Saving");
		session.persist(wishList);
		session.flush();
		
	}
}
