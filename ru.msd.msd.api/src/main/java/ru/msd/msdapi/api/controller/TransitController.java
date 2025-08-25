package ru.msd.msdapi.api.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.msd.msdapi.api.dto.TransitDto;
import ru.msd.msdapi.api.dto.UpdateTransitStatusRequest;
import ru.msd.msdapi.exception.user.TransitNotFoundExceprion;
import ru.msd.msdapi.service.transit.TransitService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/transit")
public class TransitController {

    private final TransitService transitService;

    @Autowired
    public TransitController(TransitService service) {
        this.transitService = service;
    }


    @GetMapping("/accrual")
    public ResponseEntity<TransitDto> getByAccrualNumber(@RequestParam String accrualNumber) {
        try{
            return new ResponseEntity<>(this.transitService.findByAccrualNumber(accrualNumber), HttpStatus.OK);
        } catch (TransitNotFoundExceprion transitEx){
            return ResponseEntity.status(404).body(null);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/car-number")
    public ResponseEntity<Collection<TransitDto>> getByCatNumber(@RequestParam String number) {
        return new ResponseEntity<>(this.transitService.findByCarNumber(number), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> addTransit(@RequestBody @Valid TransitDto transitDto) {
        try {
            final UUID id = this.transitService.addTransit(transitDto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping()
    public ResponseEntity<? super String> updateStatus(@RequestBody UpdateTransitStatusRequest request) {
        try {
            final UUID id = this.transitService.updateStatus(request.id(), request.status());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
