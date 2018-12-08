package cloud.martinodutto.tpt.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class OnlyPastValidator implements ConstraintValidator<OnlyPast, LocalDate> {

    @Override
    public void initialize(OnlyPast dateRange) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        final LocalDate max = LocalDate.now();
        return localDate == null || localDate.isBefore(max) || localDate.isEqual(max);
    }
}
