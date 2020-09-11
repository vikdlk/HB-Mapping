package com.hibernate.onetomanyuni.Demo;

import com.hibernate.onetomanyuni.Entity.Course;
import com.hibernate.onetomanyuni.Entity.Instructor;
import com.hibernate.onetomanyuni.Entity.InstructorDetail;
import com.hibernate.onetomanyuni.Entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {
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

            //create a course
            Course course = new Course("Lazdynai - How to Score One Million Point? ");

            //add some reviews
            course.add(new Review("Great course ... loved it!"));
            course.add(new Review("Cool course, job well done"));
            course.add(new Review("Welcome back to Vilnius :) "));

            //save the course... adn leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
