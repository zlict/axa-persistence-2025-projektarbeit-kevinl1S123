package ch.axa.punchclock.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Cant be empty")
    @Column(name = "description", length = 5000)
    private String firstName;

    @NotBlank(message = "Cant be empty")
    @Column(name = "description", length = 5000)
    private String lastName;

    @Column(name = "description", length = 5000)
    private String email;

    @Column(name = "description", length = 5000)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Vertrag> vertrage = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Vertrag> getVertrage() {
        return vertrage;
    }

    public void setVertrage(Set<Vertrag> vertrage) {
        this.vertrage = vertrage;
    }

    

}
