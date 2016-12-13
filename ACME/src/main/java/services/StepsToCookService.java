package services;

import domain.StepsToCook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.StepsToCookRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class StepsToCookService {

    // Constructors--------------------------------------------------------------------------------------

    public StepsToCookService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private StepsToCookRepository StepsToCookRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public StepsToCook create() {
        StepsToCook res = new StepsToCook();
        return res;
    }

    public Collection<StepsToCook> findAll() {
        Collection<StepsToCook> res = StepsToCookRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public StepsToCook findOne(int StepsToCook) {

        StepsToCook res = StepsToCookRepository.findOne(StepsToCook);
        Assert.notNull(res);
        return res;
    }

    public StepsToCook save(StepsToCook a) {
        Assert.notNull(a);
        StepsToCook res = StepsToCookRepository.save(a);
        return res;
    }

    public void delete(StepsToCook a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        StepsToCookRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

}



