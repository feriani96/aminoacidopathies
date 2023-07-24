package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StructureficheMapperTest {

    private StructureficheMapper structureficheMapper;

    @BeforeEach
    public void setUp() {
        structureficheMapper = new StructureficheMapperImpl();
    }
}
