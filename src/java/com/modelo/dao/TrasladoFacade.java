/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Municipio;
import com.modelo.entidades.Traslado;
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
public class TrasladoFacade extends AbstractFacade<Traslado> implements TrasladoFacadeLocal {

    @PersistenceContext(unitName = "EfisaludCorregidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrasladoFacade() {
        super(Traslado.class);
    }

    @Override
    public List<Traslado> listaTransladoPorRol(Afiliacion a) {
        Query q = getEntityManager().createQuery("SELECT t FROM Traslado t WHERE t.afiliacionIdAfiliacion = :afiliacionIdAfiliacion", Afiliacion.class);
        q.setParameter("afiliacionIdAfiliacion", a);
        return q.getResultList();
        
    }

    @Override
    public List<Traslado> listaTransladoPorRol(Usuario u) {
        Query q = getEntityManager().createQuery("SELECT t FROM Traslado t WHERE t.usuarioIdAnalista = :usuarioIdAnalista", Afiliacion.class);
        q.setParameter("usuarioIdAnalista", u);
        return q.getResultList();
    }

    @Override
    public List<Traslado> listaUltimosTresMunicipios() {
        Query q = getEntityManager().createQuery("SELECT t FROM Traslado t GROUP BY  t.municipioIdMunicipio ", Traslado.class);
        return q.setMaxResults(3).getResultList();

    }

    @Override
    public long countMunicipios(Municipio municipio) {
        System.out.println("municipio facade: " + municipio.getNombre());
        Query q = getEntityManager().createQuery("SELECT COUNT(t.idTraslado) FROM Traslado t WHERE t.municipioIdMunicipio = :municipioIdMunicipio", Integer.class);
        q.setParameter("municipioIdMunicipio", municipio);
        long temp = (long) q.getSingleResult();
        return temp;
        
    }
    
}