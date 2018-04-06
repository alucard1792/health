/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.afiliacion;

import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.MunicipioFacadeLocal;
import com.modelo.dao.TipoAfiliacionFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Municipio;
import com.modelo.entidades.TipoAfiliacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorEditarAfiliado")
@ConversationScoped
public class ControladorEditarAfiliado implements Serializable {

    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private TipoAfiliacionFacadeLocal tipoAfiliacionFacadeLocal;
    @EJB
    private MunicipioFacadeLocal municipioFacadeLocal;
    @Inject
    private Conversation conversation;
    private List<TipoAfiliacion> listaTipoAfiliacion;
    private List<Municipio> listaMunicipios;
    private Afiliacion afiliacionSeleccionada;

    public ControladorEditarAfiliado() {
    }

    @PostConstruct
    public void init() {
        listaMunicipios = municipioFacadeLocal.findAll();
        listaTipoAfiliacion = tipoAfiliacionFacadeLocal.findAll();

    }

    public List<TipoAfiliacion> getListaTipoAfiliacion() {
        return listaTipoAfiliacion;
    }

    public void setListaTipoAfiliacion(List<TipoAfiliacion> listaTipoAfiliacion) {
        this.listaTipoAfiliacion = listaTipoAfiliacion;
    }

    public List<Municipio> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Municipio> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public Afiliacion getAfiliacionSeleccionada() {
        return afiliacionSeleccionada;
    }

    public void setAfiliacionSeleccionada(Afiliacion afiliacionSeleccionada) {
        this.afiliacionSeleccionada = afiliacionSeleccionada;
    }


    public void iniciarConversacion() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

    }

    public void terminarConversacion() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/app/afiliado/listarAfiliados.xhtml?faces-redirect=true";

    }

    public String prepararEditar(Afiliacion a) {
        iniciarConversacion();
        afiliacionSeleccionada = a;
        return "/app/afiliado/editarAfiliado.xhtml?faces-redirect=true";

    }

    public String editar() {
        usuarioFacadeLocal.edit(afiliacionSeleccionada.getUsuarioIdAsignado());
        afiliacionFacadeLocal.edit(afiliacionSeleccionada);
        return cancelar();

    }

}
