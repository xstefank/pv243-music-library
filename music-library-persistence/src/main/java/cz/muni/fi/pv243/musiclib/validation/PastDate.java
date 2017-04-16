package cz.muni.fi.pv243.musiclib.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastDateValidator.class)
@Documented
public @interface PastDate {
    String message() default "Date should be from the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] members() default {};
}