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
public class DisplayBeneficiaryController implements Initializable {
    
    @FXML
    GridPane gridPane;
    
    @FXML
    Label label;
    
    Double creditT = 0.00,debitT = 0.00;
    int n = 0;
    String Selector,date = "";


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void display(){
        
            //<editor-fold defaultstate="collapsed" desc="Open Bal Fetch & Display">
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
               Statement stat = con.createStatement();
               String q1 = "SELECT * FROM beneficiary Where beneficiary='" + Selector + "' AND particulars='Opening Balance' ORDER BY date ASC";
               ResultSet rs1 = stat.executeQuery(q1);
               if(rs1.next()){
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(invertDate(rs1.getString("date"))),
                                    new Label("Opening Balance"),new Label(),
                                    new Label(doubledecimal(Double.parseDouble(rs1.getString("amount")))));
                creditT += Double.parseDouble(rs1.getString("amount"));
                date = rs1.getString("date");
               }
            }
            //</editor-fold>
            
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                
                label.setText(Selector);
                
                String q = "SELECT * FROM beneficiary Where beneficiary='" + Selector + "' ORDER BY date ASC";
                ResultSet rs = stat.executeQuery(q);
                
                while (rs.next()) {
                    if("Opening Balance".equals(rs.getString("particulars")))
                        continue;
                    Label tempDate;
                    if(date.equals(rs.getString("date"))){
                        tempDate = new Label();
                    }else{
                        tempDate = new Label(invertDate(rs.getString("date")));
                        date = rs.getString("date");
                    }
                    switch(rs.getString("type")){
                        case "debit":
                            debitT += Double.parseDouble(rs.getString("amount"));
                            Label Cash = new Label(rs.getString("particulars"));
                            Label value = new Label(doubledecimal(Double.parseDouble(rs.getString("amount"))));

                            gridPane.getRowConstraints().add(new RowConstraints(20));
                            gridPane.addRow(n++, tempDate,Cash,value);
                            break;
                        case "credit":
                            creditT += Double.parseDouble(rs.getString("amount"));
                            Label CashC = new Label(rs.getString("particulars"));
                            Label valueC = new Label(doubledecimal(Double.parseDouble(rs.getString("amount"))));

                            gridPane.getRowConstraints().add(new RowConstraints(20));
                            gridPane.addRow(n++, tempDate,CashC,new Label(),valueC);
                            break;
                    }
                    
                }
                Line x1 = new Line();
                x1.setEndX(520);
                gridPane.getRowConstraints().add(new RowConstraints(1));
                gridPane.add(x1, 0, n++);
                
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label(),new Label(doubledecimal(debitT)),new Label(doubledecimal(creditT)));
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label("Closing Balance"),new Label(doubledecimal(creditT - debitT)));
                
                Line x2 = new Line();
                x2.setEndX(520);
                gridPane.getRowConstraints().add(new RowConstraints(1));
                gridPane.add(x2, 0, n++);
                gridPane.getRowConstraints().add(new RowConstraints(20));
                gridPane.addRow(n++, new Label(),new Label(),new Label(doubledecimal(creditT)),new Label(doubledecimal(creditT)));
                Line x = new Line();
                x.setEndX(520);
                
            }
        }catch(SQLException e){
            System.err.println(""+e);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayCommonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void setString(String selector){
        Selector = selector;
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
