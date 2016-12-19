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

    @Query("select min(u.recipes.size) from User u where u.id = ?1")
    Double minumRecipesOfUser(int userID);

    @Query("select avg (u.recipes.size) from User u where u.id = ?1")
    Double averageRecipesOfUser(int userID);

    @Query("select max(u.recipes.size) from User u where u.id = ?1")
    Double maximumRecipesOfUser(int userID);

    @Query("select u from User u where u.recipes.size = (select max(w.recipes.size) from User w)")
    User getUserWhoAuthoredMoreRecipes();

}

