package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.smola.Clients.domain.clients.ClientsProvider.FIRST_CLIENT;
import static com.smola.Clients.domain.clients.ClientsProvider.SECOND_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class ClientsEndpointIT extends IntegrationTestBase {

    static final String CLIENTS_ENDPOINT = "/clients";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;
    private JacksonTester<Client> clientJson;
    private JacksonTester<Address> addressJson;

    @BeforeEach
    public void setUpJsonTester() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldReturnHttp409_whenUserAlreadyExists() throws Exception {
        createClient(FIRST_CLIENT);
        String json = clientJson
                .write(FIRST_CLIENT).getJson();

        mockMvc.perform(post(CLIENTS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldCreateNewClientAndReturnProperJsonResponse() throws Exception {
        JsonContent<Client> client = clientJson.write(FIRST_CLIENT);

        mockMvc.perform(post(CLIENTS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(client.getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(FIRST_CLIENT.getEmail())));

    }

    @Test
    public void shouldRetrieveAllUsers_afterCreation() throws Exception {
        createClient(FIRST_CLIENT);
        createClient(SECOND_CLIENT);

        mockMvc.perform(get(CLIENTS_ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$"), hasSize(2)));
    }

    @Test
    public void shouldAddNewAddresToClient() throws Exception {
        createClient(FIRST_CLIENT);
        mockMvc.perform(put("/clients")
                .param("clientEmail", FIRST_CLIENT.getEmail())
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJson.write(new Address("Warszawa")).getJson()))
                .andDo(print())
                .andExpect(status().isOk());

        Optional<Client> updatedClient = clientRepository.findByEmail(FIRST_CLIENT.getEmail());

        assertThat(updatedClient).isNotEmpty();
    }

    private void createClient(Client client) throws Exception {
        mockMvc.perform(post(CLIENTS_ENDPOINT)
                .content(clientJson.write(client).getJson())
                .contentType(MediaType.APPLICATION_JSON));
    }
}