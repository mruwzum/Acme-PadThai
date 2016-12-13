package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Others extends Actor {

    private Collection<Others> followers;
    private Collection<Comment> comments;
    private Collection<Recipe> likes;

    //No podemos poner ni following ni dislike porque traen problemas con las IDs.

    private Collection<Recipe> dislikes;

    @OneToMany(targetEntity = Comment.class)
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }


    @OneToMany(targetEntity = Others.class, cascade = CascadeType.ALL)
    public Collection<Others> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<Others> followers) {
        this.followers = followers;
    }


    @OneToMany(targetEntity = Recipe.class, cascade = CascadeType.ALL)
    public Collection<Recipe> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Recipe> likes) {
        this.likes = likes;
    }

    @OneToMany(targetEntity = Recipe.class, cascade = CascadeType.ALL)
    public Collection<Recipe> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Collection<Recipe> dislikes) {
        this.dislikes = dislikes;
    }
}
