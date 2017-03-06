/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author geekyadarsh
 */
public class DukaanSoftware extends Application {
    
    public static Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        DukaanSoftware.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("DukaanUI.fxml"));        
        primaryStage.setTitle("Mahaveer Bankers");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }
    
    
    public void addbutton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NewBill.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Data");
        stage.setScene(new Scene(root,902,590));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();    
    }
    
    public void editbutton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Data");
        stage.setScene(new Scene(root,902,590));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();    
    }
    
    public void viewButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Displayui.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,600,400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();   
    }
    
    public void viewLoan() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Displayloan.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,600,400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();   
        
    }
    
    public void viewInterest() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DisplayInterestRC.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,600,400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();   
        
    }
    
    public void viewIntInc()  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DisplayIntInc.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,600,400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setMaximized(true);
        stage.show();   
    }
    
    public void viewSelect() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DisplaySelect.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,200,100));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.show();
    }
    
    
    public void backup() throws IOException{
        BackupDB backupDB = new BackupDB();
        if (backupDB.isInternetReachable()) {
            String zipName = backupDB.zipDB();
            if(!zipName.equals("")){
               if(backupDB.runGDrive(zipName)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful Backup!!");
                    alert.showAndWait();
               }
            }else{
                System.out.println("Error while creating zip file!!");
            }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No Internet Connection!!");
            alert.showAndWait();
        }     
    }
    
//    public void exportAll(){
//        ExportMain exportMain = new ExportMain();
//        String data;
//        Alert alert;
//        if(!exportMain.expertmain()){
//            data = "Export Unuccesfull!!";
//            alert = new Alert(Alert.AlertType.WARNING);
//        }else{
//            data = "Successfully Exported";
//            alert = new Alert(Alert.AlertType.INFORMATION);
//        }
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText(data);
//        alert.showAndWait();
//    }
    
    public void addBeneficiary() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddBeneficiary.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,600,400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.show();
    }
    
    public void viewBeneficiary() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SelectBeneficiary.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.setScene(new Scene(root,200,100));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}