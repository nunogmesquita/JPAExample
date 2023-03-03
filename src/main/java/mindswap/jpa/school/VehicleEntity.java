package mindswap.jpa.school;

import javax.persistence.*;

@Entity
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String license_plate;

    private String brand;

    private VehiclesType type;

    private int year;

    @ManyToOne
    private TeacherEntity owner;

    public VehicleEntity(String license_plate, String brand, int year, VehiclesType type) {
        this.license_plate = license_plate;
        this.brand = brand;
        this.type = type;
        this.year = year;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOwner(TeacherEntity person) {
        this.owner = person;
    }

    public TeacherEntity getOwner() {
        return owner;
    }

    public Long getId() {
        return id;
    }

    public VehiclesType getType() {
        return type;
    }

    public void printVehicles() {
        System.out.println("VehiclesEntity{" +
                "id=" + getId() +
                ", owner='" + getOwner() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", type='" + getType() + '\'' +
                ", year=" + getYear() +
                '}');
    }
}
