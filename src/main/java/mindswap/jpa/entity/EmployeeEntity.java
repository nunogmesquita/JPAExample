package mindswap.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // To create a Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    private int age;

    @OneToOne(targetEntity = AddressEntity.class)  // for one to many
    private AddressEntity address;

    @ManyToMany(mappedBy ="employees",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) // for many to many
    private List<CompanyEntity> companies = new ArrayList<>();

    public EmployeeEntity() {

    }


    public List<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyEntity> companies) {
        this.companies = companies;
        this.companies.forEach(c -> c.getEmployees().add(this));
    }

    public EmployeeEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public EmployeeEntity(String name, AddressEntity address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public EmployeeEntity(int id, String name, AddressEntity address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }


public void print(){

    System.out.println("EmployeeEntity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", address=" + address +
            '}');
}
}
