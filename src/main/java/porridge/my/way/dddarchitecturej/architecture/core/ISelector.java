package porridge.my.way.dddarchitecturej.architecture.core;

public interface ISelector<TSource, TDestination> {
    String getPropertyName();

    TDestination getValue(TSource source);
}
