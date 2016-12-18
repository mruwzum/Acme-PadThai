package repositories;

import domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select u from Ingredient u")
    Collection<Ingredient> findAllIngredient();
}
