package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CasconfirmeMapperTest {

    private CasconfirmeMapper casconfirmeMapper;

    @BeforeEach
    public void setUp() {
        casconfirmeMapper = new CasconfirmeMapperImpl();
    }
}
