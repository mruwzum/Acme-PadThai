package converters;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import repositories.ActorRepository;

/**
 * Created by daviddelatorre on 16/12/16.
 */
public class StringToActorConverter implements Converter<String, Actor> {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor convert(String text) {
        Actor result;
        int id;

        try {
            if (StringUtils.isEmpty(text)) {
                result = null;
            } else {
                id = Integer.valueOf(text);
                result = actorRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
