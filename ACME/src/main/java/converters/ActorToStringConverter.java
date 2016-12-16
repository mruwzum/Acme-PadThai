package converters;

import domain.Actor;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by daviddelatorre on 16/12/16.
 */
public class ActorToStringConverter implements Converter<Actor, String> {

    @Override
    public String convert(Actor userAccount) {
        String result;

        if (userAccount == null) {
            result = null;
        } else {
            result = String.valueOf(userAccount.getId());
        }
        return result;
    }
}
