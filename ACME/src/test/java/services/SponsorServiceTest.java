package services;

import domain.Campaing;
import domain.CreditCard;
import domain.MonthlyBill;
import domain.Sponsor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by mruwzum on 12/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional


public class SponsorServiceTest extends AbstractTest {


    //Service under test ---------------------------------------------------------------------
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private MonthlyBillService monthlyBillService;
    @Autowired
    private CampaingService campaingService;


    @Test
    public void testGetAllCampaings() {
        authenticate("Sponsor1");

        Campaing aux = campaingService.create();
        Sponsor u = sponsorService.findByPrincipal();
        aux.setSponsor(u);
        aux.setStartDate(new Date(2017, 07, 22));
        aux.setEndDate(new Date(2017, 12, 12));
        aux.setNumberOfBanners(2334);
        aux.setMaximumDisplayed(13);
        Campaing aux2 = campaingService.save(aux);
        u.getCampaign().add(aux2);
        Collection<Campaing> res = sponsorService.getAllCampaings();
        Assert.notEmpty(res, "Coleccion vacia");
        authenticate(null);
    }

    @Test
    public void createCampaig() {
        authenticate("Sponsor2");
        Date start = new Date(2016, 01, 01);
        Date end = new Date(2017, 03, 22);
        Integer max = 355;
        Integer ba = 34;
        Campaing res = sponsorService.createCampaig(start, end, max, ba);
        Assert.notNull(res, "Campaña vacía");
        authenticate(null);
    }

    @Test
    public void editCampaign() {
        authenticate("Sponsor1");
        Date start = new Date(2016, 01, 01);
        Date end = new Date(2017, 03, 22);
        Integer max = 355;
        Integer ba = 34;
        Campaing aux = sponsorService.createCampaig(start, end, max, ba);
        Assert.notNull(aux, "campaña mal crada");
        Sponsor spo = sponsorService.findByPrincipal();
        Assert.notNull(spo, "usuario no valido");
        Integer max1 = 355;
        Integer ba1 = 34;
        Campaing res = sponsorService.editCampaign(start, end, max1, ba1);
        Assert.notNull(res, "camapaña mal editada");
        authenticate(null);
    }

    @Test
    public void deleteCampaign() {
        authenticate("Sponsor1");
        Date start = new Date(2016, 01, 01);
        Date end = new Date(2017, 03, 22);
        Integer max = 355;
        Integer ba = 34;
        Campaing aux = sponsorService.createCampaig(start, end, max, ba);
        Assert.notNull(aux, "campaña mal crada");
        Sponsor spo = sponsorService.findByPrincipal();
        Assert.notNull(spo, "usuario no valido");
        Assert.notNull(aux, "El sponsor no tiene campañas");
        sponsorService.deleteCampaign();
        authenticate(null);
    }


    @Test
    public void modifyCreditCard() throws Exception {
        authenticate("Sponsor2");
        String br = "new br";
        String cv = "355";
        Integer exp = 3;
        Integer expy = 19;
        Long num = (long) 454553450;
        String hol = "Pepe Pérez";
        CreditCard res = sponsorService.modifyCreditCard(br, cv, exp, expy, num, hol);
        Assert.notNull(res);
        authenticate(null);
    }

    @Test
    public void getMonthlyBills() {
        authenticate("Sponsor2");
        Assert.notNull(sponsorService.getMonthlyBills(), "MonthlyBill nulo");
        authenticate(null);
    }

    @Test
    public void setAsPaid() {
        authenticate("Sponsor2");

        List<MonthlyBill> aux = new ArrayList<>(monthlyBillService.findAll());
        Assert.notNull(aux, "coleccion vacia");
        MonthlyBill res = aux.get(0);
        Assert.notNull(res, "MonthyBill nula");
        MonthlyBill a = sponsorService.setAsPaid(res);
        Assert.notNull(a, "Modificacion nula");
    }

}
