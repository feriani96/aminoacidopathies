package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CasdecesbasageDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasdecesbasageDTO.class);
        CasdecesbasageDTO casdecesbasageDTO1 = new CasdecesbasageDTO();
        casdecesbasageDTO1.setId(1L);
        CasdecesbasageDTO casdecesbasageDTO2 = new CasdecesbasageDTO();
        assertThat(casdecesbasageDTO1).isNotEqualTo(casdecesbasageDTO2);
        casdecesbasageDTO2.setId(casdecesbasageDTO1.getId());
        assertThat(casdecesbasageDTO1).isEqualTo(casdecesbasageDTO2);
        casdecesbasageDTO2.setId(2L);
        assertThat(casdecesbasageDTO1).isNotEqualTo(casdecesbasageDTO2);
        casdecesbasageDTO1.setId(null);
        assertThat(casdecesbasageDTO1).isNotEqualTo(casdecesbasageDTO2);
    }
}
