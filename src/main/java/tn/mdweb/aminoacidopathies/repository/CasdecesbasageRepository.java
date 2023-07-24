package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Casdecesbasage;

/**
 * Spring Data JPA repository for the Casdecesbasage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasdecesbasageRepository extends JpaRepository<Casdecesbasage, Long> {}
