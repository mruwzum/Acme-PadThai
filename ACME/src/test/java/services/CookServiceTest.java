package services;

import domain.Actor;
import domain.LearningMaterial;
import domain.MasterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class CookServiceTest extends AbstractTest {


    //Service under test ---------------------------------------------------------------------

    @Autowired
    private CookService cookService;
    @Autowired
    private MasterClassService masterClass;


    @Test
    public void testGetMyMasterClasses() {
        authenticate("Cook1");
        Collection<MasterClass> res = cookService.getMyMasterClasses();
        System.out.println(res);
        authenticate(null);

    }

    @Test
    public void testCreateMasterClass() {
        authenticate("Cook1");

        String title = "Prueba";
        String description = "descripcion";
        MasterClass res = cookService.createMasterClass(title, description);
        System.out.println(res.getTitle());


        authenticate(null);
    }

    @Test
    public void testModifyMasterClass() {
        authenticate("Cook1");
        List<MasterClass> masterClasses = new ArrayList<>(masterClass.findAll());
        String title = "Prueba";
        String description = "descripcion";
        List<Actor> actors = new ArrayList<>();
        List<LearningMaterial> learningMaterials = new ArrayList<>();
        MasterClass res = cookService.modifyMasterClass(masterClasses.get(0), title, description, actors, learningMaterials);
        System.out.println(res.getTitle());
        authenticate(null);
    }

    @Test
    public void testDeleteMasterClass() {
        authenticate("Cook1");
        List<MasterClass> masterClasses = new ArrayList<>(masterClass.findAll());
        cookService.deleteMasterClass(masterClasses.get(0));
        authenticate(null);
    }


}
