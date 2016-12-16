package converters;

import domain.Sponsor;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by daviddelatorre on 16/12/16.
 */
public class SponsorToStringConverter implements Converter<Sponsor, String> {
    @Override
    public String convert(Sponsor userAccount) {
        String result;

        if (userAccount == null) {
            result = null;
        } else {
            result = String.valueOf(userAccount.getId());
        }
        return result;
    }
}
