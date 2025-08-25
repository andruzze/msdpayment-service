package ru.msd.msdapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.msd.msdapi.api.dto.TransactionDto;
import ru.msd.msdapi.service.transaction.TransactonService;
import ru.msd.msdapi.service.transit.TransitService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactonService transactonService;

    @Autowired
    public TransactionController(TransactonService transactonService, TransitService transitService) {
        this.transactonService = transactonService;
    }

    @GetMapping()
    public ResponseEntity<Collection<TransactionDto>> getUserTransaction(@RequestParam UUID userId) {
        final Collection<TransactionDto> transactions = this.transactonService.getTransactionByUserId(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<? super String> createTransaction(@RequestBody TransactionDto transactionDto) {
        try {
            final UUID transactionId = this.transactonService.createTransaction(transactionDto);
            if (transactionId == null) {
                return new ResponseEntity<>("Cant create transaction for unknown reason", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(transactionId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
