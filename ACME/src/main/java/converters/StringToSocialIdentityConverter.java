package converters;

import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import services.SocialIdentityService;

/**
 * Created by daviddelatorre on 13/12/16.
 */
public class StringToSocialIdentityConverter implements Converter<String, SocialIdentity> {
    @Autowired
    SocialIdentityService socialIdentityService;

    @Override
    public SocialIdentity convert(String text) {
        SocialIdentity result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = socialIdentityService.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
