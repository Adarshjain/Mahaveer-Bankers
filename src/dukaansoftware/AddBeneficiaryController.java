/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class AddBeneficiaryController implements Initializable {
    
    @FXML
    DatePicker datePicker;    
    @FXML
    TextField beneficiary,amount,particulars;
    @FXML
    RadioButton debit,credit;

    ToggleGroup group;
    
    String Date,Beneficiary,Particulars,Amount,Type;
    String[] AutoComplete = {""},AutoComplete2 = {""};
    String filename = "Autocomplete.txt",filename2 = "Autocomplete2.txt";
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        group = new ToggleGroup();
        debit.setToggleGroup(group);
        credit.setToggleGroup(group);
        debit.setUserData("debit");
        credit.setUserData("credit");
        
        // <editor-fold defaultstate="collapsed" desc="KeyPress Events">
        beneficiary.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                particulars.requestFocus();
            }
        });
        
        particulars.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                amount.requestFocus();
            }
        });
        //</editor-fold>
        
        amount.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {  
            amount.setText(newValue.matches("^[0-9]*\\.?[0-9]*$") ? newValue : oldValue);
        });
        
        autoComplete();
    }    
    
    public void save() throws SQLException, ClassNotFoundException{
        Beneficiary = beneficiary.getText();
        Particulars = particulars.getText();
        Amount = amount.getText();
        if(group.getSelectedToggle() == null || datePicker.getValue() == null || "".equals(Beneficiary) || "".equals(Particulars) || "".equals(Amount)) {
                alertBox("Please Enter All The Fields!", "w");
        }else{
            if (!Arrays.asList(AutoComplete).contains(Beneficiary)) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true))) {
                    String content = "," + Beneficiary;
                    bw.append(content);
                    System.out.println("Done");
		} catch (IOException e) {
                    System.out.println(""+e);
                }
            }
            if (!Arrays.asList(AutoComplete2).contains(Particulars)) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename2,true))) {
                    String content = "," + Particulars;
                    bw.append(content);
                    System.out.println("Done2");
		} catch (IOException e) {
                    System.out.println(""+e);
                }
            }
            Type = group.getSelectedToggle().getUserData().toString();
            Date = datePicker.getValue().toString();
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                String q = "INSERT INTO beneficiary VALUES (date('"+ Date +"'),'" + Beneficiary + "','" + Particulars + "',"+ new BigDecimal(Amount) +",'" + Type + "')";
                if(stat.executeUpdate(q) != 0){
                    alertBox("Added Successfully!", "i");
                }
            }
            beneficiary.setText("");
            particulars.setText("");
            debit.setSelected(false);
            credit.setSelected(false);
            amount.setText("");
            autoComplete();
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
    
    private void autoComplete(){
        // <editor-fold defaultstate="collapsed" desc="AutoComplete Code Hidden">
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
        
        new AutoCompletionTextFieldBinding(beneficiary, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete);
            }
        });
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="AutoComplete Code Hidden">
        try{
            try (BufferedReader reader = new BufferedReader(new FileReader(filename2))) {
                String line;
                while ((line = reader.readLine()) != null){
                    AutoComplete2 = line.split(",");
                }
            }
        }catch (IOException e){
            System.err.format("Exception occurred trying to read '%s'.", filename2);
            System.out.println(""+e);
        }
        
        new AutoCompletionTextFieldBinding(particulars, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList(AutoComplete2);
            }
        });
        //</editor-fold>
        
        
    }
}
