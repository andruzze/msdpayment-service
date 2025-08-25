package ru.msd.msdapi.model;


import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "billing_payments")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class BillingPayment extends AutoFillId {
    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @OneToOne()
    private Transit transit;
}
