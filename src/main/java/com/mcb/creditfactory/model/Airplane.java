package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "AIRPLANE")
public class Airplane  {
    @Id
    @NonNull
    private Long id;
    @NonNull
    private String brand;
    @NonNull
    private String model;
    @NonNull
    private String manufacturer;

    @Column(name = "year_of_issue")
    @NonNull
    private Short year;
    @Column(name = "fuel_capacity")
    @NonNull
    private Integer fuelCapacity;
    @NonNull
    private Integer seats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airplane")
    private List<AirplaneValuation> valuations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airplane)) return false;
        Airplane airplane = (Airplane) o;
        return getId().equals(airplane.getId()) &&
                getBrand().equals(airplane.getBrand()) &&
                getModel().equals(airplane.getModel()) &&
                getManufacturer().equals(airplane.getManufacturer()) &&
                getYear().equals(airplane.getYear()) &&
                getFuelCapacity().equals(airplane.getFuelCapacity()) &&
                getSeats().equals(airplane.getSeats()) &&
                getValuations().equals(airplane.getValuations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getManufacturer(), getYear(), getFuelCapacity(), getSeats(), getValuations());
    }
}
