package retoUd5;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Donde vamos a trabajar
		String directorioTrabajo = System.getProperty("user.dir");
		//Ponemos como se va a llamar el archivo a crear
		String nombreArchivo1 = "tres.dat";
		//Establecemos la ruta completa
		String rutaCompleta = directorioTrabajo+File.separator+nombreArchivo1;
		//Creamos el objeto con el que trabajaremos
		//File uno = new File(rutaCompleta1);
		
		escribirBinario(rutaCompleta, sc);
		leerBinario(rutaCompleta);
	}
	
	
	//Aqui se acaba el main y empiezan los metodos
	
	public static void escribirBinario(String fichero, Scanner sc) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichero))) {
            int numero;
            do {
                System.out.println("Introduce un numero (ingresa un número negativo para terminar):");
                numero = sc.nextInt();
                if (numero >= 0) {
                    out.writeObject(numero);
                }
            } while (numero >= 0);
            System.out.println("Se ha escrito correctamente en el archivo.");
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
	}
	
	public static void leerBinario(String fichero) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichero))) {
	        System.out.println("Contenido del archivo:");
	        Object obj;
	        while ((obj = in.readObject()) != null) {
	            if (obj instanceof Integer) {
	            	System.out.print((Integer) obj);
	            }
	        }
	        System.out.println();
	    } catch (EOFException e) {
	        // Se alcanzó el final del archivo, no es un error.
	        System.out.println("\nFin del archivo alcanzado.");
	    } catch (Exception e) {
	        System.out.println("Error al leer del archivo: " + e.getMessage());
	    }
	}
}
