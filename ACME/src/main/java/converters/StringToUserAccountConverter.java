package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import security.UserAccount;
import security.UserAccountRepository;

@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount> {

    @Autowired
    private UserAccountRepository userAccount;

    @Override
    public UserAccount convert(String text) {
        UserAccount result;
        int id;

        try {
            if (StringUtils.isEmpty(text)) {
                result = null;
            } else {
                id = Integer.valueOf(text);
                result = userAccount.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }

}