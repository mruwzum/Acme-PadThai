package services;

import domain.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CategorieRepository;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class CategorieService {

    // Constructors--------------------------------------------------------------------------------------

    public CategorieService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CategorieRepository CategorieRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Categorie create() {
        Categorie res;
        res = new Categorie();
        return res;
    }

    public Collection<Categorie> findAll() {
        Collection<Categorie> res;
        res = CategorieRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Categorie findOne(int Cate) {
        Categorie res;
        res = CategorieRepository.findOne(Cate);
        Assert.notNull(res);
        return res;
    }

    public Categorie save(Categorie a) {
        Assert.notNull(a);
        Categorie res;
        res = CategorieRepository.save(a);
        return res;

    }

    public void delete(Categorie a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        CategorieRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



