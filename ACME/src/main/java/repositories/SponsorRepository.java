package repositories;

import domain.Campaing;
import domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 */

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

    @Query("select c from Sponsor c where c.userAccount.id = ?1")
    Sponsor findByUserAccountId(int userAccountId);

    @Query("select c.campaign from Sponsor  c where  c.id  = ?1")
    Collection<Campaing> getMyCampaings(int id);
}
