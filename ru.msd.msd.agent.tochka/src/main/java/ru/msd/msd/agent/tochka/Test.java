package ru.msd.msd.agent.tochka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.msd.msd.agent.config.WebClientConfig;
import ru.msd.msd.agent.exception.ClientException;
import ru.msd.msd.agent.tochka.dto.CreatePayment;

import java.time.LocalDate;




// "Data": {
//         "accountCode": "40802810020000685896",
//         "bankCode": "044525104",
//         "counterpartyBankBic": "004525988",
//         "counterpartyAccountNumber": "03100643000000017300",
//         "counterpartyINN": "7727406020",
//         "counterpartyKPP": "770801001",
//         "counterpartyName": "Администратор Московского парковочного пространства (ГКУ АМПП)",
//         "counterpartyBankCorrAccount": "40102810545370000003",
//         "paymentAmount": "7",
//         "paymentDate": "2025-08-10",
//         "paymentPurpose": "Оплата за проезды по участкам Московского скоростного диаметра за 10.07.2025, номер ТС A002OC77",
//         "supplierBillId": "0355431019217522430297490",
//         "taxInfoStatus": "01"
//         }
//         }'

public class Test {


    private static final WebClientConfig conf = new WebClientConfig();

    private static final String tochkaBearer = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJkZTJhNmQ4MmNjMmFkNDNhMGZmN2ZkYTZhMGU5ZTUyNSJ9.G7ZSdl64qhLD8ywu53wmgWmc_kFrpXIf4O5OJwr0qloXrOD3a2X_2PIj-kurSovG1uxJVay2IQKPyhm67C6auKWUDthJ6fSGpUPjoNsh0WuWhH1SZoLxbCcAfIXODgoixuGwya4-wcdNuJ-bAty20i_EHDhZ3XOjPDzOcyK7wQpT0iyzHtMK268pmFXdnSQvtOJ7suQuADQHuLkFJjQj1V-UZOBG4ABWdTK_IOmb9jPaKU3n1gQ7QPNR9qX7MP21cKyUbjAYVXK6ZcCSgVEQymCENj_ZEpWCRI1ks3S9GQt5TPKm9L-wRWY_ENo-tK-jFsF2sXMIeyy3KhklXsw7jCZ6j36ZIK-J-cqntTgsowobBZ1Qqm9o9CbhMrxvXUk3wwnFHdLiJMnRgEuvwfyGvkZ8r9W7HMu0Rqsv9Wsn4iOAwpAnEgWnldJTU4BicykI-czgvGOpUrqoTNeUQHwouNh5AywsU1tEm9pwrj3RUKoTO7FiRdUt2E--eAlD58DZ";

    public static void main(String[] args) throws InterruptedException {
        final TochkaAgent ta = new TochkaAgent(conf.webClient(),  tochkaBearer);

        ta.createPayment(new CreatePayment(new CreatePayment.Data(
                "40802810020000685896",
                "044525104",
                "",
                "",
                "004525988",
                "03100643000000017300",
                "7727406020",
                "770801001",
                "Администратор Московского парковочного пространства (ГКУ АМПП)",
                "40102810545370000003",
                "7.0",
                LocalDate.now(),
                "",
                "",
                "Оплата за проезды по участкам Московского скоростного диаметра за 10.07.2025, номер ТС A002OC77",
                "0355431019217522430297490",
                "",
                "",
                "",
                "",
                "",
                "",
                "01",
                ""
        ))).subscribe(
                successResponse-> {},
                error -> {
                    if (error instanceof ClientException) {
                        System.out.println(error.getMessage());
                    }
                }
        );



        Thread.sleep(3000);

    }

}
