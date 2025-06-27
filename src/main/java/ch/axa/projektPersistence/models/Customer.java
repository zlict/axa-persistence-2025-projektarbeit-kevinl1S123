package ch.axa.projektPersistence.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Cant be empty")
    @Column(name = "firstName", length = 5000)
    private String firstName;

    @NotBlank(message = "Cant be empty")
    @Column(name = "lastName", length = 5000)
    private String lastName;

    @Email
    @Column(name = "email", length = 5000)
    private String email;

    @Pattern(regexp = "^(?:0|\\(?\\+41\\)?\\s?|0041\\s?)(21|22|24|26|27|31|32|33|34|41|43|44|51|52|55|56|58|61|62|71|74|76|77|78|79|81|91)(?:[\\.\\-\\s]?\\d\\d\\d)(?:[\\.\\-\\s]?\\d\\d){2}$", message = "Ungültige Schweizer Telefonnummer")
    @Column(name = "phoneNumber", length = 5000)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "customer", "claims", "payments", "policen" })
    private Set<Vertrag> vertrage = new HashSet<>();
}
