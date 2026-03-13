package org.lapaloma.mapamundi.dao;

import java.util.List;

import org.lapaloma.mapamundi.vo.Continente;

public interface IContinenteDAO {
	public Continente obtenerContinentePorClave(String codigo) ;
	public Continente actualizarContinente(Continente coche) ;
	public Continente crearContinente(Continente coche);
	public void borrarContinente(Continente coche);
	public List<Continente> obtenerListaContinentes(); 
	public List<Continente> obtenerContinentePorNombre(String nombre);
}
