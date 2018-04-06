/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.translados;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.EstadoProcesoFacadeLocal;
import com.modelo.dao.TrasladoFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Traslado;
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
@Named(value = "controladorEditarTranslado")
@ViewScoped
public class ControladorEditarTranslado implements Serializable {

    @EJB
    private TrasladoFacadeLocal trasladoFacadeLocal;
    @EJB
    private EstadoProcesoFacadeLocal estadoProcesoFacadeLocal;
    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoProceso> listaEstadoProcesos;
    private Traslado trasladoSeleccionado;

    public ControladorEditarTranslado() {
    }

    @PostConstruct
    public void init() {
        listaEstadoProcesos = estadoProcesoFacadeLocal.findAll();

    }

    public List<EstadoProceso> getListaEstadoProcesos() {
        return listaEstadoProcesos;
    }

    public Traslado getTrasladoSeleccionado() {
        return trasladoSeleccionado;
    }

    public void setTrasladoSeleccionado(Traslado trasladoSeleccionado) {
        this.trasladoSeleccionado = trasladoSeleccionado;
    }
    
    
    public void prepararEditar(Traslado t){
        trasladoSeleccionado = t;
        System.out.println(trasladoSeleccionado.getIdTraslado());
    
    }

    public void editar() {
        trasladoSeleccionado.setFechaSolucion(new Date());
        trasladoSeleccionado.setEstadoprocesosIdEstado(new EstadoProceso(2));
        trasladoSeleccionado.setUsuarioIdAnalista(controladorLogin.getUsuarioSesion());
        trasladoFacadeLocal.edit(trasladoSeleccionado);
        editarAfiliado();

    }
    
    public void editarAfiliado(){
        Afiliacion a = afiliacionFacadeLocal.find(trasladoSeleccionado.getAfiliacionIdAfiliacion().getIdAfiliacion());
        a.setMunicipioIdMunicipio(trasladoSeleccionado.getMunicipioIdMunicipio());
        afiliacionFacadeLocal.edit(a);
    
    }

}
