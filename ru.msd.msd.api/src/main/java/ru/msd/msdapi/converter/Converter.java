package ru.msd.msdapi.converter;

/**
 * A generic interface for converting objects of one type to another.
 *
 * @param <E> the type of the input object to be converted (source type)
 * @param <T> the type of the output object after conversion (target type)
 */
public interface Converter<E, T> {
    T convert(E obj);
}
