/* Authority.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package security;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

    // Constructors -----------------------------------------------------------

    private static final long serialVersionUID = 1L;

    public Authority() {
        super();
    }

    // Values -----------------------------------------------------------------

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String NUTRITIONIST = "NUTRITIONIST";
    public static final String SPONSOR = "SPONSOR";
    public static final String COOK = "COOK";

    // Attributes -------------------------------------------------------------

    private String authority;

    @NotBlank
    @Pattern(regexp = "^" + ADMIN + "|" + USER + "|" + NUTRITIONIST + "|" + SPONSOR + "|" + COOK + "$")
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public static Collection<Authority> listAuthorities() {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<Authority>();

        authority = new Authority();
        authority.setAuthority(ADMIN);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(USER);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(NUTRITIONIST);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(SPONSOR);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(COOK);
        result.add(authority);

        return result;
    }

    // Equality ---------------------------------------------------------------

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (!this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));

        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
