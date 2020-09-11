package com.hibernate.onetomanyuni.Demo;

import com.hibernate.onetomanyuni.Entity.Course;
import com.hibernate.onetomanyuni.Entity.Instructor;
import com.hibernate.onetomanyuni.Entity.InstructorDetail;
import com.hibernate.onetomanyuni.Entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            //print the course
            System.out.println(course);

            //print the course review
            System.out.println(course.getReviews());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
