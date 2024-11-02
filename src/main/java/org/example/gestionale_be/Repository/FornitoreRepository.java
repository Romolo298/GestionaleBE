package org.example.gestionale_be.Repository;

import org.example.gestionale_be.Entity.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {
    Boolean existsByRagioneSociale(String ragioneSociale);
}
