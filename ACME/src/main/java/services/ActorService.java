package services;


import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import java.util.*;

/**
 * Created by david on 05/11/2016.
 * Copyright � 2016 NullPoint Software
 */

@Service
@Transactional
public class ActorService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private ActorRepository actorRepository;

    // Managed repository--------------------------------------------------------------------------------
    // Supporting services --------------------------------------------------------------------------------
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private SocialIdentityService socialIdentityService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private CookService cookService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private RecipeService recipeService;

    public ActorService() {
        super();
    }

    // Simple CRUD method --------------------------------------------------------------------------------

    public Collection<Actor> findAll() {
        Collection<Actor> resActors;
        resActors = actorRepository.findAll();
        return resActors;
    }

    public Actor findOne(int actor) {
        Assert.isTrue(actor != 0);
        Assert.notNull(actor);
        Actor res = actorRepository.findOne(actor);
        Assert.notNull(res);
        return res;
    }


    public void delete(Actor a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        Assert.isTrue(actorRepository.exists(a.getId()));
        actorRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

    @SuppressWarnings("static-access")
    public Actor findByPrincipal() {
        Actor result;
        UserAccount userAccount;

        userAccount = loginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Actor findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Actor result;

        result = actorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    //Actor who isn't authenticated

    public Actor registerAsUser2(User u) {
        Assert.notNull(u);
        Authority autoh = new Authority();
        autoh.setAuthority("USER");
        UserAccount res = new UserAccount();
        res.addAuthority(autoh);
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
        res.setUsername(u.getUserAccount().getUsername());
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        SocialIdentity socialIdentity = socialIdentityService.create();
        socialIdentity.setNickname(u.getSocialIdentity().getNickname());
        socialIdentity.setSocialNet(u.getSocialIdentity().getSocialNet());
        socialIdentity.setLink(u.getSocialIdentity().getLink());
        SocialIdentity socres = socialIdentityService.save(socialIdentity);
        //TODO esto hay que modificarlo
        Folder f = createNewFolder();
        Collection<Folder> aux2 = new HashSet<>();
        aux2.add(f);

        u.setFolders(aux2);
        u.setUserAccount(userAccount);
        u.setSocialIdentity(socres);
        User resu = userService.save(u);
        return resu;
    }


    public Actor registerAsUser(String name, String password) {
        Assert.notNull(name, password);
        Authority autoh = new Authority();
        autoh.setAuthority("USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(autoh);
        UserAccount res = new UserAccount();
        res.setAuthorities(authorities);
        res.setUsername(name);
        res.setPassword(password);
        User u = userService.create();
        u.setUserAccount(res);
        User resu = userService.save(u);
        return resu;
    }

    public Actor registerAsNutritionist(String name, String password) {
        Assert.notNull(name, password);
        Authority autoh = new Authority();
        autoh.setAuthority("NUTRITIONIST");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(autoh);
        UserAccount res = new UserAccount();
        res.setAuthorities(authorities);
        res.setUsername(name);
        res.setPassword(password);
        Nutritionist nutritionist = nutritionistService.create();
        nutritionist.setUserAccount(res);
        Nutritionist result = nutritionistService.save(nutritionist);
        return result;
    }

    public Actor registerAsNutritionist2(Nutritionist u) {
        Assert.notNull(u);
        Authority autoh = new Authority();
        autoh.setAuthority("NUTRITIONIST");
        UserAccount res = new UserAccount();
        res.addAuthority(autoh);
        res.setUsername(u.getUserAccount().getUsername());
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        SocialIdentity socialIdentity = socialIdentityService.create();
        socialIdentity.setNickname(u.getSocialIdentity().getNickname());
        socialIdentity.setSocialNet(u.getSocialIdentity().getSocialNet());
        socialIdentity.setLink(u.getSocialIdentity().getLink());
        SocialIdentity socres = socialIdentityService.save(socialIdentity);
        //TODO esto hay que modificarlo
        Folder f = createNewFolder();
        Collection<Folder> aux2 = new HashSet<>();
        aux2.add(f);

        u.setFolders(aux2);
        u.setUserAccount(userAccount);
        u.setSocialIdentity(socres);
        Nutritionist resu = nutritionistService.save(u);
        return resu;
    }

    public Actor registerAsSponsor(String name, String password) {
        Assert.notNull(name, password);
        Authority autoh = new Authority();
        autoh.setAuthority("SPONSOR");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(autoh);
        UserAccount res = new UserAccount();
        res.setAuthorities(authorities);
        res.setUsername(name);
        res.setPassword(password);
        Sponsor sponsor = sponsorService.create();
        sponsor.setUserAccount(res);
        Sponsor result = sponsorService.save(sponsor);
        return result;
    }

    public Actor registerAsSponsor2(Sponsor u) {
        Assert.notNull(u);
        Authority autoh = new Authority();
        autoh.setAuthority("SPONSOR");
        CreditCard creditCard = creditCardService.create();
        creditCard.setCVV("000");
        creditCard.setExpirationYear(0000);
        creditCard.setExpirationMonth(00);
        creditCard.setBrandName("VISA/MASTERCARD/AMEX");
        creditCard.setHolderName("GENERIC");
        creditCard.setNumber(00000000);
        creditCardService.save(creditCard);
        UserAccount res = new UserAccount();
        res.addAuthority(autoh);
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
        res.setUsername(u.getUserAccount().getUsername());
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        SocialIdentity socialIdentity = socialIdentityService.create();
        socialIdentity.setNickname(u.getSocialIdentity().getNickname());
        socialIdentity.setSocialNet(u.getSocialIdentity().getSocialNet());
        socialIdentity.setLink(u.getSocialIdentity().getLink());
        SocialIdentity socres = socialIdentityService.save(socialIdentity);
        //TODO esto hay que modificarlo
        Folder f = createNewFolder();
        Collection<Folder> aux2 = new HashSet<>();
        aux2.add(f);

        u.setFolders(aux2);
        u.setUserAccount(userAccount);
        u.setSocialIdentity(socres);
        Sponsor resu = sponsorService.save(u);
        return resu;
    }

    public Actor registerAsCook(String name, String password) {
        Assert.notNull(name, password);
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(password, null);
        Authority autoh = new Authority();
        autoh.setAuthority("COOK");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(autoh);
        UserAccount res = new UserAccount();
        res.setAuthorities(authorities);
        res.setUsername(name);
        res.setPassword(hash);
        Cook cook = cookService.create();
        cook.setUserAccount(res);
        Cook result = cookService.save(cook);
        return result;
    }

    public Actor registerAsCook2(Cook u) {
        Assert.notNull(u);
        Authority autoh = new Authority();
        autoh.setAuthority("COOK");
        UserAccount res = new UserAccount();
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
        res.addAuthority(autoh);
        res.setUsername(u.getUserAccount().getUsername());
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        SocialIdentity socialIdentity = socialIdentityService.create();
        socialIdentity.setNickname(u.getSocialIdentity().getNickname());
        socialIdentity.setSocialNet(u.getSocialIdentity().getSocialNet());
        socialIdentity.setLink(u.getSocialIdentity().getLink());
        SocialIdentity socres = socialIdentityService.save(socialIdentity);
        //TODO esto hay que modificarlo
        Folder f = createNewFolder();
        Collection<Folder> aux2 = new HashSet<>();
        aux2.add(f);

        u.setFolders(aux2);
        u.setUserAccount(userAccount);
        u.setSocialIdentity(socres);
        Cook resu = cookService.save(u);
        return resu;
    }
    public Collection<Recipe> findAllRecipeGroupByCategorie() {
        Collection<Recipe> res;
        res = actorRepository.findAllRecipeGroupByCategorie();
        return res;
    }

    public Collection<User> findAllUser() {
        Collection<User> res = actorRepository.findAllUser();
        return res;
    }

    public Collection<Contest> findAllContest() {
        Collection<Contest> res = actorRepository.findAllContest();
        return res;
    }


    public User getUserOfRecipe(Recipe recipe) {
        Assert.notNull(recipe, "El par�metro recibido es nulo");

        User res = actorRepository.getUserofRecipe(recipe.getId());
        return res;
    }

    public Collection<Recipe> getRecipesofUser(User u) {
        Assert.notNull(u, "El par�metro recibido es nulo");
        Collection<Recipe> res = actorRepository.getRecipesofUser(u.getId());
        return res;
    }

    public Collection<Recipe> getQualifyRecipeforContest(Contest c) {
        Assert.notNull(c, "El par�metro recibido es nulo");
        Collection<Recipe> res = actorRepository.getQualifyRecipeforContest(c.getId());
        return res;
    }


    public Recipe findRecipeByTicker(String ticker) {
        Assert.notNull(ticker);
        Recipe res = actorRepository.findRecipeByTicker(ticker);
        return res;
    }
    public Recipe findRecipeByTitle(String title){
        Assert.notNull(title);
        Recipe res = actorRepository.findRecipeByTitle(title);
        return res;
    }
    public Recipe findRecipeBySummary(String summ){
        Assert.notNull(summ);
        Recipe res = actorRepository.findRecipeBySummary(summ);
        return res;
    }

    public User findUserByName(String name) {
        Assert.notNull(name);
        User res = actorRepository.findUserByName(name);
        return res;
    }

    public Actor findByName(String name) {
        Assert.notNull(name);
        Actor res = actorRepository.findByName(name);
        return res;
    }

    //Actor who is authenticated

    public Actor editPersonalData(String name, String surname, String emailAddress, String phone, String postalAddress,
                                  SocialIdentity social) {
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        u.setName(name);
        u.setSurname(surname);
        u.setEmailAddress(emailAddress);
        u.setPhone(phone);
        u.setPostalAddress(postalAddress);
        u.setSocialIdentity(social);

        //Actor res = actorRepository.save(u);
        return u;
    }

    public Actor editPersonalData2(String name, String surname, String emailAddress, String phone, String postalAddress,
                                  String nickname, String socialNet, String link) {
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.notNull(name,"name null");
        Assert.notNull(surname,"surname null");
        Assert.notNull(emailAddress, "email Address null");
        Assert.notNull(phone, "phone null");
        Assert.notNull(postalAddress,"postal Address null");
        Assert.notNull(nickname,"nickname null");
        Assert.notNull(socialNet,"social net null");
        Assert.notNull(link,"link null");

        u.setName(name);
        u.setSurname(surname);
        u.setEmailAddress(emailAddress);
        u.setPhone(phone);
        u.setPostalAddress(postalAddress);
        u.getSocialIdentity().setNickname(nickname);
        u.getSocialIdentity().setSocialNet(socialNet);
        u.getSocialIdentity().setLink(link);
        //Actor res = actorRepository.save(u);
        return u;
    }


    //Manage Folder

    public Folder createNewFolder() {
        Folder aux = folderService.create();
        aux.setName("generic");
        Folder res = folderService.save(aux);
        return res;
    }

    public Folder createFolder(String name) {
        Assert.notNull(name, "El nombre es nulo");
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Folder aux = folderService.create();
        aux.setName(name);
        Folder res = folderService.save(aux);
        u.getFolders().add(res);
        return res;
    }

    public Collection<Folder> getFolders() {

        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Collection<Folder> res = actorRepository.getFolder(u.getId());
        return res;
    }

    public Folder modifyFolder(Folder f, String name) {
        Assert.notNull(f);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(u.getFolders().contains(f));
        f.setName(name);
        return f;
    }

    public void deleteFolder(Folder f) {
        Assert.notNull(f);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(u.getFolders().contains(f), "El actor no contiene la carpeta ");
        folderService.delete(f);
    }
    public Message sendMessage(Message message){
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        recieveMessage(message,message.getRecipient());
        return message;
    }
    public Message recieveMessage(Message message, Actor a){


        List<Folder> folders = new ArrayList<>(a.getFolders());
        Assert.notEmpty(folders,"carpetas vacias");
        folders.get(2).getMessages().add(message);
        a.setFolders(folders);
        return  message;
    }

    //Manage Message

    public Message textMessage(String subject, String body, Actor recipient, Priority priority) {
        Assert.notNull(subject, "El subject no existe");
        Assert.notNull(body, "El body no existe");
        Assert.notNull(recipient, "El actor no existe");
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        //Comprobaciones
        List<Folder> auxFolder = new ArrayList<>(u.getFolders());
        List<Folder> auxFolder2 = new ArrayList<>(recipient.getFolders());
        Message aux = messageService.create();
        aux.setSender(u);
        aux.setSubject(subject);
        aux.setBody(body);
        aux.setRecipient(recipient);
        aux.setPriority(priority);
        aux.setSentDate(new Date(System.currentTimeMillis() - 100));
        chekBody(body);
        aux.setBody(body);
        aux.setFolder(auxFolder.get(0));
        Message copy = aux;

        Message res1 = messageService.save(aux);
        Message res2 = messageService.save(copy);


        //Guardar en carpeta outbox de sender
        res1.setFolder(auxFolder.get(0));
        auxFolder.get(0).getMessages().add(res1);
        u.setFolders(auxFolder);
        u.getMessage().add(res1);

        //Guardar en carpeta inbox de recipient
        res2.setFolder(auxFolder2.get(2));
        auxFolder2.get(2).getMessages().add(res2);
        recipient.setFolders(auxFolder2);
        recipient.getMessage().add(res2);

        return res1;
    }

    private void chekBody(String text) {
        List<Admin> admins = new ArrayList<>(adminService.findAll());
        for (String t : admins.get(0).getKeywor()) {
            Assert.doesNotContain(text, t, "Contiene alguna palabra no permitida");
        }


    }

    public void deleteMessage(Message m) {
        Assert.notNull(m);
        List<Folder> auxFolder = new ArrayList<>(findByPrincipal().getFolders());
        m.setFolder(auxFolder.get(1));
        auxFolder.get(1).getMessages().add(m);
        findByPrincipal().setFolders(auxFolder);
    }


    public void moveMessage(Message m, Folder f) {
        Assert.notNull(m, "El mensaje es incorrecto");
        Assert.notNull(f, "La carpeta es incorrecta");
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(m.getSender().equals(u));
        Assert.isTrue(u.getFolders().contains(f));
    }


    //A-Level

    public Collection<MasterClass> getMasterClassRestricted() {
        Collection<MasterClass> res = actorRepository.getMasterClasesRestricted();
        Assert.notEmpty(res, "La lista de MasterClass est� vac�a");
        return res;

    }

    public MasterClass registerToMasterClass(MasterClass m) {
        Assert.notNull(m);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.notNull(m.getRegisters().contains(u), "Ya te has apuntado a esta clase");
        m.getRegisters().add(u);
        return m;
    }

    public MasterClass unregisterToMasterClass(MasterClass m) {
        Assert.notNull(m);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.notNull(!m.getRegisters().contains(u), "No estas apuntado en esta clase");
        m.getRegisters().remove(u);
        return m;
    }

//    public MasterClass registerToMasterClassNutri(MasterClass m) {
//        Assert.notNull(m);
//        Nutritionist u;
//        u = nutritionistService.findByPrincipal();
//        Assert.notNull(u, "El actor no existe");
//        m.getRegisters().add(u);
//        return m;
//    }
//    public MasterClass registerToMasterClassSpon(MasterClass m) {
//        Assert.notNull(m);
//        Sponsor u;
//        u = sponsorService.findByPrincipal();
//        Assert.notNull(u, "El actor no existe");
//        m.getRegisters().add(u);
//        return m;
//    }

}



