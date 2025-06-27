package ch.axa.projektPersistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "police")
@Getter
@Setter
public class Police {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    @Positive
    @Column(name = "amountInsured")
    private double amountInsured;

    @ManyToOne
    @JsonIgnoreProperties(value = {"policen", "vertrage", "claims", "payments"})
    private Vertrag vertrag;
}
