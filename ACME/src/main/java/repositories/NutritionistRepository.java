package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {
    @Query("select c from Nutritionist c where c.userAccount.id = ?1")
    Nutritionist findByUserAccountId(int userAccountId);

    @Query("select r from Recipe r group by r.categorie")
    Collection<Recipe> findAllRecipeGroupByCategorie();

    @Query("select u from User u")
    Collection<User> findAllUser();

    @Query("select c from Contest c")
    Collection<Contest> findAllContest();

    @Query("select r from Recipe r where r.ticker = ?1")
    Recipe findRecipeByTicker(String ticker);

    @Query("select u from User u where u.name = ?1")
    User findUserByName(String name);

    @Query("select u.curricula from Nutritionist u where u = ?1")
    Curricula getCurricula(Nutritionist a);

    @Query("select u.ingredient from IngredientsRepository u")
    Collection<Ingredient> getIngredients();
}
