package ru.msd.msdapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "car_numbers")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CarNumber extends AutoFillId {

    @Column(name = "number", nullable = false, unique = true, length = 10)
    private final String number;

    @OneToMany(mappedBy = "carNumber")
    private final Set<CarRegister> numbersRegisters = new HashSet<>();

    @OneToMany(mappedBy = "carNumber")
    private final Set<Transit> transits = new HashSet<>();

    public CarNumber(UUID id, String number) {
        super(id);
        this.number = number;
    }
}
