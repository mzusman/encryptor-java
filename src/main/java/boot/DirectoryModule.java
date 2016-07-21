package boot;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import filehandler.operations.DecryptionOperator;
import filehandler.operations.DirectoryAsyncOperator;
import filehandler.operations.Operator;
import utils.files.DecryptionFilesManager;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

/**
 * Created by mzeus on 7/21/16.
 */
public class DirectoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DirectoryFilesManager.class);

    }
}
