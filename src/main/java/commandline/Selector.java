package commandline;

import java.io.IOException;
import java.util.List;

/**
 * Created by mzeus on 28/07/16.
 */
public interface Selector<T> {
    T selectFromList(List list) throws InstantiationException, IllegalAccessException, IOException;
}
