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
public class DisplayloanController implements Initializable {
    
    @FXML
    GridPane gridPane;
    String Loan1,Loan2,Loan3;
    Double LoanTotal = 0.00,Loan3Total = 0.00,closingBaL = 0.00;
    String currMonth = "",currDate = "";
    String Date1,Date2;
    int n = 0;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void display(){
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                String q = "SELECT * FROM data Where (place='loan1' or place='loan2'or place='loan3') AND date BETWEEN \"" + Date1 + "\" AND \"" + Date2 + "\" ORDER BY date ASC";
                ResultSet rs = stat.executeQuery(q);
                Boolean first = true,firstMonth = true;
                
                // <editor-fold defaultstate="collapsed" desc="Fetch Data">
                while(rs.next()){
                    if (!rs.getString("date").equals(currDate)) {
                        if (first) {
                            first = !first;
//                            Label openBalL = new Label("Opening Balance");
//                            Label OpenBalV = new Label(doubledecimal(closingBaL));
//                            Label date = new Label(invertDate(rs.getString("date")));
                            Line x = new Line();
                            x.setEndX(400);
                            Line x1 = new Line();
                            x1.setEndX(400);
                            gridPane.getRowConstraints().add(new RowConstraints(1));
                            gridPane.add(x, 0, n++);
                            gridPane.getRowConstraints().add(new RowConstraints(20));
                            gridPane.addRow(n++, new Label("Date"),
                                                 new Label("Particulars"),
                                                new Label("Loan"),
                                                new Label("Release"));
                            gridPane.getRowConstraints().add(new RowConstraints(1));
                            gridPane.add(x1, 0, n++);
//                            gridPane.getRowConstraints().add(new RowConstraints(25));
//                            gridPane.addRow(n++, date,openBalL,OpenBalV);
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
                                gridPane.getRowConstraints().add(new RowConstraints(25));
                                gridPane.addRow(n++, date,openBalL,OpenBalV);
                                
                            }
                            currMonth = getMonth(rs.getString("date"));
                        }
                    }
                    currDate = rs.getString("date");
                    
                    if(rs.getString("place").equals("loan1")){
                        Loan1 = rs.getString("amount");
                    }
                    
                    if(rs.getString("place").equals("loan2")){
                        Loan2 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("loan3")){
                        Loan3 = rs.getString("amount");
                    }
                }   populate();
                populateEnd();
                //</editor-fold>
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DisplayloanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setString(String d1, String d2){
        Date1 = d1;
        Date2 = d2;
    }

    
    private void populate(){
        
        calculate();
        
        
        // <editor-fold defaultstate="collapsed" desc="Display GridPane">
        Label date = new Label(invertDate(currDate));
        if(!(Loan1 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan1NL = new Label("Cash");
            Label Loan1L = new Label(doubledecimal(Double.parseDouble(Loan1)));
            Label Empty34 = new Label("");
            gridPane.addRow(n++, date,Loan1NL,Empty34,Loan1L);
        }
        if(!(Loan2 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan2NL = new Label("Cash");
            Label Loan2L = new Label(doubledecimal(Double.parseDouble(Loan2)));
            Label Empty3 = new Label("");
            Label Empty4 = new Label("");
            if(Loan1 == null)
                gridPane.addRow(n++, date,Loan2NL,Empty4,Loan2L);
            else
                gridPane.addRow(n++, Empty3,Loan2NL,Empty4,Loan2L);
        }
        if (!(Loan3 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan3NL = new Label("Cash");
            Label Loan3L = new Label(doubledecimal(Double.parseDouble(Loan3)));
            Label Empty20 = new Label("");
            if((Loan1 == null) && (Loan2 == null))
                gridPane.addRow(n++, date,Loan3NL,Loan3L);
            else
                gridPane.addRow(n++, Empty20,Loan3NL,Loan3L);
        }
        Loan1 = null;Loan2 = null;Loan3 = null;

        //</editor-fold>
    }
    
    

    private void populateEnd() {
        
        Loan3Total += closingBaL;
        closingBaL = Loan3Total-LoanTotal;
        
        //<editor-fold defaultstate="collapsed" desc="Labels">
        Label Empty10 = new Label("");
        Label Empty11 = new Label("");
        Label Empty14 = new Label("");
        Label Empty15 = new Label("");
        Label Empty16 = new Label("");
        Label Empty18 = new Label("");
        
        Label LoanTotalL = new Label(doubledecimal(Loan3Total));
        Label LoanTotalLC1 = new Label(doubledecimal(Loan3Total));
        Label LoanTotalLC2 = new Label(doubledecimal(Loan3Total));
        Label Loan3TotalL = new Label(doubledecimal(LoanTotal));
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
        gridPane.addRow(n++, Empty10,Empty11,LoanTotalL,Loan3TotalL);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        gridPane.addRow(n++, closing,Empty15,Empty16,ClosingLabel);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x1, 0, n++);
        gridPane.getRowConstraints().add(new RowConstraints(25));
        gridPane.addRow(n++, Empty14,Empty18,LoanTotalLC1,LoanTotalLC2);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x2, 0, n++);
        
        
        
        gridPane.addRow(n++);
        gridPane.getRowConstraints().add(new RowConstraints(15));
        
        Line x3 = new Line();
        x3.setEndX(400);
        Line x4 = new Line();
        x4.setEndX(400);
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x3, 0, n++);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        gridPane.addRow(n++, new Label("Date"),
                             new Label("Particulars"),
                            new Label("Loan"),
                            new Label("Release"));
        gridPane.getRowConstraints().add(new RowConstraints(1));
        gridPane.add(x4, 0, n++);
        LoanTotal = 0.00;Loan3Total = 0.00;   
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
    
    public String invertDate(String oldDate){
        String splits[] = oldDate.split("-");
        return splits[2]+ "-" +splits[1]+ "-" +splits[0];
    }
    private void calculate(){
        Double Loan1D = 0.00,Loan2D = 0.00,Loan3D = 0.00;
        if (!(Loan1 == null)) {
            Loan1D = Double.parseDouble(Loan1);
        }
        if (!(Loan2 == null)) {
            Loan2D = Double.parseDouble(Loan2);
        }
        if (!(Loan3 == null)) {
            Loan3D = Double.parseDouble(Loan3);
        }
        LoanTotal += Loan1D + Loan2D;
        Loan3Total += Loan3D;
    }
    
}
