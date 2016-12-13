package repositories;

import domain.Campaing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface CampaingRepository extends JpaRepository<Campaing, Integer> {

}
