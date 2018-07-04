package com.example.shoppingcart.dao;

import java.sql.SQLException;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingcart.entity.Account;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.form.CustomerValidateForm;

@Transactional
@Repository
public class CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Customer findCustomer(String id) {
		try {
			String sql = "Select e from " + Customer.class.getName() + " e Where e.id =:id ";

			Session session = this.sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery(sql, Customer.class);
			query.setParameter("id", id);
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Customer findCustomerByUsername(String username) {
		try {
			String sql = "Select e from " + Customer.class.getName() + " e Where e.account.userName =:username ";

			Session session = this.sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery(sql, Customer.class);
			query.setParameter("username", username);
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(CustomerValidateForm customerValidateForm) throws ClassNotFoundException, SQLException {
		Session session = this.sessionFactory.getCurrentSession();

		boolean isNew = true;// In case to check customer on update existing customer

		Account account = new Account();
		account.setUserName(customerValidateForm.getUsername());
		Customer customer = new Customer(generateCustId(), customerValidateForm.getName(),
				customerValidateForm.getAddress(), customerValidateForm.getPhone(), customerValidateForm.getEmail(),
				account);

		if (isNew) {
			session.persist(customer);
		}
		session.flush();
	}

	public String getLastCustId() throws SQLException, ClassNotFoundException {
		try {
			String sql = "Select e from " + Customer.class.getName() + " e order by 1 desc ";
			Session session = this.sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery(sql, Customer.class);
			query.setMaxResults(1);
			Customer lastCustomer = (Customer) query.getSingleResult();
			return lastCustomer.getId();
		} catch (NoResultException e) {
			return null;
		}

	}

	private String generateCustId() throws SQLException, ClassNotFoundException {
		String custId = getLastCustId();

		if (custId == null) {
			return "C000000001";
		} else {
			int num = Integer.parseInt(custId.split("C")[1]);
			num++;
			if (num < 10) {
				return "C00000000" + num;
			} else if (num < 100) {
				return "C0000000" + num;
			} else if (num < 1000) {
				return "C000000" + num;
			} else if (num < 10000) {
				return "C00000" + num;
			} else if (num < 100000) {
				return "C0000" + num;
			} else if (num < 1000000) {
				return "C000" + num;
			} else if (num < 10000000) {
				return "C00" + num;
			} else if (num < 100000000) {
				return "C0" + num;
			} else {
				return "C" + num;
			}
		}
	}

}
