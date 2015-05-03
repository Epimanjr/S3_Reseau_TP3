/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flux;

import static Flux.ServeurString.getColor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Blaise, S4-OS
 */
public class ServeurObjet {

    /**
     * Le port sur lequel le serveur va écouter
     */
    private int port;

    /**
     * Constructeur qui initialise le serveur et se met en écoute sur le bon
     * port.
     *
     * @param port
     */
    public ServeurObjet(String port, ThreadServeur ts) {
        try {
            this.port = new Integer(port);
        }catch(Exception e) {
            //
        }

        //Le serveur se met à l'écoute.
        this.ecoute(ts);
    }

    /**
     * Ecoute du serveur sur le bon port.
     * @param ts 
     */
    public void ecoute(ThreadServeur ts) {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println(getColor(91) + "\nServeur objet lancé ! " + getColor(0));
            Socket s = ss.accept();

            //Thread
            MyThread mt = new MyThread(s, ss, ts);
            mt.start();
        } catch (IOException ex) {
            Logger.getLogger(ServeurObjet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Récupère le port
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * Méthode principale qui lance le serveur.
     * @param args 
     */
    public static void main(String[] args) {

        int nombreArguments = 1;

        //On test le nombre d'argument, car le port doit être renseigné en args[0]
        if (args.length != nombreArguments) {
            System.out.println(getColor(91) + "Argument(s) incorrects" + getColor(0));
        } else {

            //Lancement du serveur !
            ServeurObjet server = new ServeurObjet(args[0], null);
        }
    }

    /**
     * Permet de colorer le terminal Linux.
     *
     * @param i
     * @return
     */
    public static String getColor(int i) {
        return "\033[" + i + "m";
    }

    /**
     * Class MyThread
     */
    public class MyThread extends Thread {

        Socket s;
        ServerSocket ss;
        ThreadServeur ts;

        /**
         * Constructeur du Thread
         * @param s
         * @param ss
         * @param ts 
         */
        public MyThread(Socket s, ServerSocket ss, ThreadServeur ts) {
            this.s = s;
            this.ss = ss;
            this.ts = ts;
        }

        /**
         * Méthode qui lance le serveur en parallèle du client.
         */
        public void run() {
            InputStream is = null;
            try {
                is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                //Création de l'objet
                MaClasse monObjet = (MaClasse) ois.readObject();
                //Affichage dans le terminal
                if (ts != null) {
                    ts.setPanel("<html><p>Objet reçu ! Mon Nombre : <span style=\"color: red; font-size: 3em;\">" + monObjet.getMonNombre() + "</span></p></html>");
                }
                System.out.println("Objet reçu ! Mon Nombre : " + monObjet.getMonNombre());
                s.close();
                ss.close();
            } catch (    IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServeurObjet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServeurObjet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
