package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CassuspecteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CassuspecteDTO.class);
        CassuspecteDTO cassuspecteDTO1 = new CassuspecteDTO();
        cassuspecteDTO1.setId(1L);
        CassuspecteDTO cassuspecteDTO2 = new CassuspecteDTO();
        assertThat(cassuspecteDTO1).isNotEqualTo(cassuspecteDTO2);
        cassuspecteDTO2.setId(cassuspecteDTO1.getId());
        assertThat(cassuspecteDTO1).isEqualTo(cassuspecteDTO2);
        cassuspecteDTO2.setId(2L);
        assertThat(cassuspecteDTO1).isNotEqualTo(cassuspecteDTO2);
        cassuspecteDTO1.setId(null);
        assertThat(cassuspecteDTO1).isNotEqualTo(cassuspecteDTO2);
    }
}
