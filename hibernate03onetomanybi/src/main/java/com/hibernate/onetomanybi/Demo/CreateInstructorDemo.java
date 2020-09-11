package com.hibernate.onetomanybi.Demo;



import com.hibernate.onetomanybi.Entity.Course;
import com.hibernate.onetomanybi.Entity.Instructor;
import com.hibernate.onetomanybi.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

//            Instructor instructor = new Instructor("Susan", "Muravina", "vln@lazd.lt");
//            InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Video Game!!!");

            Instructor instructor = new Instructor("Igor", "Stex", "igor@lazd.lt");
            InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Vilnius!!!");

            //associate the object together!!!!!!!!!!!!!!!!!
            instructor.setInstructorDetail(instructorDetail);


            //start transaction
            session.beginTransaction();

            //save instructor
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
