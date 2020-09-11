package com.hibernate.onetomanyuni.Demo;


import com.hibernate.onetomanyuni.Entity.Course;
import com.hibernate.onetomanyuni.Entity.Instructor;
import com.hibernate.onetomanyuni.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            // get a course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            //delete a course
            System.out.println("Deleting course: "+ course);
            session.delete(course);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
