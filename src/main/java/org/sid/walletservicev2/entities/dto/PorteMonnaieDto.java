package org.sid.walletservicev2.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PorteMonnaieDto {
    private String deviseCode;
    private Double solde;
    private String idUtilisateur;
}
