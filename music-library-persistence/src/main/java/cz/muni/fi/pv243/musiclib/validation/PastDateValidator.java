package cz.muni.fi.pv243.musiclib.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    @Override
    public void initialize(PastDate annotation) {
    }

    @Override
    public boolean isValid(LocalDate annotatedObject, ConstraintValidatorContext context) {
        if (annotatedObject == null) {
            return false;
        }
        return annotatedObject.isBefore(LocalDate.now());
    }
}