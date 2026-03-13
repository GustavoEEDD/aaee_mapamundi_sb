/**
 * 
 */
package org.lapaloma.mapamundi.service;

import java.util.List;

import org.lapaloma.mapamundi.dao.IContinenteDAO;
import org.lapaloma.mapamundi.dao.impl.ContinenteDaoJDBC;
import org.lapaloma.mapamundi.vo.Continente;

/**
 * Isidoro Nevares Martín - Virgen de la Paloma Fecha creación: 13 mar 2026
 */
public class ContinenteService {
    IContinenteDAO continenteDAO = new ContinenteDaoJDBC();

    public Continente obtenerContinentePorClave(String codigo) {
        Continente continente = null;

        continente = continenteDAO.obtenerContinentePorClave(codigo);

        return continente;
    }

    public List<Continente> obtenerListaContinentes() {
        List<Continente> listaContinentes = null;

        listaContinentes = continenteDAO.obtenerListaContinentes();

        return listaContinentes;
    }

    public List<Continente> obtenerContinentePorNombre(String nombre) {
        List<Continente> listaContinentes = null;

        listaContinentes = continenteDAO.obtenerContinentePorNombre(nombre);

        return listaContinentes;
    }

}
