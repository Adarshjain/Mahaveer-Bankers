/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

import static dukaansoftware.DukaanSoftware.primaryStage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class SelectBeneficiaryController implements Initializable {
    
    @FXML
    ComboBox comboBox;
    String[] AutoComplete = {""};
    String filename = "Autocomplete.txt";
    String Date1,Date2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null){
                    AutoComplete = line.split(",");
                }
            }
        }catch (IOException e){
            System.err.format("Exception occurred trying to read '%s'.", filename);
            System.out.println(""+e);
        }
        comboBox.getItems().addAll(AutoComplete);
    } 
    
    public void setString(String d1, String d2){
        Date1 = d1;
        Date2 = d2;
    }
    
    public void go() throws IOException{
        
        if(comboBox.getValue()!=null){
            String x = comboBox.getValue().toString();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayBeneficiary.fxml"));
            Parent root = (Parent) loader.load();
            DisplayBeneficiaryController controller = loader.getController();
            controller.setString(x,Date1,Date2);
            controller.display();
            Stage stage = new Stage();
            stage.setTitle(x);
            stage.setScene(new Scene(root,600,400));
            stage.setMaximized(true);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Select a Value");
            alert.showAndWait();
        }
    }
    
}
