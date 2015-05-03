/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flux;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Blaise
 */
public class Client {
    /**
     * Adresse ip à laquelle on envoie l'objet.
     */
    public String ip;
    /**
     * Port associé.
     */
    public int port;
    /**
     * Instance de ma classe.
     */
    public MaClasse max;
    /**
     * Si on envoie bien l'objet, ou pas.
     */
    public boolean succes = false;

    /**
     * Création du client.
     * @param ip
     * @param port
     * @param max 
     */
    public Client(String ip, String port, MaClasse max) {
        this.ip = ip;
        this.port = new Integer(port);
        this.max = max;

        //Envoie de l'objet.
        if (this.send(max)) {
            System.out.println("Done.");
            succes = true;
        }
    }

    /**
     * Méthode qui se charge d'envoyer l'objet à la bonne destination.
     * @param max
     * @return true si l'envoie a été effectué avec succès.
     */
    public boolean send(MaClasse max) {
        System.out.println("Envoi du fichier à "+getColor(92) +"@" + ip + getColor(0) + " port : " + getColor(92) + port + getColor(0) + " ...");
        try {
            Socket s = new Socket(ip, port);

            
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            oos.writeObject(max);
            
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Méthode principale qui demande un nombre, créer le client avec l'instante de MaClasse, et envoie.
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println(getColor(91) + "Client Flux : " + getColor(0));
        
        if(args.length != 2) {
            System.out.println("Argument(s) incorrects");
        } else {
            Scanner sc = new Scanner(System.in);
           // System.out.print("");
            System.out.print("Entrer un nombre : ");
            int monNombre = sc.nextInt();
            System.out.println("");
            
            MaClasse objet = new MaClasse(monNombre);
            Client c = new Client(args[0], args[1], objet);
        }
    }
    
    /**
     * Simple méthode pour colorer le terminal linux.
     * @param i
     * @return 
     */
    public static String getColor(int i) {
        return "\033[" + i + "m";
    }
}
