package cloud.martinodutto.tpt.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

/**
 * Base64-strings validator, that decodes the input value and checks the length of the <i>resulting</i> string.
 * Careful: it considers nulls as valid, so please use it together with @{@link javax.validation.constraints.NotNull} or
 * similar annotations to avoid this.
 */
public class Base64LengthValidator implements ConstraintValidator<Base64Length, String> {

    private int min;
    private int max;

    @Override
    public void initialize(Base64Length password) {
        min = password.min();
        max = password.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        String decoded = new String(Base64.getDecoder().decode(s));
        return decoded.length() >= min && decoded.length() <= max;
    }
}
