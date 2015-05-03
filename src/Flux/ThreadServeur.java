/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Flux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author blaise
 */
public class ThreadServeur implements Runnable {
    /**
     * Valeur du port sur lequel va écouter le serveur.
     */
    public String port = "2000";
    /**
     * Panel où sera présent le champ de saisie du port.
     */
    public JPanel panHaut;
    /**
     * Champ de saisie du port.
     */
    JTextField jtf = new JTextField(5);
    
    /**
     * Lance le serveur.
     */
    JButton bok = new JButton("Lancer");
    /**
     * True si le serveur est lancé.
     */
    boolean lance = false;
    /**
     * Fenêtre graphique.
     */
    JFrame fen = new JFrame();
    
    /**
     * Création de l'interface serveur.
     * @param port 
     */
    public ThreadServeur(String port) {
        this.port = port;   
        jtf.setText(port);
        JPanel pan = new JPanel();
        
        panHaut = new JPanel();
        panHaut.add(jtf);
        
        bok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lance = true;
                
                Thread t1 = new Thread(fen());
                t1.start();
            }
        });
        panHaut.add(bok);
        
        fen.setTitle("Serveur objet");
        setPanel("En attente...");
        fen.setLocation(550,150);
        fen.setPreferredSize(new Dimension(400,300));
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fen.pack();
        fen.setVisible(true);
    }
    
    /**
     * Return this.
     * @return 
     */
    public ThreadServeur fen() {
        return this;
    }
    
    /**
     * Modifie le panel pour afficher le nombre reçu.
     * @param s 
     */
    public void setPanel(String s) {
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        pan.add(panHaut, BorderLayout.NORTH);
        
        JPanel panCenter = new JPanel();
        panCenter.add(new JLabel(s));
        pan.add(panCenter, BorderLayout.CENTER);
        
        fen.setContentPane(pan);
        fen.pack();
    }

    @Override
    /**
     * Lance le serveur.
     */
    public void run() {
        ServeurObjet server = new ServeurObjet(jtf.getText(), this);
    }

    
}
