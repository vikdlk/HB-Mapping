package com.hibernate.onetoonebi.Demo;


import com.hibernate.onetoonebi.Entity.Instructor;
import com.hibernate.onetoonebi.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
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

            //get the instructor detail object
            int theId = 223;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            //print yhe instructor detail
            System.out.println("InstructorDetail: " + instructorDetail);

            //print the associated instructor
            System.out.println("the associated instructor: " + instructorDetail.getInstructor());


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();

        }
    }
}
