import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Profil {
    //Attributs
    String nom;
    String duree;
    String ville;

    String contexte;
    List<String> profilRecherche;
    List<String> missions;
    List<Techno> technos;

    String contact;

    //Constructeurs
    public Profil(String chemin){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder parseur = factory.newDocumentBuilder();
            File xmlFile = new File(chemin);

            Document xml = parseur.parse(xmlFile);
            Element Offre = xml.getDocumentElement();

            NodeList nodes = Offre.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++){
                Node node = nodes.item(i);
                switch (node.getNodeName()){
                    case "title":
                        setNom(node.getNodeValue());
                        break;
                    case "duree":
                        setDuree(node.getNodeValue());
                        break;
                    case "ville":
                        setVille(node.getNodeValue());
                        break;
                    case "contexte":
                        setContexte(node.getNodeValue());
                        break;
                    case "profil":
                        addProfilRecherche(node.getNodeValue());
                        break;
                    case "missions":
                        addMission(node.getNodeValue());
                        break;
                    case "technos":
                        NodeList technos = node.getChildNodes();
                        Techno techno = new Techno();
                        for (int j = 0; j<technos.getLength(); j++){
                            Node n = technos.item(j);
                            if (n.getNodeName() == "nom") {
                                techno.setNom(n.getNodeValue());
                            }
                            if (n.getNodeName() == "image") {
                                Image logo = ImageIO.read(new File(n.getNodeValue()));
                                techno.setLogo(logo);
                            }
                        }
                        break;
                    case "contact":
                        setContact(node.getNodeValue());
                        break;
                    default:
                        break;
                }


            }

        }catch (ParserConfigurationException p){
            p.printStackTrace();
        }catch (SAXException s){
            s.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //Getters et setters
    public String getNom(){return nom;}
    public String getDuree(){return duree;}
    public String getVille(){return ville;}

    public String getContexte(){return contexte;}
    public String getProfilRecherche(int index){return profilRecherche.get(index);}
    public String getMission(int index){return missions.get(index);}
    public Techno getTechno(int index){return technos.get(index);}
    public String getContact() {return contact; }

    public void setNom(String nom){this.nom = nom;}
    public void setDuree(String duree){this.duree = duree;}
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

    public void setContact(String contact) {this.contact = contact; }
}
