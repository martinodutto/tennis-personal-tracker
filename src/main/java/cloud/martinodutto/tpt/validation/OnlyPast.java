package cloud.martinodutto.tpt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyPastValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyPast {

    String message() default "A future date is not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
