package mindswap.jpa.school;

import javax.persistence.*;

@MappedSuperclass
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String license_plate;

    private String brand;

    private int year;

    @ManyToOne
    private Person owner;

    @OneToOne
    private ParkingSpotEntity parkingSpotEntity;


    public Vehicle(Long id, String license_plate, String brand, int year) {
        this.id = id;
        this.license_plate = license_plate;
        this.brand = brand;
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

    public void setOwner(Person person) {
        this.owner = person;
    }

    public Person getOwner() {
        return owner;
    }

    public ParkingSpotEntity getParkingSpot() {
        return parkingSpotEntity;
    }

    public void setParkingSpot(ParkingSpotEntity parkingSpotEntity) {
        this.parkingSpotEntity = parkingSpotEntity;
    }
}
