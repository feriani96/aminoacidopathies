package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.mdweb.aminoacidopathies.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
