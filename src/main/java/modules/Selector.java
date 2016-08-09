package modules;

import java.io.IOException;
import java.util.List;

/**
 * Created by mzeus on 28/07/16.
 *
 * @param <T> the type parameter
 */
interface Selector<T> {
    /**
     * Select from list t.
     *
     * @param list the list
     * @return the t
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws IOException            the io exception
     */
    T selectFromList(List list) throws InstantiationException, IllegalAccessException, IOException;
}
