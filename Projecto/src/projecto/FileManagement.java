package projecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Classe de controlo das operações de escrita/leitura*/


public class FileManagement{
    private BufferedReader fR;
    private BufferedWriter fW;
    
    /**Constructor vazio*/

    public FileManagement() {
    }
    
    /**Método para abrir o ficheiro em modo de leitura*/
    public void openRead(String fileName) throws IOException {
        fR = new BufferedReader(new FileReader(fileName)); 
    }

    /**Método para abrir o ficheiro em modo de escrita*/
    public void openWrite(String fileName) throws IOException {
        fW = new BufferedWriter(new FileWriter(fileName));
    }
    
    /**Método para ler uma linha do ficheiro*/
    public String readLine() throws IOException {
        return fR.readLine(); 
    }
    
    /**Método para escrever uma linha do ficheiro*/
    public void writeLine(String line) throws IOException {
        fW.write(line,0,line.length());
        fW.newLine(); 
    }
    
    /**Método responsável pelo fecho do ficheiro de leitura*/
    public void closeRead() throws IOException {
        fR.close(); 
    }
    
    /**Método responsável pelo fecho do ficheiro de escrita*/
    public void closeWrite() throws IOException {
        fW.close(); 
    }
    
}
