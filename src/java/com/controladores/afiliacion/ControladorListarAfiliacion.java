/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.afiliacion;

import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.entidades.Afiliacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David
 */
@Named(value = "controladorListarAfiliacion")
@ViewScoped
public class ControladorListarAfiliacion implements Serializable{

    @EJB
    private AfiliacionFacadeLocal AfiliacionFacadeLocal;
    private List<Afiliacion> listaAfiliados;
            
    public ControladorListarAfiliacion() {
    }
    
    @PostConstruct
    public void init(){
        listaAfiliados = AfiliacionFacadeLocal.findAll();
    
    }

    public List<Afiliacion> getListaAfiliados() {
        return listaAfiliados;
    }

}