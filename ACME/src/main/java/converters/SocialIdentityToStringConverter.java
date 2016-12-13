package converters;

import domain.SocialIdentity;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by daviddelatorre on 13/12/16.
 */
public class SocialIdentityToStringConverter implements Converter<SocialIdentity, String> {

    @Override
    public String convert(SocialIdentity message) {
        String result;

        if (message == null)
            result = null;
        else
            result = String.valueOf(message.getId());

        return result;
    }
}
