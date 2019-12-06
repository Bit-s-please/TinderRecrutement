Participation du groupe "Bit's, please !" au défi Leroy Merlin de la nuit de l'info 2019.
Cette application se prénomme "LeroyMatcheur", pas du tout inspiré par un réseau social de rencontre connu ccommençant par Tin et se finnissant par der...

Pour que l'application fonctionne, il faut créer un dossier nommé "XMLOffres" dans lequel seront disposés les offres d'emplois au format XML.

Format fichier <xml> d'offre d'emploi : 
<?xml version="1.0" encoding="UTF-8"?>
<Offre>
	<title>Titre dee stage</title>>
	<duree>Durée de l'emploi / Stage</duree>
	<ville>Ville</ville>
	<image>Chemin vers l'image</image>
	<contexte>Explication du poste</contexte>
	<profil>
		<point>Critère n°1 pour le profil recherché</point>
		<point>Critère n°2 pour le profil recherché</point>
	</profil>
	<missions>
		<mission>Mission n°1 de l'emploi / stage</mission>
		<mission>Mission n°2 de l'emploi / stage</mission>
	</missions>
	<technos>
		<techno>
			<nom>Nom d'une techno nécessaire</nom>
			<image>Logo de cette techno</image>
		</techno>
	</technos>
	<contact>mail ou contact vers la personne en charge de cette offre de recrutement</contact>
</Offre>