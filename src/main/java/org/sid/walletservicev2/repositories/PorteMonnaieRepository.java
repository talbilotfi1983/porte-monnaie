package org.sid.walletservicev2.repositories;

import org.sid.walletservicev2.entities.PorteMonnaie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PorteMonnaieRepository extends JpaRepository<PorteMonnaie, String> {
}
