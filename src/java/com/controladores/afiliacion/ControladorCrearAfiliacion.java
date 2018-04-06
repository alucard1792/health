/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.afiliacion;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.MunicipioFacadeLocal;
import com.modelo.dao.TipoAfiliacionFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Municipio;
import com.modelo.entidades.Rol;
import com.modelo.entidades.TipoAfiliacion;
import com.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearAfiliacion")
@ViewScoped
public class ControladorCrearAfiliacion implements Serializable{

    @EJB
    private AfiliacionFacadeLocal AfiliacionFacadeLocal;
    @EJB
    private TipoAfiliacionFacadeLocal tipoAfiliacionFacadeLocal;
    @EJB
    private MunicipioFacadeLocal municipioFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<TipoAfiliacion> listaTipoAfiliacion;
    private List<Municipio> listaMunicipios;
    private Afiliacion afiliacion;
    private Usuario usuario;
    public ControladorCrearAfiliacion() {
    }
    
    @PostConstruct
    public void init(){
        listaMunicipios = municipioFacadeLocal.findAll();
        listaTipoAfiliacion = tipoAfiliacionFacadeLocal.findAll();
        afiliacion = new Afiliacion();
        usuario = new Usuario();
    
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<TipoAfiliacion> getListaTipoAfiliacion() {
        return listaTipoAfiliacion;
    }

    public List<Municipio> getListaMunicipios() {
        return listaMunicipios;
    }

    public Afiliacion getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(Afiliacion afiliacion) {
        this.afiliacion = afiliacion;
    }
    
    public String crear(){
        usuario.setRolIdRol(new Rol(7));
        usuarioFacadeLocal.create(usuario);
        afiliacion.setUsuarioIdAsignado(usuario);
        afiliacion.setUsuarioIdAnalista(controladorLogin.getUsuarioSesion());
        afiliacion.setFecha(new Date());
        AfiliacionFacadeLocal.create(afiliacion);
        return "/app/afiliado/listarAfiliados.xhtml?faces?redirect=true";
    
    }

}