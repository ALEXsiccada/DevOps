package projetConsultation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BDConnector {
	static Connection connexion;

	public Map<String, String> getUserInformation(String matricule) {
		String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0071466"; // URL de connexion
		String utilisateur = "user0071466"; // Remplacez par votre nom d'utilisateur
		String motDePasse = "Yf3IgyBsOPa34WR"; // Remplacez par votre mot de passe
		
		Map<String, String> UserInfo= new HashMap<>();

		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			if (connexion != null) {
                System.out.println("Connexion à la base de données sakila réussie !");
                
                
                String sql = "SELECT * FROM etudiant where matricule="+matricule; // Sélectionne toutes les lignes de la table clients
                Statement statement = connexion.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                  
                  UserInfo.put("matricule", resultSet.getString("Matricule"));
                  UserInfo.put("nom", resultSet.getString("Nom"));
                  UserInfo.put("prenom", resultSet.getString("Prenom"));
                  UserInfo.put("datenaisssance", resultSet.getString("DateNaissance"));
                  UserInfo.put("ecole", resultSet.getString("Ecole"));
                  UserInfo.put("decision_etudiant", resultSet.getString("decision_etudiant"));
                  UserInfo.put("note",resultSet.getString("Note")) ;
                  
                  System.out.print("heho");

                }

                resultSet.close();
                statement.close();
                connexion.close();
                
                
            } else {
            	UserInfo=null;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return UserInfo;

	}
	

}
