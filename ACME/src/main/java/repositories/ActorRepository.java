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
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query("select c from Actor c where c.userAccount.id = ?1")
    User findByUserAccountId(int userAccountId);

    //Actor who isn't authenticated
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

    @Query("select u from Recipe u where u.title = ?1")
    Recipe findRecipeByTitle(String name);

    @Query("select u from Recipe u where u.summary = ?1")
    Recipe findRecipeBySummary(String summary);

    @Query("select r.user from Recipe r where r.id = ?1")
    User getUserofRecipe(int rid);

    @Query("select u.recipes from User u where u.id = ?1")
    Collection<Recipe> getRecipesofUser(int uid);

    @Query("select c.winners from Contest c where c.id = ?1")
    Collection<Recipe> getQualifyRecipeforContest(int cid);

    @Query("select u.folders from Actor u where u.id = ?1")
    Collection<Folder> getFolder(int aci);

    @Query("select u.cook, u.title, u.description from MasterClass u")
    Collection<MasterClass> getMasterClasesRestricted();

}
