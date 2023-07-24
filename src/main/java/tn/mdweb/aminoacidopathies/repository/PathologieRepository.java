package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Pathologie;

/**
 * Spring Data JPA repository for the Pathologie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PathologieRepository extends JpaRepository<Pathologie, Long> {}
