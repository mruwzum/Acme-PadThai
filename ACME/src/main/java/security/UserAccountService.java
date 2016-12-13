package security;


import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserAccountService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Supporting services ----------------------------------------------------

    // Constructors -----------------------------------------------------------

    public UserAccountService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public UserAccount findByActor(Actor actor) {
        Assert.notNull(actor);

        UserAccount result;

        result = userAccountRepository.findByActorId(actor.getId());

        return result;
    }

    public UserAccount save(UserAccount userAccount){
        Assert.notNull(userAccount);
        UserAccount res = userAccountRepository.save(userAccount);
        return res;
    }


    // Other business methods -------------------------------------------------

}