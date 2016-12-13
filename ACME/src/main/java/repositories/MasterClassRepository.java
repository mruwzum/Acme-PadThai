package repositories;

import domain.MasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer> {

}
