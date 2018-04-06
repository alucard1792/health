/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Traslado;
import com.modelo.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface TrasladoFacadeLocal {

    void create(Traslado traslado);

    void edit(Traslado traslado);

    void remove(Traslado traslado);

    Traslado find(Object id);

    List<Traslado> findAll();

    List<Traslado> findRange(int[] range);

    int count();
    
    List<Traslado>listaTransladoPorRol(Afiliacion a);

    List<Traslado>listaTransladoPorRol(Usuario u);
    
}
