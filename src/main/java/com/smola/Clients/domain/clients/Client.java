package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Client")
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String secondName;

    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
    }
    public void addAddresses(Collection<Address> addresses){
        addresses.forEach(this::addAddress);
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(secondName, client.secondName) &&
                Objects.equals(addresses, client.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, addresses);
    }


    public static class ClientBuilder {
        private Long id;
        private String firstName;
        private String secondName;
        private String email;
        private List<Address> addresses = new ArrayList<>();

        private ClientBuilder() {
        }

        public static ClientBuilder aClient() {
            return new ClientBuilder();
        }

        public ClientBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ClientBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public ClientBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder withAddresses(List<Address> addresses) {
            this.addresses = new ArrayList<>(addresses);
            return this;
        }

        public Client build() {
            Client client = new Client();
            client.email = this.email;
            client.addresses = this.addresses;
            client.firstName = this.firstName;
            client.secondName = this.secondName;
            client.id = this.id;
            return client;
        }
    }


}
