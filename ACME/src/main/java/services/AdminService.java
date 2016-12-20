package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class AdminService {

    // Constructors--------------------------------------------------------------------------------------

    public AdminService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private AdminRepository adminRepository;


    // Suporting services --------------------------------------------------------------------------------
    @Autowired
    private LoginService loginService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private MonthlyBillService monthlyBillService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private CookService cookService;
    @Autowired
    private UserService userService;

    // Simple CRUD method --------------------------------------------------------------------------------

    public Admin create() {
        Admin res;
        res = new Admin();
        return res;
    }

    public Collection<Admin> findAll() {

        Collection<Admin> res;
        res = adminRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Admin findOne(int Admin) {

        Admin res;
        res = adminRepository.findOne(Admin);
        Assert.notNull(res);
        return res;
    }

    public Admin save(Admin a) {

        Assert.notNull(a);
        Admin res;
        res = adminRepository.save(a);
        return res;
    }

    public void delete(Admin a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        adminRepository.delete(a);
    }




    // Dashboard  C -------------------------------------------------------

    public Double minimumRecipesOfUser(){
       Double res = adminRepository.minumRecipesOfUser();
       return res;
    }

    public Double averageRecipesOfUser(){
        Double res = adminRepository.averageRecipesOfUser();
        return res;
    }
    public Double maximumRecipesOfUser(){
        Double res = adminRepository.maximumRecipesOfUser();
        return res;
    }
    public User getUserWhoAuthoredMoreRecipes(){
        User res = adminRepository.getUserWhoAuthoredMoreRecipes();
        return res;
    }
    public  Collection<Integer> minimumNumberOfRecipesQualifiedForAContest(){
        Collection<Integer> res = adminRepository.minimumNumberOfRecipesQualifiedForAContest();
        return res;
    }
    public  Collection<Integer> averageNumberOfRecipesQualifiedForAContest(){
        Collection<Integer> res = adminRepository.averageNumberOfRecipesQualifiedForAContest();
        return res;
    }
    public  Collection<Integer> maximumNumberOfRecipesQualifiedForAContest(){
        Collection<Integer> res = adminRepository.maximumNumberOfRecipesQualifiedForAContest();
        return res;
    }
    public Collection<Contest> contestForWhichMoreRecipesHasQualified(){
        Collection<Contest> res = adminRepository.contestForWhichMoreRecipesHasQualified();
        return res;
    }
   public  Double averageOfStepsPerRecipe(){
       Double res = adminRepository.averageOfStepsPerRecipe();
       return res;
    }
    public Double standartDeviationOfNumberOfStepsPerRecipe(){
       Double res = adminRepository.standartDeviationOfNumberOfStepsPerRecipe();
       return res;
    }
    public Double averageOfIngredientsPerRecipe(){
        Double res = adminRepository.averageOfIngredientsPerRecipe();
        return res;
    }
    public Double standartDeviationOfNumberOfIngredientsPerRecipe(){
        Double res = adminRepository.standartDeviationOfNumberOfIngredientsPerRecipe();
        return res;
    }
    public Collection<User> usersInDescendingPopularity(){
        Collection<User> res = adminRepository.usersInDescendingPopularity();
        return res;
    }
    public  Collection<User> usersInDescendingOrderByAverageOfLikesPerRecipe(){
        Collection<User> res =  adminRepository.usersInDescendingOrderByAverageOfLikesPerRecipe();
        return res;
    }

    //DASHBOARD B  -----------------------------

    public Collection<Integer> minimumNumberOfCampaignsPerSponsor(){
        return adminRepository.minimumNumberOfCampaignsPerSponsor();
    }

    public Collection<Integer> averageNumberOfCampaignsPerSponsor(){
        return adminRepository.averageNumberOfCampaignsPerSponsor();
    }

    public Collection<Integer> maximumNumberOfCampaignsPerSponsor(){
        return adminRepository.maximumNumberOfCampaignsPerSponsor();
    }

    public Collection<Integer> minumumNumberOfActiveCampaignsPerSponsor(){
        return adminRepository.minumumNumberOfActiveCampaignsPerSponsor();
    }

    public Collection<Integer> maximumNumberOfActiveCampaignsPerSponsor(){
        return adminRepository.maximumNumberOfActiveCampaignsPerSponsor();
    }

    public Collection<Integer> averageNumberOfActiveCampaignsPerSponsor(){
        return adminRepository.averageNumberOfActiveCampaignsPerSponsor();
    }

    public  Collection<Integer> maxnumberOfCampaignsPerSponsor(){
        return adminRepository.maxnumberOfCampaignsPerSponsor();
    }

    public Collection<Integer> biggestCostesOfMonthlyBills(){
       return adminRepository.biggestCostesOfMonthlyBills();
    }

    public Collection<Integer> averageAndStandartDevOfPaidBills(){
        return adminRepository.averageAndStandartDevOfPaidBills();
    }

    public Sponsor sponsorHowHasntMangaedAnyCampaingInLastTreemonths(){
        return adminRepository.sponsorHowHasntMangaedAnyCampaingInLastTreemonths();
    }

    public  String nombreCompañiaQueHaGastadoMenosDeLaMediaEnSusCampañas(){
        return adminRepository.nombreCompañiaQueHaGastadoMenosDeLaMediaEnSusCampañas();
    }

    public  String nameOfCompanymenos90porciento(){
        return adminRepository.nameOfCompanymenos90porciento();
    }


    // Other business methods -------------------------------------------------------------------------------


    public Admin findByPrincipal() {
        Admin result;
        UserAccount userAccount;

        userAccount = loginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Admin findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Admin result;

        result = adminRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

    public void createKeyword(String key) {
        Admin a = findByPrincipal();
        Assert.notNull(a, "El actor no existe");
        Assert.isTrue(!a.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        a.getKeywor().add(key);
    }

    public Collection<String> getKeyword() {
        Admin a = findByPrincipal();
        Assert.notNull(a, "El actor no existe");
        //Assert.isTrue(!a.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Collection<String> keywor = Collections.EMPTY_SET;
        keywor.add("sex");
        keywor.add("viagra");
        keywor.add("cialis");
        keywor.add("love");
        return keywor;

    }

    public Categorie createCategorie(String name, String description, String picture, Collection<String> tags) {

        Assert.notNull(name);
        Assert.notNull(description);
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Categorie aux = categorieService.create();
        aux.setName(name);
        aux.setDescription(description);
        aux.setPicture(picture);
        aux.setTag(tags);
        Categorie res = categorieService.save(aux);
        u.getCategorie().add(res);
        return res;
    }

    public Collection<Categorie> getAllCateogories() {
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Collection<Categorie> res = categorieService.findAll();
        return res;
    }

    public Categorie modifyCategorie(Categorie c, String name, String description, String picture,
                                     Collection<String> tags) {
        Assert.notNull(name, "El nombre no puede ser nulo");
        Assert.notNull(description, "La descripcion no puede ser nula");
        Assert.notNull(c, "La categoria no puede ser nula");
        Admin u;
        u = findByPrincipal();
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(categorieService.findAll().contains(c), "La categoria no existe");
        c.setName(name);
        c.setDescription(description);
        c.setPicture(picture);
        c.getTag().addAll(tags);
        return c;
    }

    public void deleteCategorie(Categorie c) {
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(categorieService.findAll().contains(c), "La categoria no existe");
        Assert.isTrue(!recipesContainstCategorie(c), "No se puede borrar la categoría porque existe una receta con ella");
        categorieService.delete(c);

    }

    private Boolean recipesContainstCategorie(Categorie c) {
        Boolean res = false;
        Collection<Recipe> aux = recipeService.findAll();
        for (Recipe r : aux) {

            if (c.equals(r.getCategorie())) {
                return true;
            }
        }
        return res;
    }

    public Contest createContest(String title, Date oppening, Date closing) {
        Assert.notNull(title);
        Assert.notNull(oppening);
        Assert.notNull(closing);
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Contest aux = contestService.create();
        aux.setTitle(title);
        aux.setOppeningDate(oppening);
        aux.setClosingDate(closing);
        Contest res = contestService.save(aux);
        return res;
    }

    public Collection<Contest> getAllContest() {
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Collection<Contest> res = contestService.findAll();
        return res;
    }

    public Contest modifyContest(Contest c, Date closing) {

        Assert.notNull(closing);
        Assert.notNull(c);
        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(closing.after(new Date(System.currentTimeMillis() - 100)), "La fecha es incorrecta");
        c.setClosingDate(closing);
        return c;
    }

    public void deleteContest(Contest c) {

        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(u.getContest().contains(c), "El concurso introducido no existe");
        Assert.isTrue(!c.getWinners().isEmpty(), "El concurso no puede ser eliminado porque contiene recetas");
        contestService.delete(c);

    }

    public void computeWinners(Contest c) {

        Admin u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        List<Recipe> orderRecipes = new ArrayList<>(adminRepository.getRecipesOrderByRate());
        Assert.notNull(orderRecipes.get(0));
        Assert.notNull(orderRecipes.get(1));
        Assert.notNull(orderRecipes.get(2));
        Collection<Recipe> winners = new ArrayList<Recipe>();
        winners.add(orderRecipes.get(0));
        winners.add(orderRecipes.get(1));
        winners.add(orderRecipes.get(2));
        System.out.println(orderRecipes);
        c.setWinners(winners);

    }

    public Campaing setBannerCost(Campaing a, Double cost) {
        Assert.notNull(a);
        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.notNull(cost);
        a.setBannerCost(cost);
        return a;

    }

    public MonthlyBill computeMonthlyBills(Sponsor sponsor) {

        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Map<Campaing, Double> map = new HashMap<>();
        Double sum = 0.0;
        Assert.notNull(sponsor, "El sponsor es nulo");
        List<Campaing> a = new ArrayList<>(sponsor.getCampaign());
        for (Campaing i : a) {
            sum = sum + i.getNumberOfBanners() * i.getBannerCost();

            map.put(i, sum);
        }
        Date create = new Date(System.currentTimeMillis() - 1000);
        MonthlyBill aux = monthlyBillService.create();
        aux.setCost(sum);
        aux.setCreateDate(create);
        aux.setDescription(map.toString());
        aux.setSponsor(sponsor);
        MonthlyBill res = monthlyBillService.save(aux);
        return res;
    }


    public Message sendBulkMessage() {

        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        List<MonthlyBill> morosos = new ArrayList<>(monthlyBillService.findAll());
        Message res = messageService.create();
        for (MonthlyBill s : morosos) {
            if (s.getPaid() == false && s.getCreateDate().getTime() - new Date(System.currentTimeMillis()).getTime() >= 259200000) {
                Sponsor p = s.getSponsor();
                res = actorService.textMessage("PAY ME", "YOU HAVE TONES OF UNPAID BILLS", p, Priority.HIGH);
                return res;
            }
        }
        return res;
    }

    //A-Level

    public Cook registerAsACook() {
        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Cook aux = cookService.create();
        UserAccount usera = new UserAccount();
        Collection<Authority> authorities = null;
        Authority cook = new Authority();
        cook.setAuthority(Authority.COOK);
        usera.setAuthorities(authorities);
        aux.setUserAccount(usera);
        Cook res = cookService.save(aux);
        return res;
    }


    public void promoteMasterClas(MasterClass m) {
        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(!m.getPromoters().contains(u), "El administrador ya ha promocionado esta clase");
        m.getPromoters().add(u);
    }

    public void demoteMasterClass(MasterClass m) {
        Admin u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(!u.getUserAccount().getAuthorities().contains(Authority.ADMIN));
        Assert.isTrue(m.getPromoters().contains(u), "El administrador ya ha promocionado esta clase");
        m.getPromoters().remove(u);
    }

}




