package boot;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import filehandler.operations.DecryptionOperator;
import filehandler.operations.DirectoryAsyncOperator;
import filehandler.operations.EncryptionOperator;
import filehandler.operations.Operator;
import utils.StreamManager;
import utils.files.*;

/**
 * Created by mzeus on 7/20/16.
 */
public class DirectoryModule extends AbstractModule {

    @Override
    protected void configure() {


        bind(StreamManager.class).
                annotatedWith(Names.named("encrypt")).to(EncryptionFilesManager.class);
        bind(StreamManager.class).
                annotatedWith(Names.named("decrypt")).to(DecryptionFilesManager.class);

        bind(FilesManager.class).annotatedWith
                (Names.named("decorator")).to(DirectoryFilesManager.class);

        bind(Operator.class).annotatedWith
                (Names.named(DirectoryAsyncOperator.BASE))
                .to(Operator.class);



    }
}
