import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File folder = new File("./XMLExamples/");
        List<String> filesPath = Controleur.listFilesForFolder(folder);

        List<Profil> offres = new ArrayList<Profil>();

        for (String filePath : filesPath) {
            offres.add(new Profil(filePath));
        }

        for (int i = 0; i < offres.size(); i++){
            System.out.println(offres.get(i).getNom());
        }


    }

}
