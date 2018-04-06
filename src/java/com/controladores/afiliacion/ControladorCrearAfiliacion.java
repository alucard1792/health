/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.usuarios;

import com.modelo.dao.UsuarioFacadeLocal;
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
@Named(value = "controladorListarUsuarios")
@ViewScoped
public class ControladorListarUsuarios implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private List<Usuario> listaUsuario;
            
    public ControladorListarUsuarios() {
    }
    
    @PostConstruct
    public void init(){
        listaUsuario = usuarioFacadeLocal.findAll();
    
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    
}