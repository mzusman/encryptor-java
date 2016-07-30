package modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import domain.operations.DecryptionOperator;
import domain.operations.DirectoryAsyncOperator;
import domain.operations.DirectorySyncOperator;
import domain.operations.AbstractOperation;
import lombok.AllArgsConstructor;
import utils.files.StreamManager;
import utils.files.DecryptionFilesManager;
import utils.files.AbstractFilesManager;

import java.io.File;

/**
 * Created by mzeus on 7/21/16.
 */
@AllArgsConstructor
public class DecryptModule extends AbstractModule {
    /**
     * The File.
     */
    File file;

    @Override
    protected void configure() {
        install(new KeyModule(file));
        install(new DirectoryModule());
        install(new LoggingModule(file));
        bind(StreamManager.class).to(DecryptionFilesManager.class);
        bind(DecryptionFilesManager.class).toInstance(new DecryptionFilesManager(file));
        bind(AbstractOperation.class)
                .annotatedWith(Names.named(DirectoryAsyncOperator.BASE))
                .to(DecryptionOperator.class);


        bind(AbstractOperation.class)
                .annotatedWith(Names.named(DirectorySyncOperator.BASE))
                .to(DecryptionOperator.class);

        bind(AbstractFilesManager.class)
                .annotatedWith(Names.named("decorator"))
                .to(DecryptionFilesManager.class);
    }

}
