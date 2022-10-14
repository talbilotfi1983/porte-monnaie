package org.sid.walletservicev2.entities;

import lombok.*;
import org.sid.walletservicev2.entities.enums.TypeTransaction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PorteMonnaieTransaction {
    @Id
    private String id;
    private Long dateTransaction;
    private Double montantTransaction;
    @ManyToOne
    private PorteMonnaie porteMonnaie;
    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
}
