package converters;

import domain.Quantity;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by daviddelatorre on 21/12/16.
 */
public class QuantityToStringConverter implements Converter<Quantity, String> {

    @Override
    public String convert(Quantity message) {
        String result;

        if (message == null)
            result = null;
        else
            result = String.valueOf(message.getId());

        return result;
    }
}
