package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Structurefiche;

/**
 * Spring Data JPA repository for the Structurefiche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructureficheRepository extends JpaRepository<Structurefiche, Long> {}
