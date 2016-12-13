package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

    private String name;

    private String surname;

    private String emailAddress;

    private String phone;

    private String postalAddress;

    private SocialIdentity socialIdentity;

    private Collection<Message> message;

    private Collection<Folder> folders;


    @NotBlank
    public String getName() {
        return name;
    }

    @NotBlank
    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getSurname() {
        return surname;
    }

    @NotBlank
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Pattern(regexp = "(\\+\\d{1,3})?(\\(\\d{3}\\))?([0-9a-zA-z][ -]?){4,}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAdress) {
        this.postalAddress = postalAdress;
    }

    @OneToOne
    public SocialIdentity getSocialIdentity() {
        return socialIdentity;
    }

    public void setSocialIdentity(SocialIdentity socialIdentity) {
        this.socialIdentity = socialIdentity;
    }

    @OneToMany
    public Collection<Message> getMessage() {
        return message;
    }

    public void setMessage(Collection<Message> message) {
        this.message = message;
    }

    @OneToMany(targetEntity = Folder.class)
    @NotEmpty
    public Collection<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Collection<Folder> folders) {
        this.folders = folders;
    }

    private UserAccount userAccount;

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


}