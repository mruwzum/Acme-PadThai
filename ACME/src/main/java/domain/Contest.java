package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity {

    private String title;

    private Date oppeningDate;

    private Date closingDate;

    private Collection<Recipe> winners;

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getOppeningDate() {
        return oppeningDate;
    }

    public void setOppeningDate(Date oppeningDate) {
        this.oppeningDate = oppeningDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }


    @OneToMany(targetEntity = Recipe.class)
    public Collection<Recipe> getWinners() {
        return winners;
    }

    public void setWinners(Collection<Recipe> winners) {
        this.winners = winners;

    }

}