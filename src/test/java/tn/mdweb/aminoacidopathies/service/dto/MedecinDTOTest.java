package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class MedecinDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinDTO.class);
        MedecinDTO medecinDTO1 = new MedecinDTO();
        medecinDTO1.setId(1L);
        MedecinDTO medecinDTO2 = new MedecinDTO();
        assertThat(medecinDTO1).isNotEqualTo(medecinDTO2);
        medecinDTO2.setId(medecinDTO1.getId());
        assertThat(medecinDTO1).isEqualTo(medecinDTO2);
        medecinDTO2.setId(2L);
        assertThat(medecinDTO1).isNotEqualTo(medecinDTO2);
        medecinDTO1.setId(null);
        assertThat(medecinDTO1).isNotEqualTo(medecinDTO2);
    }
}
