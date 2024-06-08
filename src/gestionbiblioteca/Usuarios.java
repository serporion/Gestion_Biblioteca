/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.util.ArrayList;

/**
 * Clase Usuarios con la que hacer instancias de los usuarios de nuestra
 * biblioteca.
 * 
 * Implementa ServiciosGestiondeUsuarios con los servicios obligatorios que debe de
 * tener esta clase.
 *
 * @author Oscar Delgado Huertas.
 */
public class Usuarios implements ServiciosGestiondeUsuarios {

    //Atributos privados variables de objeto
    private String id;
    private String nombre;
    private String correo;

    /**
     * Constructor que genera un usuario con los atributos indicados.
     *
     * @param nombre String con el nombre del usuario.
     * @param correo String con el correo del usuario.
     * @throws NullPointerException Excepcion en el caso de entrada de nulos.
     * Llegado el caso de no completarse la creación del id con el metodo salta
     * la excepcion.
     * @throws IndexOutOfBoundsException Excepcion cuando el substring no pueda recortar
     * o el nombre o el correo según los parametros marcados.
     */
    public Usuarios(String nombre, String correo) throws NullPointerException, IndexOutOfBoundsException {

        if ((crearId(nombre, correo)) == null) {
            throw new NullPointerException("Teclee alguno de los parametros con más de 4 caracteres");
        }

        this.nombre = nombre;
        this.correo = correo;
        this.id = crearId(nombre, correo);

    }

    /**
     * Metodo por defecto para usarlo en la clase Funcionalidades para darle la
     * posibilidad de instanciar la clase y tener acceso a sus métodos.
     *
     */
    public Usuarios() {
    }

    /**
     * Método que devuelve el id del usuario.
     *
     * @return String con dicho valor.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Método que devuelve el nombre del Usuario.
     *
     * @return String con dicho valor.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método que devuelve el correo del Usuario.
     *
     * @return String con dicho valor.
     */
    public String getCorreo() {
        return this.correo;
    }

    /**
     * Metodo que crea un Id único para un Usuario con la adiccion de un
     * aleatorio numerico entre 22222 y 77777, al substring de nombre más
     * substring de correo.
     *
     * @param nombre String con el nombre del usuario.
     * @param correo String con el correo del usuario.
     * @throws IndexOutOfBoundsException Excepcion si no consigue crear el Id.
     * El nombre o el correo deben tener más de 3 caracteres.
     *
     * @return String con el codigo de usuario formado con los cuatro caracteres
     * primeros del nombre, los cinco caracteres del correo electronio y el
     * aleatorio.
     */
    private String crearId(String nombre, String correo) throws IndexOutOfBoundsException{

        String cadena;
        String nombreParte;
        String correoParte;

        //Valores elegidos sobre los que buscar el aleatorio.
        int min = 22222, max = 77777;
        //Aleatorio a usar en el Id.
        int aleatorio = (int) (Math.random() * (max - min + 1)) + min;

        //if (nombre.length() >= 4 || correo.length() >=4 ) {
        if (nombre.length() >= 4 ) {            

            nombreParte = nombre.substring(0, 3).replaceAll("\\s+", "");
            correoParte = correo.substring(4, 9);

            cadena = nombreParte + correoParte + String.valueOf(aleatorio);
        } else {
            System.err.println("\nEl nombre del usuario es demasiado corto.");
            return null;
        }

        return cadena;
    }

    /**
     * Método que permite añadir Usuarios a la biblioteca.
     *
     * @param nombre String con el nombre del Usuario.
     * @param correo String con el correo electroncio del Usuario.
     * @param usuariosBiblioteca ArrayList coleccion con los usuarios de la biblioteca.
     * 
     * @throws NullPointerException Excepcion en el caso de entrada de nulos.
     * Llegado el caso de no completarse la creación del id con el metodo salta
     * la excepcion.
     * 
     */
    @Override
    public void añadirUsuarios(String nombre, String correo, ArrayList<Usuarios> usuariosBiblioteca) throws NullPointerException{

        boolean existe = false;

            Usuarios usuCompara = new Usuarios(nombre, correo);

            if (usuariosBiblioteca.contains(usuCompara)) {
                System.out.println("El usuario ya está registrado con esa cuenta de correo electronico");
                existe = true;
            }

            if (!existe) {
                usuariosBiblioteca.add(usuCompara);
            }
       
    }

    
    /**
     * Método que permite mostrar un Usuario de la biblioteca.
     * 
     * @param usuariosBiblioteca ArrayList con los daatos de los usuarios de la Biblioteca.
     */
    @Override
    public void mostrarUsuarios(ArrayList<Usuarios> usuariosBiblioteca) {

        if (usuariosBiblioteca.isEmpty()) {
            System.out.println("No hay registro de usuarios a mostrar");
        } else {

            System.out.println("\nLos Usuarios de la biblioteca son: ");
            System.out.println("---------------------------------\n");

            for (Usuarios n : usuariosBiblioteca) {
                System.out.println(n);
            }

        }
    }
    
    /**
     * Metodo que permite buscar un usuario en la coleccion de datos de nuestra biblioteca.
     * 
     * @param codigoUsuario String con el codigo de usuario de la persona a buscar.
     * @param usuariosBiblioteca  ArrayList con los daatos de los usuarios de la Biblioteca.
     * 
     * @return int donde se devuleve el indice del ArrayList donde se encuentra el usuario para su posterior4
     * uso de sus datos. Devuelve -1 si no encuentra el usuario. 
     */
    @Override
    public int buscarUsuario(String codigoUsuario, ArrayList<Usuarios> usuariosBiblioteca) {

        int indice = -1;

        for (int i = 0; i < usuariosBiblioteca.size(); i++) {
            Usuarios usuCompara = usuariosBiblioteca.get(i);
            if (usuCompara.getId().equals(codigoUsuario)) {
                indice = i;
                break;
            }
        }

        return indice;
    }

    /**
     * Método que sobreescribe toString() de la clase para mostrar una instancia
     * de Usuario.
     *
     * @return String del objeto, según nuestras preferencias.
     */
    @Override
    public String toString() {

        return String.format("Codigo de Usuario: %-18s Nombre del Usuario: %-35s Correo del "
                + "Usuario: %s", this.getId(), this.getNombre(), this.getCorreo());
    }

    /**
     * Método que sobreescribe equals() para poder comparar la existencia o no
     * de un mismo correo electronico.
     *
     * @param o correo electronico de un usuario Usuario que quiere darse de alta.
     * 
     * @return boolean con el resultado true si es igual, o false si no es igual. Con ello aseguramos
     * la existencia del alta de un correo electronico, el cual no puede repetirse en la colección.
     */
    @Override
    public boolean equals(Object o) {

        boolean existe = false;

        Usuarios usu = (Usuarios) o;
        if (correo.equals(usu.correo)) {
            existe = true;
        }

        return existe;
    }

}
