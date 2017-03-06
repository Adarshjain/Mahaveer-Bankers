/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

// <editor-fold defaultstate="collapsed" desc="Imports">
import static dukaansoftware.DukaanSoftware.primaryStage;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
//</editor-fold>


/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class EditController implements Initializable {
    
    @FXML
    TextField OpeningBalance,Loan1,Interest1,Loan2,Interest2,Loan3,Drawings,
            ExtraAmt1,ExtraAmt2,ExtraAmt3,ExtraAmt4,ExtraAmt5,ExtraAmt6,ExtraAmt7,ExtraAmt8,ExtraAmt9,ExtraAmt10,
            ExtraName1,ExtraName2,ExtraName3,ExtraName4,ExtraName5,ExtraName6,ExtraName7,ExtraName8,ExtraName9,ExtraName10,
            closingBalance1,closingBalance2,closingBalance3,closingBalance4,closingBalance5;
    
    @FXML
    DatePicker datepicker;
    
    String Loan1TO = null,Interest1TO = null,Loan2TO = null,Interest2TO = null,Loan3TO = null,DrawingsTO = null,ExtraAmt1TO = null,ExtraAmt2TO = null,ExtraAmt3TO = null,ExtraAmt4TO = null,ExtraAmt5TO = null,ExtraAmt6TO = null,ExtraAmt7TO = null,ExtraAmt8TO = null,ExtraAmt9TO = null,ExtraAmt10TO = null,ExtraName1TO = null,ExtraName2TO = null,ExtraName3TO = null,ExtraName4TO = null,ExtraName5TO = null,ExtraName6TO = null,ExtraName7TO = null,ExtraName8TO = null,ExtraName9TO = null,ExtraName10TO = null;
    
    Double closingBalance = 0.00,OldClosingBalance = 0.00,difference = 0.00;
    String Date = "";
    Boolean Calculated = false;
    String finalDate = "";
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // <editor-fold defaultstate="collapsed" desc="KeyPress Events">
        OpeningBalance.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Loan1.requestFocus();
            }
        });
        Loan1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Interest1.requestFocus();
            }
        });
        Interest1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Loan2.requestFocus();
            }
        });
        Loan2.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Interest2.requestFocus();
            }
        });
        Interest2.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Loan3.requestFocus();
            }
        });
        
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="AutoComplete Code Hidden">
        String[] AutoComplete = {"Ashok Kumar Jain","Interest Income","Income Tax","Adarsh","Shilpa","Advance Tax","Salary","CUB","Stationary & Postage"};
        
        new AutoCompletionTextFieldBinding(ExtraName1, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName2, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName3, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName4, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName5, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName6, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName7, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName8, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName9, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        new AutoCompletionTextFieldBinding(ExtraName10, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="No text allowed">
        OpeningBalance.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            OpeningBalance.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Loan1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Loan1.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Loan2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Loan2.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Loan3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Loan3.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Interest1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Interest1.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Interest2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Interest2.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Drawings.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Drawings.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt1.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt2.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt3.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt4.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt4.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt5.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt5.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt6.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt6.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt7.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt7.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt8.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt8.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt9.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt9.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        ExtraAmt10.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            ExtraAmt10.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        
        //</editor-fold>
    }    
    
    
    public void dateselected() throws SQLException, ClassNotFoundException{
        OpeningBalance.setText("");Loan1.setText("");Interest1.setText("");Loan2.setText("");Interest2.setText("");Loan3.setText("");Drawings.setText("");ExtraAmt1.setText("");ExtraAmt2.setText("");ExtraAmt3.setText("");ExtraAmt4.setText("");ExtraAmt5.setText("");ExtraAmt6.setText("");ExtraAmt7.setText("");ExtraAmt8.setText("");ExtraAmt9.setText("");ExtraAmt10.setText("");ExtraName1.setText("");ExtraName2.setText("");ExtraName3.setText("");ExtraName4.setText("");ExtraName5.setText("");ExtraName6.setText("");ExtraName7.setText("");ExtraName8.setText("");ExtraName9.setText("");ExtraName10.setText("");closingBalance1.setText("");closingBalance2.setText("");closingBalance3.setText("");closingBalance4.setText("");closingBalance5.setText("");
        
            Class.forName("org.sqlite.JDBC");
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
            Statement stat = con.createStatement();
            
            
            // <editor-fold defaultstate="collapsed" desc="Fetch Data">
            LocalDate date = datepicker.getValue();
            
            Date = date.toString();
            String q = "SELECT * FROM data where date=\"" + Date + "\"";
            ResultSet rs = stat.executeQuery(q);
            while(rs.next()){
                if(rs.getString("place").equals("openbal")){
                    OpeningBalance.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("loan1")){
                    Loan1.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("interest1")){
                    Interest1.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("loan2")){
                    Loan2.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("interest2")){
                    Interest2.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("E1")){
                    ExtraAmt1.setText(rs.getString("amount"));
                    ExtraName1.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E2")){
                    ExtraAmt2.setText(rs.getString("amount"));
                    ExtraName2.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E3")){
                    ExtraAmt3.setText(rs.getString("amount"));
                    ExtraName3.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E4")){
                    ExtraAmt4.setText(rs.getString("amount"));
                    ExtraName4.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E5")){
                    ExtraAmt5.setText(rs.getString("amount"));
                    ExtraName5.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("loan3")){
                    Loan3.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("drawings")){
                    Drawings.setText(rs.getString("amount"));
                }
                if(rs.getString("place").equals("E6")){
                    ExtraAmt6.setText(rs.getString("amount"));
                    ExtraName6.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E7")){
                    ExtraAmt7.setText(rs.getString("amount"));
                    ExtraName7.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E8")){
                    ExtraAmt8.setText(rs.getString("amount"));
                    ExtraName8.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E9")){
                    ExtraAmt9.setText(rs.getString("amount"));
                    ExtraName9.setText(rs.getString("name"));
                }
                if(rs.getString("place").equals("E10")){
                    ExtraAmt10.setText(rs.getString("amount"));
                    ExtraName10.setText(rs.getString("name"));
                }
                if(rs.getString("name").equals("closingbalance")){
                    OldClosingBalance = Double.parseDouble(rs.getString("amount"));
                }
            }
            q = "SELECT date FROM data ORDER BY date DESC LIMIT 1";
            rs = stat.executeQuery(q);
            if(rs.next()){
                finalDate = rs.getString("date");
            }
            //</editor-fold>
            
            calculate1();
        }
    }
    
    public void doneButton() throws SQLException, ClassNotFoundException,ParseException,IOException{
        String Loan1T = Loan1.getText(),Interest1T = Interest1.getText(),Loan2T = Loan2.getText(),Interest2T = Interest2.getText(),Loan3T = Loan3.getText(),DrawingsT = Drawings.getText(),ExtraAmt1T = ExtraAmt1.getText(),ExtraAmt2T = ExtraAmt2.getText(),ExtraAmt3T = ExtraAmt3.getText(),ExtraAmt4T = ExtraAmt4.getText(),ExtraAmt5T = ExtraAmt5.getText(),ExtraAmt6T = ExtraAmt6.getText(),ExtraAmt7T = ExtraAmt7.getText(),ExtraAmt8T = ExtraAmt8.getText(),ExtraAmt9T = ExtraAmt9.getText(),ExtraAmt10T = ExtraAmt10.getText(),ExtraName1T = ExtraName1.getText(),ExtraName2T = ExtraName2.getText(),ExtraName3T = ExtraName3.getText(),ExtraName4T = ExtraName4.getText(),ExtraName5T = ExtraName5.getText(),ExtraName6T = ExtraName6.getText(),ExtraName7T = ExtraName7.getText(),ExtraName8T = ExtraName8.getText(),ExtraName9T = ExtraName9.getText(),ExtraName10T = ExtraName10.getText();

        if (!Calculated) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Calculate First!!");
            alert.show();
        }else{
            
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                
                String q;
                
                Stage stage = new Stage();
                stage.setTitle("Loading!!!");
                stage.setScene(new Scene(new Label(),200,30));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(primaryStage);
                stage.show();
                
                // <editor-fold defaultstate="collapsed" desc="Update/Insert">
                if (!Loan1T.equals("")){
                    if(Loan1TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'loan1',"+ new BigDecimal(Loan1T) +",'D','loan1')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(Loan1T) +" WHERE date=date('"+ Date +"') AND place='loan1'";
                    stat.executeUpdate(q);
                }else if(Loan1T.isEmpty() && !Loan1T.equals(Loan1TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='loan1'";
                    stat.executeUpdate(q);
                }
                if (!Interest1T.equals("")){
                    if(Interest1TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'interest1',"+ new BigDecimal(Interest1T) +",'D','interest1')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(Interest1T) +" WHERE date=date('"+ Date +"') AND place='interest1'";
                    stat.executeUpdate(q);
                }else if(Interest1T.isEmpty() && !Interest1T.equals(Interest1TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='interest1'";
                    stat.executeUpdate(q);
                }
                if (!Loan2T.equals("")){
                    if(Loan2TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'loan2',"+ new BigDecimal(Loan2T) +",'D','loan2')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(Loan2T) +" WHERE date=date('"+ Date +"') AND place='loan2'";
                    stat.executeUpdate(q);
                }else if(Loan2T.isEmpty() && !Loan2T.equals(Loan2TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='loan2'";
                    stat.executeUpdate(q);
                }
                if (!Interest2T.equals("")){
                    if(Interest2TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'interest2',"+ new BigDecimal(Interest2T) +",'D','interest2')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(Interest2T) +" WHERE date=date('"+ Date +"') AND place='interest2'";
                    stat.executeUpdate(q);
                }else if(Interest2T.isEmpty() && !Interest2T.equals(Interest2TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='interest2'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt1T.isEmpty()){
                    if(ExtraName1TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName1T + "',"+ new BigDecimal(ExtraAmt1T) +",'D','E1')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt1T) +",name='" + ExtraName1T + "'  WHERE date=date('"+ Date +"') AND place='E1'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt1T.isEmpty() && !ExtraAmt1T.equals(ExtraAmt1TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E1'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt2T.isEmpty()){
                    if(ExtraName2TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName2T + "',"+ new BigDecimal(ExtraAmt2T) +",'D','E2')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt2T) +",name='" + ExtraName2T + "' WHERE date=date('"+ Date +"') AND place='E2'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt2T.isEmpty() && !ExtraAmt2T.equals(ExtraAmt2TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E2'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt3T.isEmpty()){
                    if(ExtraName3TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName3T + "',"+ new BigDecimal(ExtraAmt3T) +",'D','E3')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt3T) +",name='" + ExtraName3T + "' WHERE date=date('"+ Date +"') AND place='E3'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt3T.isEmpty() && !ExtraAmt3T.equals(ExtraAmt3TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E3'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt4T.isEmpty()){
                    if(ExtraName4TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName4T + "',"+ new BigDecimal(ExtraAmt4T) +",'D','E4')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt4T) +",name='" + ExtraName4T + "' WHERE date=date('"+ Date +"') AND place='E4'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt4T.isEmpty() && !ExtraAmt4T.equals(ExtraAmt4TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E4'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt5T.isEmpty()){
                    if(ExtraName5TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName5T + "',"+ new BigDecimal(ExtraAmt5T) +",'D','E5')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt5T) +",name='" + ExtraName5T + "' WHERE date=date('"+ Date +"') AND place='E5'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt5T.isEmpty() && !ExtraAmt5T.equals(ExtraAmt5TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E5'";
                    stat.executeUpdate(q);
                }
                if (!Loan3T.equals("")){
                    if(Loan3TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'loan3',"+ new BigDecimal(Loan3T) +",'D','loan3')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(Loan3T) +" WHERE date=date('"+ Date +"') AND place='loan3'";
                    stat.executeUpdate(q);
                }else if(Loan3T.isEmpty() && !ExtraAmt4T.equals(Loan3TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='loan3'";
                    stat.executeUpdate(q);
                }
                if (!DrawingsT.equals("")){
                    if(DrawingsTO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'drawings',"+ new BigDecimal(DrawingsT) +",'D','drawings')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(DrawingsT) +" WHERE date=date('"+ Date +"') AND place='drawings'";
                    stat.executeUpdate(q);
                }else if(DrawingsT.isEmpty() && !DrawingsT.equals(DrawingsTO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='drawings'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt6T.isEmpty()){
                    if(ExtraName6TO.equals("")){
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName6T + "',"+ new BigDecimal(ExtraAmt6T) +",'D','E6')";
                        System.out.println("insert E6");
                    }else{
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt6T) +",name='" + ExtraName6T + "' WHERE date=date('"+ Date +"') AND place='E6'";
                        System.out.println("Update E6");
                    }stat.executeUpdate(q);
                }else if(ExtraAmt6T.isEmpty() && !ExtraAmt6T.equals(ExtraAmt6TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E6'";
                        System.out.println("Delete E6");
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt7T.isEmpty()){
                    if(ExtraName7TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName7T + "',"+ new BigDecimal(ExtraAmt7T) +",'D','E7')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt7T) +",name='" + ExtraName7T + "' WHERE date=date('"+ Date +"') AND place='E7'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt7T.isEmpty() && !ExtraAmt7T.equals(ExtraAmt7TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E7'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt8T.isEmpty()){
                    if(ExtraName8TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName8T + "',"+ new BigDecimal(ExtraAmt8T) +",'D','E8')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt8T) +",name='" + ExtraName8T + "' WHERE date=date('"+ Date +"') AND place='E8'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt8T.isEmpty() && !ExtraAmt8T.equals(ExtraAmt8TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E8'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt9T.isEmpty()){
                    if(ExtraName9TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName9T + "',"+ new BigDecimal(ExtraAmt9T) +",'D','E9')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt9T) +",name='" + ExtraName9T + "' WHERE date=date('"+ Date +"') AND place='E9'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt9T.isEmpty() && !ExtraAmt9T.equals(ExtraAmt9TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E9'";
                    stat.executeUpdate(q);
                }
                if (!ExtraAmt10T.isEmpty()){
                    if(ExtraName10TO.equals(""))
                        q = "INSERT INTO data VALUES (date('"+ Date +"'),'" + ExtraName10T + "',"+ new BigDecimal(ExtraAmt10T) +",'D','E10')";
                    else
                        q = "UPDATE data SET amount="+ new BigDecimal(ExtraAmt10T) +",name='" + ExtraName10T + "' WHERE date=date('"+ Date +"') AND place='E10'";
                    stat.executeUpdate(q);
                }else if(ExtraAmt10T.isEmpty() && !ExtraAmt10T.equals(ExtraAmt10TO)){
                    q = "DELETE FROM data WHERE date='" + Date + "' AND place='E10'";
                    stat.executeUpdate(q);
                }
                if (closingBalance != 0.00){
                    q = "UPDATE data SET amount="+ new BigDecimal(closingBalance) +" WHERE date=date('"+ Date +"') AND place='closingbalance'";
                    stat.executeUpdate(q);
                }
                if(difference != 0.00){
                    while(!Date.equals(finalDate)){
                        LocalDate date = LocalDate.parse(Date);
                        Date = date.plusDays(1).toString();
                        String open = "SELECT amount FROM data WHERE date='" + Date + "' AND name=\"openbal\"";
                        String close = "SELECT amount FROM data Where date='" + Date + "' AND name=\"closingbalance\"";
                        ResultSet openrs = stat.executeQuery(open);
                        
                        if(openrs.next()){
                            Double newOpenBal = Double.parseDouble(openrs.getString("amount")) + difference;
                            String newopenbal = "UPDATE data SET amount="+ new BigDecimal(newOpenBal.toString()) +" WHERE date=date('"+ Date +"') AND name='openbal'";
                            stat.executeUpdate(newopenbal);
                        }
                        ResultSet closers = stat.executeQuery(close);
                        if(closers.next()){
                            Double newCloseBal = Double.parseDouble(closers.getString("amount")) + difference;
                            String newclosebal = "UPDATE data SET amount="+ new BigDecimal(newCloseBal.toString()) +" WHERE date=date('"+ Date +"') AND name='closingbalance'";
                            stat.executeUpdate(newclosebal);
                        }
                    }
                }
                
                Calculated = false;
                //</editor-fold>
                
                stage.close();
                
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("Succesful");
                alert1.showAndWait();
            }
            
            // <editor-fold defaultstate="collapsed" desc="Clear Fields">
            OpeningBalance.setText("");Loan1.setText("");Interest1.setText("");Loan2.setText("");Interest2.setText("");Loan3.setText("");Drawings.setText("");ExtraAmt1.setText("");ExtraAmt2.setText("");ExtraAmt3.setText("");ExtraAmt4.setText("");ExtraAmt5.setText("");ExtraAmt6.setText("");ExtraAmt7.setText("");ExtraAmt8.setText("");ExtraAmt9.setText("");ExtraAmt10.setText("");ExtraName1.setText("");ExtraName2.setText("");ExtraName3.setText("");ExtraName4.setText("");ExtraName5.setText("");ExtraName6.setText("");ExtraName7.setText("");ExtraName8.setText("");ExtraName9.setText("");ExtraName10.setText("");closingBalance1.setText("");closingBalance2.setText("");closingBalance3.setText("");closingBalance4.setText("");closingBalance5.setText("");
            //</editor-fold>
        }   
    }
 
    

    
    public void calculate(){
        String OpenBalT = OpeningBalance.getText(),Loan1T = Loan1.getText(),Interest1T = Interest1.getText(),Loan2T = Loan2.getText(),Interest2T = Interest2.getText(),Loan3T = Loan3.getText(),DrawingsT = Drawings.getText(),ExtraAmt1T = ExtraAmt1.getText(),ExtraAmt2T = ExtraAmt2.getText(),ExtraAmt3T = ExtraAmt3.getText(),ExtraAmt4T = ExtraAmt4.getText(),ExtraAmt5T = ExtraAmt5.getText(),ExtraAmt6T = ExtraAmt6.getText(),ExtraAmt7T = ExtraAmt7.getText(),ExtraAmt8T = ExtraAmt8.getText(),ExtraAmt9T = ExtraAmt9.getText(),ExtraAmt10T = ExtraAmt10.getText(),ExtraName1T = ExtraName1.getText(),ExtraName2T = ExtraName2.getText(),ExtraName3T = ExtraName3.getText(),ExtraName4T = ExtraName4.getText(),ExtraName5T = ExtraName5.getText(),ExtraName6T = ExtraName6.getText(),ExtraName7T = ExtraName7.getText(),ExtraName8T = ExtraName8.getText(),ExtraName9T = ExtraName9.getText(),ExtraName10T = ExtraName10.getText();        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        Double OpenBalV = 0.00,Loan1V = 0.00,Interest1V = 0.00,Loan2V = 0.00,Interest2V = 0.00,Loan3V = 0.00,DrawingsV = 0.00,ExtraAmt1V = 0.00,ExtraAmt2V = 0.00,ExtraAmt3V = 0.00,ExtraAmt4V = 0.00,ExtraAmt5V = 0.00,ExtraAmt6V = 0.00,ExtraAmt7V = 0.00,ExtraAmt8V = 0.00,ExtraAmt9V = 0.00,ExtraAmt10V = 0.00;        
        
        Boolean ShowErrorBox = false;
        
        LocalDate date = datepicker.getValue();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (date != null) {
            Date = date.toString();
        }else{
            Date = "";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Date!!");
            alert.showAndWait();
        }
        
        // <editor-fold defaultstate="collapsed" desc="Check Pair Fields">
        if ((ExtraName1T.isEmpty()&&!ExtraAmt1T.isEmpty())||(!ExtraName1T.isEmpty()&&ExtraAmt1T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName2T.isEmpty()&&!ExtraAmt2T.isEmpty())||(!ExtraName2T.isEmpty()&&ExtraAmt2T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName3T.isEmpty()&&!ExtraAmt3T.isEmpty())||(!ExtraName3T.isEmpty()&&ExtraAmt3T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName4T.isEmpty()&&!ExtraAmt4T.isEmpty())||(!ExtraName4T.isEmpty()&&ExtraAmt4T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName5T.isEmpty()&&!ExtraAmt5T.isEmpty())||(!ExtraName5T.isEmpty()&&ExtraAmt5T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName6T.isEmpty()&&!ExtraAmt6T.isEmpty())||(!ExtraName6T.isEmpty()&&ExtraAmt6T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName7T.isEmpty()&&!ExtraAmt7T.isEmpty())||(!ExtraName7T.isEmpty()&&ExtraAmt7T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName8T.isEmpty()&&!ExtraAmt8T.isEmpty())||(!ExtraName8T.isEmpty()&&ExtraAmt8T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName9T.isEmpty()&&!ExtraAmt9T.isEmpty())||(!ExtraName9T.isEmpty()&&ExtraAmt9T.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName10T.isEmpty()&&!ExtraAmt10T.isEmpty())||(!ExtraName10T.isEmpty()&&ExtraAmt10T.isEmpty())) {
            ShowErrorBox = true;
        }
        //</editor-fold>
        
        if (ShowErrorBox) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all the fields!!");
            alert.showAndWait();
        }else{
            // <editor-fold defaultstate="collapsed" desc="Fetch And Calculate">
            if (!OpenBalT.equals("")) {
                OpenBalV = Double.parseDouble(OpenBalT);
            }
            if (!Loan1T.equals("")) {
                Loan1V = Double.parseDouble(Loan1T);
            }
            if (!Interest1T.equals("")) {
                Interest1V = Double.parseDouble(Interest1T);
            }
            if (!Loan2T.equals("")) {
                Loan2V = Double.parseDouble(Loan2T);
            }
            if (!Interest2T.equals("")) {
                Interest2V = Double.parseDouble(Interest2T);
            }
            if (!Loan3T.equals("")) {
                Loan3V = Double.parseDouble(Loan3T);
            }
            if (!DrawingsT.equals("")) {
                DrawingsV = Double.parseDouble(DrawingsT);
            }
            if (!ExtraAmt1T.isEmpty()) {
                ExtraAmt1V = Double.parseDouble(ExtraAmt1T);
            }
            if (!ExtraAmt2T.isEmpty()) {
                ExtraAmt2V = Double.parseDouble(ExtraAmt2T);
            }
            if (!ExtraAmt3T.isEmpty()) {
                ExtraAmt3V = Double.parseDouble(ExtraAmt3T);
            }
            if (!ExtraAmt4T.isEmpty()) {
                ExtraAmt4V = Double.parseDouble(ExtraAmt4T);
            }
            if (!ExtraAmt5T.isEmpty()) {
                ExtraAmt5V = Double.parseDouble(ExtraAmt5T);
            }
            if (!ExtraAmt6T.isEmpty()) {
                ExtraAmt6V = Double.parseDouble(ExtraAmt6T);
            }
            if (!ExtraAmt7T.isEmpty()) {
                ExtraAmt7V = Double.parseDouble(ExtraAmt7T);
            }
            if (!ExtraAmt8T.isEmpty()) {
                ExtraAmt8V = Double.parseDouble(ExtraAmt8T);
            }
            if (!ExtraAmt9T.isEmpty()) {
                ExtraAmt9V = Double.parseDouble(ExtraAmt9T);
            }
            if (!ExtraAmt10T.isEmpty()) {
                ExtraAmt10V = Double.parseDouble(ExtraAmt10T);
            }
        
            Double ClosingBal1 = OpenBalV + Loan1V + Interest1V + Loan2V + Interest2V + ExtraAmt1V + ExtraAmt2V + ExtraAmt3V + ExtraAmt4V + ExtraAmt5V;
            Double ClosingBal2 = Loan3V + DrawingsV + ExtraAmt6V + ExtraAmt7V + ExtraAmt8V + ExtraAmt9V + ExtraAmt10V;
            Double FinalValue = ClosingBal1 - ClosingBal2;

            closingBalance = Double.parseDouble(df.format(FinalValue));

            closingBalance1.setText(df.format(ClosingBal1));
            closingBalance2.setText(df.format(ClosingBal1));
            closingBalance3.setText(df.format(ClosingBal1));
            closingBalance4.setText(df.format(ClosingBal2));
            closingBalance5.setText(df.format(FinalValue));
            
            
            difference = Double.parseDouble(df.format(closingBalance - OldClosingBalance));
            //</editor-fold>            
            
            Calculated = true;
        }
    }
    
    public void calculate1(){
        String OpenBalTO = OpeningBalance.getText();
        Loan1TO = Loan1.getText();Interest1TO = Interest1.getText();Loan2TO = Loan2.getText();Interest2TO = Interest2.getText();Loan3TO = Loan3.getText();DrawingsTO = Drawings.getText();ExtraAmt1TO = ExtraAmt1.getText();ExtraAmt2TO = ExtraAmt2.getText();ExtraAmt3TO = ExtraAmt3.getText();ExtraAmt4TO = ExtraAmt4.getText();ExtraAmt5TO = ExtraAmt5.getText();ExtraAmt6TO = ExtraAmt6.getText();ExtraAmt7TO = ExtraAmt7.getText();ExtraAmt8TO = ExtraAmt8.getText();ExtraAmt9TO = ExtraAmt9.getText();ExtraAmt10TO = ExtraAmt10.getText();ExtraName1TO = ExtraName1.getText();ExtraName2TO = ExtraName2.getText();ExtraName3TO = ExtraName3.getText();ExtraName4TO = ExtraName4.getText();ExtraName5TO = ExtraName5.getText();ExtraName6TO = ExtraName6.getText();ExtraName7TO = ExtraName7.getText();ExtraName8TO = ExtraName8.getText();ExtraName9TO = ExtraName9.getText();ExtraName10TO = ExtraName10.getText();        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        Double OpenBalV = 0.00,Loan1V = 0.00,Interest1V = 0.00,Loan2V = 0.00,Interest2V = 0.00,Loan3V = 0.00,DrawingsV = 0.00,ExtraAmt1V = 0.00,ExtraAmt2V = 0.00,ExtraAmt3V = 0.00,ExtraAmt4V = 0.00,ExtraAmt5V = 0.00,ExtraAmt6V = 0.00,ExtraAmt7V = 0.00,ExtraAmt8V = 0.00,ExtraAmt9V = 0.00,ExtraAmt10V = 0.00;        
        
        Boolean ShowErrorBox = false;
        
        LocalDate date = datepicker.getValue();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (date != null) {
            Date = date.toString();
        }else{
            Date = "";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Date!!");
            alert.showAndWait();
        }
        
        // <editor-fold defaultstate="collapsed" desc="Check Pair Fields(Old)">
        if ((ExtraName1TO.isEmpty()&&!ExtraAmt1TO.isEmpty())||(!ExtraName1TO.isEmpty()&&ExtraAmt1TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName2TO.isEmpty()&&!ExtraAmt2TO.isEmpty())||(!ExtraName2TO.isEmpty()&&ExtraAmt2TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName3TO.isEmpty()&&!ExtraAmt3TO.isEmpty())||(!ExtraName3TO.isEmpty()&&ExtraAmt3TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName4TO.isEmpty()&&!ExtraAmt4TO.isEmpty())||(!ExtraName4TO.isEmpty()&&ExtraAmt4TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName5TO.isEmpty()&&!ExtraAmt5TO.isEmpty())||(!ExtraName5TO.isEmpty()&&ExtraAmt5TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName6TO.isEmpty()&&!ExtraAmt6TO.isEmpty())||(!ExtraName6TO.isEmpty()&&ExtraAmt6TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName7TO.isEmpty()&&!ExtraAmt7TO.isEmpty())||(!ExtraName7TO.isEmpty()&&ExtraAmt7TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName8TO.isEmpty()&&!ExtraAmt8TO.isEmpty())||(!ExtraName8TO.isEmpty()&&ExtraAmt8TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName9TO.isEmpty()&&!ExtraAmt9TO.isEmpty())||(!ExtraName9TO.isEmpty()&&ExtraAmt9TO.isEmpty())) {
            ShowErrorBox = true;
        }
        if ((ExtraName10TO.isEmpty()&&!ExtraAmt10TO.isEmpty())||(!ExtraName10TO.isEmpty()&&ExtraAmt10TO.isEmpty())) {
            ShowErrorBox = true;
        }
        //</editor-fold>
        
        if (ShowErrorBox) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please check all the fields!!");
            alert.showAndWait();
        }else{
            // <editor-fold defaultstate="collapsed" desc="Fetch And Calculate(Old)">
            if (!OpenBalTO.equals("")) {
                OpenBalV = Double.parseDouble(OpenBalTO);
            }
            if (!Loan1TO.equals("")) {
                Loan1V = Double.parseDouble(Loan1TO);
            }
            if (!Interest1TO.equals("")) {
                Interest1V = Double.parseDouble(Interest1TO);
            }
            if (!Loan2TO.equals("")) {
                Loan2V = Double.parseDouble(Loan2TO);
            }
            if (!Interest2TO.equals("")) {
                Interest2V = Double.parseDouble(Interest2TO);
            }
            if (!Loan3TO.equals("")) {
                Loan3V = Double.parseDouble(Loan3TO);
            }
            if (!DrawingsTO.equals("")) {
                DrawingsV = Double.parseDouble(DrawingsTO);
            }
            if (!ExtraAmt1TO.isEmpty()) {
                ExtraAmt1V = Double.parseDouble(ExtraAmt1TO);
            }
            if (!ExtraAmt2TO.isEmpty()) {
                ExtraAmt2V = Double.parseDouble(ExtraAmt2TO);
            }
            if (!ExtraAmt3TO.isEmpty()) {
                ExtraAmt3V = Double.parseDouble(ExtraAmt3TO);
            }
            if (!ExtraAmt4TO.isEmpty()) {
                ExtraAmt4V = Double.parseDouble(ExtraAmt4TO);
            }
            if (!ExtraAmt5TO.isEmpty()) {
                ExtraAmt5V = Double.parseDouble(ExtraAmt5TO);
            }
            if (!ExtraAmt6TO.isEmpty()) {
                ExtraAmt6V = Double.parseDouble(ExtraAmt6TO);
            }
            if (!ExtraAmt7TO.isEmpty()) {
                ExtraAmt7V = Double.parseDouble(ExtraAmt7TO);
            }
            if (!ExtraAmt8TO.isEmpty()) {
                ExtraAmt8V = Double.parseDouble(ExtraAmt8TO);
            }
            if (!ExtraAmt9TO.isEmpty()) {
                ExtraAmt9V = Double.parseDouble(ExtraAmt9TO);
            }
            if (!ExtraAmt10TO.isEmpty()) {
                ExtraAmt10V = Double.parseDouble(ExtraAmt10TO);
            }
        
            Double ClosingBal1 = OpenBalV + Loan1V + Interest1V + Loan2V + Interest2V + ExtraAmt1V + ExtraAmt2V + ExtraAmt3V + ExtraAmt4V + ExtraAmt5V;
            Double ClosingBal2 = Loan3V + DrawingsV + ExtraAmt6V + ExtraAmt7V + ExtraAmt8V + ExtraAmt9V + ExtraAmt10V;
            Double FinalValue = ClosingBal1 - ClosingBal2;

            closingBalance = Double.parseDouble(df.format(FinalValue));

            closingBalance1.setText(df.format(ClosingBal1));
            closingBalance2.setText(df.format(ClosingBal1));
            closingBalance3.setText(df.format(ClosingBal1));
            closingBalance4.setText(df.format(ClosingBal2));
            closingBalance5.setText(df.format(FinalValue));
           
            difference = Double.parseDouble(df.format(closingBalance - OldClosingBalance));
            
            //</editor-fold>
        }
    }
}