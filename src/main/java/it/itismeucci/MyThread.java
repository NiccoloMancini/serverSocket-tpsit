package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread extends Thread{
    Socket s;
    public MyThread (Socket s){
        this.s = s;
    }

    @Override
    public void run(){
        System.out.println("qualcuno si è collegato");
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String stringaRicevuta;
            String stringaMaiuscola;
            do {
                stringaRicevuta = in.readLine();
                System.out.println("la stringa ricevuta: " + stringaRicevuta);
                stringaMaiuscola = stringaRicevuta.toUpperCase();
                if (stringaRicevuta.equals("!")) {
                    out.writeBytes("comunicazione terminata \n");
                }else{
                    out.writeBytes( stringaMaiuscola + "\n");
                }
            } while (!stringaRicevuta.equals("!"));
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
