/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Blaise
 */
public class ServeurString {
    /**
     * Port du serveur.
     */
    int port;

    /**
     * Constructeur qui créer le serveur et attend une requête du navigateur.
     * @param port 
     */
    public ServeurString(String port) {
        this.port = new Integer(port);

        //écoute
        this.ecoute();
    }

    /**
     * Lorsque cette méthode se lance, le seveur se met en écoute d'une
     * éventuelle requête du navigateur.
     */
    public void ecoute() {
        try {
            //Serveur Socket et socket
            ServerSocket ss = new ServerSocket(port);
            System.out.println(getColor(91) + "\n\nServeur chaîne de caractère lancé ! " + getColor(0));
            Socket s = ss.accept();

            //Création des objets nécessaires à la lecture
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //On stock la lecture dans cette variable
            String retour = "";

            //Lecture
            while (br.ready()) {
                retour += br.readLine() + "<br/>";
            }

            //Affichage dans le terminal
            System.out.println(retour);

            //Création du message retour, qui sera envoyé au navigateur
            String mess = "Bonjour, je suis Maxime Blaise.";
            retour = "<html><head><meta charset=\"UTF-8\" /></head><h1 style=\"color: green;text-align: center;\">Requête envoyé : </h1><p style=\"color: red;\">\n" + retour + "\n</p>";
            retour += "<h1 style=\"color: green;text-align: center;\">Message reçu du serveur : </h1><p style=\"color: red;\">\n" + mess + "\n</p>";
            retour += "</html>";

            //Envoi du message, en html.
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.println("HTTP/1.1 200 OK\nContent-Type: text/html\n\n");
            pw.println(retour);
            pw.println("");

            //Fermeture
            pw.close();
            os.close();

            /*byte[] data = retour.getBytes();
             OutputStream os = s.getOutputStream();
             os.write(data);*/
        } catch (IOException ex) {
            Logger.getLogger(ServeurString.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Méthode principale qui lance le serveur et le met à l'écoute.
     */
    public static void main(String[] args) {

        int nombreArguments = 1;

        //On test le nombre d'argument, car le port doit être renseigné en args[0]
        if (args.length != nombreArguments) {
            System.out.println(getColor(91) + "Argument(s) incorrects" + getColor(0));
        } else {

            //Lancement du serveur !
            ServeurString server = new ServeurString(args[0]);
        }
    }

    /**
     * Permet de colorer le terminal Linux.
     * @param i
     * @return 
     */
    public static String getColor(int i) {
        return "\033[" + i + "m";
    }

}
