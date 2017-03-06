package dukaansoftware;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackupDB {
    public boolean isInternetReachable(){
        try{
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
            Object objData = urlConnect.getContent();
        }catch (UnknownHostException e) {
            return false;
        }catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public String zipDB(){
        try{
            String zipName = "Backup " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())) + ".zip";
            String zipFile = "E:/Dukaan Software/Mahaveer Bankers/Backups/" + zipName;
            String[] sourceFiles = {"mahaveerbankers.db"};
            String[] File = {"E:/Dukaan Software/Mahaveer Bankers/mahaveerbankers.db"};
            byte[] buffer = new byte[1024];
            FileOutputStream fout = new FileOutputStream(zipFile);
            try (ZipOutputStream zout = new ZipOutputStream(fout)) {
                int i=0;
                for (String sourceFile : sourceFiles) {
                    try (final FileInputStream fin = new FileInputStream(sourceFile)) {
                        zout.putNextEntry(new ZipEntry(File[i]));
                        int length;
                        while((length = fin.read(buffer)) > 0){
                            zout.write(buffer, 0, length);
                        }   zout.closeEntry();
                    }
                    i++;
                }
            }
        return zipName;
        }
        catch(IOException ioe){
            System.out.println("IOException :" + ioe);
            return "";
        }
    }
    
    public Boolean runGDrive(String zipName){
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd E:/Dukaan Software/Mahaveer Bankers/Backups && gdrive upload \"" + zipName + "\"");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(BackupDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
