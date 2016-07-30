package modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import utils.files.DirectoryFilesManager;

/**
 * Created by mzeus on 7/21/16.
 */
public class DirectoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DirectoryFilesManager.class);
        bind(int.class)
                .annotatedWith(Names.named("NTHREADS")).toInstance(5);
    }
}
