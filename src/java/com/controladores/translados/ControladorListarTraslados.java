/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.translados;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.TrasladoFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Traslado;
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
@Named(value = "controladorListarTraslados")
@ViewScoped
public class ControladorListarTraslados implements Serializable {

    @EJB
    private TrasladoFacadeLocal trasladoFacadeLocal;
    private List<Traslado> listaTraslados;
    @Inject
    private ControladorLogin controladorLogin;

    public ControladorListarTraslados() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() != 7) {
            listaTraslados = trasladoFacadeLocal.findAll();
        } else {
            for (Afiliacion a : controladorLogin.getUsuarioSesion().getListaUsuarioAsignado()) {
                listaTraslados = trasladoFacadeLocal.listaTransladoPorRol(a);

            }

        }

    }

    public List<Traslado> getListaTraslados() {
        return listaTraslados;
    }

}
