package com.example; // SERVER


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        
        
        String par;
        try {
            System.out.println("1 SERVER partito in esecuzione...");
            //dichiaro arraylist vuoto
            //passo il riferimento all'arraylist
            
            ServerSocket server = new ServerSocket(3000);
            
            do{
                Socket client = server.accept();
                MioThread m = new MioThread(client);
                //aggiungo all'arraylist
                m.start();
            }while(true);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza dal server !");
            System.exit(1);
        } 


            
    }
    
}
