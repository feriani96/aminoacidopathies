package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class StructuresanteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Structuresante.class);
        Structuresante structuresante1 = new Structuresante();
        structuresante1.setId(1L);
        Structuresante structuresante2 = new Structuresante();
        structuresante2.setId(structuresante1.getId());
        assertThat(structuresante1).isEqualTo(structuresante2);
        structuresante2.setId(2L);
        assertThat(structuresante1).isNotEqualTo(structuresante2);
        structuresante1.setId(null);
        assertThat(structuresante1).isNotEqualTo(structuresante2);
    }
}
