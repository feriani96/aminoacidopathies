package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MetaboliqueMapperTest {

    private MetaboliqueMapper metaboliqueMapper;

    @BeforeEach
    public void setUp() {
        metaboliqueMapper = new MetaboliqueMapperImpl();
    }
}
