package retoUd5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ejercicio2 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		//Mostramos nuestra ruta de trabajo
		String directorioTrabajo = System.getProperty("user.dir");
		System.out.println("Nuestro directorio de trabajo actual es: "+ directorioTrabajo);
		//Ponemos como se va a llamar el directorio a crear y los 2 archivos
		String nuevoDirectorio = "dirEjer2";
		String nombreArchivo1 = "uno.txt";
		String nombreArchivo2 = "dos.txt";
		
		//Establecemos la ruta completa
		String rutaCompleta1 = directorioTrabajo+File.separator+nuevoDirectorio+File.separator+nombreArchivo1;
		String rutaCompleta2 = directorioTrabajo+File.separator+nuevoDirectorio+File.separator+nombreArchivo2;
		
		//Creamos los 3 objetos tipo File que nos harán falta
		File directorio = new File(nuevoDirectorio);
		File uno = new File(rutaCompleta1);
		File dos = new File(rutaCompleta2);
		try {
			//Creamos el directorio y los archivos
			
			
			if (!comprobarExiste(directorio)) {
				directorio.mkdir();
			} else {
				System.out.println("El directorio ya existia");
			}
			
			if (!comprobarExiste(uno)) {
				FileWriter fw1 = new FileWriter(rutaCompleta1);
				fw1.close();
			} else {
				System.out.println("El archivo 1 ya existia");
			}
			
			if (!comprobarExiste(dos)) {
				FileWriter fw2 = new FileWriter(rutaCompleta2);
				fw2.close();
			} else {
				System.out.println("El archivo 2 ya existia");
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal en la creacion de archivos y directorios");
		}
		
		//Ahora ejecutamos el codigo para escribir en el fichero
		if (comprobarExiste(uno)) {
			escribirNombreFichero(uno);
		} else {
			System.out.println("No se pudo escribir en el archivo porque no existe");
		}
		
		
		//Duplicamos los datos al segundop archivo
		if (comprobarExiste(uno) == true && comprobarExiste (dos) == true) {
			duplicarFicheros(uno, dos);
		} else {
			System.out.println("Uno de los 2 ficheros no existe por lo que no se puede copiar");
		}
		
		//Por ultimo mostramos el contenido del segundo archivo:
		if (comprobarExiste(dos)) {
			System.out.println("\n**Estamos leyendo el archivo: **"+dos.getName());
			leerDeFichero(dos);
		} else {
			System.out.println("No se pudo leer el archivo porque no existe");
		}
		
	}
	
	//AQUI TERMINA EL MAIN Y EMPIEZAN LOS METODOS========================
	
	public static void escribirNombreFichero(File fichero) {
		try {
			Scanner sc = new Scanner(System.in);
			FileWriter input = new FileWriter(fichero, true);
			BufferedWriter output = new BufferedWriter(input);
			String linea = null;
			while (!"-".equals(linea)) {
				System.out.println("Introduce un nuevo nombre o usa '-' para terminar el programa");
				linea = sc.nextLine();
				if (!"-".equals(linea)) {
					output.write(linea);
					output.newLine();
				}
			}
			System.out.println("Se ha escrito correctamente");
			output.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal a la hora de escribir en escribirEnFichero");
			e.printStackTrace();
		}	
	}
	
	public static boolean comprobarExiste(File fichero) {
		if (fichero.exists()) {
            return true;
        } else {
            return false;
        }
	}
	
	public static void duplicarFicheros(File archivoOrigen, File archivoDestino) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader (new FileReader(archivoOrigen.getCanonicalPath()));
			//true hace aqui que se mantenga la informacion del archivo de destino
			bw = new BufferedWriter (new FileWriter(archivoDestino.getCanonicalPath(), true));
			// Obtener la fecha y hora actual
	        LocalDateTime fechaHoraActual = LocalDateTime.now();
	        // Formatear la fecha y hora según tus necesidades
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String fechaFormateada = fechaHoraActual.format(formato);


			int c;
			bw.write("Esta es la copia del archivo origen hecha el: "+fechaFormateada);
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
	
	public static void leerDeFichero(File archivo) {
		try {
			FileReader in = new FileReader(archivo.getAbsolutePath());
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
}
