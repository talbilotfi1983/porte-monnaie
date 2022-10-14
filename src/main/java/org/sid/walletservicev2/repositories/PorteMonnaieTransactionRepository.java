package org.sid.walletservicev2.repositories;

import org.sid.walletservicev2.entities.PorteMonnaieTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteMonnaieTransactionRepository extends JpaRepository<PorteMonnaieTransaction, String> {
}
