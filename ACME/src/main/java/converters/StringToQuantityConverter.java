package converters;

import domain.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import repositories.QuantityRepository;

/**
 * Created by daviddelatorre on 21/12/16.
 */
public class StringToQuantityConverter implements Converter<String, Quantity> {

    @Autowired
    private QuantityRepository quantityRepository;


    @Override
    public Quantity convert(String text) {
        Quantity result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = quantityRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
