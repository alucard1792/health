/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.reclamacion;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.ReclamacionFacadeLocal;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Reclamacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorEditarReclamacion")
@ViewScoped
public class ControladorEditarReclamacion implements Serializable {

    @EJB
    private ReclamacionFacadeLocal reclamacionFacadeLocal;
    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoProceso> listaEstadoProcesos;
    private Reclamacion reclamacionSeleccionada;

    public ControladorEditarReclamacion() {
    }

    @PostConstruct
    public void init() {

    }

    public List<EstadoProceso> getListaEstadoProcesos() {
        return listaEstadoProcesos;
    }

    public Reclamacion getReclamacionSeleccionada() {
        return reclamacionSeleccionada;
    }

    public void setReclamacionSeleccionada(Reclamacion reclamacionSeleccionada) {
        this.reclamacionSeleccionada = reclamacionSeleccionada;
    }

    public void prepararEditar(Reclamacion r) {
        reclamacionSeleccionada = r;
        System.out.println(reclamacionSeleccionada.getIdReclamacion());

    }

    public void editar() {
        reclamacionSeleccionada.setFechaSolucion(new Date());
        reclamacionSeleccionada.setEstadoprocesosIdEstado(new EstadoProceso(2));
        reclamacionSeleccionada.setUsuarioIdanalista(controladorLogin.getUsuarioSesion());
        reclamacionFacadeLocal.edit(reclamacionSeleccionada);

    }

}
