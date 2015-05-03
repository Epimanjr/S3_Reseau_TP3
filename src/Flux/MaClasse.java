/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Flux;

import java.io.Serializable;

/**
 *
 * @author blaise
 */
public class MaClasse implements Serializable {
    private int monNombre;

    /**
     * Constructeur qui construit mon objet.
     * @param monNombre 
     */
    public MaClasse(int monNombre) {
        this.monNombre = monNombre;
    }
    
    /**
     * Incrémentation de mon nombre.
     */
    public void increase() {
        this.setMonNombre(this.getMonNombre()+1);
    }
    
    /**
     * Décrémentation de mon nombre.
     */
    public void decrease() {
        this.setMonNombre(this.getMonNombre()+-1);
    }

    /**
     * Méthode d'accès à mon nombre.
     * @return 
     */
    public int getMonNombre() {
        return monNombre;
    }

    /**
     * Permet de modifier mon nombre.
     * @param monNombre 
     */
    public void setMonNombre(int monNombre) {
        this.monNombre = monNombre;
    }
    
    
}
