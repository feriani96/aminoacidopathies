package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CasconfirmeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Casconfirme.class);
        Casconfirme casconfirme1 = new Casconfirme();
        casconfirme1.setId(1L);
        Casconfirme casconfirme2 = new Casconfirme();
        casconfirme2.setId(casconfirme1.getId());
        assertThat(casconfirme1).isEqualTo(casconfirme2);
        casconfirme2.setId(2L);
        assertThat(casconfirme1).isNotEqualTo(casconfirme2);
        casconfirme1.setId(null);
        assertThat(casconfirme1).isNotEqualTo(casconfirme2);
    }
}
