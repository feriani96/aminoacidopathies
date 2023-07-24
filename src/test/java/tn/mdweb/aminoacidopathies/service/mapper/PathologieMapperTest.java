package tn.mdweb.aminoacidopathies.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathologieMapperTest {

    private PathologieMapper pathologieMapper;

    @BeforeEach
    public void setUp() {
        pathologieMapper = new PathologieMapperImpl();
    }
}
