package services;

import domain.Comment;
import domain.Others;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.OthersRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class OthersService {

    // Constructors--------------------------------------------------------------------------------------

    public OthersService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private OthersRepository othersRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------


    public Collection<Others> findAll() {

        Collection<Others> res = othersRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Others findOne(int Others) {
        Others res = othersRepository.findOne(Others);
        Assert.notNull(res);
        return res;
    }

    public Others save(Others a) {
        Assert.notNull(a);
        Others res = othersRepository.save(a);
        return res;
    }

    public void delete(Others a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        othersRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

    Others findByPrincipal() {
        Others result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    Others findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Others result;

        result = othersRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

    void followOther(Others r) {
        Assert.notNull(r);
        Others u = findByPrincipal();
        Assert.notNull(u);
        r.getFollowers().add(u);
    }

    void unfollowOther(Others r) {
        Assert.notNull(r);
        Others u = findByPrincipal();
        Assert.notNull(u);
        //Lo mismo que arriba
        r.getFollowers().remove(u);
    }

    public Collection<Recipe> getRecipesOfFollowers() {
        Collection<Recipe> res = new ArrayList<>();
        Others u = findByPrincipal();
        List<Others> followers = new ArrayList<>(othersRepository.getFollowers(u.getId()));
        for (Others o : followers) {
            if (o.getUserAccount().getAuthorities().contains(Authority.USER)) {
                res.addAll(othersRepository.getMyRecipes(o.getId()));
            }

        }
        return res;
    }

    void likeRecipe(Recipe r) {
        Assert.notNull(r);
        Others u = findByPrincipal();
        Assert.notNull(u);
        if (u.getUserAccount().getAuthorities().contains(Authority.USER)) {
            userService.rateRecipeWithLike(r);
        } else {
            Boolean like = new Boolean(true);
            Assert.isTrue(!u.getLikes().contains(r), "Ya has valorado esta receta");
            r.getRate().add(like);
            u.getLikes().add(r);

        }
    }

    void dislikeRecipe(Recipe r) {
        Assert.notNull(r);
        Others u = findByPrincipal();
        Assert.notNull(u);
        if (u.getUserAccount().getAuthorities().contains(Authority.USER)) {
            userService.rateRecipeWithDislike(r);
        } else {
            Boolean dislike = new Boolean(false);
            Assert.isTrue(!u.getDislikes().contains(r), "Ya has despreciado a esta receta");
            r.getRate().add(dislike);
            u.getDislikes().add(r);
        }
    }

    Comment writeCommentToRecipe(Recipe r, String title, String text, Integer numberOfstarts) {
        Assert.notNull(r);
        Assert.notNull(title);
        Assert.notNull(text);
        Assert.notNull(numberOfstarts);

        Comment c = commentService.create();
        c.setTitle(title);
        c.setText(text);
        c.setNumberOfStars(numberOfstarts);
        Comment saved = commentService.save(c);
        r.getComments().add(saved);
        return saved;
    }
}



