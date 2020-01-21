package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
 
public class DeleteDemo {
 
    public static void main(String[] args) {

        SessionFactory f = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
 

        Session s = f.getCurrentSession();
 
        try {
            s.beginTransaction();
            
            int id = 1;
            Instructor i = s.get(Instructor.class, id);
            System.out.println("Found instructor: " + i);
            
            if (i != null) {
            	System.out.println("Deleting instructor: " + i);
            	s.delete(i);
            }
            
            s.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            f.close();
        }
    }
    
}