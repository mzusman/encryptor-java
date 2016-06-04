package commandline;

import utils.Selectable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public interface UserInterface {


    void handleArguments(String[] arg);

    void showOptions();

    Selectable selectSelectable(List<? extends Selectable> selectables, String s) throws IOException;



}
