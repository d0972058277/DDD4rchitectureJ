package porridge.my.way.dddarchitecturej;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class PorriDG3TestExtension implements BeforeEachCallback {
    private static final AnnotationConfigApplicationContext serviceCollection = new AnnotationConfigApplicationContext(AppConfig.class);

    @SneakyThrows
    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Optional<Object> testInstanceOpt = extensionContext.getTestInstance();
        if (testInstanceOpt.isEmpty()) {
            return;
        }

        Object testInstance = testInstanceOpt.get();
        List<Field> injectFields = AnnotationSupport.findAnnotatedFields(testInstance.getClass(), PorriDG3Inject.class);

        for (Field field : injectFields) {
            field.setAccessible(true);
            Object service = serviceCollection.getBean(field.getType());
            field.set(testInstance, service);
        }
    }
}
