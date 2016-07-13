package Functions;

/**
 * Created by mikedev on 13/07/16.
 */

@FunctionalInterface
public interface Modifier<T> {
    void alter(T e);

}
