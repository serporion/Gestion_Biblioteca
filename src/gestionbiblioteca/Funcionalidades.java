/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que se encarga de estructurar y manejar el metodo menu() del programa y 
 * las opciones elegidas en el metodo opcionesDelPrograma().
 *
 * @author serporion
 */
public class Funcionalidades {

    public static String menu() {

        Scanner entrada = new Scanner(System.in);
        String opcion = "";
        boolean paso = false;

        System.out.println("\n\n   -----------------------------------");
        System.out.println("        MENU GESTION DE BIBLIOTECA");
        System.out.println("   -----------------------------------\n");

        System.out.println(" 1. Incluir un Libro");
        System.out.println(" 2. Mostrar los Libros");
        System.out.println(" 3. Incluir un Usuario");
        System.out.println(" 4. Mostrar los Usuario");
        System.out.println(" 5. Prestar un Libro");
        System.out.println(" 6. Devolucion de Prestamo");
        System.out.println(" 7. Mostrar Prestamos Activos");
        System.out.println(" 8. Mostrar Historio de Prestamos por Usuario");
        System.out.println(" 9. Mostrar Historio de Prestamos por Libro");
        System.out.println(" -. Salir");

        System.out.println("\n(teclee 1, 2, 3, 4, 5, 6, 7, 8, 9 o salir)\n");

        while (!paso) {

            opcion = entrada.next().toUpperCase();

            if ("1".equals(opcion) || opcion.equals("2") || opcion.equals("3") || opcion.equals("4") || opcion.equals("5") || opcion.equals("6") || opcion.equals("7") || opcion.equals("8") || opcion.equals("9") || opcion.equals("SALIR")) {
                paso = true;
                opcion = opcion.toUpperCase();
            } else {
                System.out.println("Opcion de menu no valida");
            }
        }
        return opcion;
    }

