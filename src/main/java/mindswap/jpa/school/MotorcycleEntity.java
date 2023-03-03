package mindswap.jpa.school;

import javax.persistence.*;

@Entity
public class MotorcycleEntity extends Vehicle {

    public MotorcycleEntity(Long id, String license_plate, String brand, int year) {
        super(id, license_plate, brand, year);
    }
}
