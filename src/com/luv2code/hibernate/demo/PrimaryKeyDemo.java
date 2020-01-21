package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) throws ParseException {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating 3 Student obj...");
			String theDateOfBirthStr = "31/12/1998"; 
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com", theDateOfBirth);
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com", theDateOfBirth);
			Student tempStudent3 = new Student("Bonita", "AppleBum", "bonita@luv2code.com", theDateOfBirth);
			
			session.beginTransaction();
			
			System.out.println("Saving a student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
