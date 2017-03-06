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
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class DisplayIntIncController implements Initializable {
    
    @FXML
    GridPane gridPane;
    
    int n = 0;
    Double total = 0.00;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                String q = "SELECT * FROM data Where name='Interest Income' or name='Interest Income (GL)' ORDER BY date ASC";
                ResultSet rs = stat.executeQuery(q);
                
                while(rs.next()){
                    
                    Label date = new Label(invertDate(rs.getString("date")));
                    Label Cash = new Label("Cash");
                    Label value = new Label(doubledecimal(Double.parseDouble(rs.getString("amount"))));
                    Label Empty = new Label();
                    
                    total += Double.parseDouble(rs.getString("amount"));
                    
                    gridPane.getRowConstraints().add(new RowConstraints(20));
                    gridPane.addRow(n++, date,Cash,Empty,value);
                }
                
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label("Profit & Loss A/C"),new Label(doubledecimal(total)));
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
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DisplayIntIncController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
            
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
