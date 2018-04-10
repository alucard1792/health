/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.reportes;

import com.controladores.translados.*;
import com.controladores.login.ControladorLogin;
import com.modelo.dao.ReclamacionFacadeLocal;
import com.modelo.dao.TrasladoFacadeLocal;
import com.modelo.entidades.Afiliacion;
import com.modelo.entidades.Municipio;
import com.modelo.entidades.Reclamacion;
import com.modelo.entidades.Traslado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorListarReportes")
@ViewScoped
public class ControladorListarReportes implements Serializable {

    @EJB
    private ReclamacionFacadeLocal reclamacionFacadeLocal;
    @EJB
    private TrasladoFacadeLocal trasladoFacadeLocal;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Reclamacion> listaReclamacionDesacatos;
    private List<Reclamacion> listaReclamacionTutelas;
    private List<Reclamacion> listaReclamacionDerechoPeticion;
    private List<Reclamacion> listaReclamacionSolicitud;
    private List<Traslado> listaTraslados;
    private List<Municipio> listaMunicipios = new ArrayList<>();
    private int municipio1;
    private int municipio2;
    private int municipio3;

    public ControladorListarReportes() {
    }

    @PostConstruct
    public void init() {
        listaReclamacionDesacatos = reclamacionFacadeLocal.listaReclamacionPorTipo("desacatos");
        listaReclamacionTutelas = reclamacionFacadeLocal.listaReclamacionPorTipo("tutelas");
        listaReclamacionDerechoPeticion = reclamacionFacadeLocal.listaReclamacionPorTipo("derecho de peticion");
        listaReclamacionSolicitud = reclamacionFacadeLocal.listaReclamacionPorTipo("Solicitud");
        listaTraslados = trasladoFacadeLocal.listaUltimosTresMunicipios();
        for (Traslado t : listaTraslados) {
            listaMunicipios.add(t.getMunicipioIdMunicipio());
            System.out.println(t.getMunicipioIdMunicipio().getNombre());
        }
        
        municipio1 = (int) trasladoFacadeLocal.countMunicipios(listaMunicipios.get(0));
        municipio2 = (int) trasladoFacadeLocal.countMunicipios(listaMunicipios.get(1));
        municipio3 = (int) trasladoFacadeLocal.countMunicipios(listaMunicipios.get(2));
        System.out.printf("%d %d %d", municipio1, municipio2, municipio3);
        
        
    }

    public List<Reclamacion> getListaReclamacionDesacatos() {
        return listaReclamacionDesacatos;
    }

    public List<Reclamacion> getListaReclamacionTutelas() {
        return listaReclamacionTutelas;
    }

    public List<Reclamacion> getListaReclamacionDerechoPeticion() {
        return listaReclamacionDerechoPeticion;
    }

    public List<Reclamacion> getListaReclamacionSolicitud() {
        return listaReclamacionSolicitud;
    }

    public List<Municipio> getListaMunicipios() {
        return listaMunicipios;
    }

    public int getMunicipio1() {
        return municipio1;
    }

    public int getMunicipio2() {
        return municipio2;
    }

    public int getMunicipio3() {
        return municipio3;
    }
    
    


}
