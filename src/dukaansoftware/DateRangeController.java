package dukaansoftware;

import static dukaansoftware.DukaanSoftware.primaryStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DateRangeController implements Initializable {

    @FXML
    DatePicker date1,date2;
    String d1 = "",d2 = "";
    String Target;
    @FXML
    TextField fyear;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void go() throws IOException{
        if(date1.getValue() == null || date2.getValue() == null){
            return;
        }
        d1 = date1.getValue().toString();
        d2 = date2.getValue().toString();
        stagecall();
    }
    
    public void fyeargo() throws IOException{
        if(fyear.getText().isEmpty()){
            return;
        }
        int year = Integer.parseInt(fyear.getText());
        d1 = year + "-04-01";
        d2 = year + 1 + "-03-31";
        stagecall();
    }
    
    private void stagecall() throws IOException{
        FXMLLoader loader;
        Parent root;
        Stage stage = new Stage();
        stage.setTitle("Mahaveer Bankers");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        switch(Target){
            case "DisplayUI":
                loader = new FXMLLoader(getClass().getResource("Displayui.fxml"));
                root = (Parent) loader.load();
                DisplayuiController controller = loader.getController();
                controller.setString(d1,d2);
                controller.display();
                stage.setScene(new Scene(root,600,400));
                stage.setMaximized(true);
                stage.show(); 
                break;
            case "Displayloan":
                loader = new FXMLLoader(getClass().getResource("Displayloan.fxml"));
                root = (Parent) loader.load();
                DisplayloanController controller1 = loader.getController();
                controller1.setString(d1,d2);
                controller1.display();
                stage.setScene(new Scene(root,600,400));
                stage.setMaximized(true);              
                stage.show(); 
                break;
            case "DisplayInterestRC":
                loader = new FXMLLoader(getClass().getResource("DisplayInterestRC.fxml"));
                root = (Parent) loader.load();
                DisplayInterestRCController controller2 = loader.getController();
                controller2.setString(d1,d2);
                controller2.display();
                stage.setScene(new Scene(root,600,400));
                stage.setMaximized(true);
                stage.show(); 
                break;
            case "DisplayIntInc":
                loader = new FXMLLoader(getClass().getResource("DisplayIntInc.fxml"));
                root = (Parent) loader.load();
                DisplayIntIncController controller3 = loader.getController();
                controller3.setString(d1,d2);
                controller3.display();
                stage.setScene(new Scene(root,600,400));
                stage.setMaximized(true);
                stage.show(); 
                break;
            case "DisplaySelect":
                loader = new FXMLLoader(getClass().getResource("DisplaySelect.fxml"));
                root = (Parent) loader.load();
                DisplaySelectController controller4 = loader.getController();
                controller4.setString(d1,d2);
                stage.setScene(new Scene(root,200,100));
                stage.show(); 
                break;
            case "SelectBeneficiary":
                loader = new FXMLLoader(getClass().getResource("SelectBeneficiary.fxml"));
                root = (Parent) loader.load();
                SelectBeneficiaryController controller5 = loader.getController();
                controller5.setString(d1,d2);
                stage.setScene(new Scene(root,200,100));
                stage.show(); 
                break;
            case "Compare":
                loader = new FXMLLoader(getClass().getResource("Compare.fxml"));
                root = (Parent) loader.load();
                CompareController controller6 = loader.getController();
                controller6.setString(d1,d2);
                controller6.display();
                stage.setScene(new Scene(root,600,400));
                stage.setMaximized(true);
                stage.show(); 
                break;
        }
    }
    
    
    public void setString(String target){
        Target = target;
    }
    
}
