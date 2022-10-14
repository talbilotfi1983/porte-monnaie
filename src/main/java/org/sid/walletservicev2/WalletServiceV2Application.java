package org.sid.walletservicev2;

import org.sid.walletservicev2.service.PorteMonnaieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletServiceV2Application {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceV2Application.class, args);
    }

    @Bean
    CommandLineRunner start(PorteMonnaieService porteMonnaieService) {
        return args -> {
            porteMonnaieService.chargementDevises();
        };
    }
}
