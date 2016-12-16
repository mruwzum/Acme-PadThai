package converters;

import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import repositories.SponsorRepository;

/**
 * Created by daviddelatorre on 16/12/16.
 */
public class StringToSponsorConverter implements Converter<String, Sponsor> {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public Sponsor convert(String text) {
        Sponsor result;
        int id;

        try {
            if (StringUtils.isEmpty(text)) {
                result = null;
            } else {
                id = Integer.valueOf(text);
                result = sponsorRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
