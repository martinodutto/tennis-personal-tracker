package cloud.martinodutto.tpt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64LengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Length {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "Invalid length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