    /**
     * Metodo que recoge las estructuras que se usarán en cada uno de los casos
     * del menu elegido.
     *
     * @param usuariosBiblioteca ArrayList con los usuarios de la Biblioteca.
     * @param librosBiblioteca ArrayList con los libros de la Biblioteca.
     * @param eleccion String con la opcion del menu.
     * @param prestamosActivos LinkedHashSet para controlar los prestamos en
     * vigor.
     * @param historicoPrestamosUsuarios HashMap para el control del historico
     * de prestamos por Usuario. Clave String y estructura de Prestamos
     * LinkedHashSet.
     * @param historicoPrestamosLibros HashMap para el control del historico de
     * prestamos por Libros. Clave String y estructura de Prestamos
     * LinkedHashSet.
     */
    public static void opcionesDelPrograma(ArrayList usuariosBiblioteca, ArrayList librosBiblioteca, String eleccion, LinkedHashSet<Prestamos> prestamosActivos, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosUsuarios, HashMap<String, LinkedHashSet<Prestamos>> historicoPrestamosLibros) {

        Usuarios funcionesDeGestionUsuarios = new Usuarios();
        Libros funcionesDeGestionLibros = new Libros();
        Prestamos funcionesDeGestionPrestamos = new Prestamos();

        /**
         * Variables String para su uso de apoyo para las clases Libro y
         * Usuario.
         */
        String tituloLibro;
        String nombreAutor;
        String codigoLibro;

        String nombreUsuario;
        String correoUsuario;
        String codigoUsuario;

        /**
         * Variables de apoyo para interactuar dentro de los menus.
         */
        String exit = "Y";
        String pregunta = "";
        boolean paso = false;

        /**
         * Variables int de uso común en ciertos menus.
         */
        int indiceLibro = -1;
        int indiceUsuario = -1;

        Scanner entrada = new Scanner(System.in);

        /**
         * Enrutador de opciones. No sale hasta dar la opcion dentro
         * establecidas.
         */
        switch (eleccion) {

            case "1":

                System.out.println("\nINCLUIR UN LIBRO");
                System.out.println("----------------");

                do {

                    System.out.print("Dame el titulo de un libro para incluirlo en la biblioteca");
                    System.out.print("\nTitulo del libro: ");
                    tituloLibro = entrada.nextLine();
                    System.out.print("Dame un autor del libro para incluirlo en la biblioteca");
                    System.out.print("\nNombre del autor: ");
                    nombreAutor = entrada.nextLine();

                    try {
                        funcionesDeGestionLibros.añadirLibro(tituloLibro, nombreAutor, librosBiblioteca);
                        System.out.println("\nLibro añadido correctamente");
                    } catch (NullPointerException e) {
                        System.err.println("\nError al añadir el libro: " + e.getMessage());
                    } catch (IndexOutOfBoundsException e2) {
                        System.err.println("\nTitulo o autor demasido corto. Inserte los datos de forma correcta.");
                    }

                    System.out.println("\nTeclee 'y' para salir o cualquier tecla para seguir introduciendo Libros.\n");

                } while (!entrada.nextLine().toUpperCase().equals(exit));

                break;

            case "2":
                System.out.println("\nMOSTRAR LOS LIBROS DE LA BIBLIOTECA");
                System.out.println("------------------------------------");
                try {
                    funcionesDeGestionLibros.mostrarLibros(librosBiblioteca);
                } catch (NoSuchElementException e) {
                    System.out.println("\nNo hay elementos que mostrar.");
                }
                break;

            case "3":

                System.out.println("\nINCLUIR UN USUARIO");
                System.out.println("------------------");

                do {

                    System.out.print("Dame el nombre de usuario para incluirlo en la biblioteca");
                    System.out.print("\nNombre: ");
                    nombreUsuario = entrada.nextLine();
                    System.out.print("Dame el correo electronico del usuario");
                    System.out.print("\nCorreo electronico: ");
                    correoUsuario = entrada.nextLine();

                    try {
                        funcionesDeGestionUsuarios.añadirUsuarios(nombreUsuario, correoUsuario, usuariosBiblioteca);
                        System.out.println("Usuario creado correctamente. Case 3");
                    } catch (NullPointerException e) {
                        System.err.println("\nError al añadir el usuario. " + e.getMessage());
                    } catch (IndexOutOfBoundsException e2) {
                        System.err.println("\nNombre del usuario o correo demasido corto");
                    }

                    System.out.println("\nTeclee 'y' para salir o cualquier tecla para seguir introduciendo Usuarios.\n");

                } while (!entrada.nextLine().toUpperCase().equals(exit));

                break;

            case "4":
                System.out.println("\nMOSTRAR LOS USUARIOS DE LA BIBLIOTECA");
                System.out.println("-------------------------------------");
                try {
                    funcionesDeGestionUsuarios.mostrarUsuarios(usuariosBiblioteca);
                } catch (NoSuchElementException e) {
                    System.out.println("No hay elementos que borrar.");
                }
                break;

            case "5":
                LocalDate fechaLocalDate = LocalDate.now();

                System.out.println("\nPRESTAR UN LIBRO");
                System.out.println("-----------------");

                try {

                    while (!paso) {
                        funcionesDeGestionLibros.mostrarLibros(librosBiblioteca);
                        System.out.println("\nDame el codigo del Libro");
                        //entrada.nextLine();
                        codigoLibro = entrada.nextLine();
                        indiceLibro = funcionesDeGestionLibros.buscarLibro(codigoLibro, librosBiblioteca);

                        if (indiceLibro != -1) {
                            paso = true;

                        } else {
                            System.out.println("\nCodigo incorrecto");
                            System.out.println("¿Quiere continuar con el prestamo de libros?");
                            System.out.println("Teclee y / n\n");
                            pregunta = entrada.nextLine().toUpperCase();

                            if (!pregunta.equals("Y")) {
                                paso = true;
                            }
                        }
                    }

                    paso = pregunta.equals("N");

                    while (!paso) {

                        if (usuariosBiblioteca.isEmpty()) {
                            System.out.println("\nNo existen usuarios para hacer un prestamo. Por favor inserte uno");
                            System.out.println("Saliendo del prestamo");
                            paso = true;

                        } else {
                            funcionesDeGestionUsuarios.mostrarUsuarios(usuariosBiblioteca);
                            System.out.println("\nDame el codigo del Usuario");
                            //entrada.nextLine();
                            codigoUsuario = entrada.nextLine();
                            indiceUsuario = funcionesDeGestionUsuarios.buscarUsuario(codigoUsuario, usuariosBiblioteca);

                            if (indiceUsuario != -1) {
                                paso = true;
                            } else {
                                System.out.println("\nCodigo incorrecto");
                                System.out.println("¿Quiere continuar con el prestamo?");
                                System.out.println("Teclee y / n\n");
                                pregunta = entrada.next().toUpperCase();

                                if (!pregunta.equals("Y")) {
                                    paso = true;
                                }
                            }

                            if (indiceUsuario == -1) {
                                if (!pregunta.equals("N")) {

                                    if (pregunta.equals("Y")) {
                                        entrada.nextLine();
                                        paso = false;
                                    }
                                }
                            } else {
                                funcionesDeGestionPrestamos.añadirPrestamo((Usuarios) usuariosBiblioteca.get(indiceUsuario), (Libros) librosBiblioteca.get(indiceLibro), fechaLocalDate, prestamosActivos, historicoPrestamosUsuarios, historicoPrestamosLibros);
                            }
                        }
                    }

                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha incorrecto. Por favor, ingrese la fecha en el formato especificado.");
                } catch (IllegalStateException e1) {
                    System.out.println(e1.getMessage());
                }

                break;

            case "6":
                boolean paso1 = false;
                boolean paso2 = false;
                System.out.println("\nDEVOLVER UN LIBRO");
                System.out.println("------------------");

                funcionesDeGestionPrestamos.consultarPrestamosActivos(prestamosActivos);
                if (prestamosActivos.isEmpty()) {
                    paso = true;
                }

                while (!paso) {
                    System.out.println("\nDame el codigo del Libro");
                    //entrada.nextLine();
                    codigoLibro = entrada.nextLine();
                    indiceLibro = funcionesDeGestionPrestamos.devolverLibro(codigoLibro, prestamosActivos, paso1, paso2);

                    if (indiceLibro != -1) {
                        paso = true;
                        paso1 = true;

                    } else {
                        System.out.println("\nCodigo incorrecto");
                        System.out.println("¿Quiere continuar con la devolucion del Libro?");
                        System.out.println("Teclee y / n\n");
                        pregunta = entrada.nextLine().toUpperCase();

                        if (!pregunta.equals("Y")) {
                            paso = true;
                        }
                    }
                    if (indiceLibro == -1) {
                        if (!pregunta.equals("N")) {

                            if (pregunta.equals("Y")) {
                                //entrada.nextLine();
                                paso = false;
                            }
                        }
                    } else {
                        paso1 = true;                       
                        funcionesDeGestionPrestamos.devolverLibro(codigoLibro, prestamosActivos, paso1, paso2);
                    }

                }
                break;

            case "7":
                System.out.println("\nMOSTRAR PRESTAMOS ACTIVOS");
                System.out.println("--------------------------");
                funcionesDeGestionPrestamos.consultarPrestamosActivos(prestamosActivos);
                break;

            case "8":
                System.out.println("\nMOSTRAR PRESTAMOS HISTORICO POR USUARIO");
                System.out.println("---------------------------------------");

                
                if (historicoPrestamosUsuarios.isEmpty()) {
                	System.out.println("No existen aun ningún préstamo a usuarios. Anima a tus usuarios a que los realicen.");
                    paso = true;
                }
                

                while (!paso) {

                    if (usuariosBiblioteca.isEmpty()) {
                        System.out.println("\nNo existen usuarios con prestamos realizados.");
                        System.out.println("Saliendo de la solicitud");
                        paso = true;

                    } else {
                        funcionesDeGestionUsuarios.mostrarUsuarios(usuariosBiblioteca);
                        System.out.println("\nDame el codigo del Usuario");
                        //entrada.nextLine();
                        codigoUsuario = entrada.nextLine();
                        indiceUsuario = funcionesDeGestionUsuarios.buscarUsuario(codigoUsuario, usuariosBiblioteca);

                        if (indiceUsuario != -1) {

                            paso = true;
                        } else {
                            System.out.println("\nCodigo incorrecto");
                            System.out.println("¿Quiere continuar con la busqueda?");
                            System.out.println("Teclee y / n\n");
                            pregunta = entrada.next().toUpperCase();

                            if (!pregunta.equals("Y")) {
                                paso = true;
                            }
                        }

                        if (indiceUsuario == -1) {
                            if (!pregunta.equals("N")) {

                                if (pregunta.equals("Y")) {
                                    entrada.nextLine();
                                    paso = false;
                                }
                            }
                        } else {
                            funcionesDeGestionPrestamos.listarPrestamoPorUsuario(codigoUsuario, historicoPrestamosUsuarios);
                        }
                    }
                }
                break;

            case "9":
                System.out.println("\nMOSTRAR PRESTAMOS HISTORICO POR LIBROS");
                System.out.println("---------------------------------------");

                if (historicoPrestamosLibros.isEmpty()) {
                	System.out.println("No existen aun ningún préstamo de libros. Anima a tus usuarios a que los realicen.");
                    paso = true;
                }

                while (!paso) {

                    if (usuariosBiblioteca.isEmpty()) {
                        System.out.println("\nNo existen aún libros prestados. Por favor inserte uno");
                        System.out.println("Saliendo de la solicitud");
                        paso = true;

                    } else {
                        funcionesDeGestionLibros.mostrarLibros(librosBiblioteca);
                        System.out.println("\nDame el codigo del Libro");
                        //entrada.nextLine();
                        codigoLibro = entrada.nextLine();
                        indiceLibro = funcionesDeGestionLibros.buscarLibro(codigoLibro, librosBiblioteca);

                        if (indiceLibro != -1) {

                            paso = true;
                        } else {
                            System.out.println("\nCodigo incorrecto");
                            System.out.println("¿Quiere continuar con la busqueda?");
                            System.out.println("Teclee y / n\n");
                            pregunta = entrada.next().toUpperCase();

                            if (!pregunta.equals("Y")) {
                                paso = true;
                            }
                        }

                        if (indiceLibro == -1) {
                            if (!pregunta.equals("N")) {

                                if (pregunta.equals("Y")) {

                                    entrada.nextLine();

                                    paso = false;
                                }

                            }
                        } else {
                            funcionesDeGestionPrestamos.listarPrestamoPorLibro(codigoLibro, historicoPrestamosLibros);
                        }
                    }
                }
                break;

            default:
                System.out.println("\n\nOpcion no valida\n");
                break;
        }
    }
}
