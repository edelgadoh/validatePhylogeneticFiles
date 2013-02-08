/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validateph_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author projects
 */
public class ValidatePH_Files {

    public void validtePH(String listOfFileNames, String folderInput, String folderOutput){
        BufferedReader readbuffer = null;
        String strRead = "";
        String content = "";
        FileWriter arquivo;
        File f;
                
        try {  
            
            Integer openParenthesis = 0;
            Integer closeParenthesis = 0;
            try {
                // TODO code application logic here
                readbuffer = new BufferedReader(new FileReader(listOfFileNames));//id
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ValidatePH_Files.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<String> fileNames = new ArrayList<String>();
            
            try {
                while ((strRead=readbuffer.readLine())!=null){
                    fileNames.add(strRead);
                }
            } catch(Exception e){}
            
            readbuffer.close();
            
            char temp[] = null;
            Stack pilha = new Stack();
            for(String s: fileNames){
                //System.out.println(s);
                openParenthesis = 0;
                closeParenthesis = 0;
                pilha.removeAllElements();
                content = "";
                
                try {
                    readbuffer = new BufferedReader(new FileReader(folderInput+"/"+s));//id
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ValidatePH_Files.class.getName()).log(Level.SEVERE, null, ex);
                }
                while ((strRead=readbuffer.readLine())!=null){
                    content += strRead;
                    if(strRead.contains("(") || strRead.contains(")")){
                        temp = strRead.toCharArray();
                        for(char c: temp){
                            if(c == '('){
                                pilha.push(c);
                            } else if(c == ')'){
                                if(!pilha.empty()){
                                    pilha.pop();
                                } else {
                                    System.out.println(s+": Erro in stack, about pop ) ");
                                }
                            }
                        }
                        //openParenthesis += strRead.split("\\(").length-1;
                    }
                    //if(strRead.contains(")")){
                    //    closeParenthesis += strRead.split("\\)").length-1;
                    //}
                }
                while(!pilha.empty()){
                    pilha.pop();
                    System.out.println(s+": Already content in stack, about pop ) ");
                }
                //System.out.println("#( = "+openParenthesis+", #) = "+closeParenthesis);
                
                f = new File(folderOutput+"/"+s);
                Writer w = new FileWriter(f);
                try {
                    w.write(content+");");
                } finally {
                    try {
                        w.close();
                    } catch (IOException ex) {
                    }
                }
                //arquivo.write(content+")");
                //arquivo.close();
                //arquivo = null;
                
            }
            
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
    
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        ValidatePH_Files i = new ValidatePH_Files();
        String listOfFileNames = "/home/projects/Downloads/corrigir";
        String folderInput = "/home/projects/Downloads/phErro";
        String folderOutput = "/home/projects/Downloads/phCorrigido";
        
        Integer argsLength = args.length;
        
        if(argsLength > 0 && argsLength == 3){
            listOfFileNames = args[0];
            folderInput = args[1];
            folderOutput = args[2];
            i.validtePH(listOfFileNames,folderInput,folderOutput);
            
        } else {
            System.out.println("Usage: jar <listOfFiles> <input dir> <output dir>");
        }
        //testes locais
        //i.validtePH(listOfFileNames,folderInput,folderOutput);
        
        
    }
}
