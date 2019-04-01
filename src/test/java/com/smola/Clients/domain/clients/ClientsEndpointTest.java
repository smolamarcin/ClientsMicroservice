package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smola.Clients.domain.clients.dto.ClientDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.smola.Clients.domain.clients.ClientsProvider.FIRST_CLIENT_DTO;
import static com.smola.Clients.domain.clients.ClientsProvider.SECOND_CLIENT_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientsEndpoint.class)
public class ClientsEndpointTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<List<ClientDto>> jsonResultClients;
    private JacksonTester<ClientDto> clientDtoJson;

    @Before
    public void setUpJsonTester() {
        initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldRetrieveAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Arrays.asList(FIRST_CLIENT_DTO, SECOND_CLIENT_DTO));
        MockHttpServletResponse response = mockMvc.perform(get("/clients"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

        assertThat(response.getContentAsString()).isEqualTo(jsonResultClients.write(Arrays.asList(FIRST_CLIENT_DTO, SECOND_CLIENT_DTO)).getJson());
    }

    @Test
    public void shouldCreateNewClient() throws Exception {
        String json = clientDtoJson
                .write(FIRST_CLIENT_DTO).getJson();

        MockHttpServletResponse response = mockMvc
                .perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }
}