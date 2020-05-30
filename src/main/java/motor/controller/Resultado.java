/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author mateo
 */
public class Resultado implements Comparable<Resultado>{
    private String nomDoc;
    private ArrayList<String> termino;
    private int peso = 0;
    private String titulo;

    public Resultado() {
    }

    
    public Resultado(String nomDoc, String titulo, String termino, int peso) {
        this.nomDoc = nomDoc;
        this.titulo = titulo;
        this.termino = new ArrayList();
        this.termino.add(termino);
        this.peso = peso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    

    public ArrayList<String> getTermino() {
        return termino;
    }

    public void setTermino(ArrayList<String> termino) {
        this.termino = termino;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nomDoc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resultado other = (Resultado) obj;
        if (!Objects.equals(this.nomDoc, other.nomDoc)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Resultado x)
    {
        return x.peso - this.peso;
    }

    @Override
    public String toString() {
        return nomDoc + " título = " + titulo + " terminos = [" + termino + "] \t peso = " + peso + '\n';
    }

    public void agregarTermino(String termino, int peso){
        if (!this.termino.contains(termino)){
            this.termino.add(termino);
            peso += peso*0.1;
        }
        
        this.acumularPeso(peso);
    }
    
    private void acumularPeso(int peso) {
        this.peso += peso;
    }
    
}
