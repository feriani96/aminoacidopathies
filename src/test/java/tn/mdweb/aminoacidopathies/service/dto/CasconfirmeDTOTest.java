package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CasconfirmeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasconfirmeDTO.class);
        CasconfirmeDTO casconfirmeDTO1 = new CasconfirmeDTO();
        casconfirmeDTO1.setId(1L);
        CasconfirmeDTO casconfirmeDTO2 = new CasconfirmeDTO();
        assertThat(casconfirmeDTO1).isNotEqualTo(casconfirmeDTO2);
        casconfirmeDTO2.setId(casconfirmeDTO1.getId());
        assertThat(casconfirmeDTO1).isEqualTo(casconfirmeDTO2);
        casconfirmeDTO2.setId(2L);
        assertThat(casconfirmeDTO1).isNotEqualTo(casconfirmeDTO2);
        casconfirmeDTO1.setId(null);
        assertThat(casconfirmeDTO1).isNotEqualTo(casconfirmeDTO2);
    }
}
