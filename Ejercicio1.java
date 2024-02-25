package retoUd5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

public class Ejercicio1 {
	public static void main(String[] args) throws IOException {
		//Creamos el scanner, la ruta en la que queremos trabajar y los 2 archivos que usaremos como null
		Scanner sc = new Scanner(System.in);
		String rutaTrabajo = "src"+File.separator+"retoUd5"+File.separator+"Archivos"+File.separator;
		String archivo1 = null;
		String archivo2 = null;

		//Declaramos las condiciones a cumplir para que el archivo deba ser más largo de 3 caracteres
		boolean a = true;
		boolean b = true;
		//Primer archivo
		System.out.println("Introduce el nombre del PRIMER archivo que quieres crear");
		while (a) {
			archivo1 = sc.nextLine();
			if (archivo1.length()>3) {
				a = false;
				System.out.println("El nombre del fichero: "+archivo1+" es valido");
				//FileWriter input1= new FileWriter("src/retoUd5/Archivos/"+archivo1);
			} else {
				System.out.println("Introduce un nombre que tenga más de 3 caracteres");
			}
		}
		//Segundo archivo
		System.out.println("Introduce el nombre del SEGUNDO archivo que quieres crear");
		while (b) {
			archivo2 = sc.nextLine();
			if (archivo2.length()>3) {
				b = false;
				System.out.println("El nombre del fichero: "+archivo2+" es valido");
				//FileWriter input2= new FileWriter("src/retoUd5/Archivos/"+archivo2);
			} else {
				System.out.println("Introduce un nombre que tenga más de 3 caracteres");
			}
		}

		
		//Obtenemos el directorio actual completo para el archivo 1
		String rutaCompleta1 = System.getProperty("user.dir")+ File.separator+rutaTrabajo+archivo1;
		File fichero1 = new File(rutaCompleta1);
		//Usamos comprobar existe para ver si nuestro archivo 1 existe, en caso de que devuelva false se crea
		if (!comprobarExiste(fichero1)) {
			try {
				FileWriter input1 = new FileWriter(rutaCompleta1);
				System.out.println("Se ha creado el fichero 1");
				input1.close();
			} catch (Exception e) {
				System.out.println("Ocurrió un error");
			}
			
		} else {
			System.out.println("El fichero "+archivo1+" existe fiera");
		}
		
		//Obtenemos el directorio actual completo para el archivo 2
		String rutaCompleta2 = System.getProperty("user.dir")+ File.separator+rutaTrabajo+archivo2;
		File fichero2 = new File(rutaCompleta2);
		//Usamos comprobar existe para ver si nuestro archivo 2 existe, en caso de que devuelva false se crea
		if (!comprobarExiste(fichero2)) {
			try {
				FileWriter input2 = new FileWriter(rutaCompleta2);
				System.out.println("Se ha creado el fichero 2");
				input2.close();
			} catch (Exception e) {
				System.out.println("Ocurrió un error");
			}
			
		} else {
			System.out.println("El fichero "+archivo2+" existe fiera");
		}
		
		//A esta altura vamos a crear 2 File que nos harán falta más adelante

		
		//Ahora vamos a escribir en el archivo 1
		if (comprobarExiste(fichero2)) {
			escribirEnFichero(rutaCompleta1);
		}
		//Ahora vamos a escribir en el archivo 2
		if (comprobarExiste(fichero2)) {
			escribirEnFichero(rutaCompleta2);
		}
		
		//Leemos los archivos 1 y 2
		System.out.println("\n**Lectura del archivo 1: **");
		leerDeFichero(rutaCompleta1);
		System.out.println("\n**Lectura del archivo 2: **");
		leerDeFichero(rutaCompleta2);
		
		//Ahora vamos a mostrar todos los datos del fichero 1, en el ejercicio no pone que se pide el 2 pero sería basicamente lo mismo cambiando a rutaCompleta2
		if (comprobarExiste(fichero1)) {
			try {
				System.out.println("\n**Estos son los datos del fichero 1**");
				System.out.println("El nombre del fichero es: "+fichero1.getName());
				System.out.println("La ruta absoluta es: "+fichero1.getAbsolutePath());
				System.out.println("La ruta relativa es: "+fichero1.getCanonicalPath());
				System.out.println("El archivo pesa: "+fichero1.length()+" bytes");
				if (fichero1.isFile()) {
					System.out.println("La ruta es un archivo");
				} else if (fichero1.isDirectory()) {
					System.out.println("La ruta es un directorio");
				} else {
					System.out.println("Algo ha ido mal en la comprobacion del archivo");
				}
				//Permisos lectura
				String lectura1 = (fichero1.canRead())  ? "El archivo tiene permisos de lectura" : "El archivo no tiene permisos de lectura";
				System.out.println(lectura1);
				//Permisos escritura
				String escritura1 = (fichero1.canWrite())  ? "El archivo tiene permisos de escritura" : "El archivo no tiene permisos de escritura";
				System.out.println(escritura1);
				//Permisos ejecucion
				String ejecucion1 = (fichero1.canExecute())  ? "El archivo tiene permisos de ejecucion" : "El archivo no tiene permisos de ejecucion";
				System.out.println(ejecucion1);
				//Está oculto
				String oculto1 = (fichero1.isHidden())  ? "El archivo está oculto" : "El archivo no está oculto";
				System.out.println(oculto1);
			} catch (Exception e) {
				System.out.println("Ocurrió un error de E/S al mostrar datos del archivo");
			}
		} else {
			System.out.println("El archivo "+fichero1.getName()+" no existe");
		}
		
		//Ahora vamos a copiar los datos de un archivo a otro
		if (comprobarExiste(fichero1) && comprobarExiste(fichero2)) {
			duplicarFicheros (fichero1, fichero2);
		}
		
		//Borramos el fichero, si no existe ya está controlado dentro del metodo, ha sido una pequeña mejora que he visto al copiar tanto codigo
		borrarFichero (fichero1);
		
		//Leemos de nuevo el archivo 2 para ver que todo se ha copiado bien
		System.out.println("\n**Lectura del archivo 2 de nuevo ahora con el archivo 1 copiado: **");
		leerDeFichero(rutaCompleta2);
		
		//Por ultimo vamos a crear el directorio que nos pide el ejercicio dirEjer1
		File directorio = new File (rutaTrabajo+"dirEjer1");
		if (!comprobarExiste(directorio)) {
			directorio.mkdir();
			System.out.println("Se ha creado el directorio");
		} else {
			System.out.println("El directorio ya existe");
		}
		sc.close();
		
	}
	
