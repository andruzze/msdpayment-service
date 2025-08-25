package ru.msd.msdapi.api.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.msd.msdapi.api.dto.CarNumberDto;
import ru.msd.msdapi.api.dto.SearchNumberDto;
import ru.msd.msdapi.service.number.CarNumberService;

import java.util.Collection;

@RestController
@RequestMapping("/api/car_numbers")
public class CarNumberController {

    private final CarNumberService carNumberService;

    @Autowired
    public CarNumberController(CarNumberService carNumberService) {
        this.carNumberService = carNumberService;
    }

    @PostMapping()
    public Collection<CarNumberDto> searchCarNumbers(@RequestBody @Valid SearchNumberDto searchNumberDto) {
        return this.carNumberService.getCarNumbers(searchNumberDto);
    }
}
