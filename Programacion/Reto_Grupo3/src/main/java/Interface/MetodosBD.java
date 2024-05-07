/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author DAW112
 */
public interface MetodosBD<T> {
    
    //Metodo que listar cualquier objeto T
    ArrayList<T> listar();
    
    //Metodo que recuperara un objeto mediante un parametro String
    T buscar(String aux);
    
    //Metodo que guardara un objeto mediante un un generico
    void guardar(T t);
    
    //metodo que eliminar un objeto mediante un parametro String
    void eliminar(String aux);
    
    //modificar
    void modificar(T t);
    
}
