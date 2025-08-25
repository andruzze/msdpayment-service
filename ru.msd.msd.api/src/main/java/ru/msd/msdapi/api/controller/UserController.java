package ru.msd.msdapi.api.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.msd.msdapi.api.dto.CarRegisterDto;
import ru.msd.msdapi.api.dto.UserDto;
import ru.msd.msdapi.api.dto.UserFilterDto;
import ru.msd.msdapi.service.number.RegisterNumberService;
import ru.msd.msdapi.service.user.UserService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final RegisterNumberService numberService;

    @Autowired
    public UserController(UserService userService, RegisterNumberService numberService) {
        this.userService = userService;
        this.numberService = numberService;
    }

    @PostMapping("number")
    public ResponseEntity<UUID> registerCarNumber(@RequestBody @Valid CarRegisterDto carRegisterDto) {
        final UUID id = this.userService.registerCarNumber(carRegisterDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> registerUser(@RequestBody UserDto userDto) {
        final UUID id = userService.registerUser(userDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Collection<UserDto>> searchUsers(@RequestBody UserFilterDto dto) {
        return new ResponseEntity<>(this.userService.searchUsers(dto), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<? super String> updateUser(
            @RequestParam @NotNull UUID uuid,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) @Positive Double balance) {
        return new ResponseEntity<>(this.userService.updateUser(uuid, username, balance), HttpStatus.OK);
    }

    @PostMapping("/delete/number")
    public int deleteCarNumber(@RequestParam UUID userId, @RequestParam UUID carNumberId) {
        return this.numberService.deleteNumberRecord(userId, carNumberId);
    }
}
