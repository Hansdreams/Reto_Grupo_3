/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Daw112
 */
public class Usuario {
    
    private String email;
    private String password;
    private String perfil;

    public Usuario(String email, String password , String perfil) {
        this.email = email;
        this.password = password;
        this.perfil = perfil;
    }
    
    // Gets y Sets
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", password=" + password + ", perfil=" + perfil + '}';
    }

    
    
    
    
    
}
