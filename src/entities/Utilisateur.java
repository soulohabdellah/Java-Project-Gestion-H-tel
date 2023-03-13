/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Souloh
 */
public class Utilisateur {
    private int id;
    private static int count;
    private String nom;
    private String email;
    private String password;
    public Utilisateur(String nom,String email,String password){
    this.id=count++;
    this.nom=nom;
    this.email=email;
    this.password=password;
    
    }
    public Utilisateur(int id,String nom,String email,String password){
    this.id=id;
    this.nom=nom;
    this.email=email;
    this.password=password;
    
    }
    public String getEmail(){
    return this.email;
    }
      public String getNom(){
    return this.nom;
    }
        public int getId(){
    return this.id;
    }
     public String getPassword(){
    return this.password;
    }
      public void setEmail(String email){
     this.email=email;
    }
      public void setNom(String nom){
     this.nom=nom;
    }
     public void setPassword(String password){
     this.password=password;
    }
     public void setId(int id){
     this.id=id;
    }
     public String toString() {
		return  nom ;
	}
}
