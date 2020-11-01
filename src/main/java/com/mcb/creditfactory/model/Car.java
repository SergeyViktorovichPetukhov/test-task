package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "CAR")
public class Car {
    @NonNull
    @Id
    private Long id;
    @NonNull
    private String brand;
    @NonNull
    private String model;
    @NonNull
    @Column(name = "year_of_issue")
    private Short year;
    @NonNull
    private Double power;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<CarValuation> valuations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) &&
                Objects.equals(getBrand(), car.getBrand()) &&
                Objects.equals(getModel(), car.getModel()) &&
                Objects.equals(getYear(), car.getYear()) &&
                Objects.equals(getPower(), car.getPower()) &&
                Objects.equals(getValuations(), car.getValuations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getYear(), getPower(), getValuations());
    }
}
