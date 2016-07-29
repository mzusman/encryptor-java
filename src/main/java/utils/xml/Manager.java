package utils.xml;

import com.google.inject.Inject;
import domain.operations.CommandsEnum;
import exceptions.FileErrorException;
import lombok.AllArgsConstructor;
import utils.XmlAlgorithm;
import utils.status.FileEnd;
import utils.status.FileStart;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mzeus on 29/07/16.
 */

@AllArgsConstructor(onConstructor = @__({@Inject}))
public class Manager implements Observer {


    Logging logging;
    XmlReportManager reportManager;

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Throwable) {
            if (o instanceof FileErrorException) {
                logging.fileError(((FileErrorException) o).getFile(), (Throwable) o);
                reportManager.writeFileError(((FileErrorException) o).getFile(), (Throwable) o);
            }
        }
        if (o instanceof FileStart) {
            logging.fileStarted(((FileStart) o).getFile());
        }
        if (o instanceof FileEnd) {
            logging.fileFinished(((FileEnd) o).getFile());
            reportManager.writeFileDone(((FileEnd) o).getFile());
        }
        if (o.equals(CommandsEnum.END_OPT))
            reportManager.writeReport();


    }
}
