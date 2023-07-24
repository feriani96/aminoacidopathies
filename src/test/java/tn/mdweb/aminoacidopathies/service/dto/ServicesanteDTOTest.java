package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class ServicesanteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServicesanteDTO.class);
        ServicesanteDTO servicesanteDTO1 = new ServicesanteDTO();
        servicesanteDTO1.setId(1L);
        ServicesanteDTO servicesanteDTO2 = new ServicesanteDTO();
        assertThat(servicesanteDTO1).isNotEqualTo(servicesanteDTO2);
        servicesanteDTO2.setId(servicesanteDTO1.getId());
        assertThat(servicesanteDTO1).isEqualTo(servicesanteDTO2);
        servicesanteDTO2.setId(2L);
        assertThat(servicesanteDTO1).isNotEqualTo(servicesanteDTO2);
        servicesanteDTO1.setId(null);
        assertThat(servicesanteDTO1).isNotEqualTo(servicesanteDTO2);
    }
}
