/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.reclamacion;

import com.controladores.email.MailController;
import com.controladores.login.ControladorLogin;
import com.modelo.dao.AfiliacionFacadeLocal;
import com.modelo.dao.ReclamacionFacadeLocal;
import com.modelo.entidades.EstadoProceso;
import com.modelo.entidades.Reclamacion;
import com.modelo.entidades.Traslado;
import com.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Calendar;
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
@Named(value = "controladorEditarReclamacion")
@ViewScoped
public class ControladorEditarReclamacion implements Serializable {

    @EJB
    private ReclamacionFacadeLocal reclamacionFacadeLocal;
    @EJB
    private AfiliacionFacadeLocal afiliacionFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoProceso> listaEstadoProcesos;
    private Reclamacion reclamacionSeleccionada;
    String message = "";
    public ControladorEditarReclamacion() {
    }

    @PostConstruct
    public void init() {

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

    public Reclamacion getReclamacionSeleccionada() {
        return reclamacionSeleccionada;
    }

    public void setReclamacionSeleccionada(Reclamacion reclamacionSeleccionada) {
        this.reclamacionSeleccionada = reclamacionSeleccionada;
    }

    public void prepararEditar(Reclamacion r) {
        reclamacionSeleccionada = r;
        System.out.println(reclamacionSeleccionada.getIdReclamacion());

    }

    public void editar() {
        reclamacionSeleccionada.setFechaSolucion(new Date());
        reclamacionSeleccionada.setEstadoprocesosIdEstado(new EstadoProceso(2));
        reclamacionSeleccionada.setUsuarioIdanalista(controladorLogin.getUsuarioSesion());
        reclamacionFacadeLocal.edit(reclamacionSeleccionada);
        enviarEmail();
    }
    
    public void enviarEmail() {
        System.out.println("mensaje = " + message);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String temp = "<h3><br/>Nos permitimos informarle la respuesta a su reclamacion ha sido gestionada: <br/><h3/>"
                + "<h4>resumen: <br/>"
                + "id translado = " + reclamacionSeleccionada.getIdReclamacion()+ "<br/>"
                + "motivo = " + reclamacionSeleccionada.getTipoReclamacion()+ "<br/>"
                + "Municipio = " + reclamacionSeleccionada.getMotivo()+ "<br/>"
                + "Municipio = " + reclamacionSeleccionada.getDescripcion()+ "<br/>"
                + "Analista = " + controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos() + "<br/>"
                + "Otra informacion = " + (message.length() == 0 ? "N/A": message) + "<br/>"
                + "EfiSalud " + year + "<h5/>";

        MailController mc = new MailController();
        //Traslado t = reclamacionSeleccionada.find(trasladoSeleccionado.getIdTraslado());
        Usuario u = afiliacionFacadeLocal.findFetchReclamacion(reclamacionSeleccionada.getIdReclamacion());
        mc.enviarEmailCliente(u.getEmail(), "Notificacion respuesta reclamo", temp);

    }
}
