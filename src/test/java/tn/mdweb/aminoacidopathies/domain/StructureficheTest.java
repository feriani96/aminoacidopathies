package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class StructureficheTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Structurefiche.class);
        Structurefiche structurefiche1 = new Structurefiche();
        structurefiche1.setId(1L);
        Structurefiche structurefiche2 = new Structurefiche();
        structurefiche2.setId(structurefiche1.getId());
        assertThat(structurefiche1).isEqualTo(structurefiche2);
        structurefiche2.setId(2L);
        assertThat(structurefiche1).isNotEqualTo(structurefiche2);
        structurefiche1.setId(null);
        assertThat(structurefiche1).isNotEqualTo(structurefiche2);
    }
}
