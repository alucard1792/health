/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Traslado;
import com.modelo.entidades.Usuario;
import java.util.ArrayList;
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
public class AfiliacionFacade extends AbstractFacade<Afiliacion> implements AfiliacionFacadeLocal {

    @PersistenceContext(unitName = "EfisaludCorregidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfiliacionFacade() {
        super(Afiliacion.class);
    }

    @Override
    public Usuario findFetch(Object id) {
        Query q = getEntityManager().createQuery("SELECT t FROM Traslado t JOIN FETCH t.afiliacionIdAfiliacion a JOIN FETCH a.usuarioIdAsignado u WHERE t.idTraslado = :idTraslado", Afiliacion.class);
        q.setParameter("idTraslado", id);
        Usuario usuario = null;
        List<Traslado>listaAfiliados = q.getResultList();
        for(Traslado t: listaAfiliados){
            usuario = t.getAfiliacionIdAfiliacion().getUsuarioIdAsignado();
            System.out.println(usuario.getEmail());
        
        }
        return  usuario;
        
    }
    
}
