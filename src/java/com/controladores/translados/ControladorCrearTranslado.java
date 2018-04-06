/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.translados;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.MunicipioFacadeLocal;
import com.modelo.dao.TrasladoFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Municipio;
import com.modelo.entidades.Traslado;
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
@Named(value = "controladorCrearTranslado")
@ViewScoped
public class ControladorCrearTranslado implements Serializable {

    @EJB
    private TrasladoFacadeLocal trasladoFacadeLocal;
    @EJB
    private MunicipioFacadeLocal municipioFacadeLocal;
    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Municipio> listaMunicipio;
    private Traslado traslado;

    public ControladorCrearTranslado() {
    }

    @PostConstruct
    public void init() {
        listaMunicipio = municipioFacadeLocal.findAll();
        traslado = new Traslado();

    }

    public List<Municipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public Traslado getTraslado() {
        return traslado;
    }

    public void setTraslado(Traslado traslado) {
        this.traslado = traslado;
    }

    public String crear() {
        if (controladorLogin.getUsuarioSesion().getRolIdRol().getIdRol() == 7) {
            traslado.setFecharegistro(new Date());
            traslado.setEstadoprocesosIdEstado(new EstadoProceso(1));
            for (Afiliacion a : controladorLogin.getUsuarioSesion().getListaUsuarioAsignado()) {
                traslado.setAfiliacionIdAfiliacion(afiliacionFacadeLocal.find(a.getIdAfiliacion()));
                System.out.println(a.getIdAfiliacion());

            }
            trasladoFacadeLocal.create(traslado);
            return "/app/translados/listarTranslados.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usted no puede crear un translado ya que no tiene el rol y se salto la seguridad: ", "Rol = " + controladorLogin.getUsuarioSesion().getRolIdRol().getNombre()));
        return "";
    }

}
