package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class QueryEmployeeDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session s = factory.getCurrentSession();
		
		try {
			s.beginTransaction();
			
			List<Employee> el = s.createQuery("from Employee").list();
			displayEmployees(el);
			
			el = s.createQuery("from Employee s where s.lastName='Doe'").list();
			System.out.println("\n\nEmployee who have lastname of Doe");
			displayEmployees(el);
			
			el = s.createQuery("from Employee s where s.lastName='Doe' OR s.firstName='Daffy'").list();
			System.out.println("\n\nEmployee who have lastname of Doe OR s.firstName='Daffy");
			displayEmployees(el);
			
			el = s.createQuery("from Employee s where s.company LIKE '%Google'").list();
			System.out.println("\n\nEmployee who have s.company like '%Google'");
			displayEmployees(el);
			
			el = s.createQuery("from Employee s where s.company LIKE '%tesla'").list();
			System.out.println("\n\nEmployee who have s.company like '%tesla'");
			displayEmployees(el);
			
			s.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayEmployees(List<Employee> el) {
		for (Employee e : el) {
			System.out.println(e);
		}
	}

}
