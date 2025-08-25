package ru.msd.msdapi.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@MappedSuperclass
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class AutoFillId {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public AutoFillId(UUID id) {
        this.id = id;
    }
}
