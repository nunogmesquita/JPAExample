package mindswap.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // To create a Entity
public class EmployeeEntity extends Person {


    private int age;

    @OneToOne(targetEntity = AddressEntity.class)  // for one to many
    private AddressEntity address;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) // for many to many
    private List<CompanyEntity> companies = new ArrayList<>();

    public EmployeeEntity() {
        super();
    }

    public List<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyEntity> companies) {
        this.companies = companies;
//        for (     CompanyEntity company : companies) {
//            this.companies.add(company);
//        }
        this.companies.forEach(c -> c.getEmployees().add(this));
    }

    public void setCompany(CompanyEntity company) {
        this.companies.add(company);
        company.getEmployees().add(this);
    }

    public EmployeeEntity(String name, int age) {
        super(name);
        this.age = age;
    }

    public EmployeeEntity(String name, AddressEntity address, int age) {
        super(name);
        this.address = address;
        this.age = age;
    }

    public EmployeeEntity(int id, String name, AddressEntity address, int age) {
        super(id, name);
        this.address = address;
        this.age = age;
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


    public void print() {

        System.out.println("EmployeeEntity{" +
                "id=" + getPersonId() +
                ", name='" + getName() + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}');
    }
}
