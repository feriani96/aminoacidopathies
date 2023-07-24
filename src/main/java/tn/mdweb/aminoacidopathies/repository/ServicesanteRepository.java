package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.mdweb.aminoacidopathies.domain.Servicesante;

/**
 * Spring Data JPA repository for the Servicesante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesanteRepository extends JpaRepository<Servicesante, Long> {}
