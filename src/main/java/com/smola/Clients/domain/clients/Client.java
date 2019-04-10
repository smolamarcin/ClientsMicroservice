package com.smola.Clients.domain.clients;


import javax.persistence.*;
import java.util.*;

@Entity(name = "Client")
@Table(name = "client")
class Client extends BaseEntity {

    private String firstName;

    private String secondName;

    private String email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private List<Address> addresses = new ArrayList<>();

    void addAddress(Address address) {
        addresses.add(address);
    }

    public void addAddresses(Collection<Address> addresses) {
        addresses.forEach(this::addAddress);
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }


    static class ClientBuilder {
        private String firstName;
        private String secondName;
        private String email;
        private List<Address> addresses = new ArrayList<>();

        private ClientBuilder() {
        }

        static ClientBuilder aClient() {
            return new ClientBuilder();
        }

        ClientBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        ClientBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        ClientBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        ClientBuilder withAddresses(List<Address> addresses) {
            this.addresses = new ArrayList<>(addresses);
            return this;
        }

        Client build() {
            Client client = new Client();
            client.email = this.email;
            client.addresses = this.addresses;
            client.firstName = this.firstName;
            client.secondName = this.secondName;
            return client;
        }
    }




}
