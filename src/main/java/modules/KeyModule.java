package modules;

import com.google.inject.AbstractModule;
import lombok.AllArgsConstructor;
import utils.files.KeyFilesManager;

import java.io.File;

/**
 * Created by mzeus on 7/21/16.
 */
@AllArgsConstructor
public class KeyModule extends AbstractModule{
    /**
     * The File.
     */
    File file;
    @Override
    protected void configure() {
        bind(KeyFilesManager.class).toInstance(new KeyFilesManager(file));
    }
}
