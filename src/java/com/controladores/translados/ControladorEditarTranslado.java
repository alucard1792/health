/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.translados;

import com.controladores.email.MailController;
import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.EstadoProcesoFacadeLocal;
import com.modelo.dao.TrasladoFacadeLocal;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Traslado;
import com.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.mail.MessagingException;

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
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoProceso> listaEstadoProcesos;
    private Traslado trasladoSeleccionado;
    String message = "";

    public ControladorEditarTranslado() {
    }

    @PostConstruct
    public void init() {
        listaEstadoProcesos = estadoProcesoFacadeLocal.findAll();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void prepararEditar(Traslado t) {
        trasladoSeleccionado = t;
        System.out.println(trasladoSeleccionado.getIdTraslado());

    }

    public void editar() throws MessagingException {
        trasladoSeleccionado.setFechaSolucion(new Date());
        trasladoSeleccionado.setEstadoprocesosIdEstado(new EstadoProceso(2));
        trasladoSeleccionado.setUsuarioIdAnalista(controladorLogin.getUsuarioSesion());
        trasladoFacadeLocal.edit(trasladoSeleccionado);
        editarAfiliado();

    }

    public void editarAfiliado() throws MessagingException {
        Afiliacion a = afiliacionFacadeLocal.find(trasladoSeleccionado.getAfiliacionIdAfiliacion().getIdAfiliacion());
        a.setMunicipioIdMunicipio(trasladoSeleccionado.getMunicipioIdMunicipio());
        afiliacionFacadeLocal.edit(a);
        enviarEmail();

    }

    public void enviarEmail() {
        System.out.println("mensaje = " + message);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String temp = "<h3><br/>Nos permitimos informarle que ha sido asignada al municipio " + trasladoSeleccionado.getMunicipioIdMunicipio().getNombre() + "<br/><h3/>"
                + "<h4>resumen del translado: <br/>"
                + "id translado = " + trasladoSeleccionado.getIdTraslado() + "<br/>"
                + "motivo = " + trasladoSeleccionado.getMotivo() + "<br/>"
                + "Municipio = " + trasladoSeleccionado.getMunicipioIdMunicipio().getNombre() + "<br/>"
                + "Analista = " + controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos() + "<br/>"
                + "Otra informacion = " + (message.length() == 0 ? "N/A": message) + "<br/>"
                + "EfiSalud " + year + "<h5/>";

        MailController mc = new MailController();
        Traslado t = trasladoFacadeLocal.find(trasladoSeleccionado.getIdTraslado());
        Usuario u = afiliacionFacadeLocal.findFetch(trasladoSeleccionado.getIdTraslado());
        mc.enviarEmailCliente(u.getEmail(), "Notificacion cambio municipio", temp);

    }

}
