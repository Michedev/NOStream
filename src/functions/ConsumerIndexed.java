package functions;

/**
 * Created by mikedev on 02/08/16.
 */
@FunctionalInterface
public interface ConsumerIndexed<T> {
    void alter(T e, int index);

}
