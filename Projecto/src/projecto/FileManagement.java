package projecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagement{
    private BufferedReader fR;
    private BufferedWriter fW;

    public FileManagement() {
    }
    
    public void openRead(String fileName) throws IOException {
        fR = new BufferedReader(new FileReader(fileName)); 
    }

    public void openWrite(String fileName) throws IOException {
        fW = new BufferedWriter(new FileWriter(fileName));
    }
    
    public String readLine() throws IOException {
        return fR.readLine(); 
    }
    
    public void writeLine(String line) throws IOException {
        fW.write(line,0,line.length());
        fW.newLine(); 
    }
    
    public void closeRead() throws IOException {
        fR.close(); 
    }
    
    
    public void closeWrite() throws IOException {
        fW.close(); 
    }
    
}
