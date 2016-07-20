package utils.files;

import com.google.inject.name.Named;
import utils.StreamManager;

import java.io.File;

/**
 * Created by mzeus on 7/20/16.
 */
public interface FilesManagerFactory {
    @Named("stream-files")
    StreamManager create(File file);

    KeyFilesManager getKeyFilesManager(File file);
}
