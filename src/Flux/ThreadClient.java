/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class ThreadClient implements Runnable {

    /**
     * Valeur de l'IP.
     */
    public String ip = "";
    /**
     * Valeur du port.
     */
    public String port = "";
    /**
     * Panel où seront présent les champs de saisie, et le bouton envoyer.
     */
    public JPanel panHaut;
    /**
     * Champ de saisie de l'IP, pré rempli au démarrage.
     */
    JTextField jtf_ip = new JTextField(10);
    /**
     * Champ de saisie du port, pré rempli au démarrage.
     */
    JTextField jtf_port = new JTextField(5);
    /**
     * Champ de saisie du nombre à envoyer.
     */
    JTextField jtf_nombre = new JTextField(5);
    //JLabel label = new JLabel("Nomber à envoyer");
    /**
     * Button envoyer, qui envoie l'objet.
     */
    JButton bok = new JButton("Envoyer");
    /**
     * Devient true dès qu'on lance l'objet.
     */
    boolean lance = false;
    /**
     * Fenêtre graphique.
     */
    JFrame fen = new JFrame();

    /**
     * Constructeur de l'interface client.
     * @param ip
     * @param port 
     */
    public ThreadClient(String ip, String port) {
        JPanel pan = new JPanel();

        panHaut = new JPanel();
        panHaut.setLayout(new BorderLayout());
        JPanel panHaut1 = new JPanel();
        panHaut1.setLayout(new GridLayout(3, 2));
        panHaut1.add(new JLabel("IP : "));
        jtf_ip.setText(ip);
        panHaut1.add(jtf_ip);
        panHaut1.add(new JLabel("Port : "));
        jtf_port.setText(port);
        panHaut1.add(jtf_port);
        panHaut1.add(new JLabel("Nombre à envoyer "));
        panHaut1.add(jtf_nombre);

        bok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lance = true;

                Thread t2 = new Thread(fen());
                t2.start();
            }
        });
        panHaut.add(panHaut1, BorderLayout.CENTER);
        panHaut.add(bok, BorderLayout.SOUTH);

        fen.setTitle("Client objet");
        setPanel("En attente...");
        fen.setLocation(150,150);
        fen.setPreferredSize(new Dimension(400, 300));
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fen.pack();
        fen.setVisible(true);
    }

    /**
     * Modifie le panel pour afficher le label.
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
     * Lance le client et envoie l'objet.
     */
    public void run() {
        Client client = new Client(jtf_ip.getText(), jtf_port.getText(), new MaClasse(new Integer(jtf_nombre.getText())));
        if (client.succes) {
            setPanel("Objet envoyé !!");
        }
    }
    
    /**
     * Retourne this.
     * @return 
     */
    public ThreadClient fen() {
        return this;
    }

    /*public static void main(String[] args) {

        ThreadClient tc = new ThreadClient();
    }*/
}
