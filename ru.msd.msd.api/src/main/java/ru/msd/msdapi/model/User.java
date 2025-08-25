package ru.msd.msdapi.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class User extends AutoFillId {
    @Column(name = "name", length = 224)
    private String name;

    @Column(name = "telegram_id", unique = true)
    private final Long telegramId;

    @Column(name = "auto_payment")
    private Boolean autoPayment = false;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "register_date")
    private final LocalDate registerDate;

    @OneToMany(mappedBy = "user")
    private final Set<CarRegister> numbersRegisters = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private final Set<Transaction> transactions = new HashSet<>();

    public User(UUID id, String name, Long telegramId, Double balance, LocalDate registerDate) {
        super(id);
        this.name = name;
        this.telegramId = telegramId;
        this.balance = balance;
        this.registerDate = registerDate;
    }
}
