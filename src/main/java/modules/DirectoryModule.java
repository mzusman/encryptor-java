package modules;

import com.google.inject.AbstractModule;
import utils.files.DirectoryFilesManager;

/**
 * Created by mzeus on 7/21/16.
 */
public class DirectoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DirectoryFilesManager.class);

    }
}
