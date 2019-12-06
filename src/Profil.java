import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        profilRecherche = new ArrayList<String>();
        missions = new ArrayList<String>();
        technos = new ArrayList<Techno>();

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
                        setNom(node.getTextContent());
                        break;
                    case "duree":
                        setDuree(node.getTextContent());
                        break;
                    case "ville":
                        setVille(node.getTextContent());
                        break;
                    case "contexte":
                        setContexte(node.getTextContent());
                        break;
                    case "profil":
                        NodeList points = node.getChildNodes();
                        for (int j = 0; j<points.getLength(); j++){
                             Node n = points.item(j);
                             if (n.getNodeName() == "point") {
                                 addProfilRecherche(n.getTextContent());
                             }
                        }
                        break;
                    case "missions":
                        NodeList missions = node.getChildNodes();
                        for (int j = 0; j<missions.getLength(); j++){
                            Node n = missions.item(j);
                            if (n.getNodeName() == "mission") {
                                addMission(n.getTextContent());
                            }
                        }
                        break;
                    case "technos":
                        NodeList technos = node.getChildNodes();
                        for (int j = 0; j<technos.getLength(); j++){
                            Techno techno = new Techno();
                            NodeList t = technos.item(j).getChildNodes();
                            for (int k = 0; k<t.getLength(); k++){
                                Node n = t.item(k);
                                if (n.getNodeName() == "nom"){
                                    techno.setNom(n.getTextContent());
                                }
                                if (n.getNodeName() == "image"){
                                    techno.setLogo(ImageIO.read(new File(n.getTextContent())));
                                }
                            }
                        }
                        break;
                    case "contact":
                        setContact(node.getTextContent());
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
