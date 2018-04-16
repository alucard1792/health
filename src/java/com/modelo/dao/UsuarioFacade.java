/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Rol;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "EfisaludCorregidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(int documento, String clave) {
        List<Usuario> listaUsuario = new ArrayList<>();
        Usuario usuario = null;
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.documento = :documento AND u.clave = :clave AND u.estado = 1", Usuario.class);
        q.setParameter("documento", documento);
        q.setParameter("clave", clave);
        listaUsuario = q.getResultList();
        for (Usuario u : listaUsuario) {
            usuario = u;

        }
        return usuario;

    }

    @Override
    public List<Usuario> listarUsuariosNoAfiliados() {
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.rolIdRol = :rolIdRol1 OR u.rolIdRol = :rolIdRol2", Usuario.class);
        q.setParameter("rolIdRol1", new Rol(5));
        q.setParameter("rolIdRol2", new Rol(6));
        return q.getResultList();

    }

    @Override
    public Afiliacion findFetchReporte(Usuario u) {
        Afiliacion afiliacion = null;
        List<Afiliacion> listaAfiliacion = new ArrayList<>();
        Query q = getEntityManager().createQuery("SELECT a FROM Afiliacion a JOIN FETCH a.usuarioIdAsignado u JOIN FETCH a.municipioIdMunicipio m WHERE a.usuarioIdAsignado = :usuarioIdAsignado", Usuario.class);
        q.setParameter("usuarioIdAsignado", u);
        listaAfiliacion = q.getResultList();
        for (Afiliacion a : listaAfiliacion) {
            afiliacion = a;

        }
        return afiliacion;

    }

    @Override
    public List<Afiliacion> listarUsuariosRolAfiliado() {
        Query q = getEntityManager().createQuery("SELECT a FROM Afiliacion a JOIN FETCH a.usuarioIdAsignado u JOIN FETCH u.rolIdRol r WHERE r.idRol = 7 OR r.idRol = 8", Usuario.class);
        return (List<Afiliacion>) q.getResultList();
    }

    @Override
    public List<Afiliacion> listarUsuariosRegistradoAnalistaEnSesion(Usuario usuario) {
        System.out.println("entro session bean");
        Query q = getEntityManager().createQuery("SELECT a FROM Afiliacion a JOIN FETCH a.usuarioIdAsignado u JOIN FETCH a.usuarioIdAsignado.rolIdRol r WHERE (a.usuarioIdAnalista = :usuarioIdAnalista AND r.idRol = 7) OR (a.usuarioIdAnalista = :usuarioIdAnalista AND r.idRol = 8)", Usuario.class);
        q.setParameter("usuarioIdAnalista", usuario);
        /*List<Usuario> listaUsuarios = new ArrayList<>();
        for (Afiliacion a : new ArrayList<Afiliacion>(q.getResultList())) {
            listaUsuarios.add(a.getUsuarioIdAsignado());

        }*/
        return (List<Afiliacion>) q.getResultList();

    }
    @Override
    public Boolean loadUsuarios(String pathFile) {
        try {
            System.out.println("path: " + pathFile);
            String sql = "LOAD DATA LOCAL INFILE '" + pathFile + "' "
                    + "INTO TABLE municipio "
                    + "FIELDS TERMINATED BY ';' "
                    + "LINES TERMINATED BY '\n' "
                    + "(Nombre, Codigo_Municipio);";
            Query nq = getEntityManager().createNativeQuery(sql);
            nq.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
