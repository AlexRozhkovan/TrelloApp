package spd.trello.service;

import spd.trello.domain.parent_classes.Domain;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

//@Service
public class ValidatorService<T extends Domain> {

    private Validator validator;

    public ValidatorService(Validator validator) {
        this.validator = validator;
    }

    public void validateInputWithInjectedValidator(T input) {
        Set<ConstraintViolation<T>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
