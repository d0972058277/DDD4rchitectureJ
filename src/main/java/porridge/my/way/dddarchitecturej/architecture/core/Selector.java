package porridge.my.way.dddarchitecturej.architecture.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Function;

public class Selector {
    public static <TSource, TDestination> ISelector<TSource, TDestination> set(Function<TSource, TDestination> propertySelector, String propertyName) {
        return new SelectorImpl<>(propertySelector, propertyName);
    }

    public static <TSource, TProperty, TDestination> ISelector<TSource, TDestination> set(
            Function<TSource, TProperty> propertySelector, Function<TProperty, TDestination> factory, String propertyName) {
        return new SelectorImpl<>(source -> factory.apply(propertySelector.apply(source)), propertyName);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class SelectorImpl<TSource, TDestination> implements ISelector<TSource, TDestination> {
        private final Function<TSource, TDestination> factory;
        private final String propertyName;

        @Override
        public String getPropertyName() {
            return propertyName;
        }

        @Override
        public TDestination getValue(TSource source) {
            return factory.apply(source);
        }
    }
}
