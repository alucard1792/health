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
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearUsuarios")
@ViewScoped
public class ControladorCrearUsuarios implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private RolFacadeLocal rolFacadeLocal;
    private List<Rol> listaRoles;
    private Usuario usuario;

    public ControladorCrearUsuarios() {
    }

    @PostConstruct
    public void init() {
        listaRoles = rolFacadeLocal.findAll();
        usuario = new Usuario();

    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String crear(){
        usuarioFacadeLocal.create(usuario);
        return "/app/usuarios/listarUsuarios.xhtml?faces-redirect=true";
        
    }

}
