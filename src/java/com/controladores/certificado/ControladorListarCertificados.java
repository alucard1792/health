/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.certificado;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
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
@Named(value = "controladorListarCertificados")
@ViewScoped
public class ControladorListarCertificados implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Afiliacion> listaAfiliacion;

    public ControladorListarCertificados() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() == 5) {
            System.out.println("es admin");
            listaAfiliacion = usuarioFacadeLocal.listarUsuariosRolAfiliado();

        } else {
            System.out.println("es analista");
            listaAfiliacion = usuarioFacadeLocal.listarUsuariosRegistradoAnalistaEnSesion(controladorLogin.getUsuarioSesion());

        }

    }

    public List<Afiliacion> getListaAfiliacion() {
        return listaAfiliacion;
    }

}
