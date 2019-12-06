import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controleur {
    public static List<String> listFilesForFolder(final File folder) {
        List<String> files = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry.getPath());
            }
        }
        return files;
    }
}
