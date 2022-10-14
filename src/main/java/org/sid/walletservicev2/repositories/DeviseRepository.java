package org.sid.walletservicev2.repositories;

import org.sid.walletservicev2.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviseRepository extends JpaRepository<Devise, String> {
}
