package com.hibernate.onetooneUni.Demo;


import com.hibernate.onetooneUni.Entity.Instructor;
import com.hibernate.onetooneUni.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

         //get instructor by primary key / id
            int theId = 2;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println("Found Instructor: "+ instructor);


            if (instructor != null){
                System.out.println("Deleting: "+ instructor);

                session.delete(instructor);
            }




            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
