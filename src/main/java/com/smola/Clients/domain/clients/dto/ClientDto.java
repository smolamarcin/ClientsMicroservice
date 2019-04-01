package com.smola.Clients.domain.clients.dto;

import com.smola.Clients.domain.clients.Address;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ClientDto {
    private String firstName;
    private String secondName;
    private String email;
    private List<AddressDto> addresses;

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public List<AddressDto> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public static final class ClientDtoBuilder {
        private String firstName;
        private String secondName;
        private String email;
        private List<AddressDto> addresses;

        private ClientDtoBuilder() {
        }

        public static ClientDtoBuilder aClientDto() {
            return new ClientDtoBuilder();
        }

        public ClientDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ClientDtoBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public ClientDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientDtoBuilder withAddresses(List<AddressDto> addresses) {
            this.addresses = addresses;
            return this;
        }

        public ClientDto build() {
            ClientDto clientDto = new ClientDto();
            clientDto.email = this.email;
            clientDto.firstName = this.firstName;
            clientDto.secondName = this.secondName;
            clientDto.addresses = this.addresses;
            return clientDto;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(firstName, clientDto.firstName) &&
                Objects.equals(secondName, clientDto.secondName) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(addresses, clientDto.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, email, addresses);
    }
}
