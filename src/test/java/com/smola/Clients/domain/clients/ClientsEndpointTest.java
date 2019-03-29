package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientsEndpoint.class)
public class ClientsEndpointTest {
    private Client FIRST_CLIENT;
    private Client SECOND_CLIENT;

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Collection<Client>> jsonResultClients;
    private JacksonTester<Client> clientJson;

    @Before
    public void setUpJsonTester() {
        initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Before
    public void createObjects() {
        FIRST_CLIENT = Client.ClientBuilder.aClient()
                .withFirstName("Marcin")
                .withSecondName("Smola")
                .build();
        FIRST_CLIENT.addAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")));
        SECOND_CLIENT = Client.ClientBuilder.aClient()
                .withFirstName("Jan")
                .withSecondName("Kowalski")
                .build();
        SECOND_CLIENT.addAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")));
        when(clientService.getAllClients()).thenReturn(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT));
    }


    @Test
    public void shouldRetrieveAllClients() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/clients"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

        assertThat(response.getContentAsString()).isEqualTo(jsonResultClients.write(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT)).getJson());
    }

    @Test
    public void shouldCreateNewClient() throws Exception {
        String json = clientJson
                .write(FIRST_CLIENT).getJson();

        System.out.println(json);
        MockHttpServletResponse response = mockMvc
                .perform(post("/clientsxd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }
}