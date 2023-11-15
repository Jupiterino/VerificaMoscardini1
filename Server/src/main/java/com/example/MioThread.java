package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread {
    Socket s;
    ArrayList<String> parole = new ArrayList<String>();

    public MioThread(Socket s) {
        this.s = s;
    }
    
    public void run() {

        parole.add("casa");
        parole.add("albero");
        parole.add("gatto");
        parole.add("palla");
        parole.add("fiume");

        try {

            System.out.println("un client si è collegato");
            // server.close();
            char[] arr = new char[1000];
            String[] arr2 = new String[1000];
            String out = "";
            int casuale = (int)(Math.random()*5);
            String ParGen = parole.get(casuale);
            String ParUt = "";
            int cont = 0;
            BufferedReader inDalClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream outVersoClient = new DataOutputStream(s.getOutputStream());
            //System.out.println(ParGen);
            do {
                cont++;
                String stringaRicevuta = inDalClient.readLine(); // rimango in attesa nell ingresso
                System.out.println(stringaRicevuta);
                ParUt = stringaRicevuta;
                String A;
                //for(int i = 0; i < ParGen.length(); i++){
                //    out = out + "+";
                //}
                        for (int i = 0; i < ParGen.length(); i++) {
                            
                            arr2[i] = "*";
                            
                        }
                if (ParUt.length() == 1) { //PROVA CON LA LETTERA
                    //System.out.println("stai provando con la lettera: "+ParUt);

                    if(ParGen.contains(ParUt)){
                        System.out.println("è presente");
                        

                        
                        for (int i = 0; i < ParGen.length(); i++) {
                            arr[i] = ParGen.charAt(i);
                            
                        }
                        for(int i = 0; i < ParGen.length(); i++){
                            String s=String.valueOf(arr[i]);  
                            
                            
                            if(s.equals(ParUt)){
                                out = out + s;
                                arr2[i] = ParUt;
                                //System.out.println(s);   
                            }
                            else{
                                out = out + "*";
                                //System.out.println("*");
                            }
                        }
                        String Aaa="";
                        for(int i = 0; i < ParGen.length(); i++){
                            Aaa = Aaa + arr2[i];
                        }
                        outVersoClient.writeBytes("1\n");
                        outVersoClient.writeBytes(Aaa + "\n");
                        
                        
                    }
                    else{
                        System.out.println("non è presente\n");
                        outVersoClient.writeBytes("2\n");
                    }
                }
                if (ParUt.length() > 1) { //PROVA CON LA PAROLA
                    if (ParUt.equals(ParGen)){
                        System.out.println("hai indovinato!!!\n");
                        outVersoClient.writeBytes("3\n");
                        outVersoClient.writeBytes(Integer.toString(cont) + '\n');

                    }
                    else{
                        System.out.println("non hai indovinato!!!\n");
                        outVersoClient.writeBytes("4\n");
                    }
                }
                

            } while (ParGen != ParUt);
            System.out.println("un client si è disconnesso");
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza dal server !");
            System.exit(1);
        }
    }
}
