package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class ReadEmployeeDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating a new Employee obj...");
			Employee e = new Employee("Elon", "Mask", "Tesla");
			
			session.beginTransaction();
			
			System.out.println("Saving a Employee...");
			System.out.println(e);
			session.save(e);
			
			session.getTransaction().commit();
			System.out.println("Saved Employee, generated id: " + e.getId());
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nGetting Employee with id: " + e.getId());
			Employee getE = session.get(Employee.class, e.getId());
			
			System.out.println("Get complete: " + getE);
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
