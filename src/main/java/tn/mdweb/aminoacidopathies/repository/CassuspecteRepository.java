package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Cassuspecte;

/**
 * Spring Data JPA repository for the Cassuspecte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CassuspecteRepository extends JpaRepository<Cassuspecte, Long> {}
