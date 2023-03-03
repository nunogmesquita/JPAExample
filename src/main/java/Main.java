import mindswap.jpa.school.ParkingSpotEntity;
import mindswap.jpa.school.TeacherEntity;
import mindswap.jpa.school.VehicleEntity;
import mindswap.jpa.school.VehiclesType;

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

        // Creates parking spots
        em.getTransaction().begin();
        ParkingSpotEntity p1 = new ParkingSpotEntity(t1);
        ParkingSpotEntity p2 = new ParkingSpotEntity(t2);
        ParkingSpotEntity p3 = new ParkingSpotEntity(t3);
        ParkingSpotEntity p4 = new ParkingSpotEntity();
        ParkingSpotEntity p5 = new ParkingSpotEntity();


        t1.addParkingSpot(p1);
        t2.addParkingSpot(p2);
        t3.addParkingSpot(p3);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);

        em.getTransaction().commit();

        // Create vehicles and associate with teachers

        em.getTransaction().begin();
        VehicleEntity c1 = new VehicleEntity("00AB11", "bmw", 2010, VehiclesType.CAR);
        VehicleEntity c2 = new VehicleEntity("11CD12", "mercedes", 2020, VehiclesType.CAR);
        t1.addVehicle(c1);
        t1.addVehicle(c2);

        VehicleEntity c3 = new VehicleEntity("55ZD15", "nissan", 2005, VehiclesType.CAR);
        VehicleEntity m1 = new VehicleEntity("12KD34", "bmw", 2015, VehiclesType.MOTORCYCLE);
        t2.addVehicle(c3);
        t2.addVehicle(m1);

        VehicleEntity m2 = new VehicleEntity("54OK95", "suzuki", 2003, VehiclesType.MOTORCYCLE);
        t3.addVehicle(m2);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(m1);
        em.persist(m2);

        em.getTransaction().commit();

        // Listing all the vehicles
        System.out.println("List of all the vehicles");
        em.getTransaction().begin();

        em.createQuery("SELECT v FROM VehicleEntity v", VehicleEntity.class)
                .getResultList()
                .forEach(VehicleEntity::printVehicles);

        em.getTransaction().commit();

        //Finding the vehicle assigned to a particular spot
        System.out.println("Vehicle associated to the spot");
        em.getTransaction().begin();

        em.createQuery("SELECT v FROM ParkingSpotEntity v WHERE v.owner = :id", ParkingSpotEntity.class)
                .setParameter("id", 4)
                .getSingleResult();
                //.forEach(ParkingSpotEntity::getOwner);


        em.getTransaction().commit();

        /*
        //em.getTransaction().begin();
        em.createQuery("SELECT e FROM EmployeeEntity e", EmployeeEntity.class)
                .getResultList()
                .forEach(EmployeeEntity::print);

        em.getTransaction().commit();

         */


    }
}
