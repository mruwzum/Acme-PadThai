package services;

import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.PropertyRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class PropertyService {

    // Constructors--------------------------------------------------------------------------------------

    public PropertyService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private  PropertyRepository PropertyRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Property create() {
        Property res = new Property();
        return res;
    }

    public Collection<Property> findAll() {
        Collection<Property> res = PropertyRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public  Property findOne(int Property) {
        Property res = PropertyRepository.findOne(Property);
        Assert.notNull(res);
        return res;
    }

    public  Property save(Property a) {
        Assert.notNull(a);
        Property res = PropertyRepository.save(a);
        return res;
    }

    public  void delete(Property a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        PropertyRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------

}



