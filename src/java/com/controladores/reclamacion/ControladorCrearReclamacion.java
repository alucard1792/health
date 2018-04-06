/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.reclamacion;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.ReclamacionFacadeLocal;
import com.modelo.dao.RolFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Reclamacion;
import com.modelo.entidades.Rol;
import com.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearReclamacion")
@ViewScoped
public class ControladorCrearReclamacion implements Serializable {

    @EJB
    private ReclamacionFacadeLocal reclamacionFacadeLocal;
    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;

    private Reclamacion reclamacion;

    public ControladorCrearReclamacion() {
    }

    @PostConstruct
    public void init() {
        reclamacion = new Reclamacion();

    }

    public Reclamacion getReclamacion() {
        return reclamacion;
    }

    public void setReclamacion(Reclamacion reclamacion) {
        this.reclamacion = reclamacion;
    }

    public String crear() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() == 7) {
            reclamacion.setFecharegistro(new Date());
            reclamacion.setEstadoprocesosIdEstado(new EstadoProceso(1));
            for (Afiliacion a : controladorLogin.getUsuarioSesion().getListaUsuarioAsignado()) {
                reclamacion.setAfiliacionIdAfiliado(afiliacionFacadeLocal.find(a.getIdAfiliacion()));

            }
            reclamacionFacadeLocal.create(reclamacion);
            return "/app/reclamacion/listarReclamacion.xhtml?faces-redirect=true";

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usted no puede crear una reclamacion ya que no tiene el rol y se salto la seguridad: ", "Rol = " + controladorLogin.getUsuarioSesion().getRolIdRol().getNombre()));
        return "";

    }

}
