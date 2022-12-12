package com.example.denimshop.service;

import com.example.denimshop.entity.Denim;
import com.example.denimshop.repository.DenimRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.denimshop.type.MaterialCategory.RAW;
import static com.example.denimshop.type.MaterialCategory.SELVEDGE;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DenimServiceTest {

    @Mock
    private DenimRepository denimRepository;

    @InjectMocks
    private DenimService denimService;


    @Test
    void findAll() {
        when(denimRepository.findAll()).thenReturn(List.of(
                Denim.builder()
                        .denimId(1)
                        .productCode("")
                        .category(RAW)
                        .description("좋음")
                        .price(1000L)
                        .image("어딘가에")
                        .build(),
                Denim.builder()
                        .denimId(2)
                        .productCode("12345")
                        .category(SELVEDGE)
                        .description("좋음")
                        .price(2000L)
                        .image("어딘가에")
                        .build()
                ));

        denimService.findAll().forEach(System.out::println);

    }
}