package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Metabolique;

/**
 * Spring Data JPA repository for the Metabolique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetaboliqueRepository extends JpaRepository<Metabolique, Long> {}
