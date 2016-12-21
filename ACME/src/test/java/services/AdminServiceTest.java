package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.SystemPropertyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by daviddelatorre on 12/11/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class AdminServiceTest extends AbstractTest {


    //Service under test ---------------------------------------------------------------------

    @Autowired
    private AdminService adminService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private CampaingService campaingService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private MasterClassService masterClass;

    @Test
    public void findByPrincipal() throws Exception {

        Admin u;
        authenticate("Admin2");
        u = adminService.findByPrincipal();
        System.out.println(u.getName());
        authenticate(null);
    }

    @Test
    public void createKeyword() throws Exception {

    }

    @Test
    public void createCategorie() throws Exception {

        authenticate("Admin2");
        String name = "PRUEBA";
        String description = "Prueba de categoria";
        String pic = "asdadsa";
        Collection<String> collection = new ArrayList<String>();
        Categorie res = adminService.createCategorie(name, description, pic, collection);
        System.out.println(res);
        authenticate(null);

    }

    @Test
    public void getAllCateogories() throws Exception {
        authenticate("Admin2");
        Collection<Categorie> res = adminService.getAllCateogories();
        System.out.println(res);
        authenticate(null);


    }

    @Test
    public void modifyCategorie() throws Exception {

        authenticate("Admin2");
        List<Categorie> categories = new ArrayList<>(categorieService.findAll());
        Collection<String> tags = new ArrayList<>();
        Categorie res = adminService.modifyCategorie(categories.get(0), "PRUEBA MOD", "Prueba de modificacion", "pic", tags);
        System.out.println(res.getName());
        authenticate(null);

    }

    @Test
    public void deleteCategorie() throws Exception {
        authenticate("Admin1");
        List<Categorie> categories = new ArrayList<>(categorieService.findAll());

        try {
            adminService.deleteCategorie(categories.get(1));

        } catch (IllegalArgumentException e) {
            System.out.println("Existe una receta con esta categoria");

        }
        authenticate(null);

    }

    @Test
    public void createContest() throws Exception {

        authenticate("Admin1");

        String title = "Prueba creacion";
        Date oppening = new Date(2017, 01, 22);
        Date clossing = new Date(2017, 02, 22);
        Contest c = adminService.createContest(title, oppening, clossing);
        System.out.println(c);


        authenticate(null);
    }

    @Test
    public void getAllContest() throws Exception {
        authenticate("Admin2");
        Collection<Contest> res = adminService.getAllContest();
        System.out.println(res);
        authenticate(null);

    }

    @Test
    public void modifyContest() throws Exception {
        authenticate("Admin2");
        List<Contest> contest = new ArrayList<>(contestService.findAll());
        Date clossing = new Date(2017, 03, 22);
        Contest res = adminService.modifyContest(contest.get(0), clossing);
        System.out.println(res.getClosingDate());
        authenticate(null);

    }

    @Test
    public void deleteContest() throws Exception {

        authenticate("Admin1");
        List<Contest> contest = new ArrayList<>(contestService.findAll());

        try {
            adminService.deleteContest(contest.get(1));

        } catch (IllegalArgumentException e) {
            System.out.println("Existen recetas dentro de este concurso, no puede ser borrado");

        }
        authenticate(null);

    }


    @Test
    public void computeWinners() throws Exception {

        authenticate("Admin1");
        List<Contest> aux = new ArrayList<>(contestService.findAll());
        adminService.computeWinners(aux.get(0));
        System.out.println(aux.get(0).getWinners());
        authenticate(null);

    }

    @Test
    public void testSetBannerCost() {
        authenticate("Admin3");
        Admin u = adminService.findByPrincipal();
        Assert.notNull(u, "Usuario invalido");
        Double cost = 23.7;
        List<Campaing> aux = new ArrayList<>(campaingService.findAll());
        Assert.notNull(aux);
        Campaing res = aux.get(1);
        res.setBannerCost(cost);

        authenticate(null);
    }

    @Test
    public void testComputeMonthlyBills() {

        authenticate("Admin1");
        Sponsor u = sponsorService.findOne(119);
        List<Sponsor> spon = new ArrayList<>(sponsorService.findAll());
        // Assert.notNull(u,"El usuario es nulo");
        MonthlyBill res = adminService.computeMonthlyBills(spon.get(0));
        Assert.notNull(res, "Monthlybill vacios");
        authenticate(null);
    }

    @Test
    public void testSendBulkMessage() {
        authenticate("Admin3");
        adminService.sendBulkMessage();
        authenticate(null);
    }

    @Test
    public void testRegisterAsACook() {
        authenticate("Admin3");
        Cook a = adminService.registerAsACook();
        System.out.println(a);
        authenticate(null);
    }

    @Test
    public void testPromoteMasterClass() {
        authenticate("Admin3");
        List<MasterClass> masterClasses = new ArrayList<>(masterClass.findAll());
        adminService.promoteMasterClas(masterClasses.get(1));
        authenticate(null);
    }

    @Test
    public void testDemoteMasterClass() {

        authenticate("Admin3");
        List<MasterClass> masterClasses = new ArrayList<>(masterClass.findAll());
        adminService.promoteMasterClas(masterClasses.get(1));
        adminService.demoteMasterClass(masterClasses.get(1));
        authenticate(null);
    }
    @Test
    public void testRandom(){

        System.out.println("-----------------------------------------------------");

        Double minRecOf = adminService.minimumRecipesOfUser();
        Double avgRecOf = adminService.averageRecipesOfUser();
        Double maxRecOf = adminService.maximumRecipesOfUser();
        System.out.println(minRecOf); System.out.println(avgRecOf); System.out.println(maxRecOf);

        System.out.println("-----------------------------------------------------");

        User more = adminService.getUserWhoAuthoredMoreRecipes();
        System.out.println(more.getName());

        System.out.println("-----------------------------------------------------");

        Collection<Integer> minRecOf2 = adminService.minimumNumberOfRecipesQualifiedForAContest();
        Collection<Integer> avgRecOf2 = adminService.averageNumberOfRecipesQualifiedForAContest();
        Collection<Integer> maxRecOf2 = adminService.maximumNumberOfRecipesQualifiedForAContest();
        System.out.println(minRecOf2); System.out.println(avgRecOf2); System.out.println(maxRecOf2);

        System.out.println("-----------------------------------------------------");

       Collection<Contest> cont = adminService.contestForWhichMoreRecipesHasQualified();
        System.out.println(cont);

        System.out.println("-----------------------------------------------------");

        Double avgStepsPerRecipe = adminService.averageOfStepsPerRecipe();
        Double stdevStepsPerRecipe = adminService.standartDeviationOfNumberOfStepsPerRecipe();
        Double avgOfIngrPerRecipe = adminService.averageOfIngredientsPerRecipe();
        Double stddevOfIngredientsPerRecipe = adminService.standartDeviationOfNumberOfIngredientsPerRecipe();
        System.out.println(avgStepsPerRecipe); System.out.println(stdevStepsPerRecipe);
        System.out.println(avgOfIngrPerRecipe);System.out.println(stddevOfIngredientsPerRecipe);

        System.out.println("-----------------------------------------------------");

        Collection<User> usersInDescendingByAverageOfLikes = new ArrayList<>(adminService.usersInDescendingOrderByAverageOfLikesPerRecipe());
        System.out.println(usersInDescendingByAverageOfLikes);

        Collection<User>  usersInDescendingOrderByPopularity = new ArrayList<>(adminService.usersInDescendingPopularity());
        System.out.println(usersInDescendingOrderByPopularity);
    }
}