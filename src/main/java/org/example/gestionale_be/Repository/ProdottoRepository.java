package org.example.gestionale_be.Repository;

import org.example.gestionale_be.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    Boolean existsByCodiceProdotto(String codiceProdotto);

    Prodotto findByCodiceProdotto(String codiceProdotto);

}
