package inheritance.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
@DiscriminatorValue("ourTruck")

public class DemoTruck extends DemoVehicle {
    private static final String TRUCK_TYPE = "TRUCK";

    public DemoTruck() {
        super(TRUCK_TYPE);
    }
}
