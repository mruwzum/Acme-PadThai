package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class SponsorService {

    // Constructors--------------------------------------------------------------------------------------

    public SponsorService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private CampaingService campaingService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MonthlyBillService monthlyBillService;

    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Sponsor create() {
        Sponsor res = new Sponsor();
        return res;
    }

    public Collection<Sponsor> findAll() {
        Collection<Sponsor> res = sponsorRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Sponsor findOne(int Sponsor) {
        Sponsor res = sponsorRepository.findOne(Sponsor);
        Assert.notNull(res);
        return res;
    }

    public Sponsor save(Sponsor a) {
        Assert.notNull(a);
        Sponsor res = sponsorRepository.save(a);
        return res;
    }

    public void delete(Sponsor a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        sponsorRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

    public Sponsor findByPrincipal() {
        Sponsor result;
        UserAccount userAccount;
        userAccount = loginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public Sponsor findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);
        Sponsor result;
        result = sponsorRepository.findByUserAccountId(userAccount.getId());
        return result;
    }


    public Collection<Campaing> getAllCampaings() {
        Sponsor s = findByPrincipal();
        Assert.notNull(s);
        Collection<Campaing> aux = sponsorRepository.getMyCampaings(s.getId());
        Assert.notEmpty(aux, "Campañas vacias");
        return aux;
    }

    public Campaing createCampaig(Date start, Date end, Integer max, Integer bab) {
        Sponsor u = findByPrincipal();
        Campaing res = campaingService.create();
        res.setStartDate(start);
        res.setSponsor(u);
        res.setEndDate(end);
        res.setMaximumDisplayed(max);
        res.setNumberOfBanners(bab);
        res.setBannerCost(0.25);
        Campaing res2 = campaingService.save(res);
        return res2;
    }

    public Campaing editCampaign(Date start, Date end, Integer max, Integer bab) {
        Sponsor u = findByPrincipal();
        List<Campaing> res = new ArrayList<>(campaingService.findAll());
        Assert.notEmpty(res, "Este sponsor no tiene camapañas");
        Campaing aux = res.get(0);
        Assert.notNull(res, "camapaña nula");
        Assert.isTrue(isActive(aux));
        Assert.isTrue(isPassed(aux));
        aux.setNumberOfBanners(bab);
        aux.setStartDate(start);
        aux.setEndDate(end);
        aux.setMaximumDisplayed(max);
        campaingService.save(aux);
        return aux;

    }

    public void deleteCampaign() {
        Sponsor u = findByPrincipal();
        List<Campaing> res = new ArrayList<>(campaingService.findAll());
        Assert.notEmpty(res, "Este sponsor no tiene camapañas");
        Campaing aux = res.get(0);
        Assert.isTrue(isActive(aux));
        campaingService.delete(aux);
    }

    public Boolean isActive(Campaing a) {
        Boolean res = false;
        if (a.getEndDate().after(new Date(System.currentTimeMillis())) || a.getStartDate().before(new Date(System.currentTimeMillis()))) {
            res = true;
        }
        return res;
    }

    public Boolean isPassed(Campaing a) {
        Boolean res = false;
        if (a.getEndDate().before(new Date(System.currentTimeMillis()))) {
            res = true;
        }
        return res;
    }

    public CreditCard modifyCreditCard(String br, String cv, Integer exp, Integer expy, Long num, String hol) {
        Sponsor u = findByPrincipal();
        CreditCard aux = u.getCreditCard();
        Assert.notNull(aux);
        aux.setBrandName(br);
        aux.setCVV(cv);
        aux.setExpirationMonth(exp);
        aux.setExpirationYear(expy);
        aux.setNumber(num);
        aux.setHolderName(hol);
        u.setCreditCard(aux);
        return aux;
    }

    public CreditCard getMyCreditCard() {
        Sponsor u = findByPrincipal();
        Assert.notNull(u);
        CreditCard aux = u.getCreditCard();
        Assert.notNull(aux);
        return aux;
    }

    public Collection<MonthlyBill> getMonthlyBills() {
        Sponsor u = findByPrincipal();
        Assert.notNull(u, "Usuario no valido");
        List<MonthlyBill> aux = new ArrayList<>(monthlyBillService.findAll());
        Assert.notEmpty(aux, "Collecion vacia de montly");
        Collection<MonthlyBill> res = new ArrayList<>();
        for (MonthlyBill m : aux) {
            if (m.getSponsor().equals(u)) {
                res.add(m);
            }
        }
        return res;
    }

    public Collection<MonthlyBill> getUnpaidMonthlyBills() {
        Sponsor u = findByPrincipal();
        Assert.notNull(u, "Usuario no valido");
        List<MonthlyBill> aux = new ArrayList<>(monthlyBillService.findAll());
        Assert.notEmpty(aux, "Collecion vacia de montly");
        Collection<MonthlyBill> myMB = new ArrayList<>();
        for (MonthlyBill m : aux) {
            if (m.getSponsor().equals(u)) {
                myMB.add(m);
            }
        }
        Collection<MonthlyBill> res = new ArrayList<>();

        for (MonthlyBill m : myMB) {
            if (m.getPaid().equals(Boolean.FALSE)) {
                res.add(m);
            }
        }
        return res;
    }

    public MonthlyBill setAsPaid(MonthlyBill a) {
        Assert.notNull(a);

        Assert.isTrue(!a.getPaid());
        a.setPaid(true);

        return a;
    }


}


