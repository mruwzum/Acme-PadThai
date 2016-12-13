package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity {

    private String name;
    private Quantity quantity;

    public Property() {
        super();
    }

    @NotBlank
    @GeneratedValue(strategy = GenerationType.TABLE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

}
