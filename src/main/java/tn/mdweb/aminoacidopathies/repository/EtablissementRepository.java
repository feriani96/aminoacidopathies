package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Etablissement;

/**
 * Spring Data JPA repository for the Etablissement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {}
