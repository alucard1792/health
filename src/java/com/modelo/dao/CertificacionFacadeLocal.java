/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Certificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface CertificacionFacadeLocal {

    void create(Certificacion certificacion);

    void edit(Certificacion certificacion);

    void remove(Certificacion certificacion);

    Certificacion find(Object id);

    List<Certificacion> findAll();

    List<Certificacion> findRange(int[] range);

    int count();
    
}
