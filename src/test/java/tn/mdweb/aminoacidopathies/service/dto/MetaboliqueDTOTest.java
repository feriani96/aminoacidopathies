package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class MetaboliqueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MetaboliqueDTO.class);
        MetaboliqueDTO metaboliqueDTO1 = new MetaboliqueDTO();
        metaboliqueDTO1.setId(1L);
        MetaboliqueDTO metaboliqueDTO2 = new MetaboliqueDTO();
        assertThat(metaboliqueDTO1).isNotEqualTo(metaboliqueDTO2);
        metaboliqueDTO2.setId(metaboliqueDTO1.getId());
        assertThat(metaboliqueDTO1).isEqualTo(metaboliqueDTO2);
        metaboliqueDTO2.setId(2L);
        assertThat(metaboliqueDTO1).isNotEqualTo(metaboliqueDTO2);
        metaboliqueDTO1.setId(null);
        assertThat(metaboliqueDTO1).isNotEqualTo(metaboliqueDTO2);
    }
}
