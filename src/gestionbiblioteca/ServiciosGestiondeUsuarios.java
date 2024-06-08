/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.util.ArrayList;


/**
 * 
 * Interface con los servicios obligatorios que debe de contener la clase Usuarios.
 * 
 * @author serporion
 */
 public interface ServiciosGestiondeUsuarios {
    
  
    /**
     * Método que permite añadir Usuarios a la biblioteca.
     * @param nombre String con el nombre del Usuario.
     * @param correo String con el correo electroncio del Usuario.
     * @param usuariosBiblioteca ArrayList coleccion con los usuarios de la biblioteca.
     */
    abstract public void añadirUsuarios(String nombre, String correo, ArrayList<Usuarios> usuariosBiblioteca);
    
    /**
     * Método que permite mostrar un Usuario de la biblioteca.
     * 
     * @param usuariosBiblioteca ArrayList con los daatos de los usuarios de la Biblioteca.
     */
    abstract public void mostrarUsuarios(ArrayList<Usuarios> usuariosBiblioteca);
    
    
    /**
     * Metodo que permite buscar un usuario en la coleccion de datos de nuestra biblioteca.
     * 
     * @param codigoUsuario String con el codigo de usuario de la persona a buscar.
     * @param usuariosBiblioteca  ArrayList con los daatos de los usuarios de la Biblioteca.
     * 
     * @return int donde se devuelve el indice del ArrayList donde se encuentra el usuario para su posterior
     * uso de sus datos. Devuelve -1 si no encuentra el usuario. 
     */
    abstract public int buscarUsuario(String codigoUsuario, ArrayList<Usuarios> usuariosBiblioteca);
    
    
    
}
