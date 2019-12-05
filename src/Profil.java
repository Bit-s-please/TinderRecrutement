import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.font.TextHitInfo;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Profil {
    //Attributs
    String nom;
    LocalDate duree;
    String ville;

    String contexte;
    List<String> profilRecherche;
    List<String> missions;
    List<Techno> technos;

    //Constructeurs
    public Profil(String chemin){
    }

    //Getters et setters
    public String getNom(){return nom;}
    public LocalDate getDuree(){return duree;}
    public String getVille(){return ville;}

    public String getContexte(){return contexte;}
    public String getProfilRecherche(int index){return profilRecherche.get(index);}
    public String getMission(int index){return missions.get(index);}
    public Techno getTechno(int index){return technos.get(index);}

    public void setNom(String nom){this.nom = nom;}
    public void setDuree(LocalDate duree){this.duree = duree;}
    public void setVille(String ville){this.ville = ville;}

    public void setContexte(String contexte){this.contexte = contexte;}

    public void addProfilRecherche(String profil){profilRecherche.add(profil);}
    public void removeProfilRecherche(int index){profilRecherche.remove(index);}
    public void removeProfilRecherche(String profil){profilRecherche.remove(profil);}
    public void clearProfilRecherche(){profilRecherche.clear();}

    public void addMission(String mission){missions.add(mission);}
    public void removeMission(int index){missions.remove(index);}
    public void removeMission(String mission){missions.remove(mission);}
    public void clearMission(){missions.clear();}

    public void addTechno(Techno tec){technos.add(tec);}
    public void removeTechno(int index){technos.remove(index);}
    public void removeTechno(Techno tec){technos.remove(tec);}
    public void clearTechno(){technos.clear();}

}
