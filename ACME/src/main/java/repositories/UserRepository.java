package repositories;

import domain.Recipe;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select c from User c where c.userAccount.id = ?1")
    User findByUserAccountId(int userAccountId);

    @Query("select c.recipes from User c where c.id = ?1")
    Collection<Recipe> getMyRecipes(int id);


}
