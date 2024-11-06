package org.example.gestionale_be.Repository;

import org.example.gestionale_be.Entity.Fornitore;
import org.example.gestionale_be.Entity.Prodotto;
import org.example.gestionale_be.Entity.ProdottoStorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProdottoStoricoRepository extends JpaRepository<ProdottoStorico, Long> {

    List<ProdottoStorico> findByCodiceProdotto(String codiceProdotto);

    @Query(value = "select distinct ps.codice_prodotto from prodotti_storico ps ",nativeQuery = true)
    List<String> findDistinctCodiceProdotto();

}
