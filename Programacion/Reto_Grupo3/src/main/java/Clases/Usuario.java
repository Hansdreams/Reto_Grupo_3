/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Enums.Perfil;

/**
 * Esta clase contiene los atributos y metodos de un Usuario
 * @author Grupo3
 * @version 1.0
 */
public class Usuario {

    private int idUsuario;
    private String email;
    private String password;

    /**
     *
     * @param idUsuario Numero identificador de un usuario
     * @param email Correo electronico de un usuario
     * @param password Contraseña de un usuario
     */
    public Usuario(int idUsuario, String email, String password) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;

    }

    /**
     * Método que obtiene el id del usuario
     *
     * @return Regresa el id del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Método que obtiene el email del usuario
     *
     * @return Regresa el email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que devuelve la contrseña del usuario
     *
     * @return Regresa la contraseña
     */
    public String getPassword() {
        return password;
    }

}
