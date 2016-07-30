package modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import domain.operations.DirectoryAsyncOperator;
import domain.operations.DirectorySyncOperator;
import domain.operations.EncryptionOperator;
import domain.operations.AbstractOperation;
import lombok.AllArgsConstructor;
import utils.files.StreamManager;
import utils.files.EncryptionFilesManager;
import utils.files.AbstractFilesManager;

import java.io.File;

/**
 * Created by mzeus on 7/21/16.
 */
@AllArgsConstructor
public class EncryptModule extends AbstractModule {
    /**
     * The File.
     */
    File file;

    @Override
    protected void configure() {
        install(new KeyModule(file));
        install(new DirectoryModule());
        install(new LoggingModule(file));
        bind(StreamManager.class).to(EncryptionFilesManager.class);
        bind(EncryptionFilesManager.class).toInstance(new EncryptionFilesManager(file));
        bind(AbstractFilesManager.class)
                .annotatedWith(Names.named("decorator"))
                .to(EncryptionFilesManager.class);

        bind(AbstractOperation.class)
                .annotatedWith(Names.named(DirectoryAsyncOperator.BASE))
                .to(EncryptionOperator.class);

        bind(AbstractOperation.class)
                .annotatedWith(Names.named(DirectorySyncOperator.BASE))
                .to(EncryptionOperator.class);
    }
}
