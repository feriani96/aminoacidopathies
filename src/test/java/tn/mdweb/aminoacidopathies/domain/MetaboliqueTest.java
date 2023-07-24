package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class MetaboliqueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Metabolique.class);
        Metabolique metabolique1 = new Metabolique();
        metabolique1.setId(1L);
        Metabolique metabolique2 = new Metabolique();
        metabolique2.setId(metabolique1.getId());
        assertThat(metabolique1).isEqualTo(metabolique2);
        metabolique2.setId(2L);
        assertThat(metabolique1).isNotEqualTo(metabolique2);
        metabolique1.setId(null);
        assertThat(metabolique1).isNotEqualTo(metabolique2);
    }
}
