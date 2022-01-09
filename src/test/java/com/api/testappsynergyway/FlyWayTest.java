package com.api.testappsynergyway;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlyWayTest {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.flyway.table}")
    String tableHistory;
    
    @Test
    public void migrationTest() {
        Flyway.configure()
                .dataSource(url, username, password)
                .table(tableHistory)
                .load()
                .migrate();
    }


}
