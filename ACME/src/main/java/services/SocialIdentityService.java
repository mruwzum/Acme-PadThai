package services;

import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SocialIdentityRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class SocialIdentityService {

    // Constructors--------------------------------------------------------------------------------------

    public SocialIdentityService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private SocialIdentityRepository SocialIdentityRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public SocialIdentity create() {
        SocialIdentity res = new SocialIdentity();
        return res;
    }

    public Collection<SocialIdentity> findAll() {
        Collection<SocialIdentity> res = SocialIdentityRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public SocialIdentity findOne(int SocialIdentity) {
        SocialIdentity res = SocialIdentityRepository.findOne(SocialIdentity);
        Assert.notNull(res);
        return res;
    }

    public SocialIdentity save(SocialIdentity a) {
        Assert.notNull(a);
        SocialIdentity res = SocialIdentityRepository.save(a);
        return res;
    }

    public void delete(SocialIdentity a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        SocialIdentityRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



