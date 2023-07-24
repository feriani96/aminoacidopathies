package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class StructuresanteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StructuresanteDTO.class);
        StructuresanteDTO structuresanteDTO1 = new StructuresanteDTO();
        structuresanteDTO1.setId(1L);
        StructuresanteDTO structuresanteDTO2 = new StructuresanteDTO();
        assertThat(structuresanteDTO1).isNotEqualTo(structuresanteDTO2);
        structuresanteDTO2.setId(structuresanteDTO1.getId());
        assertThat(structuresanteDTO1).isEqualTo(structuresanteDTO2);
        structuresanteDTO2.setId(2L);
        assertThat(structuresanteDTO1).isNotEqualTo(structuresanteDTO2);
        structuresanteDTO1.setId(null);
        assertThat(structuresanteDTO1).isNotEqualTo(structuresanteDTO2);
    }
}
