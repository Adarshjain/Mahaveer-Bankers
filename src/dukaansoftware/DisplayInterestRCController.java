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
public class DisplayInterestRCController implements Initializable {
    
    @FXML
    GridPane gridPane;
    String I1,I2;
    Double closingBaL = 0.00;
    String currMonth = "",currDate = "";
    int n = 0;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db");
            Statement stat = con.createStatement();
            String q = "SELECT * FROM data Where place='interest1' or place='interest2' ORDER BY date ASC";
            ResultSet rs = stat.executeQuery(q);
            
            Boolean first = true,firstMonth = true;
            
            // <editor-fold defaultstate="collapsed" desc="Fetch Data">
            while(rs.next()){
                if (!rs.getString("date").equals(currDate)) {
                    if (first) {
                        first = !first;
                    }else{
                        populate();
                    }
                    if(!getMonth(rs.getString("date")).equals(currMonth)){
                        if(firstMonth){
                            firstMonth = !firstMonth;
                        }else{
                            populateEnd();
                            Label date = new Label(invertDate(rs.getString("date")));
                            Label openBalL = new Label("Opening Balance");
                            Label OpenBalV = new Label(doubledecimal(closingBaL));
                            Label Empty18 = new Label("");
                            gridPane.getRowConstraints().add(new RowConstraints(25));
                            gridPane.addRow(n++, date,openBalL,Empty18,OpenBalV);
        
                        }
                        currMonth = getMonth(rs.getString("date"));
                    }
                }
                currDate = rs.getString("date");
                
                if(rs.getString("place").equals("interest1")){
                    I1 = rs.getString("amount");
                }
                
                if(rs.getString("place").equals("interest2")){
                    I2 = rs.getString("amount");
                }
            }
            populate();
            populateEnd();
            //</editor-fold>
        
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayloanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayInterestRCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void populate(){
        
        calculate();
        
        
        // <editor-fold defaultstate="collapsed" desc="Display GridPane">
        Label date = new Label(invertDate(currDate));
        if(!(I1 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label I1NL = new Label("Cash");
            Label I1L = new Label(doubledecimal(Double.parseDouble(I1)));
            Label Empty34 = new Label("");
            gridPane.addRow(n++, date,I1NL,Empty34,I1L);
        }
        if(!(I2 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label I2NL = new Label("Cash");
            Label I2L = new Label(doubledecimal(Double.parseDouble(I2)));
            Label Empty3 = new Label("");
            Label Empty4 = new Label("");
            if(I1 == null)
                gridPane.addRow(n++, date,I2NL,Empty4,I2L);
            else
                gridPane.addRow(n++, Empty3,I2NL,Empty4,I2L);
        }
        
        I1 = null;I2 = null;

        //</editor-fold>
        
    }
    
    private void populateEnd() {
        
        //<editor-fold defaultstate="collapsed" desc="Labels">
        Label Empty10 = new Label("");
        Label Empty11 = new Label("");
        Label Empty14 = new Label("");
        Label Empty15 = new Label("");
        Label Empty16 = new Label("");
        Label Empty18 = new Label("");
        
        Label IlL = new Label(doubledecimal(closingBaL));
        Label ILC1 = new Label(doubledecimal(closingBaL));
        Label ILC2 = new Label(doubledecimal(closingBaL));
        Label ClosingLabel = new Label(doubledecimal(closingBaL));
        Label closing = new Label("Closing Balance");
        //</editor-fold>
        
        
        Line x = new Line();
        x.setEndX(400);
        Line x1 = new Line();
        x1.setEndX(400);
        Line x2 = new Line();
        x2.setEndX(400);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x, 0, n++);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        gridPane.addRow(n++, Empty10,Empty11,Empty16,IlL);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        gridPane.addRow(n++, closing,Empty15,ClosingLabel);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x1, 0, n++);
        gridPane.getRowConstraints().add(new RowConstraints(25));
        gridPane.addRow(n++, Empty14,Empty18,ILC1,ILC2);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x2, 0, n++);
        
        gridPane.addRow(n++);
        gridPane.getRowConstraints().add(new RowConstraints(15));
    }
    
    private void calculate(){
        Double I1D = 0.00,I2D = 0.00;
        if (!(I1 == null)) {
            I1D = Double.parseDouble(I1);
        }
        if (!(I2 == null)) {
            I2D = Double.parseDouble(I2);
        }
        closingBaL += I1D + I2D;
    }

    private String doubledecimal(Double doublenum) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        return df.format(doublenum);
    }
    
    private String getMonth(String Date){
        String splits[] = Date.split("-");
        return splits[1];
    }
    
    private String invertDate(String oldDate){
        String splits[] = oldDate.split("-");
        return splits[2]+ "-" +splits[1]+ "-" +splits[0];
    }    
}


