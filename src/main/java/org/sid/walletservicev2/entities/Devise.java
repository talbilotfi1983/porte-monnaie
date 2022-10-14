package org.sid.walletservicev2.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Devise {
    @Id
    private String code;
    private String name;
    private String symbol;
    private Double prixAchat;
    private Double prixVente;
}
