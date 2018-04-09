/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.afiliacion;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Rol;
import com.modelo.entidades.Usuario;
import java.io.Serializable;
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
@Named(value = "controladorListarAfiliacion")
@ViewScoped
public class ControladorListarAfiliacion implements Serializable {

    @EJB
    private AfiliacionFacadeLocal AfiliacionFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Afiliacion> listaAfiliados;

    public ControladorListarAfiliacion() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() == 5) {
            System.out.println("es admin");
            listaAfiliados = usuarioFacadeLocal.listarUsuariosRolAfiliado();

        } else {
            System.out.println("es analista");
            listaAfiliados = usuarioFacadeLocal.listarUsuariosRegistradoAnalistaEnSesion(controladorLogin.getUsuarioSesion());

        }

    }

    public List<Afiliacion> getListaAfiliados() {
        return listaAfiliados;
    }

    public String cambiarEstado(Afiliacion a) {
        System.out.println("entro el afiliado: " + a.getUsuarioIdAsignado().getNombres());
        Rol rolAfiliado = new Rol(7);
        Rol rolNoAfiliado = new Rol(8);

        if (a.getUsuarioIdAsignado().getRolIdRol().equals(rolAfiliado)) {
            a.getUsuarioIdAsignado().setRolIdRol(rolNoAfiliado);
            a.getUsuarioIdAsignado().setEstado(0);
            usuarioFacadeLocal.edit(a.getUsuarioIdAsignado());
            AfiliacionFacadeLocal.edit(a);
            return "";

        } else if (a.getUsuarioIdAsignado().getRolIdRol().equals(rolNoAfiliado)) {
            a.getUsuarioIdAsignado().setRolIdRol(rolAfiliado);
            a.getUsuarioIdAsignado().setEstado(1);
            usuarioFacadeLocal.edit(a.getUsuarioIdAsignado());
            AfiliacionFacadeLocal.edit(a);
            return "";

        }
        return "";

    }

}
