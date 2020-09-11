package com.hibernate.onetomanybi.Demo;


import com.hibernate.onetomanybi.Entity.Instructor;
import com.hibernate.onetomanybi.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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
            int theId = 5;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            //print yhe instructor detail
            System.out.println("InstructorDetail: " + instructorDetail);

            //print the associated instructor
            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            //now delete the instructor detail
            System.out.println("Deleting instructorDetail");

            //remove the associated object
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);


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
