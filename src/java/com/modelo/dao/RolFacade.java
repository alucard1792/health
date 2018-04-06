/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Rol;
import com.modelo.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author David
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "EfisaludCorregidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public List<Rol> listarAdminAnalista() {
        Query q = getEntityManager().createQuery("SELECT r FROM Rol r WHERE r.idRol = :rolIdRol1 OR r.idRol = :rolIdRol2", Rol.class);
        q.setParameter("rolIdRol1", 5);
        q.setParameter("rolIdRol2", 6);
        return q.getResultList();

    }
    
}
