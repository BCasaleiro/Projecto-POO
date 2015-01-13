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
    
    /**Método para abrir o ficheiro em modo de leitura
     * @param fileName
     * @throws java.io.IOException*/
    public void openRead(String fileName) throws IOException {
        fR = new BufferedReader(new FileReader(fileName)); 
    }

    /**Método para abrir o ficheiro em modo de escrita
     * @param fileName
     * @throws java.io.IOException*/
    public void openWrite(String fileName) throws IOException {
        fW = new BufferedWriter(new FileWriter(fileName));
    }
    
    /**Método para ler uma linha do ficheiro
     * @return String
     * @throws java.io.IOException*/
    public String readLine() throws IOException {
        return fR.readLine(); 
    }
    
    /**Método para escrever uma linha do ficheiro
     * @param line
     * @throws java.io.IOException*/
    public void writeLine(String line) throws IOException {
        fW.write(line,0,line.length());
        fW.newLine(); 
    }
    
    /**Método responsável pelo fecho do ficheiro de leitura
     * @throws IOException 
     */
    public void closeRead() throws IOException {
        fR.close(); 
    }
    
    /**Método responsável pelo fecho do ficheiro de escrita
     * @throws IOException 
     */
    public void closeWrite() throws IOException {
        fW.close(); 
    }
    
}
