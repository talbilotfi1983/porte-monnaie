package org.sid.walletservicev2.web;

import org.sid.walletservicev2.entities.PorteMonnaie;
import org.sid.walletservicev2.repositories.PorteMonnaieRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PorteMonnaieGraphQlApi {
    private PorteMonnaieRepository porteMonnaieRepository;

    public PorteMonnaieGraphQlApi(PorteMonnaieRepository porteMonnaieRepository) {
        this.porteMonnaieRepository = porteMonnaieRepository;
    }

    @QueryMapping
    public List<PorteMonnaie> porteMonnaies() {
        return porteMonnaieRepository.findAll();
    }

    @QueryMapping
    public PorteMonnaie porteMonnaieById(@Argument String id) {
        System.out.println(id);
        return porteMonnaieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("PorteMonnaie %s not found", id)));
    }
}
