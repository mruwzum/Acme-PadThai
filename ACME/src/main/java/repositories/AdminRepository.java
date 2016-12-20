package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
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


    //Dashboard  C  -------------------------


    @Query("select min(u.recipes.size) from User u")
    Double minumRecipesOfUser();

    @Query("select avg (u.recipes.size) from User u")
    Double averageRecipesOfUser();

    @Query("select max(u.recipes.size) from User u")
    Double maximumRecipesOfUser();

    @Query("select u from User u where u.recipes.size = (select max(w.recipes.size) from User w)")
    User getUserWhoAuthoredMoreRecipes();

    @Query("select min(s.rate.size) from Recipe s group by s")
    Collection<Integer> minimumNumberOfRecipesQualifiedForAContest();

    @Query("select avg(s.rate.size) from Recipe s group by s")
    Collection<Integer> averageNumberOfRecipesQualifiedForAContest();

    @Query("select max(s.rate.size) from Recipe s group by s")
    Collection<Integer> maximumNumberOfRecipesQualifiedForAContest();

    @Query("select a from Contest a where a.winners.size = (select max(aa.winners.size) from Contest aa)")
    Collection<Contest> contestForWhichMoreRecipesHasQualified();

    @Query("select avg(r.stepsToCook.size) from Recipe r")
    Double averageOfStepsPerRecipe();

    @Query("select stddev(r.stepsToCook.size) from Recipe r")
    Double standartDeviationOfNumberOfStepsPerRecipe();

    @Query("select avg(r.ingredient.size) from Recipe r")
    Double averageOfIngredientsPerRecipe();

    @Query("select stddev(r.ingredient.size) from Recipe r")
    Double standartDeviationOfNumberOfIngredientsPerRecipe();

    @Query("select c from User c order by c.followers.size desc")
    Collection<User> usersInDescendingPopularity();

    @Query("select a from User a order by coalesce((select avg(b.rate.size) from Recipe b where b.user=a), 0) desc")
    Collection<User> usersInDescendingOrderByAverageOfLikesPerRecipe();


    //Dashboard  B  -------------------------

   @Query("select min(u.campaign.size) from Sponsor u")
   Collection<Integer> minimumNumberOfCampaignsPerSponsor();

   @Query("select avg(u.campaign.size) from Sponsor u")
   Collection<Integer> averageNumberOfCampaignsPerSponsor();

   @Query("select max (u.campaign.size) from Sponsor u")
   Collection<Integer> maximumNumberOfCampaignsPerSponsor();

    @Query("select min(s.campaign.size) from Sponsor s group by s")
    Collection<Integer> minumumNumberOfActiveCampaignsPerSponsor();

    @Query("select max(s.campaign.size) from Sponsor s group by s")
    Collection<Integer> maximumNumberOfActiveCampaignsPerSponsor();

    @Query("select avg(s.campaign.size) from Sponsor s group by s")
    Collection<Integer> averageNumberOfActiveCampaignsPerSponsor();

    @Query("select max(s.campaign.size) from Sponsor s group by s")
    Collection<Integer> maxnumberOfCampaignsPerSponsor();

    @Query("select c.cost from MonthlyBill c order by c.cost")
    Collection<Integer> biggestCostesOfMonthlyBills();

    @Query("select avg(r.paid),stddev(r.paid) from MonthlyBill r")
    Collection<Integer> averageAndStandartDevOfPaidBills();

    @Query("select s from Sponsor s join s.campaign c where datediff(current_date, c.endDate) >= 91")
    Sponsor sponsorHowHasntMangaedAnyCampaingInLastTreemonths();

    @Query("select b.sponsor.nameOfCompany from MonthlyBill b where b.cost >=(select max(b.cost)*0.9 from MonthlyBill b)")
    String nombreCompaniaQueHaGastadoMenosDeLaMediaEnSusCampanas();

    @Query("select b.sponsor.nameOfCompany from MonthlyBill b where (select sum(b.cost) from MonthlyBill b) < (select avg(b.cost) from MonthlyBill b)")
    String nameOfCompanymenos90porciento();
    //Dashboard  A  -------------------------

    @Query("select min(u.masterClass.size),avg(u.masterClass.size),max(u.masterClass.size),stddev(u.masterClass.size) from Cook u")
    Collection<Integer> minumumandmaximumandaverageNumberOfMasterClassesPerCook();

    @Query("select avg(u.material.size) from MasterClass u group by u order by u.material.size ASC")
    Collection<Integer> averageOfLearningMaterialsPerMasterClass();

    @Query("select sum(u.masterClass.size) from Admin u")
    Long numberOfPromotedMasterClasses();

    @Query("select c from Cook c group by c order by sum(c.masterClass.size)")
    Collection<Cook> cookByPromotedMasterClasses();

    @Query("select avg(u.masterClass.size) from Cook u where u.masterClass=u.masterClass")
    Collection<Integer> averageOfMasterClassesPromotedByAdmin();
}


