/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.TipoAfiliacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface TipoAfiliacionFacadeLocal {

    void create(TipoAfiliacion tipoAfiliacion);

    void edit(TipoAfiliacion tipoAfiliacion);

    void remove(TipoAfiliacion tipoAfiliacion);

    TipoAfiliacion find(Object id);

    List<TipoAfiliacion> findAll();

    List<TipoAfiliacion> findRange(int[] range);

    int count();
    
}
