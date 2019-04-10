package com.smola.Clients.domain.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebAppConfiguration
class IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;
}
