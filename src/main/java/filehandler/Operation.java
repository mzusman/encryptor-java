package filehandler;

import java.io.File;

/**
 * Created by mzeus on 29/05/16.
 */
public interface Operation {
    String getDescription();
    String act(File file);

}
