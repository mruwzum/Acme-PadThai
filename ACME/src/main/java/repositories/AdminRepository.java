package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("select c from Admin c where c.userAccount.id = ?1")
    Admin findByUserAccountId(int userAccountId);

    @Query("select u.categorie from Admin u")
    Collection<Categorie> getCategories();

    @Query("select u.contest from Admin u")
    Collection<Contest> getConstests();

    @Query("select u from Recipe u order by u.likesNumber")
    Collection<Recipe> getRecipesOrderByRate();


    //Dashboard    -------------------------


    @Query("select min(u.recipes.size) from User u where u.id = ?1")
    Double minumRecipesOfUser(int userID);

    @Query("select avg (u.recipes.size) from User u where u.id = ?1")
    Double averageRecipesOfUser(int userID);

    @Query("select max(u.recipes.size) from User u where u.id = ?1")
    Double maximumRecipesOfUser(int userID);

    @Query("select u from User u where u.recipes.size = (select max(w.recipes.size) from User w)")
    User getUserWhoAuthoredMoreRecipes();

    @Query("select min(s.rate.size) from Recipe s group by s")
    Double minimumNumberOfRecipesQualifiedForAContest();

    @Query("select avg(s.rate.size) from Recipe s group by s")
    Double averageNumberOfRecipesQualifiedForAContest();

    @Query("select max(s.rate.size) from Recipe s group by s")
    Double maximumNumberOfRecipesQualifiedForAContest();

    @Query("select a from Contest a where a.winners.size = (select max(aa.winners.size) from Contest aa)")
    Contest contestForWhichMoreRecipesHasQualified();

    @Query("select avg(r.stepsToCook.size) from Recipe r")
    Double averageOfStepsPerRecipe();

    @Query("select stddev(r.stepsToCook.size) from Recipe r")
    Double standartDeviationOfNumberOfStepsPerRecipe();

    @Query("select avg(r.ingredient.size) from Recipe r")
    Double averageOfIngredientsPerRecipe();

    @Query("select stddev(r.ingredient.size) from Recipe r")
    Double standartDeviationOfNumberOfIngredientsPerRecipe();

    @Query("select c, c.followers.size from User c order by c.followers.size desc")
    Collection<User> usersInDescendingPopularity();

    @Query("select a from User a order by coalesce((select avg(b.rate.size) from Recipe b where b.user=a), 0) desc")
    Collection<User> usersInDescendingOrderByAverageOfLikesPerRecipe();
}

