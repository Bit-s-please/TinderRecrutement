import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File folder = new File("./XMLExamples/");
        List<String> filesPath = Controleur.listFilesForFolder(folder);

        List<Profil> offres = new ArrayList<Profil>();

        for (String filePath : filesPath) {
            if(Controleur.getExtensionOfFile(filePath).equals("xml")){
                offres.add(new Profil(filePath));
            }
        }


        for (int i = 0; i < offres.size(); i++){
            System.out.println("Nom : "+offres.get(i).getNom());
            System.out.println("Durée : "+offres.get(i).getDuree());
            System.out.println("Contexte : "+offres.get(i).getContexte());
            System.out.println("Profil recherché :");
            for (int j = 0; j < offres.get(i).getProfilRechercheLength(); j++){
                System.out.println(offres.get(i).getProfilRecherche(j));
            }
            System.out.println("Mission :");
            for (int j = 0; j < offres.get(i).getMissionLength(); j++){
                System.out.println(offres.get(i).getMission(j));
            }
            System.out.println("Technos :");
            for (int j = 0; j < offres.get(i).getTechnoLength(); j++){
                System.out.println(offres.get(i).getTechno(j).getNom());
            }
            System.out.println("Merci de contacter : "+offres.get(i).getContact());
            System.out.println();
        }




    }

}
