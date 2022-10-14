package org.sid.walletservicev2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PorteMonnaie {
    @Id
    private String id;
    private Double solde;
    private Long dateCreation;
    private String idUtilisateur;
    @ManyToOne
    private Devise devise;
    @OneToMany(mappedBy = "porteMonnaie")
    private List<PorteMonnaieTransaction> porteMonnaieTransaction;
}
