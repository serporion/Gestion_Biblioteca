/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionbiblioteca;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Aplicación de gestión de una biblioteca en Java, que incluye una interfaz de
 * menú para facilitar la interacción con el sistema. Esta aplicación permitirá
 * no solo el registro y la gestión de libros y usuarios, sino también el manejo
 * "eficiente" (entre comillas) de préstamos para mantener un control sobre los 
 * libros disponibles.
 *
 * @author serporion
 */
public class GestionBiblioteca {

    /**
     * Lanzador del programa tran inicialilzar las colecciones de datos que usaremos.
     * Llama a la clase Funcionalidades a su funcion menu() que devuelve la opcion que 
     * usaremos para llamar al método opcionesDelPrograma() que está dentro de la propia 
     * clase funcionalididades. A este métodos le enviaremos las estructuras de datos a usar.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Inicialización de las colecciones de datos necesarias que usará el programa.
        //El uso que se le da a cada una está definida en los métodos que las usan.
        ArrayList<Usuarios> usuariosBiblioteca = new ArrayList<>();
        ArrayList<Libros> librosBiblioteca = new ArrayList<>();
        LinkedHashSet<Prestamos> prestamosActivos = new LinkedHashSet<>();
        HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios = new HashMap<>();
        HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros = new HashMap<>();

        //Añado Datos de ejemplo para realizar pruebas.
        
        //Usuarios instancias nuevas.
        Usuarios usu1 = new Usuarios("Lilyana Megias Teruel", "lilyana@lilyana.com");
        Usuarios usu2 = new Usuarios("Pedro Romero Lozano", "pedro@pedro.com");
        Usuarios usu3 = new Usuarios("Jose Moreno Romero", "jose@jose.com");

        //Añado los usuarios al ArrayList de Usuarios.
        usuariosBiblioteca.add(usu1);
        usuariosBiblioteca.add(usu2);
        usuariosBiblioteca.add(usu3);

        //Libros instancias nuevas.
        Libros lib1 = new Libros("El mundo de sofia", "Ralph Perrot Smith");
        Libros lib2 = new Libros("La biblia", "Nuestro padre el Señor");
        Libros lib3 = new Libros("El desafio de los dioses", "Jose Antonio Silva");

        //Añado los libros al ArrayList de Libros.
        librosBiblioteca.add(lib1);
        librosBiblioteca.add(lib2);
        librosBiblioteca.add(lib3);
        
        
        boolean paso = false;

        while (!paso) {

            String eleccion = Funcionalidades.menu();

            if (eleccion.equals("SALIR")) {
                paso = true;

            } else {
                Funcionalidades.opcionesDelPrograma(usuariosBiblioteca, librosBiblioteca, eleccion, prestamosActivos, historicoPrestamosUsuarios, historicoPrestamosLibros);
            }
        }
        System.out.println("\n\nSaliendo del programa");

    }
}