	//Metodo para leer un fichero
	public static void leerDeFichero(String archivo) {
		try {
			FileReader in = new FileReader(archivo);
			BufferedReader out = new BufferedReader(in);
			String linea;
			while ((linea=out.readLine())!= null) {
				System.out.println(linea);
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no existe: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Ocurrió un error de E/S " + e.getMessage());
		}
	}
	
	//Metodo para escribir en un fichero
	public static void escribirEnFichero(String archivo) {
		try {
			FileWriter input = new FileWriter(archivo, true);
			BufferedWriter output = new BufferedWriter(input);
			for (int i = 0; i <= 10; i++) {
				String linea = Integer.toString(i);
				output.write(linea);
				output.newLine();
			}
			System.out.println("Se ha escrito correctamente");
			output.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal a la hora de escribir en escribirEnFichero");
			e.printStackTrace();
		}	
	}
	
	//Metodo para borrar un fichero
	public static void borrarFichero(File archivo) {
        String rutaArchivo = archivo.getAbsolutePath();
        try {
            Path path = Paths.get(rutaArchivo);
            Files.delete(path);
            System.out.println("Archivo eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo eliminar el archivo: " + e.getMessage());
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
	
	
	//Duplicar ficheros
	public static void duplicarFicheros(File archivoOrigen, File archivoDestino) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader (new FileReader(archivoOrigen.getCanonicalPath()));
			//true hace aqui que se mantenga la informacion del archivo de destino
			bw = new BufferedWriter (new FileWriter(archivoDestino.getCanonicalPath(), true));
			int c;
			bw.newLine();
			bw.write("Esta es la copia del archivo origen: ");
			bw.newLine();
			while ((c = br.read()) != -1) {
				bw.write(c);
			}
			System.out.println("El fichero de origen se ha copiado en el de destino");
			br.close();
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
}
