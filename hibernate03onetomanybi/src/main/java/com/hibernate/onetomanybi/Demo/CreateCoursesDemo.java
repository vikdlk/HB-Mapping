package com.hibernate.onetomanybi.Demo;



import com.hibernate.onetomanybi.Entity.Course;
import com.hibernate.onetomanybi.Entity.Instructor;
import com.hibernate.onetomanybi.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            //get the instruction from db
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            //create some courses
            Course course1 =new Course("Air Guitar - Yhe Ultimate Guide");
            Course course2 =new Course("The Pinball MasterClass");

            //add courses to instructor
            instructor.add(course1);
            instructor.add(course2);

            //save the courses
            session.save(course1);
            session.save(course2 );


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
