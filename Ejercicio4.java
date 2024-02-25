package retoUd5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio4 {
	public static void main(String[] args) {
		String archivo = "persona.dat";
		File fichero= new File (archivo);
		final Persona[] persona = {new Persona("Arsene", "Maldicion", 100, 50), new Persona("Yoshitsune", "Fisico", 500, 10), new Persona("Yamata no Orochi", "Hielo", 210, 250), new Persona("Satanael", "Todopoderoso", 370, 370)};
		
		escribirObjeto(persona, fichero);
		
		if (comprobarExiste(fichero)) {
			leerObjeto(fichero);
		} else {
			System.out.println("El archivo no existe, por favor crealo y fijate bien.");
		}
		
		
	}
	
	
	//ESCRIBIR
	public static void escribirObjeto(Persona[] persona, File fichero) {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichero.getAbsolutePath())));
			for (int i = 0; i < persona.length; i++) {
                output.writeObject(persona[i]);

            }
		} catch (Exception e) {
			System.out.println(e);
		} finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el flujo de salida: " + e.getMessage());
            }
        }
	}
	
	
	//LEER
	public static void leerObjeto(File fichero) {
		//Inicializamos la escritura y el objeto persona
		ObjectInputStream input = null;
		//Creamos un array de personas
		List<Persona> personas = new ArrayList<>();
		try {
			//Instanciamos un objeto ObjectInputStream para leer el fichero
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichero.getAbsolutePath())));
			while (true) {
                try {
                	//Leemos cada persona del archivo y la añadimos a una variable llamada personas que es el array de antes
                    Persona persona = (Persona) input.readObject();
                    personas.add(persona);
                } catch (EOFException e) {
                    // Se alcanza el final del archivo
                    break;
                }
            }
			
			//Ahora por cada persona creamos una persona que se mostrará gracias al toString() creado en la clase Persona.java
			for (Persona persona : personas) {
	            System.out.println(persona.mostrarPersona());
	        }
		} catch (Exception e) {
			System.out.println("Error al leer el archivo "+e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el flujo de entrada: " + e.getMessage());
            }
        }
		
	}
	
	//Comprobar si existe
		public static boolean comprobarExiste(File fichero) {
			if (fichero.exists()) {
	            return true;
	        } else {
	            return false;
	        }
		}
		
}

