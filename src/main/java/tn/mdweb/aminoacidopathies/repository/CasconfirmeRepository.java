package tn.mdweb.aminoacidopathies.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.domain.Casconfirme;


/**
 * Spring Data JPA repository for the Casconfirme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasconfirmeRepository extends JpaRepository<Casconfirme, Long> {
List<CasconfirmeDTO>findByFiche(Fiche fiche);
}
