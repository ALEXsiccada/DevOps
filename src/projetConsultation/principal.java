package projetConsultation;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class principal extends Application {
	          
	@Override
    public void start(Stage primaryStage) throws Exception {
		 //stage pour la création de fenêtre
        sreenOne(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
    //Methode pour crée la première page pour entrer le matricule de l'étudiant 
    public void sreenOne(Stage primaryStage) {
    	
    	AnchorPane root = new AnchorPane();//la grande page de départ PAPA 
    	
    	VBox aligneverticalement = new VBox(20);//permet d'aligner verticalement PAPA2
    	
    	Label labelbienvenue = new Label("BIENVENUE SUR LE CITE DE CONSULTATION DES RÉSULTATS DE PIGIER\r\n"+ "");
    	labelbienvenue.setStyle("-fx-font-weight: bold; -fx-underline: true;");//titre de la grande page 1
    	
    	Label labelresultats = new Label();//titre de la grande page 2
    	
    	TextField Textfield1 = new TextField("veuillez entrer votre matricule");//Zone de saisie du matricule
    	
    	Button detail = new Button("AFFICHER LES DETAILS");
    	detail.setStyle("-fx-font-weight: bold;");//Button de détail
    	
    	Button valider = new Button("VALIDER");// Button de validation
    	valider.setStyle("-fx-font-weight: bold;");// Mettre le texte en gras; // Button de validation
    	valider.setPrefSize(300, 10);// Changer la couleur du bouton en vert clair au survol
    	valider.setOnMouseEntered(event -> valider.setStyle("-fx-background-color: lightgreen;"));
    	valider.setOnMouseExited(event -> valider.setStyle("-fx-background-color: initial;")); // Remettre la couleur initiale
    	aligneverticalement.getChildren().addAll(labelbienvenue,Textfield1,valider);//père
    	aligneverticalement.setAlignment(Pos.CENTER);
    	
    	valider.setOnAction(even ->{
    		//screenTwo(primaryStage);
    		
    		BDConnector bdconnector = new BDConnector();
    		
    		Map<String, String> userInfo= new HashMap<>();
    		
    		String matricule= Textfield1.getText();
    		if(matricule=="") {
    			displayInfo("INFORMATION","Veuillez saisir le matricule");
    		}else {
    			
    			userInfo = bdconnector.getUserInformation(Textfield1.getText());
    			
    			if(userInfo.isEmpty()) {
    				displayInfo("INFORMATION","matricule non trouvé");
    			}else {
    				
    				if(Integer.parseInt(userInfo.get("note"))>=10) {
    					
    					aligneverticalement.getChildren().addAll(labelresultats,detail);//père
    					aligneverticalement.getChildren().remove(valider);//père
    					labelresultats.setText("SUCCÈS");
    					labelresultats.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        			}else {
        				aligneverticalement.getChildren().addAll(labelresultats,detail);//père
    					aligneverticalement.getChildren().remove(valider);//père
        				labelresultats.setText("ECHEC");
        			}
    			}		
    		}
    	});
    	
    	//centrer le bouton
    	AnchorPane.setTopAnchor(aligneverticalement,20.0);
    	AnchorPane.setBottomAnchor(aligneverticalement,20.0);
    	AnchorPane.setLeftAnchor(aligneverticalement,20.0);
    	AnchorPane.setRightAnchor(aligneverticalement,20.0);
    	/*
    	//définir la taille du boutton
    	valider.setMinWidth(100);
    	detail.setMinHeight(50);
    	
    	//Ajjuster les ancres pour centere le bouton*/
    	
    	root.getChildren().add(aligneverticalement);//grand père
    	
        Scene scene = new Scene(root, 900, 300);
		
		primaryStage.setTitle("CONSULATATION DE RESULTATS");
		
		primaryStage.setScene(scene);
		
        primaryStage.show();	
    }
   
    //numero 2 
    public void screenTwo(Stage primaryStage) {
    	AnchorPane root = new AnchorPane();
    	
        Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setTitle("CONSULTATION DE RESULTAT");
		
		primaryStage.setScene(scene);
		
        primaryStage.show();	
    }
    public static void displayInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
      }
}
