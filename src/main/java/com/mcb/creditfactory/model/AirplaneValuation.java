package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "AIRPLANE_VALUATION")
public class AirplaneValuation {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(name = "value")
    @NonNull
    private BigDecimal value;
    @NonNull
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    private Airplane airplane;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirplaneValuation)) return false;
        AirplaneValuation valuation = (AirplaneValuation) o;
        return getId().equals(valuation.getId()) &&
                getValue().equals(valuation.getValue()) &&
                getDate().equals(valuation.getDate()) &&
                Objects.equals(getAirplane(), valuation.getAirplane());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getDate(), getAirplane());
    }
}
