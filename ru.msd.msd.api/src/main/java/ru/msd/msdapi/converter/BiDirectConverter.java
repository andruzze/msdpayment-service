package ru.msd.msdapi.converter;

/**
 * A bidirectional converter interface that extends {@link Converter} to provide conversion
 * capabilities in both directions between two types.
 *
 * <p>This interface adds reverse conversion functionality to the base {@link Converter} contract,
 * allowing conversion from type {@code T} back to type {@code E}.
 *
 * @param <E> the source type for forward conversion and target type for reverse conversion
 * @param <T> the target type for forward conversion and source type for reverse conversion
 * @see Converter
 */

public interface BiDirectConverter<E, T> extends Converter<E, T> {
    E convertFrom(T obj);
}
