package boot;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import filehandler.operations.DecryptionOperator;
import filehandler.operations.DirectoryAsyncOperator;
import filehandler.operations.DirectorySyncOperator;
import filehandler.operations.Operator;
import lombok.AllArgsConstructor;
import utils.StreamManager;
import utils.files.DecryptionFilesManager;
import utils.files.FilesManager;

import java.io.File;

/**
 * Created by mzeus on 7/21/16.
 */
@AllArgsConstructor
public class DecryptModule extends AbstractModule {
    File file;

    @Override
    protected void configure() {
        install(new KeyModule(file));
        install(new DirectoryModule());
        bind(StreamManager.class).to(DecryptionFilesManager.class);
        bind(DecryptionFilesManager.class).toInstance(new DecryptionFilesManager(file));

        bind(Operator.class)
                .annotatedWith(Names.named(DirectoryAsyncOperator.BASE))
                .to(DecryptionOperator.class);

        bind(Operator.class)
                .annotatedWith(Names.named(DirectorySyncOperator.BASE))
                .to(DecryptionOperator.class);

        bind(FilesManager.class)
                .annotatedWith(Names.named("decorator"))
                .to(DecryptionFilesManager.class);
    }

}
