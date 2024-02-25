package retoUd5;

import java.io.Serializable;

public class Persona implements Serializable{
	private String nombre;
	private String tipo;
	private int ataque;
	private int defensa;
	
	public Persona (String nombre, String tipo, int ataque, int defensa) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.ataque = ataque;
		this.defensa = defensa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	public String mostrarPersona() {
        return "Nombre: " + nombre + ", tipo: " + tipo+", ataque: "+ataque+", defensa: "+defensa;
    }
	
}
