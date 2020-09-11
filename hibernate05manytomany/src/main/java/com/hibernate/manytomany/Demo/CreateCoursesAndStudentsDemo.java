package com.hibernate.manytomany.Demo;

import com.hibernate.manytomany.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {
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

            //create a course
            Course course = new Course("Kaunas - How to Score One Million Point? ");

            // save the course
            System.out.println("\nSaving the course...");
            session.save(course);
            System.out.println("Saved the course: " + course);

            //create the students
            Student student1 = new Student("Dima", "Dulk", "viktor@mail.ru");
            Student student2 = new Student("Denis", "Stex", "stex@gmail.com");

            //add students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            // save the students
            System.out.println("Saving the students.....");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
