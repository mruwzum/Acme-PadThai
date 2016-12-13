package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CookRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class CookService {

    // Constructors--------------------------------------------------------------------------------------

    public CookService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MasterClassService masterClassService;
    @Autowired
    private ActorService actorService;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Cook create() {
        Cook res = new Cook();
        return res;
    }

    public Collection<Cook> findAll() {
        Collection<Cook> res = cookRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Cook findOne(int Cook) {
        Cook res = cookRepository.findOne(Cook);
        Assert.notNull(res);
        return res;
    }

    public Cook save(Cook a) {
        Assert.notNull(a);
        Cook res = cookRepository.save(a);
        return res;
    }

    public void delete(Cook a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        cookRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------


    public Cook findByPrincipal() {
        Cook result;
        UserAccount userAccount;
        userAccount = loginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Cook findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);
        Cook result;
        result = cookRepository.findByUserAccountId(userAccount.getId());
        return result;
    }

    public Collection<MasterClass> getMyMasterClasses() {
        Cook u;
        u = findByPrincipal();
        Collection<MasterClass> res = cookRepository.getMyMasterClasses(u.getId());
        return res;
    }

    public MasterClass createMasterClass(String title, String description) {
        Assert.notNull(title);
        Assert.notNull(description);
        Cook u;
        u = findByPrincipal();
        Assert.notNull(u, "El cocinero no es valido");
        MasterClass aux = masterClassService.create();
        aux.setTitle(title);
        aux.setDescription(description);
        aux.setCook(u);
        MasterClass res = masterClassService.save(aux);
        return res;
    }


    public MasterClass modifyMasterClass(MasterClass m, String title, String description, Collection<Actor> registers, Collection<LearningMaterial> learning) {
        Assert.notNull(m);
        Assert.notNull(title);
        Assert.notNull(description);
        Cook u;
        u = findByPrincipal();
        Assert.notNull(u, "El cocinero no es valido");
        Assert.isTrue(u.getMasterClass().contains(m));
        m.setTitle(title);
        m.setDescription(description);
        m.getRegisters().addAll(registers);
        m.getMaterial().addAll(learning);
        return m;
    }

    public void deleteMasterClass(MasterClass m) {
        Cook u;
        u = findByPrincipal();
        Assert.notNull(u, "El cocinero no es valido");
        Assert.isTrue(u.getMasterClass().contains(m));
        if (!m.getRegisters().isEmpty()) {
            for (Actor a : m.getRegisters()) {
                actorService.textMessage("CLASE ELIMINADA", "La clase " + m + " va a ser eliminada", a, Priority.HIGH);
            }
        }
        masterClassService.delete(m);
    }
}



