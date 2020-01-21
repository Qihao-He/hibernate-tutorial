package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
 
public class CreateDemo {
 
    public static void main(String[] args) {
        

        SessionFactory f = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
 

        Session s = f.getCurrentSession();
 
        try {
//        	Instructor i = new Instructor("Chad", "Darby", "darby@luv2code.com");
//        	InstructorDetail idet = new InstructorDetail("http://youtube", "luv 2 code"); 
//        	i.setInstructorDetail(idet);
        	
        	Instructor i = new Instructor("Madu", "Patel", "madhu@luv2code.com");
        	InstructorDetail idet = new InstructorDetail("http://youtube", "guita"); 
        	i.setInstructorDetail(idet);
        	
            s.beginTransaction();
            System.out.println("Saving instructor: " + i);
            s.save(i);
            s.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            f.close();
        }
    }
    
}