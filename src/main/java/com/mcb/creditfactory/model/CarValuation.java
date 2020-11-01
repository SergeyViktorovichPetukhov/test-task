package com.mcb.creditfactory.model;

//import com.mcb.creditfactory.model.id.ValuationId;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "CAR_VALUATION")
public class CarValuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "value")
    @NonNull
    private BigDecimal value;
    @Column(name = "date")
    @NonNull
    private LocalDate date;

    @ManyToOne
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarValuation)) return false;
        CarValuation valuation = (CarValuation) o;
        return getId().equals(valuation.getId()) &&
                getValue().equals(valuation.getValue()) &&
                getDate().equals(valuation.getDate()) &&
                Objects.equals(getCar(), valuation.getCar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getDate(), getCar());
    }

}
