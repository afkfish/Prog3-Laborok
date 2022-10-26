package swingmvclab;

import java.io.Serializable;

/*
 * Egy hallgat� adatait t�rol� oszt�ly.
 * 
 * NE M�DOS�TSD!
 */
public class Student implements Serializable {

    // Hallgat� neve
    private String name;

    // Hallgat� Neptun-k�dja.
    private String neptun;

    // Van-e al��r�sa.
    private boolean signature;

    // A megszerzett oszt�lyzat.
    private int grade;

    // Hallgat� nev�nek lek�rdez�se. 
    public String getName() {
        return name;
    }
    
    // Hallgat� nev�nek be�ll�t�sa.
    public void setName(String name) {
        this.name = name;
    }
    
    // Hallgat� Neptun-k�dj�nak lek�rdez�se.
    public String getNeptun() {
        return neptun;
    }
    
    // Hallgat� Neptun-k�dj�nak be�ll�t�sa.
    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }
    
    // Al��r�s megl�t�nek lek�rdez�se.
    public Boolean hasSignature() {
        return signature;
    }
    
    // Al��r�s megl�t�nek be�ll�t�sa.
    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    // Oszt�lyzat lek�rdez�se.
    public Integer getGrade() {
        return grade;
    }
    
    // Oszt�lyzat be�ll�t�sa.
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    // Hallgat� l�trehoz�sa
    public Student(String name, String neptun, Boolean signature, Integer grade) {
        this.name = name;
        this.neptun = neptun;
        this.signature = signature;
        this.grade = grade;
    }
}
