/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Clase Prestamos con la que hacer instancias con la información de los
 * prestamos que se realizar en nuestra biblioteca.
 * 
 * Implementa ServiciosGestiondePrestamos con los servicios obligatorios que debe de
 * tener esta clase.
 *
 * @author serporion.
 */
public class Prestamos implements ServiciosGestionPrestamos {

    //Atributos privados variables de objeto
    private Usuarios usuario;
    private Libros libro;
    private LocalDate fechaInicio;

    /**
     * Constructor que genera un prestamo con los atributos indicados.
     *
     * @param usu tipo Usuarios con los datos del usuario del préstamo.
     * @param lib tipo Libros con los datos del libro del préstamo.
     * @param fecha tipo LocalDate con el autor del autor. 
     */
    public Prestamos(Usuarios usu, Libros lib, LocalDate fecha) {

        usuario = usu;
        libro = lib;
        fechaInicio = fecha;
    }

    /**
     * Metodo por defecto para usarlo en la clase Funcionalidades para darle la
     * posibilidad de instanciar la clase y tener acceso a sus métodos.
     */
    public Prestamos() {
    }

    /**
     * Metodo que nos dice si un libro está o no prestado. Si lo está no permite la creacion
     * de un prestamos con ese codigo de libro.
     */
    private boolean isPrestado(Prestamos presCompara, LinkedHashSet<Prestamos> prestamosActivos) {

        for (Prestamos prestamo : prestamosActivos) {
           
            if (prestamo.getLibro().equals(presCompara.getLibro())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que devuelve el Usuario del prestamo.
     *
     * @return tipo Usuario con el valor de un Usuario.
     */
    public Usuarios getUsuario() {
        return this.usuario;
    }

    /**
     * Método que devuelve el Libro prestado.
     *
     * @return tipo Libro con el valor de un Libro.
     */
    public Libros getLibro() {
        return this.libro;
    }

    /**
     * Método que devuelve la fecha de un préstamo.
     *
     * @return LocalDate fecha del préstamo.
     */
    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

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
    @Override
    public void añadirPrestamo(Usuarios usuario, Libros libro, LocalDate fechaPrestamo, LinkedHashSet<Prestamos> prestamosActivos, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros) throws IllegalStateException {

        String codigoUsuario = usuario.getId();
        String codigoLibro = libro.getId();

        Prestamos presCompara = new Prestamos(usuario, libro, fechaPrestamo);

        if (isPrestado(presCompara, prestamosActivos)) {
            throw new IllegalStateException("\nEl libro indicado con el Id " + codigoLibro + " ya esta prestado.");
        } else {
            
            System.out.println("                   -----------------------------");
            System.out.println("\n                   DATOS DEL PRESTAMO REALIZADO");

            //AÑADO EL PRESTAMO AL LINKED
            prestamosActivos.add(presCompara);

            System.out.println("                   -----------------------------");
            System.out.println(presCompara);

            // Para añadir los prestamos a un Usuario, debemos reconstruir un 
            // LinkedHasSet<Prestamos> con sus anteriores prestamos y añadir sobre 
            // este, el nuevo Prestamo. Luego hacemos un put sobre el HashMap con
            // este Linked para disponer de todos los prestamos del usuario.
            LinkedHashSet<Prestamos> prestamosDelUsuario = historicoPrestamosUsuarios.getOrDefault(codigoUsuario, new LinkedHashSet<>());
           
            prestamosDelUsuario.add(presCompara);
            
            historicoPrestamosUsuarios.put(codigoUsuario, prestamosDelUsuario);
            
            //Para añadir los prestamos a un Libro, debemos reconstruir un 
            //LinkedHasSet<Prestamos> con sus anteriores prestamos y añadir sobre 
            //este el nuevo Prestamo. Luego hacemos un put sobre el HashMap con este Linked.
            LinkedHashSet<Prestamos> prestamosDelUsuarioLibro = historicoPrestamosLibros.getOrDefault(codigoLibro, new LinkedHashSet<>());

            prestamosDelUsuarioLibro.add(presCompara);

            historicoPrestamosLibros.put(codigoLibro, prestamosDelUsuarioLibro);

        }
    }
    
    /**
     * Metodo que lista el historico de prestamos por Usuario.
     * 
     * @param codigoUsuario String con el codigo de Usuario a consulta.
     * @param historicoPrestamosUsuarios HashMap donde se guarda el historico de todos los usuarios.
     */
    @Override
    public void listarPrestamoPorUsuario(String codigoUsuario, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios) {

        if (historicoPrestamosUsuarios.isEmpty()) {
            System.out.println("\n\nAun no existen prestamos en la biblioteca. Convendría animar a los usuarios a realizar alguno.\n");
        } else {
            System.out.println("\nHISTORIO DE PRESTAMOS DEL USUARIO " + codigoUsuario);
            System.out.println("--------------------------------------------------");

            System.out.println();

            // Obtener el conjunto de préstamos para el usuario dado
            LinkedHashSet<Prestamos> prestamosUsuario = historicoPrestamosUsuarios.get(codigoUsuario);

            if (prestamosUsuario == null || prestamosUsuario.isEmpty()) {
                System.out.println("El usuario " + codigoUsuario + " no tiene prestamos registrados");
            } else {
                for (Prestamos prestamo : prestamosUsuario) {
                    System.out.println("\n" + prestamo.getLibro() + "Fecha: " + prestamo.getFechaInicio());
                }
            }
        }
    }

    /**
     * Metodo que lista el historico de prestamos por Libro.
     * 
     * @param codigoLibro String con el codigo de Libro a consultar.
     * @param historicoPrestamosLibros HashMap donde se guarda el historico de todos los Libros.
     */
    @Override
    public void listarPrestamoPorLibro(String codigoLibro, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros) {

        if (historicoPrestamosLibros.isEmpty()) {
            System.out.println("No hay historico de Prestamos por Libro. No hay prestamos.");
        } else {
            System.out.println("\n\nHISTORIO DE PRESTAMOS DEL LIBRO: " + codigoLibro);
            System.out.println("-------------------------------------------------");

            System.out.println();

            // Obtener el conjunto de préstamos para el usuario dado
            LinkedHashSet<Prestamos> prestamosLibro = historicoPrestamosLibros.get(codigoLibro);

            if (prestamosLibro == null || prestamosLibro.isEmpty()) {
                System.out.println("El Libro " + codigoLibro + " no tiene prestamos registrados");
            } else {
                for (Prestamos prestamo : prestamosLibro) {
                    System.out.println("\n" + prestamo.getUsuario() + "Fecha: " + prestamo.getFechaInicio());
                }
            }
        }
    }

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
    @Override
    public int devolverLibro(String codigoLibro, LinkedHashSet<Prestamos> prestamosActivos, boolean paso1,boolean paso2) {

        Scanner entrada = new Scanner(System.in);
        
        Prestamos prestamosADevolver = new Prestamos();
        
        int salida = -1;

        if (paso1 == false) {

            //Libros lib = new Libros();
            for (Prestamos prestamo : prestamosActivos) {
                // Obtener el libro del prestamo
                Libros lib = prestamo.getLibro();
                // Verificar si el código del libro coincide
                if (lib.getId().equals(codigoLibro)) {
                    // Se encontró el prestamo con el código del libro
                    salida = 0;
                    prestamosADevolver = prestamo;
                }
            }
            
            if (paso2==true){
                prestamosActivos.remove(prestamosADevolver);
            }
            
        } else {
            System.out.println("Desea continuar con la devolucion/eliminacion del prestamo del libro?");
            System.out.println("Teclee y / n");
            String pregunta = entrada.nextLine().toUpperCase();

            if (pregunta.equals("Y")) {
                devolverLibro(codigoLibro,prestamosActivos,false,true);
                
                System.out.println("Prestamo eliminado");
            }

            System.out.println("\n\nSaliendo de la opcion Devolucion");
        }
        return salida;
    }

     /**
     * Metodo para la consulta de los prestamos activos en vigor.
     * 
     * @param prestamosActivos LinkedHashSet con los prestamos en vigor.
     */
    @Override
    public void consultarPrestamosActivos(LinkedHashSet<Prestamos> prestamosActivos) {
        if (prestamosActivos.isEmpty()) {
            System.out.println("Aun no hay prestamos realizados. Anima a tus usuarios!");
        } else {
            Iterator<Prestamos> iterator = prestamosActivos.iterator();
            int contador = 0;

            while (iterator.hasNext()) {

                System.out.println("                                  -------------------");
                System.out.println("                                         Libro " + ++contador);
                System.out.println("                                  -------------------");
                Prestamos pres = iterator.next();

                System.out.println(pres);
            }
        }
    }

    /**
     * Metodo que sobreescribe el metodo toString() para su salida formateada 
     * de un objeto Prestamos según nuestras preferencias.
     * 
     * @return String con la salida formateada.
     */
    @Override
    public String toString() {

        String cadena;
        String cadenaLibro;
        String cadenaUsuario;

        cadenaUsuario = String.format("\nLIBRO PRESTADO\n--------------\n%s  ", this.getLibro());
        cadenaLibro = String.format("\n\nPRESTADO A\n----------\n%10s ", this.getUsuario());

        cadena = cadenaUsuario + cadenaLibro + "\n\nFECHA DEL PRESTAMO\n------------------\n" + this.getFechaInicio() + "\n";

        return cadena;
    }

}
