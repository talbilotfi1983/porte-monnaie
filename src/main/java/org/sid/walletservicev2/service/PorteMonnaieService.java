package org.sid.walletservicev2.service;

import org.sid.walletservicev2.entities.Devise;
import org.sid.walletservicev2.entities.PorteMonnaie;
import org.sid.walletservicev2.entities.PorteMonnaieTransaction;
import org.sid.walletservicev2.entities.enums.TypeTransaction;
import org.sid.walletservicev2.repositories.DeviseRepository;
import org.sid.walletservicev2.repositories.PorteMonnaieRepository;
import org.sid.walletservicev2.repositories.PorteMonnaieTransactionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class PorteMonnaieService {
    private DeviseRepository deviseRepository;
    private PorteMonnaieRepository porteMonnaieRepository;
    private PorteMonnaieTransactionRepository porteMonnaieTransactionRepository;

    public PorteMonnaieService(DeviseRepository deviseRepository, PorteMonnaieRepository porteMonnaieRepository, PorteMonnaieTransactionRepository porteMonnaieTransactionRepository) {
        this.deviseRepository = deviseRepository;
        this.porteMonnaieRepository = porteMonnaieRepository;
        this.porteMonnaieTransactionRepository = porteMonnaieTransactionRepository;
    }

    public void chargementDevises() throws IOException {
        URI uri = new ClassPathResource("static/devises.data.csv").getURI();
        Path path = Paths.get(uri);
        List<String> lignes = Files.readAllLines(path);
        for (int i = 1; i < lignes.size(); i++) {
            String[] ligneArray = lignes.get(i).split(";");
            Devise devise = Devise
                    .builder()
                    .code(ligneArray[0])
                    .name(ligneArray[1])
                    .prixAchat(Double.parseDouble(ligneArray[2]))
                    .prixVente(Double.parseDouble(ligneArray[3]))
                    .build();
            this.deviseRepository.save(devise);
        }
        deviseRepository.findAll().forEach(d->{
            Devise devise = deviseRepository.findById(d.getCode()).orElseThrow(RuntimeException::new);
            PorteMonnaie porteMonnaie = PorteMonnaie
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .devise(devise)
                    .solde(10000.0)
                    .dateCreation(System.currentTimeMillis())
                    .idUtilisateur("U1")
                    .build();
            porteMonnaieRepository.save(porteMonnaie);
            PorteMonnaieTransaction porteMonnaieTransaction =
                    PorteMonnaieTransaction
                            .builder()
                            .id(UUID.randomUUID().toString())
                            .dateTransaction(System.currentTimeMillis())
                            .montantTransaction(Math.random() * 15.0)
                            .porteMonnaie(porteMonnaie)
                            .typeTransaction(TypeTransaction.DEBIT)
                            .build();
            porteMonnaieTransactionRepository.save(porteMonnaieTransaction);
            porteMonnaie.setSolde(porteMonnaie.getSolde() - porteMonnaieTransaction.getMontantTransaction());
            porteMonnaieRepository.save(porteMonnaie);

            // le credit

             porteMonnaieTransaction =
                    PorteMonnaieTransaction
                            .builder()
                            .id(UUID.randomUUID().toString())
                            .dateTransaction(System.currentTimeMillis())
                            .montantTransaction(Math.random() * 15.0)
                            .porteMonnaie(porteMonnaie)
                            .typeTransaction(TypeTransaction.CREDIT)
                            .build();
            porteMonnaieTransactionRepository.save(porteMonnaieTransaction);
            porteMonnaie.setSolde(porteMonnaie.getSolde() + porteMonnaieTransaction.getMontantTransaction());
            porteMonnaieRepository.save(porteMonnaie);
        });

    }
}
