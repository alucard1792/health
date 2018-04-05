/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.usuarios;

import com.modelo.dao.RolFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Rol;
import com.modelo.entidades.Usuario;
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
@Named(value = "controladorEditarUsuarios")
@ConversationScoped
public class ControladorEditarUsuarios implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private RolFacadeLocal rolFacadeLocal;
    @Inject
    private Conversation conversation;
    private List<Rol> listaRoles;
    private Usuario usuarioSeleccionado;

    public ControladorEditarUsuarios() {
    }

    @PostConstruct
    public void init() {
        listaRoles = rolFacadeLocal.findAll();

    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
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
    
    public String cancelar(){
        terminarConversacion();
        return "/app/usuarios/listarUsuarios.xhtml?faces-redirect=true";
        
    }
    
    public String prepararEditar(Usuario u){
        iniciarConversacion();
        usuarioSeleccionado = u;
        return "/app/usuarios/editarUsuario.xhtml?faces-redirect=true";
    
    }

    public String editar() {
        usuarioFacadeLocal.edit(usuarioSeleccionado);
        return cancelar();

    }

}
