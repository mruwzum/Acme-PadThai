package repositories;

import domain.Cook;
import domain.MasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {

    @Query("select c from Cook c where c.userAccount.id = ?1")
    Cook findByUserAccountId(int userAccountId);

    @Query("select c.masterClass from Cook c where c.id = ?1")
    Collection<MasterClass> getMyMasterClasses(int id);

}
