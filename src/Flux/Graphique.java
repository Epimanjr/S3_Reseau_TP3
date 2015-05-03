/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flux;

/**
 *
 * @author Maxime Blaise
 */
public class Graphique {

    /**
     * Méthode principale qui se charge de lancer les deux fenêtres client et serveur.
     * @param args 
     */
    public static void main(String[] args) {

        if (args.length == 2) {
            //IP et PORT sont les deux arguments (normalement)
            ThreadClient tc = new ThreadClient(args[0], args[1]);
            ThreadServeur ts = new ThreadServeur(args[1]);
        } else {
            //Sinon, on met un port et une adresse IP par défaut.
            ThreadClient tc = new ThreadClient("127.0.0.1", "2000");
            ThreadServeur ts = new ThreadServeur("2000");
        }

    }
}
