package porridge.my.way.dddarchitecturej.architecture.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
@AllArgsConstructor
public class SpecificationRule<T> {
    private final String message;
    private final Predicate<T> validate;
}
