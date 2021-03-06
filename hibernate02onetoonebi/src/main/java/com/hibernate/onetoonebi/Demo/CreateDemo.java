package com.hibernate.onetoonebi.Demo;



import com.hibernate.onetoonebi.Entity.Instructor;
import com.hibernate.onetoonebi.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Instructor instructor = new Instructor("Kaif", "Lama", "vln@lazd.lt");
            InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Luv3code!!!");

//            Instructor instructor = new Instructor("Igor", "Stex", "igor@lazd.lt");
//            InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Vilnius!!!");

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
