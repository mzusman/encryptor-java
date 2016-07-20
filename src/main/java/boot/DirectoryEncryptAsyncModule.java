package boot;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import filehandler.operations.DirectoryAsyncOperator;
import filehandler.operations.EncryptionOperator;
import filehandler.operations.Operator;
import lombok.AllArgsConstructor;
import utils.StreamManager;
import utils.files.DirectoryFilesManager;
import utils.files.EncryptionFilesManager;
import utils.files.FilesManager;
import utils.files.KeyFilesManager;

import java.io.File;

/**
 * Created by mzeus on 7/20/16.
 */
@AllArgsConstructor
public class DirectoryEncryptAsyncModule extends AbstractModule {
    File file;

    @Override
    protected void configure() {
        bind(Operator.class)
                .annotatedWith(Names.named(DirectoryAsyncOperator.BASE))
                .to(EncryptionOperator.class);
        bind(StreamManager.class).to(EncryptionFilesManager.class);
        bind(KeyFilesManager.class).toInstance(new KeyFilesManager(file));
        bind(DirectoryFilesManager.class);
        bind(FilesManager.class)
                .annotatedWith(Names.named("decorator"))
                .to(EncryptionFilesManager.class);
        bind(EncryptionFilesManager.class).toInstance(new EncryptionFilesManager(file));
    }
}
