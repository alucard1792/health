/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.reclamacion;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.ReclamacionFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Reclamacion;
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
@Named(value = "controladorListarReclamacion")
@ViewScoped
public class ControladorListarReclamacion implements Serializable {

    @EJB
    private ReclamacionFacadeLocal reclamacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Reclamacion> listaReclamaciones;

    public ControladorListarReclamacion() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() != 7) {
            listaReclamaciones = reclamacionFacadeLocal.findAll();
            
        } else {
            for (Afiliacion a : controladorLogin.getUsuarioSesion().getListaUsuarioAsignado()) {
                listaReclamaciones = reclamacionFacadeLocal.listaReclamacionPorRol(a);

            }

        }

    }

    public List<Reclamacion> getListaReclamaciones() {
        return listaReclamaciones;
    }

}
