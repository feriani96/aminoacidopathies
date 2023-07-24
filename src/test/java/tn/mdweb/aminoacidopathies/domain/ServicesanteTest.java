package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class ServicesanteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicesante.class);
        Servicesante servicesante1 = new Servicesante();
        servicesante1.setId(1L);
        Servicesante servicesante2 = new Servicesante();
        servicesante2.setId(servicesante1.getId());
        assertThat(servicesante1).isEqualTo(servicesante2);
        servicesante2.setId(2L);
        assertThat(servicesante1).isNotEqualTo(servicesante2);
        servicesante1.setId(null);
        assertThat(servicesante1).isNotEqualTo(servicesante2);
    }
}
