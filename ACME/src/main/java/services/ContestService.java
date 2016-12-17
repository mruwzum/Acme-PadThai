package services;

import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ContestRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class ContestService {

    // Constructors--------------------------------------------------------------------------------------

    public ContestService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private ContestRepository ContestRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Contest create() {
        Contest res = new Contest();
        return res;
    }

    public Collection<Contest> findAll() {
        Collection<Contest> res = ContestRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Contest findOne(int Contest) {
        Contest res = ContestRepository.findOne(Contest);
        Assert.notNull(res);
        return res;
    }

    public Contest save(Contest a) {
        Assert.notNull(a);
        Contest res = ContestRepository.save(a);
        return res;
    }

    public void delete(Contest a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        ContestRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



