/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "traslado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traslado.findAll", query = "SELECT t FROM Traslado t")
    , @NamedQuery(name = "Traslado.findByIdTraslado", query = "SELECT t FROM Traslado t WHERE t.idTraslado = :idTraslado")
    , @NamedQuery(name = "Traslado.findByMotivo", query = "SELECT t FROM Traslado t WHERE t.motivo = :motivo")
    , @NamedQuery(name = "Traslado.findByMunicipio", query = "SELECT t FROM Traslado t WHERE t.municipio = :municipio")
    , @NamedQuery(name = "Traslado.findByFecharegistro", query = "SELECT t FROM Traslado t WHERE t.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "Traslado.findByFechaSolucion", query = "SELECT t FROM Traslado t WHERE t.fechaSolucion = :fechaSolucion")})
public class Traslado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Traslado")
    private Integer idTraslado;
    @Column(name = "Motivo")
    private String motivo;
    @Column(name = "Municipio")
    private String municipio;
    @Column(name = "Fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @Column(name = "fecha_solucion")
    @Temporal(TemporalType.DATE)
    private Date fechaSolucion;
    @JoinColumn(name = "afiliacion_Id_Afiliacion", referencedColumnName = "Id_Afiliacion")
    @ManyToOne(fetch = FetchType.EAGER)
    private Afiliacion afiliacionIdAfiliacion;
    @JoinColumn(name = "estado_procesos_Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoProceso estadoprocesosIdEstado;
    @JoinColumn(name = "municipio_id_municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.EAGER)
    private Municipio municipioIdMunicipio;
    @JoinColumn(name = "usuario_Id_Analista", referencedColumnName = "Id_Usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioIdAnalista;

    public Traslado() {
    }

    public Traslado(Integer idTraslado) {
        this.idTraslado = idTraslado;
    }

    public Integer getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(Integer idTraslado) {
        this.idTraslado = idTraslado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaSolucion() {
        return fechaSolucion;
    }

    public void setFechaSolucion(Date fechaSolucion) {
        this.fechaSolucion = fechaSolucion;
    }

    public Afiliacion getAfiliacionIdAfiliacion() {
        return afiliacionIdAfiliacion;
    }

    public void setAfiliacionIdAfiliacion(Afiliacion afiliacionIdAfiliacion) {
        this.afiliacionIdAfiliacion = afiliacionIdAfiliacion;
    }

    public EstadoProceso getEstadoprocesosIdEstado() {
        return estadoprocesosIdEstado;
    }

    public void setEstadoprocesosIdEstado(EstadoProceso estadoprocesosIdEstado) {
        this.estadoprocesosIdEstado = estadoprocesosIdEstado;
    }

    public Municipio getMunicipioIdMunicipio() {
        return municipioIdMunicipio;
    }

    public void setMunicipioIdMunicipio(Municipio municipioIdMunicipio) {
        this.municipioIdMunicipio = municipioIdMunicipio;
    }

    public Usuario getUsuarioIdAnalista() {
        return usuarioIdAnalista;
    }

    public void setUsuarioIdAnalista(Usuario usuarioIdAnalista) {
        this.usuarioIdAnalista = usuarioIdAnalista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTraslado != null ? idTraslado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traslado)) {
            return false;
        }
        Traslado other = (Traslado) object;
        if ((this.idTraslado == null && other.idTraslado != null) || (this.idTraslado != null && !this.idTraslado.equals(other.idTraslado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.Traslado[ idTraslado=" + idTraslado + " ]";
    }
    
}
