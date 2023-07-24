package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CassuspecteMapperTest {

    private CassuspecteMapper cassuspecteMapper;

    @BeforeEach
    public void setUp() {
        cassuspecteMapper = new CassuspecteMapperImpl();
    }
}
