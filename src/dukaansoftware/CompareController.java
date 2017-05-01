package dukaansoftware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class CompareController implements Initializable {
    @FXML
    GridPane gridPane;
    Double total = 0.00;
    int n = 0;
    String Date1,Date2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void display(){
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                String query = "SELECT date,name,amount from data WHERE (name=\"interest1\" or name=\"Interest Income\") AND date BETWEEN \"" + Date1 + "\" AND \"" + Date2 + "\" ORDER BY date ASC";
                
                ResultSet rs = stat.executeQuery(query);
                while(rs.next()){
                    if(!rs.getString("name").equals("Interest Income")){
                        total += Double.parseDouble(rs.getString("amount"));
                    }else{
                        Label date = new Label(invertDate(rs.getString("date")));
                        gridPane.getRowConstraints().add(new RowConstraints(20));
                        gridPane.addRow(n++, date,new Label(doubledecimal(total)),new Label(doubledecimal(Double.parseDouble(rs.getString("amount")))));
                        total = 0.00;
                    }
                }
            }
        }catch(SQLException e){
            System.err.println(""+e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setString(String d1, String d2){
        Date1 = d1;
        Date2 = d2;
    }
    
    private String doubledecimal(Double doublenum) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        return df.format(doublenum);
    }
    
    
    public String invertDate(String oldDate){
        String splits[] = oldDate.split("-");
        return splits[1] + "-" + splits[0];
    }
}
