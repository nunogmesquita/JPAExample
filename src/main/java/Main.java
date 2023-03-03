import mindswap.jpa.school.TeacherEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parking_spots_assignment");
        EntityManager em = emf.createEntityManager();

        // Insert Teachers
        System.out.println("Please insert the name of the teachers");
        em.getTransaction().begin();

        TeacherEntity t1 = new TeacherEntity("Rui", "Eletropichelaria");
        TeacherEntity t2 = new TeacherEntity("Susana", "De Gruas 101");
        TeacherEntity t3 = new TeacherEntity("Rui", "Cenas e tal");

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);

        em.getTransaction().commit();

        // Get all the Teachers
        System.out.println("Print all the Employees");
        em.getTransaction().begin();

        em.createQuery("SELECT e FROM TeacherEntity e", TeacherEntity.class)
                .getResultList()
                .forEach(TeacherEntity::printTeachers);

        em.getTransaction().commit();

    }
}
