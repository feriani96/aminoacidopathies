package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StructuresanteMapperTest {

    private StructuresanteMapper structuresanteMapper;

    @BeforeEach
    public void setUp() {
        structuresanteMapper = new StructuresanteMapperImpl();
    }
}
