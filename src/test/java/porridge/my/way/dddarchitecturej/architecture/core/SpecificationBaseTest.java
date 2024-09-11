package porridge.my.way.dddarchitecturej.architecture.core;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@FieldNameConstants
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class SomethingValueObject extends ValueObject {
    private String string;
    private int number;
    private boolean bool;
    private Instant instant;

    public static Try<SomethingValueObject> create(String string, int number, boolean bool, Instant instant) {
        Specification<SomethingValueObject> specification = Specification.create(
                Selector.set(SomethingValueObject::getString, Fields.string),
                Selector.set(SomethingValueObject::getNumber, Fields.number),
                Selector.set(SomethingValueObject::isBool, Fields.bool),
                Selector.set(SomethingValueObject::getInstant, Fields.instant));
        SomethingValueObject instance = new SomethingValueObject(string, number, bool, instant);
        return specification.isSatisfiedBy(instance);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(string, number, bool, instant);
    }
}

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Specification<T> extends SpecificationBase<T> {
    private final ISelector<T, String> stringSelector;
    private final ISelector<T, Integer> numberSelector;
    private final ISelector<T, Boolean> booleanSelector;
    private final ISelector<T, Instant> instantSelector;

    public static <T> Specification<T> create(ISelector<T, String> stringSelector, ISelector<T, Integer> numberSelector, ISelector<T, Boolean> booleanSelector, ISelector<T, Instant> instantSelector) {
        return new Specification<T>(stringSelector, numberSelector, booleanSelector, instantSelector);
    }

    @Override
    protected List<SpecificationRule<T>> getSpecificationRules() {
        return List.of(
                new SpecificationRule<>(String.format("%s 應該不可為空或空字串", stringSelector.getPropertyName()), arg -> !Strings.isNullOrEmpty(stringSelector.getValue(arg))),
                new SpecificationRule<>(String.format("%s 應該大於等於 0", numberSelector.getPropertyName()), arg -> numberSelector.getValue(arg) >= 0),
                new SpecificationRule<>(String.format("%s 應該為 True", booleanSelector.getPropertyName()), booleanSelector::getValue),
                new SpecificationRule<>(String.format("%s 應該小於系統的現在時間(UTC)", instantSelector.getPropertyName()), arg -> instantSelector.getValue(arg).isBefore(Instant.now()))
        );
    }
}

class SpecificationBaseTest {
    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(null, 1, true, Instant.ofEpochMilli(0L)),
                Arguments.of("", 1, true, Instant.ofEpochMilli(0L)),
                Arguments.of("string", -1, true, Instant.ofEpochMilli(0L)),
                Arguments.of("string", 1, false, Instant.ofEpochMilli(0L)),
                Arguments.of("string", 1, true, Instant.now().plusSeconds(10))
        );
    }

    @Test
    void isSatisfiedBy_true() {
        String string = "string";
        int number = 1;
        boolean bool = true;
        Instant instant = Instant.ofEpochMilli(0L);

        var somethingValueObject = SomethingValueObject.create(string, number, bool, instant);

        assertThat(somethingValueObject.isSuccess()).isTrue();
        assertThat(somethingValueObject.get().getString()).isEqualTo(string);
        assertThat(somethingValueObject.get().getNumber()).isEqualTo(number);
        assertThat(somethingValueObject.get().isBool()).isEqualTo(bool);
        assertThat(somethingValueObject.get().getInstant()).isEqualTo(instant);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void isSatisfiedBy_false(String string, int number, boolean bool, Instant instant) {

        var somethingValueObject = SomethingValueObject.create(string, number, bool, instant);

        assertThat(somethingValueObject.isFailure()).isTrue();
    }
}