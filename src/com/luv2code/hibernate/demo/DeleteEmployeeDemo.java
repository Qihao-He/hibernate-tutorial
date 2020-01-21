package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int eId = 2;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nGetting Employee with id: " + eId);
			Employee e = session.get(Employee.class, eId);
			
//			System.out.println("Deleting Employee: " + e);
//			session.delete(e);
			
			System.out.println("Deleting Employee: " + eId);
			session.createQuery("delete from Employee where id=2").executeUpdate();
			
			System.out.println("Get complete: " + e);
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
