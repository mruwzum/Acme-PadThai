package repositories;

import domain.Admin;
import domain.Categorie;
import domain.Contest;
import domain.Recipe;
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


}

