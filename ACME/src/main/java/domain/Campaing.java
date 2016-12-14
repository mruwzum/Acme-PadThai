package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Campaing extends DomainEntity {

    private Date startDate;

    private Date endDate;

    private Integer numberOfBanners;

    private Integer maximumDisplayed;

    private Sponsor sponsor;

    private Double bannerCost;

    public Campaing() {
        super();
    }


    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @NotNull
    public Integer getNumberOfBanners() {
        return numberOfBanners;
    }

    public void setNumberOfBanners(Integer numberOfBanners) {
        this.numberOfBanners = numberOfBanners;
    }

    @NotNull
    public Integer getMaximumDisplayed() {
        return maximumDisplayed;
    }

    public void setMaximumDisplayed(Integer maximumDisplayed) {
        this.maximumDisplayed = maximumDisplayed;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


    public Double getBannerCost() {
        return bannerCost;
    }

    public void setBannerCost(Double bannerCost) {
        this.bannerCost = bannerCost;
    }

}
