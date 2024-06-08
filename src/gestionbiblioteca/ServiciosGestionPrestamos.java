/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Interface que define de manera obligada los metodos en la clase Prestamos con 
 * los que trabajar.
 * 
 * @author serporion
 */
public interface ServiciosGestionPrestamos {
    
    /**
     * Metodo que es necesario en la clase que crea el servicio de prestamos de la 
     * biblioteca.
     * 
     * @param usuario Usuario que recoge quien realiza el prestamo.
     * @param libro Libro que es el prestado por la clase Prestamo.
     * @param fechaPrestamo LocalDate con la fecha actual del Prestamo.
     * @param prestamosActivos LinkedHashSet con los prestamos en vigor.
     * @param historicoPrestamosUsuarios HashMap para el control del historico de prestamos por Usuario. Clave String y estructura de Prestamos LinkedHashSet.
     * @param historicoPrestamosLibros HashMap para el control del historico de prestamos por Libros. Clave String y estructura de Prestamos LinkedHashSet.
     */
    abstract public void añadirPrestamo(Usuarios usuario, Libros libro, LocalDate fechaPrestamo, LinkedHashSet<Prestamos> prestamosActivos, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros);

    /**
     * Metodo que lista el historico de prestamos por Usuario.
     * 
     * @param codigoUsuario String con el codigo de Usuario a consultar.
     * @param historicoPrestamosUsuarios HashMap donde se guarda el historico de todos los usuarios.
     */
    abstract public void listarPrestamoPorUsuario(String codigoUsuario, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios);
         
    /**
     * Metodo que lista el historico de prestamos por Libro.
     * 
     * @param codigoLibro String con el codigo de Libro a consultar.
     * @param historicoPrestamosLibros HashMap donde se guarda el historico de todos los Libros.
     */
    abstract public void listarPrestamoPorLibro(String codigoLibro, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros);
    
    /**
     * Metodo para hacer una devolucion de un libro prestado. Primero busca si el 
     * codigo del libro está en el LinkedHasSet y posteriormente si es así lo 
     * devuelve elimiándolo de la colección de prestados, dejandolo de nuevo para 
     * la posibilidad de prestamo.
     * 
     * @param codigoLibro String con el codigo de Libro a consultar.
     * @param prestamosActivos LinkedHashSet con los prestamos en vigor.
     * @param paso1 booleano que si llega false busca el libro
     * @param paso2 booleano que si llega true realiza la devolucion.
     * @return int con los casos establecidos. -1 si es false, 0 si es true.
     */
    abstract public int devolverLibro(String codigoLibro, LinkedHashSet<Prestamos> prestamosActivos, boolean paso1,boolean paso2);
    

    /**
     * Metodo para la consulta de los prestamos activos en vigor.
     * 
     * @param prestamosActivos LinkedHashSet con los prestamos en vigor.
     */
    abstract public void consultarPrestamosActivos(LinkedHashSet<Prestamos> prestamosActivos);
    
}