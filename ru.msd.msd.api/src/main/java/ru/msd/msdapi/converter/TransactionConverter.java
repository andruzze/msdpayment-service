package ru.msd.msdapi.converter;

import ru.msd.msdapi.api.dto.TransactionDto;
import ru.msd.msdapi.api.dto.TransitDto;
import ru.msd.msdapi.model.BillingPayment;
import ru.msd.msdapi.model.Transaction;

public class TransactionConverter implements Converter<Transaction, TransactionDto> {
    private  final TransitConverter transitConverter;

    public TransactionConverter(TransitConverter transitConverter) {
        this.transitConverter = transitConverter;
    }

    @Override
    public TransactionDto convert(Transaction obj) {
        TransitDto transitDto = null;
        final BillingPayment billingPayments = obj.getBillingPayments();
        if (billingPayments != null) {
            transitDto = this.transitConverter.convert(billingPayments.getTransit());
        }
        return new TransactionDto(
                obj.getId(),
                obj.getUser().getId(),
                obj.getAmount(),
                obj.getTransactionType(),
                transitDto);
    }
}
