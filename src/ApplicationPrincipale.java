import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import java.io.File;

import java.util.ArrayList;
import java.util.List;


public class ApplicationPrincipale {

	private JFrame frame;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private final Color colorHover = new Color(169, 171, 184);
	private final Color colorLeroyMerlin = new Color(121, 187, 48);
	
	public static int panelToDisplay = 1;
	public static JPanel chatPanel;
	public static JPanel matchPanel;
	
	//MATCH ELEMENTS :
    public static List<Profil> offres = new ArrayList<Profil>();
    public static JLabel imageLabel = new JLabel();
    public static JLabel labelNomDuPoste = new JLabel();
    public static JLabel labelDuree = new JLabel();
    public static JLabel labelVille = new JLabel();
    public static int indexProfilCourant = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Initialisation des fichiers :
        File folder = new File("./XMLExamples/");
        List<String> filesPath = Controleur.listFilesForFolder(folder);

        for (String filePath : filesPath) {
            if(Controleur.getExtensionOfFile(filePath).equals("xml")){
                offres.add(new Profil(filePath));
            }
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationPrincipale window = new ApplicationPrincipale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationPrincipale() {
		initialize();
	}
	
	public static void getNextProfile() {
		if(indexProfilCourant < offres.size()-1 ) {
			indexProfilCourant++;
			imageLabel.setIcon(new ImageIcon(offres.get(indexProfilCourant).getPicture()));
			labelNomDuPoste.setText(offres.get(indexProfilCourant).getNom());;
		    labelDuree.setText(offres.get(indexProfilCourant).getDuree());
		    labelVille.setText(offres.get(indexProfilCourant).getVille());
		}
		else {
			System.out.println("Plus d'offres disponibles !");
		}
	}
	
	public static void togglePanel() {
		if(panelToDisplay == 0) {
			chatPanel.setVisible(false);
			matchPanel.setVisible(true);
			panelToDisplay++;
		}
		else {
			chatPanel.setVisible(true);
			matchPanel.setVisible(false);
			panelToDisplay--;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		imageLabel.setIcon(new ImageIcon(offres.get(0).getPicture()));
		labelNomDuPoste.setText(offres.get(0).getNom());
	    labelDuree.setText(offres.get(0).getDuree());
	    labelVille.setText(offres.get(0).getVille());
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		frame = new JFrame();
		ImageIcon logo = new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/leroy.png"));
		frame.setIconImage(logo.getImage());
		frame.setBounds(dim.width/2-540/2, dim.height/2-960/2, 540, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		matchPanel = new JPanel();
		matchPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_matchPanel = new GridBagConstraints();
		gbc_matchPanel.fill = GridBagConstraints.BOTH;
		gbc_matchPanel.gridx = 0;
		gbc_matchPanel.gridy = 0;
		frame.getContentPane().add(matchPanel, gbc_matchPanel);
		GridBagLayout gbl_matchPanel = new GridBagLayout();
		gbl_matchPanel.columnWidths = new int[]{0, 0};
		gbl_matchPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_matchPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_matchPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		matchPanel.setLayout(gbl_matchPanel);
		
		JPanel topMenuPanel = new JPanel();
		topMenuPanel.setBackground(colorLeroyMerlin);
		GridBagConstraints gbc_topMenuPanel = new GridBagConstraints();
		gbc_topMenuPanel.anchor = GridBagConstraints.NORTH;
		gbc_topMenuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topMenuPanel.gridx = 0;
		gbc_topMenuPanel.gridy = 0;
		matchPanel.add(topMenuPanel, gbc_topMenuPanel);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/chat_button.png")).getImage().getScaledInstance(50, 46, Image.SCALE_DEFAULT));
        GridBagLayout gbl_topMenuPanel = new GridBagLayout();
        gbl_topMenuPanel.columnWidths = new int[]{33, 33, 33, 0};
        gbl_topMenuPanel.rowHeights = new int[]{47, 0};
        gbl_topMenuPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_topMenuPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        topMenuPanel.setLayout(gbl_topMenuPanel);
        
        JLabel leroyLogo = new JLabel();
        leroyLogo.setIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/leroy_resized.png")));
        leroyLogo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_leroyLogo = new GridBagConstraints();
        gbc_leroyLogo.fill = GridBagConstraints.HORIZONTAL;
        gbc_leroyLogo.insets = new Insets(0, 0, 0, 5);
        gbc_leroyLogo.gridx = 1;
        gbc_leroyLogo.gridy = 0;
        topMenuPanel.add(leroyLogo, gbc_leroyLogo);
        
        JButton chatButton = new JButton("");
        chatButton.setToolTipText("Accéder au chat");
        chatButton.setOpaque(true);
        chatButton.setBorder(null);
        chatButton.setBackground(colorLeroyMerlin);
        chatButton.setIcon(imageIcon);
        chatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				togglePanel();
            }
		});
        chatButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	chatButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	chatButton.setBackground(colorLeroyMerlin);
		    }
		});
        GridBagConstraints gbc_chatButton = new GridBagConstraints();
        gbc_chatButton.insets = new Insets(5, 0, 5, 0);
        gbc_chatButton.anchor = GridBagConstraints.NORTHEAST;
        gbc_chatButton.gridx = 2;
        gbc_chatButton.gridy = 0;
        topMenuPanel.add(chatButton, gbc_chatButton);
        
        JPanel matchBodyPanel = new JPanel();
        matchBodyPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc_matchBodyPanel = new GridBagConstraints();
        gbc_matchBodyPanel.fill = GridBagConstraints.BOTH;
        gbc_matchBodyPanel.gridx = 0;
        gbc_matchBodyPanel.gridy = 1;
        matchPanel.add(matchBodyPanel, gbc_matchBodyPanel);
        GridBagLayout gbl_matchBodyPanel = new GridBagLayout();
        gbl_matchBodyPanel.columnWidths = new int[]{249, 0};
        gbl_matchBodyPanel.rowHeights = new int[]{0, 0, 0};
        gbl_matchBodyPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_matchBodyPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        matchBodyPanel.setLayout(gbl_matchBodyPanel);
        
        JPanel matchImagePanel = new JPanel();
        matchImagePanel.setBackground(Color.WHITE);
        GridBagConstraints gbc_matchImagePanel = new GridBagConstraints();
        gbc_matchImagePanel.weighty = 90.0;
        gbc_matchImagePanel.gridx = 0;
        gbc_matchImagePanel.gridy = 0;
        matchBodyPanel.add(matchImagePanel, gbc_matchImagePanel);

        matchImagePanel.add(imageLabel);
        
        JPanel matchDescriptionPanel = new JPanel(); // NOM DUREE VILLE
        matchDescriptionPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_matchDescriptionPanel = new GridBagConstraints();
        gbc_matchDescriptionPanel.weighty = 20.0;
        gbc_matchDescriptionPanel.fill = GridBagConstraints.BOTH;
        gbc_matchDescriptionPanel.gridx = 0;
        gbc_matchDescriptionPanel.gridy = 1;
        matchBodyPanel.add(matchDescriptionPanel, gbc_matchDescriptionPanel);
        GridBagLayout gbl_matchDescriptionPanel = new GridBagLayout();
        gbl_matchDescriptionPanel.columnWidths = new int[]{0, 0};
        gbl_matchDescriptionPanel.rowHeights = new int[]{0, 0};
        gbl_matchDescriptionPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_matchDescriptionPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        matchDescriptionPanel.setLayout(gbl_matchDescriptionPanel);
        
        JPanel insideOfMatchDescriptionPanel = new JPanel();
        GridBagConstraints gbc_insideOfMatchDescriptionPanel = new GridBagConstraints();
        gbc_insideOfMatchDescriptionPanel.insets = new Insets(10, 10, 10, 10);
        gbc_insideOfMatchDescriptionPanel.fill = GridBagConstraints.BOTH;
        gbc_insideOfMatchDescriptionPanel.gridx = 0;
        gbc_insideOfMatchDescriptionPanel.gridy = 0;
        matchDescriptionPanel.add(insideOfMatchDescriptionPanel, gbc_insideOfMatchDescriptionPanel);
        GridBagLayout gbl_insideOfMatchDescriptionPanel = new GridBagLayout();
        gbl_insideOfMatchDescriptionPanel.columnWidths = new int[]{216, 0};
        gbl_insideOfMatchDescriptionPanel.rowHeights = new int[]{14, 0, 0, 0};
        gbl_insideOfMatchDescriptionPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_insideOfMatchDescriptionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        insideOfMatchDescriptionPanel.setLayout(gbl_insideOfMatchDescriptionPanel);
        
        labelNomDuPoste.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 30));
        GridBagConstraints gbc_lblNomDuPoste = new GridBagConstraints();
        gbc_lblNomDuPoste.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNomDuPoste.insets = new Insets(0, 0, 5, 0);
        gbc_lblNomDuPoste.gridx = 0;
        gbc_lblNomDuPoste.gridy = 0;
        insideOfMatchDescriptionPanel.add(labelNomDuPoste, gbc_lblNomDuPoste);
        
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_label_1.insets = new Insets(0, 0, 5, 0);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 1;
        insideOfMatchDescriptionPanel.add(labelDuree, gbc_label_1);
        
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_label_2.anchor = GridBagConstraints.NORTH;
        gbc_label_2.gridx = 0;
        gbc_label_2.gridy = 2;
        insideOfMatchDescriptionPanel.add(labelVille, gbc_label_2);
        
        JPanel bottomPanelSurrounder = new JPanel();
        bottomPanelSurrounder.setBackground(colorLeroyMerlin);
        GridBagConstraints gbc_bottomPanelSurrounder = new GridBagConstraints();
        gbc_bottomPanelSurrounder.fill = GridBagConstraints.BOTH;
        gbc_bottomPanelSurrounder.gridx = 0;
        gbc_bottomPanelSurrounder.gridy = 2;
        matchPanel.add(bottomPanelSurrounder, gbc_bottomPanelSurrounder);
        GridBagLayout gbl_bottomPanelSurrounder = new GridBagLayout();
        gbl_bottomPanelSurrounder.columnWidths = new int[]{0, 0};
        gbl_bottomPanelSurrounder.rowHeights = new int[]{0, 0};
        gbl_bottomPanelSurrounder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_bottomPanelSurrounder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        bottomPanelSurrounder.setLayout(gbl_bottomPanelSurrounder);
        
        JPanel bottomMatchPanel = new JPanel();
        GridBagConstraints gbc_bottomMatchPanel = new GridBagConstraints();
        gbc_bottomMatchPanel.insets = new Insets(8, 0, 8, 0);
        gbc_bottomMatchPanel.gridx = 0;
        gbc_bottomMatchPanel.gridy = 0;
        bottomPanelSurrounder.add(bottomMatchPanel, gbc_bottomMatchPanel);
        bottomMatchPanel.setBackground(colorLeroyMerlin);
        GridBagLayout gbl_bottomMatchPanel = new GridBagLayout();
        gbl_bottomMatchPanel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_bottomMatchPanel.rowHeights = new int[]{0, 0};
        gbl_bottomMatchPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_bottomMatchPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        bottomMatchPanel.setLayout(gbl_bottomMatchPanel);
        
        JButton infoButton = new JButton("");
        infoButton.setToolTipText("Détails");
        infoButton.setOpaque(true);
        infoButton.setBorder(null);
        infoButton.setBackground(colorLeroyMerlin);
        infoButton.setIcon(new ImageIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/info_button.png")).getImage().getScaledInstance(50, 46, Image.SCALE_DEFAULT)));
        infoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("erign");
            }
		});
        infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	infoButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	infoButton.setBackground(colorLeroyMerlin);
		    }
		});
        
        JButton dislikeButton = new JButton();
        dislikeButton.setToolTipText("Disike");
        dislikeButton.setOpaque(true);
        dislikeButton.setBorder(null);
        dislikeButton.setBackground(colorLeroyMerlin);
        dislikeButton.setIcon(new ImageIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/dislike.png")).getImage().getScaledInstance(50, 46, Image.SCALE_DEFAULT)));
        dislikeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getNextProfile();
            }
		});
        dislikeButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	dislikeButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	dislikeButton.setBackground(colorLeroyMerlin);
		    }
		});
        GridBagConstraints gbc_dislikeButton = new GridBagConstraints();
        gbc_dislikeButton.insets = new Insets(0, 0, 0, 5);
        gbc_dislikeButton.gridx = 0;
        gbc_dislikeButton.gridy = 0;
        bottomMatchPanel.add(dislikeButton, gbc_dislikeButton);
        GridBagConstraints gbc_infoButton = new GridBagConstraints();
        gbc_infoButton.insets = new Insets(0, 0, 0, 5);
        gbc_infoButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_infoButton.gridx = 1;
        gbc_infoButton.gridy = 0;
        bottomMatchPanel.add(infoButton, gbc_infoButton);
        
        JButton likeButton = new JButton();
        likeButton.setToolTipText("Like");
        likeButton.setOpaque(true);
        likeButton.setBorder(null);
        likeButton.setBackground(colorLeroyMerlin);
        likeButton.setIcon(new ImageIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/like.png")).getImage().getScaledInstance(50, 46, Image.SCALE_DEFAULT)));
        likeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getNextProfile();
            }
		});
        likeButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	likeButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	likeButton.setBackground(colorLeroyMerlin);
		    }
		});
        GridBagConstraints gbc_likeButton = new GridBagConstraints();
        gbc_likeButton.gridx = 2;
        gbc_likeButton.gridy = 0;
        bottomMatchPanel.add(likeButton, gbc_likeButton);
		
        chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_chatPanel = new GridBagConstraints();
		gbc_chatPanel.fill = GridBagConstraints.BOTH;
		gbc_chatPanel.gridx = 0;
		gbc_chatPanel.gridy = 0;
		frame.getContentPane().add(chatPanel, gbc_chatPanel);
		GridBagLayout gbl_chatPanel = new GridBagLayout();
		gbl_chatPanel.columnWidths = new int[]{0, 0};
		gbl_chatPanel.rowHeights = new int[]{0, 0};
		gbl_chatPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_chatPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		chatPanel.setLayout(gbl_chatPanel);
		
		JPanel chatTopPanel = new JPanel();
		chatTopPanel.setBackground(new Color(121, 187, 48));
		GridBagConstraints gbc_chatTopPanel = new GridBagConstraints();
		gbc_chatTopPanel.anchor = GridBagConstraints.NORTH;
		gbc_chatTopPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_chatTopPanel.gridx = 0;
		gbc_chatTopPanel.gridy = 0;
		chatPanel.add(chatTopPanel, gbc_chatTopPanel);
		GridBagLayout gbl_chatTopPanel = new GridBagLayout();
		gbl_chatTopPanel.columnWidths = new int[]{33, 33, 33, 0};
		gbl_chatTopPanel.rowHeights = new int[]{47, 0};
		gbl_chatTopPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_chatTopPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		chatTopPanel.setLayout(gbl_chatTopPanel);
		
		JLabel leroyLogo2 = new JLabel();
		leroyLogo2.setIcon(new ImageIcon(ApplicationPrincipale.class.getClassLoader().getResource("ressources/leroy_resized.png")));
		leroyLogo2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_leroyLogo2 = new GridBagConstraints();
		gbc_leroyLogo2.fill = GridBagConstraints.HORIZONTAL;
		gbc_leroyLogo2.insets = new Insets(0, 0, 0, 5);
		gbc_leroyLogo2.gridx = 1;
		gbc_leroyLogo2.gridy = 0;
		chatTopPanel.add(leroyLogo2, gbc_leroyLogo2);
		
		JButton chatButton2 = new JButton("");
		chatButton2.setToolTipText("Accéder au chat");
		chatButton2.setOpaque(true);
		chatButton2.setBorder(null);
		chatButton2.setBackground(colorLeroyMerlin);
		chatButton2.setIcon(imageIcon);
        chatButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				togglePanel();
            }
		});
        chatButton2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	chatButton2.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	chatButton2.setBackground(colorLeroyMerlin);
		    }
		});
		chatButton2.setToolTipText("Acc\u00E9der au chat");
		chatButton2.setOpaque(true);
		chatButton2.setBorder(null);
		chatButton2.setBackground(new Color(121, 187, 48));
		GridBagConstraints gbc_chatButton2 = new GridBagConstraints();
		gbc_chatButton2.insets = new Insets(5, 0, 5, 0);
		gbc_chatButton2.anchor = GridBagConstraints.NORTHEAST;
		gbc_chatButton2.gridx = 2;
		gbc_chatButton2.gridy = 0;
		chatTopPanel.add(chatButton2, gbc_chatButton2);
		
		
		chatPanel.setVisible(false);
		matchPanel.setVisible(true);
	}

}
