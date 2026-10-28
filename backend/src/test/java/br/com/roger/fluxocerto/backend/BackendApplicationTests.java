package br.com.roger.fluxocerto.backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(properties = "DB_PASSWORD=test")
class BackendApplicationTests {
    @Autowired JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {}

    @Test
    void flywayAplicouAMigracaoBaseline() {
        Integer aplicadas =
                jdbcTemplate.queryForObject(
                        "select count(*) from flyway_schema_history where success = true",
                        Integer.class);
        assertThat(aplicadas).isGreaterThanOrEqualTo(1);
    }
}
