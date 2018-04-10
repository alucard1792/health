/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.jasper;

import com.controladores.login.ControladorLogin;
import com.modelo.dao.UsuarioFacade;
import com.modelo.dao.UsuarioFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author David
 */
@Named(value = "reporteUsuarios")
@ViewScoped
public class ReporteUsuarios implements Serializable {

    @Inject
    private ControladorLogin controladorLogin;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private JasperPrint jasperPrint;
    private Usuario usuario;

    public ReporteUsuarios() {
    }

    @PostConstruct
    public void init() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public void prepararExportar() throws JRException {
        Map<String, Object> params = new HashMap<>();
        Afiliacion a = usuarioFacadeLocal.findFetchReporte(controladorLogin.getUsuarioSesion());
        String estado = "";
        if (a.getUsuarioIdAsignado().getEstado() == 1) {
            estado = "Activo";

        } else {
            estado = "Inactivo";

        }
        System.out.println(a.getMunicipioIdMunicipio().getNombre());
        params.put("documento", a.getUsuarioIdAsignado().getDocumento());
        params.put("nombre", a.getUsuarioIdAsignado().getNombres());
        params.put("apellido", a.getUsuarioIdAsignado().getApellidos());
        params.put("fechaNacimiento", a.getUsuarioIdAsignado().getFechaNacimiento());
        params.put("direccion", a.getUsuarioIdAsignado().getDireccion());
        params.put("telefono", a.getUsuarioIdAsignado().getTelefono());
        params.put("email", a.getUsuarioIdAsignado().getEmail());
        params.put("estado", estado);
        params.put("tipoAfiliacion", a.getTipoafiliacionIdCodigo().getRegimen());
        params.put("municipio", a.getMunicipioIdMunicipio().getNombre());
        params.put("fechaCreacion", new Date());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/report/report1.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, params, new JREmptyDataSource());

    }

    public void exportarPDF() throws JRException, IOException {
        prepararExportar();
        ServletOutputStream servletOutputStream = null;
        String contentType = "aplication/PDF";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();

    }

    public void prepararExportar(Usuario u) throws JRException {
        Map<String, Object> params = new HashMap<>();
        Afiliacion a = usuarioFacadeLocal.findFetchReporte(u);
        String estado = "";
        if (a.getUsuarioIdAsignado().getEstado() == 1) {
            estado = "Activo";

        } else {
            estado = "Inactivo";

        }
        System.out.println(a.getMunicipioIdMunicipio().getNombre());
        params.put("documento", a.getUsuarioIdAsignado().getDocumento());
        params.put("nombre", a.getUsuarioIdAsignado().getNombres());
        params.put("apellido", a.getUsuarioIdAsignado().getApellidos());
        params.put("fechaNacimiento", a.getUsuarioIdAsignado().getFechaNacimiento());
        params.put("direccion", a.getUsuarioIdAsignado().getDireccion());
        params.put("telefono", a.getUsuarioIdAsignado().getTelefono());
        params.put("email", a.getUsuarioIdAsignado().getEmail());
        params.put("estado", estado);
        params.put("tipoAfiliacion", a.getTipoafiliacionIdCodigo().getRegimen());
        params.put("municipio", a.getMunicipioIdMunicipio().getNombre());
        params.put("fechaCreacion", new Date());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/report/report1.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, params, new JREmptyDataSource());

    }

    public void exportarPDF(Usuario u) throws JRException, IOException {
        prepararExportar(u);
        ServletOutputStream servletOutputStream = null;
        String contentType = "aplication/PDF";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();

    }

}
