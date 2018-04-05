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
import javax.persistence.Lob;
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
@Table(name = "certificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificacion.findAll", query = "SELECT c FROM Certificacion c")
    , @NamedQuery(name = "Certificacion.findByIdCertificacion", query = "SELECT c FROM Certificacion c WHERE c.idCertificacion = :idCertificacion")
    , @NamedQuery(name = "Certificacion.findByTipo", query = "SELECT c FROM Certificacion c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Certificacion.findByCodigo", query = "SELECT c FROM Certificacion c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Certificacion.findByFecha", query = "SELECT c FROM Certificacion c WHERE c.fecha = :fecha")})
public class Certificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Certificacion")
    private Integer idCertificacion;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Codigo")
    private String codigo;
    @Lob
    @Column(name = "Certificado")
    private String certificado;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "afiliacion_Id_Afiliacion", referencedColumnName = "Id_Afiliacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Afiliacion afiliacionIdAfiliacion;

    public Certificacion() {
    }

    public Certificacion(Integer idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    public Integer getIdCertificacion() {
        return idCertificacion;
    }

    public void setIdCertificacion(Integer idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Afiliacion getAfiliacionIdAfiliacion() {
        return afiliacionIdAfiliacion;
    }

    public void setAfiliacionIdAfiliacion(Afiliacion afiliacionIdAfiliacion) {
        this.afiliacionIdAfiliacion = afiliacionIdAfiliacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCertificacion != null ? idCertificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificacion)) {
            return false;
        }
        Certificacion other = (Certificacion) object;
        if ((this.idCertificacion == null && other.idCertificacion != null) || (this.idCertificacion != null && !this.idCertificacion.equals(other.idCertificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.Certificacion[ idCertificacion=" + idCertificacion + " ]";
    }
    
}
