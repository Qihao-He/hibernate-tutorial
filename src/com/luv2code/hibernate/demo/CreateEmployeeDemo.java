package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a employee object
			System.out.println("Creating new employee object...");
			List<Employee> el  = new ArrayList<Employee>(Arrays.asList(
					new Employee("Anup", "Joshi", "Acme Inc"),
					new Employee("Daffy", "Duck", "Google"),
					new Employee("John", "Doe", "MS"),
					new Employee("Mary", "Sue", "FB"),
					new Employee("Tim", "King", "Ama"))) ;
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the employee...");
			for (Employee e : el) {
				session.save(e);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
	}

}





