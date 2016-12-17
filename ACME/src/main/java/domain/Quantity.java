package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity {

    private Double ammount;
    private String currencyUnit;


    @NotNull
    public Double getAmmount() {
        return ammount;
    }


    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    @NotNull
    public String getCurrencyUnit() {
        return currencyUnit;
    }


    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "ammount=" + ammount +
                ", currencyUnit='" + currencyUnit + '\'' +
                '}';
    }
}
