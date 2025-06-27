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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "damageType")
@Getter
@Setter
public class DamageType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Cant be empty")
    @Column(name = "type", length = 5000)
    private String type;

    @ManyToMany(mappedBy = "damageTypes")
    @JsonIgnoreProperties(value = {"damageTypes", "vertrag", "customer", "claims", "payments", "policen"})
    private Set<Claim> claims = new HashSet<>();
}
