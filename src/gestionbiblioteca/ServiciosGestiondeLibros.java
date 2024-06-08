/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.util.ArrayList;

/**
 * Interface con los servicios obligatorios que debe de contener la clase Libros.
 * 
 * @author Serporion
 */
public interface ServiciosGestiondeLibros {
    
    /**
     * Método que permite añadir Usuarios a la biblioteca.
     * @param titulo String con el titulo del Libro.
     * @param autor String con el autor del Libro.
     * @param librosBiblioteca ArrayList coleccion con los libros de la biblioteca.
     */
    abstract public void añadirLibro(String titulo, String autor, ArrayList<Libros> librosBiblioteca);
    
    /**
     * Metodo que muestra los libros de los que dispone la biblioteca.
     * 
     * @param librosBiblioteca ArrayList coleccion con los datos de los libros
     * de la biblioteca.
     */
    abstract public void mostrarLibros(ArrayList<Libros> librosBiblioteca);
    
    /**
     * Metodo que permite buscar un usuario en la coleccion de datos de nuestra biblioteca.
     * 
     * @param codigoLibro String con el codigo de libro que queremos buscar.
     * @param librosBiblioteca  ArrayList con los daatos de los libros de la Biblioteca donde buscar.
     * 
     * @return int donde se devuelve el indice del ArrayList donde se encuentra el libro para su posterior
     * uso de sus datos. Devuelve -1 si no encuentra el libro. 
     */
    abstract public int buscarLibro(String codigoLibro, ArrayList<Libros> librosBiblioteca);
    
    
    
}
