package com.hibernate.manytomany.Demo;

import com.hibernate.manytomany.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForViktorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            //get student mary from db
            int studentId = 1;
            Student student = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: "+ student);
            System.out.println("Course: "+ student.getCourses());

            //create more courses
            Course course1 = new Course("Rubik Cube - How to speed Cube");
            Course course2 = new Course("Atari 2600  - Game Developer");

            //add student to courses
            course1.addStudent(student);
            course2.addStudent(student);

            //save courses
            System.out.println("\nSaving the course....");
            session.save(course1);
            session.save(course2);



            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
