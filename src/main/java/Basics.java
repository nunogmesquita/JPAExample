import mindswap.jpa.entity.AddressEntity;
import mindswap.jpa.entity.CompanyEntity;
import mindswap.jpa.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Basics {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee_details");
        EntityManager em = emf.createEntityManager();

        //Insert Employee
        System.out.println("Insert new Employees");
        em.getTransaction().begin();
        EmployeeEntity e1 = new EmployeeEntity( "Christophe", 00);
        EmployeeEntity e2 = new EmployeeEntity( "Diogo", 42);

        em.persist(e1);
        em.persist(e2);

        em.getTransaction().commit();

        // Get all the Employees
        System.out.println("Print all the Employees");
        em.getTransaction().begin();
        em.createQuery("SELECT e FROM EmployeeEntity e", EmployeeEntity.class)
                .getResultList()
                .forEach(employee -> employee.print());
        em.getTransaction().commit();


        //Update Employee with ID 1
        System.out.println("Update the first Employee");
        em.getTransaction().begin();
        EmployeeEntity employeeUpdate = em.find(EmployeeEntity.class, 1);
        // generate random age 0 - 100
        employeeUpdate.setAge((int)(Math.random() * 100));
        em.getTransaction().commit();


        // Get Employee with ID 1
        System.out.println("Get the Employee with ID 1");
        EmployeeEntity employeeEntity = em.find(EmployeeEntity.class, 1);
        employeeEntity.print();


        // Delete the last Employee
        System.out.println("Delete the last Employee");
        em.getTransaction().begin();
        EmployeeEntity lastEmployee = em.createQuery("SELECT e FROM EmployeeEntity e ORDER BY e.id DESC", EmployeeEntity.class)
                .setMaxResults(1)
                .getSingleResult();
        em.remove(lastEmployee);
        em.getTransaction().commit();


        //Create Address
        em.getTransaction().begin();
        AddressEntity a1 = new AddressEntity();
        a1.setAddressLine1("Avenida...");
        a1.setCity("Portimão");
        a1.setState("Faro");

        AddressEntity a2 = new AddressEntity();
        a2.setAddressLine1("Avenida...");
        a2.setCity("Esmoriz");
        a2.setState("Ovar");

        AddressEntity a3 = new AddressEntity();
        a3.setAddressLine1("Avenida...");
        a3.setCity("Porto");
        a3.setState("Porto");


        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        em.getTransaction().commit();


        em.getTransaction().begin();
        // obtain the employee with name Christophe
        EmployeeEntity employee = em.createQuery("select e from EmployeeEntity e where e.name = :name", EmployeeEntity.class)
                .setParameter("name", "Christophe")
                .setMaxResults(1)
                .getSingleResult();


        employee.setAddress(a1);

        em.persist(employee);
        em.getTransaction().commit();

        
        // Create a Company
        em.getTransaction().begin();
        CompanyEntity c1 = new CompanyEntity();
        c1.setName("Mindera");
        c1.setAddress(List.of(a3, a2));
        c1.setEmployees(List.of(employee));

        em.persist(c1);

        CompanyEntity c2 = new CompanyEntity();
        c2.setName("Universidade");
        c2.setAddress(List.of(a1));
        c2.setEmployees(List.of(employee));

        em.persist(c2);
        em.getTransaction().commit();

        // Print all the employees of a company
        em.getTransaction().begin();
        CompanyEntity company = em.createQuery("select c from CompanyEntity c where c.name = :name", CompanyEntity.class)
                .setParameter("name", "Mindera")
                .setMaxResults(1)
                .getSingleResult();


        System.out.println(company.getName() + " has the following employees:");
        company.getEmployees().forEach(e -> e.print());
        System.out.println("has the following addresses:");
        company.getAddress().forEach(a -> a.print());

        company = em.createQuery("select c from CompanyEntity c where c.name = :name", CompanyEntity.class)
                .setParameter("name", "Universidade")
                .setMaxResults(1)
                .getSingleResult();

        company.print();
        em.getTransaction().commit();




        // Print all the companies of the employee Christophe
        System.out.println("Print all the companies of the employee Christophe");
        em.getTransaction().begin();
        employee = em.createQuery("select e from EmployeeEntity e where e.name = :name", EmployeeEntity.class)
                .setParameter("name", "Christophe")
                .setMaxResults(1)
                .getSingleResult();

        employee.getCompanies().forEach(c -> c.print());
        em.persist(employee);
        em.getTransaction().commit();


        emf.close();
        em.close();
    }
}