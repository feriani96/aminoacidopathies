package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Structuresante;

/**
 * Spring Data JPA repository for the Structuresante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructuresanteRepository extends JpaRepository<Structuresante, Long> {}
