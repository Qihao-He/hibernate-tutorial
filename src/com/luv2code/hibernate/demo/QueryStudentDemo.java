package com.luv2code.hibernate.demo;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			List<Student> theStudents = session.createQuery("from Student").list();
			
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
			
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").list();
			
			System.out.println("\n\nStudents who have last name of Doe OR s.firstName='Daffy");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list();
			
			System.out.println("\n\nStudents who have s.email like '%luv2code.com'");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").list();
			
			System.out.println("\n\nStudents who have s.email like '%gmail.com'");
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
