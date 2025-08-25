package ru.msd.msdapi.model;


import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.msd.msdapi.model.type.TransitStatus;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transits")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class Transit extends AutoFillId {

    @Column(name = "name", nullable = false, updatable = false)
    private final String name;

    @ManyToOne
    private final CarNumber carNumber;

    @Column(name = "accrual_number", nullable = false, updatable = false)
    private final String accrual_number;

    @Column(name = "accrual_date", nullable = false, updatable = false)
    private final LocalDate accrualDate;

    @Column(name = "amount", nullable = false, updatable = false)
    private final Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(50) default 'UNPAID'")
    private TransitStatus status = TransitStatus.UNPAID;

    @OneToOne(mappedBy = "transit", cascade = CascadeType.ALL)
    private BillingPayment billingPayment;

    public Transit(UUID id, String name, CarNumber carNumber, String accrual_number, LocalDate accrualDate, Double amount) {
        super(id);
        this.name = name;
        this.carNumber = carNumber;
        this.accrual_number = accrual_number;
        this.accrualDate = accrualDate;
        this.amount = amount;
    }
}
