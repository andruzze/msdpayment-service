package ru.msd.msdapi.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "car_registers")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CarRegister extends AutoFillId {

    @ManyToOne
    private final User user;

    @ManyToOne
    @JoinColumn(name = "number_id", nullable = false)
    private CarNumber carNumber;

    @Column(name = "register_date", nullable = false)
    private final LocalDate registerDate;

    public CarRegister(UUID id, User user, CarNumber carNumber, LocalDate registerDate) {
        super(id);
        this.user = user;
        this.carNumber = carNumber;
        this.registerDate = registerDate;
    }
}
