package porridge.my.way.dddarchitecturej.architecture.core;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@FieldNameConstants
final class Person {
    private final String name;
    private final int age;
}

class SelectorTest {
    @Test
    void getPropertyName() {
        ISelector<Person, Integer> selector = Selector.set(Person::getAge, Person.Fields.age);

        assertThat(selector.getPropertyName()).isEqualTo("age");
    }

    @Test
    void getValue() {
        ISelector<Person, Integer> selector = Selector.set(Person::getAge, Person.Fields.age);
        Person person = new Person("John", 20);

        Integer age = selector.getValue(person);

        assertThat(age).isEqualTo(20);
    }
}