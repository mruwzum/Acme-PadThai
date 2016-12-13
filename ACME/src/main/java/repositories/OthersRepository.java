package repositories;

import domain.Others;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface OthersRepository extends JpaRepository<Others, Integer> {


    @Query("select c from Others c where c.userAccount.id = ?1")
    Others findByUserAccountId(int userAccountId);

    @Query("select c.followers from Others  c where c.id = ?1")
    Collection<Others> getFollowers(int id);

    @Query("select c.recipes from User c where c.id = ?1")
    Collection<Recipe> getMyRecipes(int id);
}
