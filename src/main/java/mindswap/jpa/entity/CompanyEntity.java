package mindswap.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @OneToMany(targetEntity = AddressEntity.class)
    private List<AddressEntity> address = new ArrayList<>();

    @ManyToMany(targetEntity = EmployeeEntity.class ,fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<EmployeeEntity> employees = new ArrayList<>();

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
        this.employees.forEach(e -> e.getCompanies().add(this));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressEntity> getAddress() {
        return address;
    }

    public void setAddress(List<AddressEntity> address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

public void print(){
        String employeeNames = "";
        for (EmployeeEntity employee : employees) {
            employeeNames += employee.getName() + ", ";
        }
        String addressNames = "";
        for (AddressEntity address : address) {
            addressNames += address.getCity() + ", ";
        }
        System.out.println("CompanyEntity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address=" + addressNames +
            ", employees=" + employeeNames +
            '}');
    }


}
