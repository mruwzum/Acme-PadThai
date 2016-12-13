package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mruwzum on 11/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class NutritionistServiceTest extends AbstractTest {
    //Service under test ---------------------------------------------------------------------
    @Autowired
    private NutritionistService nutritionistService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private CurriculaService curriculaService;
    @Autowired
    private QuantityService quantityService;


    //Tests -------------------------------------------------

    @Test
    public void testFindAll() {
        Collection<Nutritionist> res = nutritionistService.findAll();
        Assert.notNull(res);

    }

    @Test
    public void testFindOne() {
        Nutritionist res;
        res = nutritionistService.findOne(200);
        Assert.notNull(res);
        Assert.isTrue(res.getId() != 0);
    }

    @Test
    public void testGetCurricula() {
        int id = 200;
        Nutritionist a = nutritionistService.findOne(id);
        nutritionistService.getCurricula(a);
    }

    @Test
    public void testEditCurricula() {
        authenticate("Nutritionist1");
        List<Curricula> aux1 = new ArrayList<>(curriculaService.findAll());
        Curricula aux = aux1.get(0);
        Assert.notNull(aux, "Curriculim vacío");
        String edu, exp, pho, hob;
        edu = "eduSection";
        exp = "expSection";
        pho = "phoSection";
        hob = "ggsdgbhsdabfls";
        nutritionistService.editCurricula(aux, edu, exp, pho, hob);
        authenticate(null);

    }

    @Test
    public void testDeleteCurricula() {
        authenticate("Nutritionist1");
        List<Curricula> aux1 = new ArrayList<>(curriculaService.findAll());
        Curricula aux = aux1.get(0);
        Assert.isTrue(aux.getId() != 0);
        nutritionistService.deleteCurricula(aux);
        authenticate(null);
    }

    @Test
    public void testGetIngredients() {
        Collection<Ingredient> res = nutritionistService.getIngredients();
        Assert.notNull(res);
    }

    @Test
    public void removeAllIngredients() {
        authenticate("Nutritionist1");

        nutritionistService.removeAllIngredients();
        authenticate(null);
    }

    @Test
    public void testRemoveIngredient() {
        authenticate("Nutritionist1");
        List<Ingredient> aux = new ArrayList<>(ingredientService.findAll());
        Ingredient i = aux.get(0);
        nutritionistService.removeIngredient(i);
        authenticate(null);
    }

    @Test
    public void testEditIngredient() {
        authenticate("Nutritionist1");
        List<Ingredient> aux = new ArrayList<>(ingredientService.findAll());
        Ingredient i = aux.get(0);
        nutritionistService.editIngredient(i, "Canela", "Para los postres", "/directorio/foto3.png");
        authenticate(null);
    }

    @Test
    public void testGetProperties() {
        Collection<Property> res = nutritionistService.getProperties1();
        Assert.notEmpty(res);
    }

    @Test
    public void testDeleteAllProperties() {
        authenticate("Nutritionist1");
        nutritionistService.deleteAllProperties();
        authenticate(null);
    }

    @Test
    public void testDeleteProperty() {
        authenticate("Nutritionist1");
        List<Property> aux = new ArrayList<>(propertyService.findAll());
        Property p = aux.get(0);
        Assert.notNull(p);
        nutritionistService.deleteProperty(p);
        authenticate(null);
    }

    @Test
    public void testEditProperty() {
        authenticate("Nutritionist1");
        authenticate("Nutritionist1");
        List<Property> aux = new ArrayList<>(propertyService.findAll());
        Property p = aux.get(0);
        Assert.notNull(p);
        Quantity q = quantityService.create();
        nutritionistService.editProperty(p, "Onzas", q);
    }
}
