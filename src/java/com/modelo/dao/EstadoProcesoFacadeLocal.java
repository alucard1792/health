/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.EstadoProceso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface EstadoProcesoFacadeLocal {

    void create(EstadoProceso estadoProceso);

    void edit(EstadoProceso estadoProceso);

    void remove(EstadoProceso estadoProceso);

    EstadoProceso find(Object id);

    List<EstadoProceso> findAll();

    List<EstadoProceso> findRange(int[] range);

    int count();
    
}
