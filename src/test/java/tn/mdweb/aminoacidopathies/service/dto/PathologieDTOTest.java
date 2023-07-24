package tn.mdweb.aminoacidopathies.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class PathologieDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PathologieDTO.class);
        PathologieDTO pathologieDTO1 = new PathologieDTO();
        pathologieDTO1.setId(1L);
        PathologieDTO pathologieDTO2 = new PathologieDTO();
        assertThat(pathologieDTO1).isNotEqualTo(pathologieDTO2);
        pathologieDTO2.setId(pathologieDTO1.getId());
        assertThat(pathologieDTO1).isEqualTo(pathologieDTO2);
        pathologieDTO2.setId(2L);
        assertThat(pathologieDTO1).isNotEqualTo(pathologieDTO2);
        pathologieDTO1.setId(null);
        assertThat(pathologieDTO1).isNotEqualTo(pathologieDTO2);
    }
}
