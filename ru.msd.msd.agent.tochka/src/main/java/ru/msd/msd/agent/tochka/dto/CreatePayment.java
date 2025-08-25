package ru.msd.msd.agent.tochka.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreatePayment(
        @JsonProperty(value = "Data")
        Data data) {
    public record Data(
            @JsonProperty(value = "accountCode", required = true)
            String accountCode,

            @JsonProperty(value = "bankCode", required = true)
            String bankCode,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "payerINN")
            String payerINN,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "payerKPP")
            String payerKPP,

            @JsonProperty(value = "counterpartyBankBic", required = true)
            String counterpartyBankBic,

            @JsonProperty(value = "counterpartyAccountNumber", required = true)
            String counterpartyAccountNumber,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "counterpartyINN")
            String counterpartyINN,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "counterpartyKPP")
            String counterpartyKPP,

            @JsonProperty(value = "counterpartyName", required = true)
            String counterpartyName,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "counterpartyBankCorrAccount")
            String counterpartyBankCorrAccount,

            @JsonProperty(value = "paymentAmount", required = true)
            String paymentAmount,

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @JsonProperty(value = "paymentDate", required = true)
            LocalDate paymentDate,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "paymentNumber")
            String paymentNumber,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "paymentPriority")
            String paymentPriority,

            @JsonProperty(value = "paymentPurpose", required = true)
            String paymentPurpose,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "supplierBillId")
            String supplierBillId,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoDocumentDate")
            String taxInfoDocumentDate,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoDocumentNumber")
            String taxInfoDocumentNumber,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoKBK")
            String taxInfoKBK,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoOKATO")
            String taxInfoOKATO,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoPeriod")
            String taxInfoPeriod,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoReasonCode")
            String taxInfoReasonCode,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "taxInfoStatus")
            String taxInfoStatus,

            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            @JsonProperty(value = "budgetPaymentCode")
            String budgetPaymentCode) {
    }
}