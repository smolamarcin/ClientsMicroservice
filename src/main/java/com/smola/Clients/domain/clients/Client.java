package com.smola.Clients.domain.clients;


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

    void addAddress(Address address) {
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
