package mindswap.jpa.school;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TeacherEntity extends Person {

    @OneToMany (targetEntity = VehicleEntity.class)
    private Set<VehicleEntity> vehicleEntities = new HashSet<>();

    @OneToOne
    private ParkingSpotEntity parkingSpotEntity;

    private String discipline;

    public TeacherEntity(String name, String discipline) {
        super(name);
        this.discipline = discipline;
    }

    public Set<VehicleEntity> getVehicles() {
        return vehicleEntities;
    }

    public void addVehicle(VehicleEntity vehicleEntity) {
        vehicleEntities.add(vehicleEntity);
        vehicleEntity.setOwner(this);
    }

    public ParkingSpotEntity getParkingSpot() {
        return parkingSpotEntity;
    }

    public void addParkingSpot(ParkingSpotEntity parkingSpotEntity) {
        this.parkingSpotEntity = parkingSpotEntity;
        parkingSpotEntity.setOwner(this);
    }

    public void printTeachers() {
        System.out.println("TeacherIdentity{" +
                "id=" + getPersonId() +
                ", name='" + getName() + '\'' +
                ", discipline=" + discipline +
                '}');
    }

}
