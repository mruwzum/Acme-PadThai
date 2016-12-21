package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 6/11/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class ActorServiceTest extends AbstractTest {


    //Service under test ---------------------------------------------------------------------

    @Autowired
    private ActorService actorService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private SocialIdentityService socialIdentityService;
    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private CookService cookService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private AdminService adminService;

    //Tests -------------------------------------------------

    @Test
    public void testfindAll() {

        List<Actor> res = new ArrayList<>(actorService.findAll());
        Assert.notEmpty(res, "La colección esta vacia");
        System.out.println("La lista de  actore guardados es" + res);


    }

    @Test
    public void testFindOne() {
        User a = userService.findOne(183);
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        Actor b = userService.findOne(a.getId());
        Assert.notNull(b);
    }

    @Test
    public void testDelete() {
        List<Actor> res = new ArrayList<>(actorService.findAll());

        Actor toDelete = res.get(3);
        actorService.delete(toDelete);

        System.out.println("El actor eliminado es" + toDelete);

    }

    @Test
    public void testFindByPrincipal() {

        Actor u;
        authenticate("User2");
        u = actorService.findByPrincipal();
        System.out.println(u.getName());
        authenticate(null);
    }

    @Test
    public void testRegisterAsUser() {

        String name = "Davdedia";
        String password = "34243242bbvj234bj24n342j34njn32jb5";
        Actor res = actorService.registerAsUser(name, password);
        System.out.println("El user creado es " + res.getUserAccount().getUsername());
        System.out.println("El user creado es " + res.getUserAccount().getPassword());
        System.out.println("El user creado es " + res.getUserAccount().getAuthorities().toString());


    }

    @Test
    public void testRegisterAsNutritionist() {

        String name = "Migantber";
        String password = "34243242bbvj234bj24n342j34nj832jb5";
        Actor res = actorService.registerAsNutritionist(name, password);
        System.out.println("El nutricionista creado es " + res.getUserAccount().getUsername());

    }

    @Test
    public void testRegisterAsSponsor() {

        String name = "Perri";
        String password = "34243242bbvj234bj24n342j34nj832jb5";
        Actor res = actorService.registerAsSponsor(name, password);
        System.out.println("El sponsor creado es " + res.getUserAccount().getUsername());

    }

    @Test
    public void testRegisterAsCook() {

        String name = "Arguiñano";
        String password = "34243242bbvj234bj24n342j34nj832jb5";
        Actor res = actorService.registerAsCook(name, password);
        System.out.println("El cocinero creado es " + res.getUserAccount().getUsername());

    }
    @Test
    public void testFindAllRecipesGroupByCategorie() {

        Collection<Recipe> res = actorService.findAllRecipeGroupByCategorie();
        System.out.println("Las recetas encontradas son" + res);

    }

    @Test
    public void testFindAllUser() {

        Collection<User> res = actorService.findAllUser();
        System.out.println("Los usuarios encontrados son" + res);

    }

    @Test
    public void testFindAllContest() {

        Collection<Contest> res = actorService.findAllContest();
        System.out.println("Los concursos encontrados son" + res);

    }

    @Test
    public void testGetUserofRecipe() {

        User res;
        List<Recipe> aux = new ArrayList<>(actorService.findAllRecipeGroupByCategorie());
        res = actorService.getUserOfRecipe(aux.get(2));
        System.out.println("El usuario de la receta " + aux.get(2) + " es " + res);


    }

    @Test
    public void testGetRecipesOfUser() {
        List<User> aux = new ArrayList<>(userService.findAll());
        Collection<Recipe> res = actorService.getRecipesofUser(aux.get(1));
        System.out.println("Las recetas del usuario " + aux.get(1).getName() + " es " + res);

    }

    @Test
    public void testGetQualifyRecipeForContest() {
        List<Contest> aux = new ArrayList<>(contestService.findAll());
        Collection<Recipe> res = actorService.getQualifyRecipeforContest(aux.get(2));
        Assert.notNull(res);
        System.out.println("las recetias para el concurso" + res);
    }

    @Test
    public void testFindRecipeByTickr() {

        Recipe res = actorService.findRecipeByTicker("150722-errt");
        Assert.notNull(res);

    }

    @Test
    public void testFindByUserNameVOID() {
        String name = "Davdedia";
        Actor res = actorService.findUserByName(name);
        Assert.isNull(res);
    }

    @Test
    public void testFindByUserNameNonVOID() {
        String name = "Actor3";
        Actor res = actorService.findUserByName(name);
        Assert.isNull(res);
    }

    @Test
    public void testEditPersonalData() {

        authenticate("User2");
        String nname = "paco";
        String nsurname = "me";
        String nemail = "pacome@aol.com";
        String nphone = "+34(653)1234";
        String npostal = "nueva calle,5";
        SocialIdentity s = socialIdentityService.create();
        s.setNickname("Twitter");
        s.setLink("www.tw.es");
        Actor u = actorService.editPersonalData(nname, nsurname, nemail, nphone, npostal, s);
        System.out.println("El actor cambiado es" + u.getName());
        authenticate(null);

    }

    @Test
    public void testCreateFolder() {

        authenticate("User2");
        Actor a = actorService.findByPrincipal();
        System.out.println("Las carpetas del actor antes de ejecutar la accion son : " + a.getFolders());
        actorService.createFolder("Prueba");
        System.out.println("Las carpetas del actor despues de ejecutar la accion son : " + a.getFolders());

        authenticate(null);
    }

    @Test
    public void testGetFolders() {
        authenticate("User2");
        Collection<Folder> res = actorService.getFolders();
        System.out.println("Las carpetas del actor son : " + res);
        authenticate(null);

    }

    @Test
    public void testModifyFolder() throws Exception {
        authenticate("User2");
        Actor a = actorService.findByPrincipal();
        List<Folder> aux = new ArrayList<>(a.getFolders());
        Folder f = aux.get(2);
        Folder res = actorService.modifyFolder(f, "PRUEBA MODIFICACION");
        System.out.println("La nueva carpeta modificada es " + res.getName());
        authenticate(null);


    }

    @Test
    public void testDeleteFolder() throws Exception {
        authenticate("User2");
        Actor a = actorService.findByPrincipal();
        System.out.println("Las carpetas del actor antes de ejecutar la accion son : " + a.getFolders());
        List<Folder> aux = new ArrayList<>(a.getFolders());
        Folder f = aux.get(2);
        actorService.deleteFolder(f);
        System.out.println("Las carpetas del actor despues de ejecutar la accion son : " + a.getFolders());
        authenticate(null);

    }

    @Test
    public void testTextMessage() throws Exception {
        authenticate("User2");
        String subject = "PRUEBA";
        String body = "Este es el body de la prueba";
        List<User> aux = new ArrayList<>(userService.findAll());
        Actor recipient = aux.get(0);
        Priority prio = Priority.HIGH;
        Message m = actorService.textMessage(subject, body, recipient, prio);
        System.out.println("El mensaje creado es : " + m);
        System.out.println("Los mensajes del receptor son: " + recipient.getMessage());
        authenticate(null);

    }


    @Test
    public void testDeleteMessage() throws Exception {
        authenticate("User1");
        Actor a = actorService.findByPrincipal();
        String subject = "PRUEBA";
        String body = "Este es el body de la prueba";
        List<User> aux = new ArrayList<>(userService.findAll());
        Actor recipient = aux.get(0);
        Priority prio = Priority.HIGH;
        Message m = actorService.textMessage(subject, body, recipient, prio);
        System.out.println(m);
        List<Message> messages = new ArrayList<>(a.getMessage());
        actorService.deleteMessage(messages.get(0));
        authenticate(null);

    }

    @Test
    public void testMoveMessage() throws Exception {

        authenticate("User2");
        Actor a = actorService.findByPrincipal();
        String subject = "PRUEBA";
        String body = "Este es el body de la prueba";
        List<User> aux = new ArrayList<>(userService.findAll());
        Actor recipient = aux.get(0);
        Priority prio = Priority.HIGH;
        Message m = actorService.textMessage(subject, body, recipient, prio);
        Collection<Message> messages = new ArrayList<>(a.getMessage());
        Assert.notNull(messages, "Los mensajes estan vacios");
        List<Folder> folders = new ArrayList<>(a.getFolders());
        Assert.notNull(messages, "Las carpetas estan vacias");

        Folder c = folders.get(2);
        Assert.notNull(m, "Mensaje nulo");
        Assert.notNull(c, "Carpeta vacía");
        actorService.moveMessage(m, c);
        authenticate(null);
    }

    @Test
    public void testGetMasterClass() {
        authenticate("User2");
        Collection<MasterClass> res = actorService.getMasterClassRestricted();
        Assert.notEmpty(res, "Coleccion vacia");
        System.out.println(res);
        authenticate(null);


    }

    @Test
    public void testRegisterMasterClass() {
        authenticate("User2");
        List<MasterClass> masterClasses = new ArrayList<>(masterClassService.findAll());
        actorService.registerToMasterClass(masterClasses.get(0));
        System.out.println(masterClasses.get(0).getRegisters());
        authenticate(null);


    }

    @Test
    public void testRandom(){
        authenticate("admin1");
        List<Sponsor> sponsors = new ArrayList<>(sponsorService.findAll());
        System.out.println(sponsors.get(0).getCampaign());
        System.out.println(adminService.computeMonthlyBills(sponsors.get(0)));

        authenticate(null);
    }

}
