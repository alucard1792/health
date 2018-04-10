/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Reclamacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface ReclamacionFacadeLocal {

    void create(Reclamacion reclamacion);

    void edit(Reclamacion reclamacion);

    void remove(Reclamacion reclamacion);

    Reclamacion find(Object id);

    List<Reclamacion> findAll();

    List<Reclamacion> findRange(int[] range);

    int count();
    
    List<Reclamacion>listaReclamacionPorRol(Afiliacion afiliacion);
    
    List<Reclamacion> listaReclamacionPorTipo(String tipo);
    
}
