/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.dao;

import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario iniciarSesion(int documento, String clave);
    
    List<Usuario>listarUsuariosNoAfiliados();
    
    Afiliacion findFetchReporte(Usuario u);
    
    List<Afiliacion> listarUsuariosRegistradoAnalistaEnSesion(Usuario usuario);
    
    List<Afiliacion> listarUsuariosRolAfiliado();
    
    Boolean loadUsuarios(String pathFile);

}
