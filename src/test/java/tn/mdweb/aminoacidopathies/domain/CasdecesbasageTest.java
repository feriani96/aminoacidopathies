package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CasdecesbasageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Casdecesbasage.class);
        Casdecesbasage casdecesbasage1 = new Casdecesbasage();
        casdecesbasage1.setId(1L);
        Casdecesbasage casdecesbasage2 = new Casdecesbasage();
        casdecesbasage2.setId(casdecesbasage1.getId());
        assertThat(casdecesbasage1).isEqualTo(casdecesbasage2);
        casdecesbasage2.setId(2L);
        assertThat(casdecesbasage1).isNotEqualTo(casdecesbasage2);
        casdecesbasage1.setId(null);
        assertThat(casdecesbasage1).isNotEqualTo(casdecesbasage2);
    }
}
