package services;

import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MasterClassRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class MasterClassService {

    // Constructors--------------------------------------------------------------------------------------

    public MasterClassService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private MasterClassRepository MasterClassRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public MasterClass create() {
        MasterClass res = new MasterClass();
        return res;
    }

    public Collection<MasterClass> findAll() {
        Collection<MasterClass> res = MasterClassRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public MasterClass findOne(int MasterClass) {
        MasterClass res = MasterClassRepository.findOne(MasterClass);
        Assert.notNull(res);
        return res;

    }

    public MasterClass save(MasterClass a) {
        Assert.notNull(a);
        MasterClass res = MasterClassRepository.save(a);
        return res;
    }

    public void delete(MasterClass a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        MasterClassRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

}



