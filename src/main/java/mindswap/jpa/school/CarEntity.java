package mindswap.jpa.school;

import javax.persistence.*;

@Entity
public class CarEntity extends Vehicle {

    public CarEntity(String license_plate, String brand, int year, Long id) {
        super(id, license_plate, brand, year);
    }
}
