package dukaansoftware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// <editor-fold defaultstate="collapsed" desc="Imports">
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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Line;
//</editor-fold>
/**
 * FXML Controller class
 *
 * @author geekyadars
 */
public class DisplayuiController implements Initializable {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">

    @FXML
    GridPane gridPane;
    
    @FXML
    ScrollPane parentscroll;
    
    
    String OpeningBalance,Loan1,Interest1,Loan2,Interest2,Loan3,Drawings,
            ExtraAmt1,ExtraAmt2,ExtraAmt3,ExtraAmt4,ExtraAmt5,ExtraAmt6,ExtraAmt7,ExtraAmt8,ExtraAmt9,ExtraAmt10,
            ExtraName1,ExtraName2,ExtraName3,ExtraName4,ExtraName5,ExtraName6,ExtraName7,ExtraName8,ExtraName9,ExtraName10,closingbal;
    Double OpeningBalanceD = 0.00,Loan1D = 0.00,Interest1D = 0.00,Loan2D = 0.00,Interest2D = 0.00,Loan3D = 0.00,DrawingsD = 0.00,
            ExtraAmt1D = 0.00,ExtraAmt2D = 0.00,ExtraAmt3D = 0.00,ExtraAmt4D = 0.00,ExtraAmt5D = 0.00,ExtraAmt6D = 0.00,ExtraAmt7D = 0.00,ExtraAmt8D = 0.00,ExtraAmt9D = 0.00,ExtraAmt10D = 0.00,
            closingbalD = 0.00;
    String currDate = "",Date1,Date2;
    
    int n = 0;

