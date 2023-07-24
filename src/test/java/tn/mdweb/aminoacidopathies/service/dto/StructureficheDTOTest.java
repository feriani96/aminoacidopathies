package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class StructureficheDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StructureficheDTO.class);
        StructureficheDTO structureficheDTO1 = new StructureficheDTO();
        structureficheDTO1.setId(1L);
        StructureficheDTO structureficheDTO2 = new StructureficheDTO();
        assertThat(structureficheDTO1).isNotEqualTo(structureficheDTO2);
        structureficheDTO2.setId(structureficheDTO1.getId());
        assertThat(structureficheDTO1).isEqualTo(structureficheDTO2);
        structureficheDTO2.setId(2L);
        assertThat(structureficheDTO1).isNotEqualTo(structureficheDTO2);
        structureficheDTO1.setId(null);
        assertThat(structureficheDTO1).isNotEqualTo(structureficheDTO2);
    }
}
