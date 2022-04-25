package spd.trello.configuration.Reminder;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReminderValidator.class)
public @interface IReminder {

    String message() default "Incorrect reminder ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
