package services;

import domain.Campaing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CampaingRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class CampaingService {

    // Constructors--------------------------------------------------------------------------------------

    public CampaingService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CampaingRepository CampaingRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Campaing create() {
        Campaing res;
        res = new Campaing();
        return res;
    }

    public Collection<Campaing> findAll() {
        Collection<Campaing> res;
        res = CampaingRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Campaing findOne(int Campaing) {
        Campaing res;
        res = CampaingRepository.findOne(Campaing);
        Assert.notNull(res);
        return res;
    }

    public Campaing save(Campaing a) {
        Assert.notNull(a);
        Campaing res;
        res = CampaingRepository.save(a);
        return res;
    }

    public void delete(Campaing a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        CampaingRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



