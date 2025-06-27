package ch.axa.projektPersistence.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "claim")
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Bitte Fälligkeitsdatum eingeben!")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount")
    private double amount;

    @NotBlank(message = "Cant be empty")
    @Column(name = "description", length = 5000)
    private String description;

    @ManyToOne
    @JsonIgnoreProperties(value = "claims")
    private Vertrag vertrag;

    @ManyToMany
    @JsonIgnoreProperties(value = "claims")
    @JoinTable(name = "claim_damageType", joinColumns = @JoinColumn(name = "claims_id"), inverseJoinColumns = @JoinColumn(name = "damageType_id"))
    private Set<DamageType> damageTypes = new HashSet<>();

}
