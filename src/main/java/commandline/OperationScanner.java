package commandline;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

/**
 * Created by mzeus on 28/07/16.
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class OperationScanner implements CommandlineScanner {
    String pattern = "^(enc|dec)(\\s)(s|a)(\\s)(\\S+)(\\s)$";

    @Override
    public boolean scanForPattern(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : args) {
            stringBuilder.append(s).append(" ");
        }
        String arg = stringBuilder.toString();
        return arg.matches(pattern);
    }

    public void showOptions() {
        System.out.println("usage: encryptor <enc|dec> <s|a> <file|dir>");
    }
}
