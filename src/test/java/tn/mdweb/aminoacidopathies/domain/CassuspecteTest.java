package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class CassuspecteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cassuspecte.class);
        Cassuspecte cassuspecte1 = new Cassuspecte();
        cassuspecte1.setId(1L);
        Cassuspecte cassuspecte2 = new Cassuspecte();
        cassuspecte2.setId(cassuspecte1.getId());
        assertThat(cassuspecte1).isEqualTo(cassuspecte2);
        cassuspecte2.setId(2L);
        assertThat(cassuspecte1).isNotEqualTo(cassuspecte2);
        cassuspecte1.setId(null);
        assertThat(cassuspecte1).isNotEqualTo(cassuspecte2);
    }
}
