package com.trizic.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AssetValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface ValidAsset {

    String message() default "{sum of assests is not 100}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}