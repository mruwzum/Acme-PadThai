package services;

import domain.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.QuantityRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class QuantityService {

    // Constructors--------------------------------------------------------------------------------------

    public QuantityService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private QuantityRepository QuantityRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Quantity create() {
        Quantity res = new Quantity();
        return res;
    }

    public Collection<Quantity> findAll() {
        Collection<Quantity> res = QuantityRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Quantity findOne(int Quantity) {
        Quantity res = QuantityRepository.findOne(Quantity);
        Assert.notNull(res);
        return res;
    }

    public Quantity save(Quantity a) {
        Assert.notNull(a);
        Quantity res = QuantityRepository.save(a);
        return res;
    }

    public void delete(Quantity a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        QuantityRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



