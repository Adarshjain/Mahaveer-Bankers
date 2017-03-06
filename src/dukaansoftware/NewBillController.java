/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class NewBillController implements Initializable {

    @FXML
    TextField OpeningBalance,Loan1,Interest1,Loan3,
            ExtraAmt1,ExtraAmt2,ExtraAmt3,ExtraAmt4,ExtraAmt5,ExtraAmt6,ExtraAmt7,ExtraAmt8,ExtraAmt9,ExtraAmt10,
            ExtraName1,ExtraName2,ExtraName3,ExtraName4,ExtraName5,ExtraName6,ExtraName7,ExtraName8,ExtraName9,ExtraName10,
            closingBalance1,closingBalance2,closingBalance3,closingBalance4,closingBalance5;
    
    @FXML
    DatePicker datepicker;
    
    Double closingBalance = 0.00;
    String Date = "";
    Boolean Calculated = false,currentDate = false;
    LocalDate date;
    
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
        Loan3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Loan3.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
            Calculated = false;
        });
        Interest1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            Interest1.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
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
        
        Class.forName("org.sqlite.JDBC");
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
            Statement stat = con.createStatement();
            
            
            date = datepicker.getValue();
            System.out.println(date);

            if(date != null){
                Date = date.toString();
                String q = "SELECT amount FROM data where name=\"openbal\" and date=\"" + Date + "\"";
                ResultSet rs = stat.executeQuery(q);
                if(rs.next()){
                    alertBox("This Date is already Entered!", "w");
                    currentDate = false;
                }else{

                    if(date.getDayOfWeek().getValue() == 1) Date = date.minusDays(2).toString();
                    else Date = date.minusDays(1).toString();
                    q = "SELECT amount FROM data where name=\"closingbalance\" and date=\"" + Date + "\"";
                    ResultSet rs1 = stat.executeQuery(q);
                    if(rs1.next()){
                        String amount = rs.getString("amount");
                        OpeningBalance.setText(amount);
                    }
                    currentDate = true;
                }
            }
        }
    }
    
    public void doneButton() throws SQLException, ClassNotFoundException{
        String OpenBalT = OpeningBalance.getText(),Loan1T = Loan1.getText(),Interest1T = Interest1.getText(),Loan3T = Loan3.getText(),ExtraAmt1T = ExtraAmt1.getText(),ExtraAmt2T = ExtraAmt2.getText(),ExtraAmt3T = ExtraAmt3.getText(),ExtraAmt4T = ExtraAmt4.getText(),ExtraAmt5T = ExtraAmt5.getText(),ExtraAmt6T = ExtraAmt6.getText(),ExtraAmt7T = ExtraAmt7.getText(),ExtraAmt8T = ExtraAmt8.getText(),ExtraAmt9T = ExtraAmt9.getText(),ExtraAmt10T = ExtraAmt10.getText(),ExtraName1T = ExtraName1.getText(),ExtraName2T = ExtraName2.getText(),ExtraName3T = ExtraName3.getText(),ExtraName4T = ExtraName4.getText(),ExtraName5T = ExtraName5.getText(),ExtraName6T = ExtraName6.getText(),ExtraName7T = ExtraName7.getText(),ExtraName8T = ExtraName8.getText(),ExtraName9T = ExtraName9.getText(),ExtraName10T = ExtraName10.getText();

        if (!Calculated) {
            alertBox("Please Calculate First!!", "w");
        }else if(!currentDate){
            alertBox("Pleaase Check Date!!", "w");
        }else{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db");
            Statement stat = con.createStatement();

            String q = "INSERT INTO data VALUES";
        
            
            if (!OpenBalT.equals(""))
                q += "(date('"+ Date +"'),'openbal',"+ new BigDecimal(OpenBalT) +",'D','openbal'),";
            if (!Loan1T.equals(""))
                q += "(date('"+ Date +"'),'loan1',"+ new BigDecimal(Loan1T) +",'D','loan1'),";
            if (!Interest1T.equals(""))
                q += "(date('"+ Date +"'),'interest1',"+ new BigDecimal(Interest1T) +",'D','interest1'),";
            if (!ExtraAmt1T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName1T +"',"+ new BigDecimal(ExtraAmt1T) +",'D','E1'),";
            if (!ExtraAmt2T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName2T +"',"+ new BigDecimal(ExtraAmt2T) +",'D','E2'),";
            if (!ExtraAmt3T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName3T +"',"+ new BigDecimal(ExtraAmt3T) +",'D','E3'),";
            if (!ExtraAmt4T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName4T +"',"+ new BigDecimal(ExtraAmt4T) +",'D','E4'),";
            if (!ExtraAmt5T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName5T +"',"+ new BigDecimal(ExtraAmt5T) +",'D','E5'),";
            if (!Loan3T.equals(""))
                q += "(date('"+ Date +"'),'loan3',"+ new BigDecimal(Loan3T) +",'C','loan3'),";
            if (!ExtraAmt6T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName6T +"',"+ new BigDecimal(ExtraAmt6T) +",'C','E6'),";
            if (!ExtraAmt7T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName7T +"',"+ new BigDecimal(ExtraAmt7T) +",'C','E7'),";
            if (!ExtraAmt8T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName8T +"',"+ new BigDecimal(ExtraAmt8T) +",'C','E8'),";
            if (!ExtraAmt9T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName9T +"',"+ new BigDecimal(ExtraAmt9T) +",'C','E9'),";
            if (!ExtraAmt10T.isEmpty())
                q += "(date('"+ Date +"'),'"+ ExtraName10T +"',"+ new BigDecimal(ExtraAmt10T) +",'C','E10'),";
            if (closingBalance != 0.00)
                q += "(date('"+ Date +"'),'closingbalance',"+ new BigDecimal(closingBalance) +",'NA','closingbalance')";
            if(!q.equals("INSERT INTO data VALUES")) 
                stat.executeUpdate(q);
            Calculated = false;
            currentDate = false;

            alertBox("Successful!", "i");
            
            OpeningBalance.setText("");Loan1.setText("");Interest1.setText("");Loan3.setText("");ExtraAmt1.setText("");ExtraAmt2.setText("");ExtraAmt3.setText("");ExtraAmt4.setText("");ExtraAmt5.setText("");ExtraAmt6.setText("");ExtraAmt7.setText("");ExtraAmt8.setText("");ExtraAmt9.setText("");ExtraAmt10.setText("");ExtraName1.setText("");ExtraName2.setText("");ExtraName3.setText("");ExtraName4.setText("");ExtraName5.setText("");ExtraName6.setText("");ExtraName7.setText("");ExtraName8.setText("");ExtraName9.setText("");ExtraName10.setText("");closingBalance1.setText("");closingBalance2.setText("");closingBalance3.setText("");closingBalance4.setText("");closingBalance5.setText("");
            if(date.getDayOfWeek().getValue() == 6) datepicker.setValue(date.plusDays(2));
            else datepicker.setValue(date.plusDays(1));
            con.close();
        }   
        
    }
    
    public void calculate(){
        String OpenBalT = OpeningBalance.getText(),Loan1T = Loan1.getText(),Interest1T = Interest1.getText(),Loan3T = Loan3.getText(),ExtraAmt1T = ExtraAmt1.getText(),ExtraAmt2T = ExtraAmt2.getText(),ExtraAmt3T = ExtraAmt3.getText(),ExtraAmt4T = ExtraAmt4.getText(),ExtraAmt5T = ExtraAmt5.getText(),ExtraAmt6T = ExtraAmt6.getText(),ExtraAmt7T = ExtraAmt7.getText(),ExtraAmt8T = ExtraAmt8.getText(),ExtraAmt9T = ExtraAmt9.getText(),ExtraAmt10T = ExtraAmt10.getText(),ExtraName1T = ExtraName1.getText(),ExtraName2T = ExtraName2.getText(),ExtraName3T = ExtraName3.getText(),ExtraName4T = ExtraName4.getText(),ExtraName5T = ExtraName5.getText(),ExtraName6T = ExtraName6.getText(),ExtraName7T = ExtraName7.getText(),ExtraName8T = ExtraName8.getText(),ExtraName9T = ExtraName9.getText(),ExtraName10T = ExtraName10.getText();        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        Double OpenBalV = 0.00,Loan1V = 0.00,Interest1V = 0.00,Loan2V = 0.00,Interest2V = 0.00,Loan3V = 0.00,DrawingsV = 0.00,ExtraAmt1V = 0.00,ExtraAmt2V = 0.00,ExtraAmt3V = 0.00,ExtraAmt4V = 0.00,ExtraAmt5V = 0.00,ExtraAmt6V = 0.00,ExtraAmt7V = 0.00,ExtraAmt8V = 0.00,ExtraAmt9V = 0.00,ExtraAmt10V = 0.00;        
        
        Boolean ShowErrorBox = false;
        
        date = datepicker.getValue();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (date != null) {
            Date = date.toString();
        }else{
            Date = "";
            alertBox("Please Enter Date!!", "w");
        }
        
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
        
        if (ShowErrorBox) {
            alertBox("Please check all the fields!!", "w");
        }else if(!currentDate){
            alertBox("Please check Date!!", "w");
        }else{
            if (!OpenBalT.equals("")) {
                OpenBalV = Double.parseDouble(OpenBalT);
            }
            if (!Loan1T.equals("")) {
                Loan1V = Double.parseDouble(Loan1T);
            }
            if (!Interest1T.equals("")) {
                Interest1V = Double.parseDouble(Interest1T);
            }
            if (!Loan3T.equals("")) {
                Loan3V = Double.parseDouble(Loan3T);
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

            closingBalance1.setText(""+df.format(ClosingBal1));
            closingBalance2.setText(""+df.format(ClosingBal1));
            closingBalance3.setText(""+df.format(ClosingBal1));
            closingBalance4.setText(""+df.format(ClosingBal2));
            closingBalance5.setText(""+df.format(FinalValue));
            Calculated = true;
        }
    }
    
    private void alertBox(String content,String type){
        Alert alert;
        if("i".equals(type))
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.WARNING); 
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


