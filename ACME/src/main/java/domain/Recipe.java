package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity {

    private String ticker;

    private String title;

    private String summary;

    private Date creationDate;

    private Date updateDate;


    private Integer likesNumber;

    private Collection<String> pictures;

    private Collection<String> hints;

    private Collection<Boolean> rate;

    private User user;

    private Collection<Comment> comments;

    private Collection<Ingredient> ingredient;

    private Categorie categorie;

    private Collection<StepsToCook> stepsToCook;

    @NotNull
    @Pattern(regexp = "(((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|(0?[2469]|11)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))(0?[13578]|10|12)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))((-[a-z][a-z][a-z][a-z]))$")
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<String> pictures) {
        this.pictures = pictures;
    }

    @ElementCollection(targetClass = String.class)
    public Collection<String> getHints() {
        return hints;
    }

    public void setHints(Collection<String> hints) {
        this.hints = hints;
    }


    @OneToMany(targetEntity = Comment.class)
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @NotEmpty
    @OneToMany(targetEntity = Ingredient.class)
    public Collection<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Collection<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    @NotNull
    @OneToOne(targetEntity = Categorie.class)
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @NotNull
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotEmpty
    @OneToMany(targetEntity = StepsToCook.class, cascade = CascadeType.ALL)
    public Collection<StepsToCook> getStepsToCook() {
        return stepsToCook;
    }

    public void setStepsToCook(Collection<StepsToCook> stepsToCook) {
        this.stepsToCook = stepsToCook;
    }

    @NotEmpty
    @ElementCollection(targetClass = Boolean.class)
    public Collection<Boolean> getRate() {
        return rate;
    }

    public void setRate(Collection<Boolean> rate) {
        this.rate = rate;
    }


    public Integer getLikesNumber() {
        Integer res = 0;
        for (Boolean i : this.getRate()) {
            if (i) {
                res++;
            }

        }

        return res;
    }

    public void setLikesNumber(Integer likesNumber) {

        this.likesNumber = likesNumber;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ticker='" + ticker + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