    //</editor-fold>
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }
    
    public void setString(String d1, String d2){
        Date1 = d1;
        Date2 = d2;
    }

    public void display(){
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:mahaveerbankers.db")) {
                Statement stat = con.createStatement();
                
                String q = "SELECT * FROM data WHERE date BETWEEN \"" + Date1 + "\" AND \"" + Date2 + "\" ORDER BY date ASC";
                ResultSet rs = stat.executeQuery(q);
                
                Boolean first = true;
                
                // <editor-fold defaultstate="collapsed" desc="Fetch Data">
                while(rs.next()){
                    if(!rs.getString("date").equals(currDate)){
                        if(first){
                            first = !first;
                            Line x = new Line();
                            x.setEndX(500);
                            Line x1 = new Line();
                            x1.setEndX(500);
                            gridPane.getRowConstraints().add(new RowConstraints(1));
                            n = n + 1;
                            gridPane.add(x, 0, n);
                            gridPane.getRowConstraints().add(new RowConstraints(20));
                            n = n + 1;
                            gridPane.addRow(n, new Label("Date"),
                                                 new Label("Particulars"),
                                                new Label("Release"),
                                                new Label("Loan"));
                            gridPane.getRowConstraints().add(new RowConstraints(1));
                            n = n + 1;
                            gridPane.add(x1, 0, n);
                        }else{
                            populate();
                        }
                        currDate = rs.getString("date");
                    }

                    if(rs.getString("place").equals("openbal")){
                        OpeningBalance = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("loan1")){
                        Loan1 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("interest1")){
                        Interest1 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("loan2")){
                        Loan2 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("interest2")){
                        Interest2 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("E1")){
                        ExtraAmt1 = rs.getString("amount");
                        ExtraName1 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E2")){
                        ExtraAmt2 = rs.getString("amount");
                        ExtraName2 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E3")){
                        ExtraAmt3 = rs.getString("amount");
                        ExtraName3 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E4")){
                        ExtraAmt4 = rs.getString("amount");
                        ExtraName4 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E5")){
                        ExtraAmt5 = rs.getString("amount");
                        ExtraName5 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("loan3")){
                        Loan3 = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("drawings")){
                        Drawings = rs.getString("amount");
                    }
                    if(rs.getString("place").equals("E6")){
                        ExtraAmt6 = rs.getString("amount");
                        ExtraName6 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E7")){
                        ExtraAmt7 = rs.getString("amount");
                        ExtraName7 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E8")){
                        ExtraAmt8 = rs.getString("amount");
                        ExtraName8 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E9")){
                        ExtraAmt9 = rs.getString("amount");
                        ExtraName9 = rs.getString("name");
                    }
                    if(rs.getString("place").equals("E10")){
                        ExtraAmt10 = rs.getString("amount");
                        ExtraName10 = rs.getString("name");
                    }
                    if(rs.getString("name").equals("closingbalance")){
                        closingbal = doubledecimal(Double.parseDouble(rs.getString("amount")));
                    }
                }
                //</editor-fold>
                
                populate();
            }
        }catch(SQLException e){
            System.err.println(""+e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void populate(){
        
        String closebal[] = calculate();

        // <editor-fold defaultstate="collapsed" desc="Labels">       
        Label closingbalL = new Label(closingbal);
        Label closingBa0L = new Label(closebal[0]);        
        Label closingBa1L = new Label(closebal[1]);  
        Label closing0copy = new Label(closebal[0]);
        Label closing0copy2 = new Label(closebal[0]);
        Label closing = new Label("Closing Balance");
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Display GridPane">
        Label date = new Label(invertDate(currDate));
        gridPane.getRowConstraints().add(new RowConstraints(20));
        Label OpenBalNL = new Label("Opening Balance");
        Label OpeningBalanceL = new Label(OpeningBalance);
        n = n + 1;
        gridPane.addRow(n, date,OpenBalNL,OpeningBalanceL);
        if(!(Loan1 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan1NL = new Label("Loans");
            Label Loan1L = new Label(Loan1);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, Loan1NL);
            gridPane.addColumn(2, Loan1L);
        }
        if(!(Interest1 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Interest1NL = new Label("Interest");
            Label Interest1L = new Label(Interest1);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, Interest1NL);
            gridPane.addColumn(2, Interest1L);
        }
        if(!(Loan2 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan2NL = new Label("Loans(GL)");
            Label Loan2L = new Label(Loan2);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, Loan2NL);
            gridPane.addColumn(2, Loan2L);
        }
        if(!(Interest2 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Interest2NL = new Label("Interest");
            Label Interest2L = new Label(Interest2);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, Interest2NL);
            gridPane.addColumn(2, Interest2L);
        }
        if(!(ExtraAmt1 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt1L = new Label(ExtraAmt1);
            Label ExtraName1L = new Label(ExtraName1);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, ExtraName1L);
            gridPane.addColumn(2, ExtraAmt1L);
        }
        if(!(ExtraAmt2 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt2L = new Label(ExtraAmt2);
            Label ExtraName2L = new Label(ExtraName2);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, ExtraName2L);
            gridPane.addColumn(2, ExtraAmt2L);
        }
        if(!(ExtraAmt3 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt3L = new Label(ExtraAmt3);
            Label ExtraName3L = new Label(ExtraName3);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, ExtraName3L);
            gridPane.addColumn(2, ExtraAmt3L);
        }
        if(!(ExtraAmt4 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt4L = new Label(ExtraAmt4);
            Label ExtraName4L = new Label(ExtraName4);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, ExtraName4L);
            gridPane.addColumn(2, ExtraAmt4L);
        }
        if(!(ExtraAmt5 == null)){
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt5L = new Label(ExtraAmt5);
            Label ExtraName5L = new Label(ExtraName5);
        n = n + 1;
            gridPane.addRow(n);
            gridPane.addColumn(1, ExtraName5L);
            gridPane.addColumn(2, ExtraAmt5L);
        }
        if (!(Loan3 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label Loan3NL = new Label("Loans");
            Label Loan3L = new Label(Loan3);
        n = n + 1;
            gridPane.add(Loan3NL,1,n);
            gridPane.add(Loan3L,3,n);
        }
        if (!(Drawings == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label DrawingsNL = new Label("Drawings");
            Label DrawingsL = new Label(Drawings);
        n = n + 1;
            gridPane.add(DrawingsNL,1,n);
            gridPane.add(DrawingsL,3,n);
        }
        if (!(ExtraAmt6 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt6L = new Label(ExtraAmt6);
            Label ExtraName6L = new Label(ExtraName6);
        n = n + 1;
            gridPane.add(ExtraName6L,1,n);
            gridPane.add(ExtraAmt6L,3,n);
        }
        if (!(ExtraAmt7 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt7L = new Label(ExtraAmt7);
            Label ExtraName7L = new Label(ExtraName7);
        n = n + 1;
            gridPane.add(ExtraName7L,1,n);
            gridPane.add(ExtraAmt7L,3,n);
        }
        if (!(ExtraAmt8 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt8L = new Label(ExtraAmt8);
            Label ExtraName8L = new Label(ExtraName8);
        n = n + 1;
            gridPane.add(ExtraName8L,1,n);
            gridPane.add(ExtraAmt8L,3,n);
        }
        if (!(ExtraAmt9 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt9L = new Label(ExtraAmt9);
            Label ExtraName9L = new Label(ExtraName9);
        n = n + 1;
            gridPane.add(ExtraName9L,1,n);
            gridPane.add(ExtraAmt9L,3,n);
        }
        if (!(ExtraAmt10 == null)) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
            Label ExtraAmt10L = new Label(ExtraAmt10);
            Label ExtraName10L = new Label(ExtraName10);
        n = n + 1;
            gridPane.add(ExtraName10L,1,n);
            gridPane.add(ExtraAmt10L,3,n);
        }
        Line x = new Line();
        x.setEndX(500);
        Line x1 = new Line();
        x1.setEndX(500);
        Line x2 = new Line();
        x2.setEndX(500);
        gridPane.getRowConstraints().add(new RowConstraints(10));
        n=n+1;
        gridPane.add(x, 0, n);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        n=n+1;
        gridPane.add(closingBa0L,2,n);
        gridPane.add(closingBa1L,3,n);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        n=n+1;
        gridPane.add(closing,0,n);
        gridPane.add(closingbalL,3,n);
        gridPane.getRowConstraints().add(new RowConstraints(10));
        n=n+1;
        gridPane.add(x1, 0, n);
        gridPane.getRowConstraints().add(new RowConstraints(20));
        n=n+1;
        gridPane.add(closing0copy,2,n);
        gridPane.add(closing0copy2,3,n);
        gridPane.getRowConstraints().add(new RowConstraints(10));
        n=n+1;
        gridPane.add(x2, 0, n);
        n=n+1;
        gridPane.addRow(n);
        gridPane.getRowConstraints().add(new RowConstraints(15));
//</editor-fold>
        
        clear();
       
    }

    private String[] calculate() {
        // <editor-fold defaultstate="collapsed" desc="Fetch and Calculate">
        if (!(OpeningBalance == null)) {
            OpeningBalanceD = Double.parseDouble(OpeningBalance);
            OpeningBalance = doubledecimal(OpeningBalanceD);
        }
        if (!(Loan1 == null)) {
            Loan1D = Double.parseDouble(Loan1);
            Loan1 = doubledecimal(Loan1D);
        }
        if (!(Interest1 == null)) {
            Interest1D = Double.parseDouble(Interest1);
            Interest1 = doubledecimal(Interest1D);
        }
        if (!(Loan2 == null)) {
            Loan2D = Double.parseDouble(Loan2);
            Loan2 = doubledecimal(Loan2D);
        }
        if (!(Interest2 == null)) {
            Interest2D = Double.parseDouble(Interest2);
            Interest2 = doubledecimal(Interest2D);
        }     
        if (!(ExtraAmt1 == null)) {
            ExtraAmt1D = Double.parseDouble(ExtraAmt1);
            ExtraAmt1 = doubledecimal(ExtraAmt1D);
        }
        if (!(ExtraAmt2 == null)) {
            ExtraAmt2D = Double.parseDouble(ExtraAmt2);
            ExtraAmt2 = doubledecimal(ExtraAmt2D);
        }
        if (!(ExtraAmt3 == null)) {
            ExtraAmt3D = Double.parseDouble(ExtraAmt3);
            ExtraAmt3 = doubledecimal(ExtraAmt3D);
        }
        if (!(ExtraAmt4 == null)) {
            ExtraAmt4D = Double.parseDouble(ExtraAmt4);
            ExtraAmt4 = doubledecimal(ExtraAmt4D);
        }
        if (!(ExtraAmt5 == null)) {
            ExtraAmt5D = Double.parseDouble(ExtraAmt5);
            ExtraAmt5 = doubledecimal(ExtraAmt5D);
        }
        if (!(Loan3 == null)) {
            Loan3D = Double.parseDouble(Loan3);
            Loan3 = doubledecimal(Loan3D);
        }
        if (!(Drawings == null)) {
            DrawingsD = Double.parseDouble(Drawings);
            Drawings = doubledecimal(DrawingsD);
        }
        if (!(ExtraAmt6 == null)) {
            ExtraAmt6D = Double.parseDouble(ExtraAmt6);
            ExtraAmt6= doubledecimal(ExtraAmt6D);
        }
        if (!(ExtraAmt7 == null)) {
            ExtraAmt7D = Double.parseDouble(ExtraAmt7);
            ExtraAmt7 = doubledecimal(ExtraAmt7D);
        }
        if (!(ExtraAmt8 == null)) {
            ExtraAmt8D = Double.parseDouble(ExtraAmt8);
            ExtraAmt8 = doubledecimal(ExtraAmt8D);
        }
        if (!(ExtraAmt9 == null)) {
            ExtraAmt9D = Double.parseDouble(ExtraAmt9);
            ExtraAmt9 = doubledecimal(ExtraAmt9D);
        }
        if (!(ExtraAmt10 == null)) {
            ExtraAmt10D = Double.parseDouble(ExtraAmt10);
            ExtraAmt10 = doubledecimal(ExtraAmt10D);
        }
        
        
        Double closebal0 = OpeningBalanceD+Loan1D+Interest1D+Loan2D+Interest2D+ExtraAmt1D+ExtraAmt2D+ExtraAmt3D+ExtraAmt4D+ExtraAmt5D;
        Double closebal1= Loan3D+DrawingsD+ExtraAmt6D+ExtraAmt7D+ExtraAmt8D+ExtraAmt9D+ExtraAmt10D;
        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String last[] = new String[2];
        last[0] = df.format(closebal0);
        last[1] = df.format(closebal1);
        return last;
        //</editor-fold>
    }


    private void clear() {
        OpeningBalance = null;
        Loan1 = null;Interest1 = null;Loan2 = null;Interest2 = null;Loan3 = null;Drawings = null;
            ExtraAmt1 = null;ExtraAmt2 = null;ExtraAmt3 = null;ExtraAmt4 = null;ExtraAmt5 = null;ExtraAmt6 = null;ExtraAmt7 = null;ExtraAmt8 = null;ExtraAmt9 = null;ExtraAmt10 = null;
            ExtraName1 = null;ExtraName2 = null;ExtraName3 = null;ExtraName4 = null;ExtraName5 = null;ExtraName6 = null;ExtraName7 = null;ExtraName8 = null;ExtraName9 = null;ExtraName10 = null;closingbal = null;
            
        OpeningBalanceD = 0.00;Loan1D = 0.00;Interest1D = 0.00;Loan2D = 0.00;Interest2D = 0.00;Loan3D = 0.00;DrawingsD = 0.00;
            ExtraAmt1D = 0.00;ExtraAmt2D = 0.00;ExtraAmt3D = 0.00;ExtraAmt4D = 0.00;ExtraAmt5D = 0.00;ExtraAmt6D = 0.00;ExtraAmt7D = 0.00;ExtraAmt8D = 0.00;ExtraAmt9D = 0.00;ExtraAmt10D = 0.00;
            closingbalD = 0.00;
    }

    private String doubledecimal(Double doublenum) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        return df.format(doublenum);
    }
    
    
    public String invertDate(String oldDate){
        String splits[] = oldDate.split("-");
        return splits[2]+ "-" +splits[1]+ "-" +splits[0];
    }
}