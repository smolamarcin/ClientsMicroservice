package com.smola.Clients.domain.clients;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ITTestConfiguration.class})
class IntegrationTestBase {
}
