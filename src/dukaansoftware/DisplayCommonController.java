/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dukaansoftware;

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
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class DisplayCommonController implements Initializable {
    
    @FXML
    GridPane gridPane;
    
    @FXML
    Label label;
    
    Double total = 0.00;
    int n = 0;
    String Selector;
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
                
                label.setText(Selector);
                String q = "SELECT * FROM data Where (name='" + Selector + "') AND date BETWEEN \"" + Date1 + "\" AND \"" + Date2 + "\" ORDER BY date ASC";
                ResultSet rs = stat.executeQuery(q);
                
                while (rs.next()) {                    
                    Label date = new Label(invertDate(rs.getString("date")));
                    Label Cash = new Label("Cash");
                    Label value = new Label(doubledecimal(Double.parseDouble(rs.getString("amount"))));
                    
                    total += Double.parseDouble(rs.getString("amount"));
                
                    gridPane.getRowConstraints().add(new RowConstraints(20));
                    gridPane.addRow(n++, date,Cash,value);
                }
                
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label("Profit & Loss A/C"),new Label(),new Label(doubledecimal(total)));
                Line x2 = new Line();
                x2.setEndX(420);
                gridPane.getRowConstraints().add(new RowConstraints(1));
                gridPane.add(x2, 0, n++);
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label(),new Label(doubledecimal(total)),new Label(doubledecimal(total)));
                Line x = new Line();
                x.setEndX(420);
                gridPane.getRowConstraints().add(new RowConstraints(1));
                gridPane.add(x, 0, n++);
            }
        }catch(SQLException e){
            System.err.println(""+e);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayCommonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void setString(String selector,String d1,String d2){
        Selector = selector;
        Date1 = d1;
        Date2 = d2;
    }
    
    private String doubledecimal(Double doublenum) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        return df.format(doublenum);
    }
    
    private String invertDate(String oldDate){
        String splits[] = oldDate.split("-");
        return splits[2]+ "-" +splits[1]+ "-" +splits[0];
    }
}
