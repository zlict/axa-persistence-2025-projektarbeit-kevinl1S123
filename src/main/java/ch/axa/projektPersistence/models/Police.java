package ch.axa.projektPersistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "police")
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
    @JsonIgnoreProperties(value = "policen")
    private Vertrag vertrag;

}
