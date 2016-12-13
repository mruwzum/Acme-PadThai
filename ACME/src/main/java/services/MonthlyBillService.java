package services;

import domain.MonthlyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MonthlyBillRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class MonthlyBillService {

    // Constructors--------------------------------------------------------------------------------------

    public MonthlyBillService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private MonthlyBillRepository MonthlyBillRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public MonthlyBill create() {
        MonthlyBill res = new MonthlyBill();
        return res;
    }

    public Collection<MonthlyBill> findAll() {
        Collection<MonthlyBill> res = MonthlyBillRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public MonthlyBill findOne(int MonthlyBill) {
        MonthlyBill res = MonthlyBillRepository.findOne(MonthlyBill);
        Assert.notNull(res);
        return res;
    }

    public MonthlyBill save(MonthlyBill a) {

        Assert.notNull(a);
        MonthlyBill res = MonthlyBillRepository.save(a);
        return res;
    }

    public void delete(MonthlyBill a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        MonthlyBillRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



