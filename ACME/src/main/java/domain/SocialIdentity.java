package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {
    private String nickname;
    private String socialNet;
    private String link;
    private String picture;

    //@NotBlank
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //@NotBlank
    public String getSocialNet() {
        return socialNet;
    }

    public void setSocialNet(String socialNet) {
        this.socialNet = socialNet;
    }

    //@NotBlank
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
