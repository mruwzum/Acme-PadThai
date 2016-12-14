package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor {

    private String nameOfCompany;

    private CreditCard creditCard;

    private Collection<Campaing> campaign;

    @NotBlank
    public String getNameOfCompany() {
        return nameOfCompany;
    }

    @NotBlank
    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    @NotNull
    @OneToOne(targetEntity = CreditCard.class, cascade = CascadeType.ALL)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    @NotBlank
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToMany(targetEntity = Campaing.class, cascade = CascadeType.ALL)
    public Collection<Campaing> getCampaign() {
        return campaign;
    }

    public void setCampaign(Collection<Campaing> campaign) {
        this.campaign = campaign;
    }

}
