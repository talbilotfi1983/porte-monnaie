package org.sid.walletservicev2.web;

import org.sid.walletservicev2.entities.Devise;
import org.sid.walletservicev2.entities.PorteMonnaie;
import org.sid.walletservicev2.entities.PorteMonnaieTransaction;
import org.sid.walletservicev2.entities.dto.PorteMonnaieDto;
import org.sid.walletservicev2.repositories.DeviseRepository;
import org.sid.walletservicev2.repositories.PorteMonnaieRepository;
import org.sid.walletservicev2.service.PorteMonnaieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PorteMonnaieGraphQlApi {
    private PorteMonnaieRepository porteMonnaieRepository;
    private DeviseRepository deviseRepository;
    private PorteMonnaieService porteMonnaieService;

    public PorteMonnaieGraphQlApi(PorteMonnaieRepository porteMonnaieRepository, DeviseRepository deviseRepository, PorteMonnaieService porteMonnaieService) {
        this.porteMonnaieRepository = porteMonnaieRepository;
        this.deviseRepository = deviseRepository;
        this.porteMonnaieService = porteMonnaieService;
    }

    @QueryMapping
    public List<PorteMonnaie> porteMonnaies() {
        return porteMonnaieRepository.findAll();
    }

    @QueryMapping
    public PorteMonnaie porteMonnaieById(@Argument String id) {
        return porteMonnaieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("PorteMonnaie %s not found", id)));
    }

    @MutationMapping
    public PorteMonnaie ajoutMonnaie(@Argument PorteMonnaieDto porteMonnaie) {

        Devise devise = deviseRepository.
                findById(porteMonnaie.getDeviseCode())
                .orElseThrow(() ->
                        new RuntimeException(String.format("Devise code %s not found",
                                porteMonnaie.getDeviseCode())));
        return porteMonnaieRepository.save(PorteMonnaie
                .builder()
                .id(UUID.randomUUID().toString())
                .solde(porteMonnaie.getSolde())
                .idUtilisateur(porteMonnaie.getIdUtilisateur())
                .dateCreation(System.currentTimeMillis())
                .devise(devise)
                .build());

    }

    @MutationMapping
    public List<PorteMonnaieTransaction> transfert(@Argument String idPorteMonnaieSource,
                                                   @Argument String idPorteMonnaiedestination,
                                                   @Argument Double montantTransaction
    ) {
        return porteMonnaieService.transfert(idPorteMonnaieSource, idPorteMonnaiedestination, montantTransaction);
    }
}
