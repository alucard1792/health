/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Reclamacion;
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
public class ReclamacionFacade extends AbstractFacade<Reclamacion> implements ReclamacionFacadeLocal {

    @PersistenceContext(unitName = "EfisaludCorregidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReclamacionFacade() {
        super(Reclamacion.class);
    }

    @Override
    public List<Reclamacion> listaReclamacionPorRol(Afiliacion afiliacion) {
        Query q = getEntityManager().createQuery("SELECT r FROM Reclamacion r WHERE r.afiliacionIdAfiliado = :afiliacionIdAfiliado", Reclamacion.class);
        q.setParameter("afiliacionIdAfiliado", afiliacion);
        return q.getResultList();

    }

}
