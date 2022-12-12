package com.example.denimshop.repository;

import com.example.denimshop.entity.Denim;
import com.example.denimshop.type.MaterialCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.example.denimshop.type.MaterialCategory.RAW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
//@Testcontainers
class DenimRepositoryTest {

//    @Container
//    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.24")
//            .withInitScript("schema.sql");
//
//    @DynamicPropertySource
//    public static void properties(DynamicPropertyRegistry registry) {
//        registry.add("sping.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
//        registry.add("sping.datasource.username", MY_SQL_CONTAINER::getUsername);
//        registry.add("sping.datasource.password", MY_SQL_CONTAINER::getPassword);
//    }
//
    @Autowired
    private DenimRepository denimRepository;
//
//    @Test
//    void test() {
//        assertThat(MY_SQL_CONTAINER.isRunning(), is(true));
//    }

    @Test
    void add() {
        Denim denim = Denim.builder()
                .productCode("12345")
                .category(RAW)
                .description("좋음")
                .price(1000L)
                .image("어딘가에")
                .build();

        denimRepository.save(denim);
    }

}