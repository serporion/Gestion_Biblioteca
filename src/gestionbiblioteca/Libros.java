/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblioteca;

import java.util.ArrayList;

/**
 * Clase Libros con la que hacer instancias de cada libro que tengamos en la
 * biblioteca con los atributos correspondientes. 
 * 
 * Implementa ServiciosGestiondeLibros con los servicios obligatorios que debe de
 * tener esta clase.
 *
 * @author Oscar Delgado Huertas.
 */
public class Libros implements ServiciosGestiondeLibros{

    //Atributos privados variables de objeto
    private String id;
    private String titulo;
    private String autor;
    

    /**
     * Constructor que genera un libro con los atributos indicados.
     *
     * @param titulo String con el título del libro
     * @param autor String con el autor del autor.
     * @throws NullPointerException Excepcion en el caso de entrada de nulos. 
     * Llegado el caso de no completarse la creación del id con el metodo salta la
     * excepcion al ser nulo el propio Id.
     * @throws IndexOutOfBoundsException Excepcion si no puede usar el metodo substring
     * de forma correcta usado en el metodo creadId().
     * 
     */
    public Libros(String titulo, String autor) throws NullPointerException, IndexOutOfBoundsException {

        if (crearId(titulo, autor) == null) {
            throw new NullPointerException("Teclee alguno de los parametros con más de 4 caracteres");
        }

        this.titulo = titulo;
        this.autor = autor;
        this.id = crearId(titulo, autor);
    }
    
    /**
    * Metodo por defecto para usarlo en la clase Funcionalidades para darle la
    * posibilidad de instanciar la clase y tener acceso a sus métodos.
    */
    public Libros(){
        
    }

    /**
     * Método que devuelve el id del Libros.
     *
     * @return String con dicho valor.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Método que devuelve el titulo del Libro.
     *
     * @return String con dicho valor.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Método que devuelve el autor del Libro.
     *
     * @return String con dicho valor.
     */
    public String getAutor() {
        return this.autor;
    }

 
    /**
     * Metodo que crea un Id único para un Libro.
     *
     * @param titulo String con el titulo del usuario.
     * @param autor String con el autor del usuario.
     * @throws IndexOutOfBoundsException Excepcion si no puede usar el metodo substring
     * de forma correcta.
     *
     * @return String con el codigo de usuario formado. En caso de tener menos de 
     * 4 caracteres ambos parámetros la devolución será un null.
     */
    private String crearId(String titulo, String autor) throws IndexOutOfBoundsException{

        String cadena;
        String tituloParte;
        String autorParte;

        int min = 22222, max = 77777;
        int aleatorio = (int) (Math.random() * (max - min + 1)) + min;

        if (titulo.length() >= 4 || autor.length() >= 4) {
            
            //tituloParte = titulo.substring(0, Math.min(titulo.length(), 3)).replaceAll("\\s+", "");
            //Quita los espacios en el caso de artitulos.
            tituloParte = titulo.substring(0, 1).replaceAll("\\s+", "");
            
            //autorParte = autor.substring(0, Math.min(autor.length(), 3)).replaceAll("\\s+", "");
            autorParte = autor.substring(0, 3).replaceAll("\\s+", "");
            cadena = tituloParte + autorParte + String.valueOf(aleatorio);

        } else {
            System.err.println("\nEl titulo del libro y el autor son demasiado cortos.");
            return null;
        }

        return cadena;
    }
    
    
     /**
     * Método que permite añadir libros a la biblioteca. Permite la adicción de ejemplares de
     * forma automatica de los libros llamando a crearId() y generando un codigo diferente para
     * todos los libros.
     * 
     * @throws NullPointerException Excepcion en el caso de entrada de nulos.
     * Llegado el caso de no completarse la creación del id con el metodo salta
     * la excepcion.
     * 
     * @param titulo String con el titulo del Libro.
     * @param autor String con el autor del Libro.
     * @param librosBiblioteca ArrayList coleccion con los libros de la biblioteca.
     */
    @Override
    public void añadirLibro(String titulo, String autor, ArrayList<Libros> librosBiblioteca) throws NullPointerException  {

        Libros lib = new Libros(titulo, autor);
        librosBiblioteca.add(lib);

    }
    
    /**
     * Metodo que muestra los libros de los que dispone la biblioteca.
     * 
     * @param librosBiblioteca ArrayList coleccion con los datos de los libros
     * de la biblioteca.
     */
    @Override
    public void mostrarLibros(ArrayList<Libros> librosBiblioteca) {

        if (librosBiblioteca.isEmpty()) {
            System.out.println("No hay registro de libros a mostrar");
        } else {

            System.out.println("\nLos Libros en la biblioteca son: ");
            System.out.println("---------------------------------\n");

            for (Libros n : librosBiblioteca) {
                System.out.println(n);
            }

        }
    }

     /**
     * Metodo que permite buscar un usuario en la coleccion de datos de nuestra biblioteca.
     * 
     * @param codigoLibro String con el codigo de libro que queremos buscar.
     * @param librosBiblioteca  ArrayList con los daatos de los libros de la Biblioteca donde buscar.
     * 
     * @return int donde se devuelve el indice del ArrayList donde se encuentra el libro para su posterior
     * uso de sus datos. Devuelve -1 si no encuentra el libro. 
     */
    @Override
    public int buscarLibro(String codigoLibro, ArrayList<Libros> librosBiblioteca) {

        int indice = -1;

        for (int i = 0; i < librosBiblioteca.size(); i++) {
            Libros libCompara = librosBiblioteca.get(i);
            if (libCompara.getId().equals(codigoLibro)) {
                indice = i;
                break;
            }
        }

        return indice;
    }
    

    /**
     * Método que sobreescribe toString() de la clase para mostrar una instancia
     * de Libro de la forma que hemos considerado.
     *
     * @return String del objeto con todos sus atributos.
     */
    @Override
    public String toString() {

        return String.format("Codigo del Libro: %-18s  Titulo del libro: %-35s   Autor del "
                + "libro: %-37s ", this.getId(), this.getTitulo(), this.getAutor());
    }

}
