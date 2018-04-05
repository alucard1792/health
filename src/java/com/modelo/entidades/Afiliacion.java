/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "afiliacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Afiliacion.findAll", query = "SELECT a FROM Afiliacion a")
    , @NamedQuery(name = "Afiliacion.findByIdAfiliacion", query = "SELECT a FROM Afiliacion a WHERE a.idAfiliacion = :idAfiliacion")
    , @NamedQuery(name = "Afiliacion.findByCodigo", query = "SELECT a FROM Afiliacion a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Afiliacion.findByFecha", query = "SELECT a FROM Afiliacion a WHERE a.fecha = :fecha")})
public class Afiliacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Afiliacion")
    private Integer idAfiliacion;
    @Column(name = "Codigo")
    private Integer codigo;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "municipio_Id_Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.EAGER)
    private Municipio municipioIdMunicipio;
    @JoinColumn(name = "tipo_afiliacion_Id_Codigo", referencedColumnName = "Id_Codigo")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoAfiliacion tipoafiliacionIdCodigo;
    @JoinColumn(name = "usuario_Id_Asignado", referencedColumnName = "Id_Usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioIdAsignado;
    @JoinColumn(name = "usuario_Id_Analista", referencedColumnName = "Id_Usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioIdAnalista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afiliacionIdAfiliacion", fetch = FetchType.EAGER)
    private List<Certificacion> certificacionList;
    @OneToMany(mappedBy = "afiliacionIdAfiliado", fetch = FetchType.EAGER)
    private List<Reclamacion> reclamacionList;
    @OneToMany(mappedBy = "afiliacionIdAfiliacion", fetch = FetchType.EAGER)
    private List<Traslado> trasladoList;

    public Afiliacion() {
    }

    public Afiliacion(Integer idAfiliacion) {
        this.idAfiliacion = idAfiliacion;
    }

    public Integer getIdAfiliacion() {
        return idAfiliacion;
    }

    public void setIdAfiliacion(Integer idAfiliacion) {
        this.idAfiliacion = idAfiliacion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Municipio getMunicipioIdMunicipio() {
        return municipioIdMunicipio;
    }

    public void setMunicipioIdMunicipio(Municipio municipioIdMunicipio) {
        this.municipioIdMunicipio = municipioIdMunicipio;
    }

    public TipoAfiliacion getTipoafiliacionIdCodigo() {
        return tipoafiliacionIdCodigo;
    }

    public void setTipoafiliacionIdCodigo(TipoAfiliacion tipoafiliacionIdCodigo) {
        this.tipoafiliacionIdCodigo = tipoafiliacionIdCodigo;
    }

    public Usuario getUsuarioIdAsignado() {
        return usuarioIdAsignado;
    }

    public void setUsuarioIdAsignado(Usuario usuarioIdAsignado) {
        this.usuarioIdAsignado = usuarioIdAsignado;
    }

    public Usuario getUsuarioIdAnalista() {
        return usuarioIdAnalista;
    }

    public void setUsuarioIdAnalista(Usuario usuarioIdAnalista) {
        this.usuarioIdAnalista = usuarioIdAnalista;
    }

    @XmlTransient
    public List<Certificacion> getCertificacionList() {
        return certificacionList;
    }

    public void setCertificacionList(List<Certificacion> certificacionList) {
        this.certificacionList = certificacionList;
    }

    @XmlTransient
    public List<Reclamacion> getReclamacionList() {
        return reclamacionList;
    }

    public void setReclamacionList(List<Reclamacion> reclamacionList) {
        this.reclamacionList = reclamacionList;
    }

    @XmlTransient
    public List<Traslado> getTrasladoList() {
        return trasladoList;
    }

    public void setTrasladoList(List<Traslado> trasladoList) {
        this.trasladoList = trasladoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAfiliacion != null ? idAfiliacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afiliacion)) {
            return false;
        }
        Afiliacion other = (Afiliacion) object;
        if ((this.idAfiliacion == null && other.idAfiliacion != null) || (this.idAfiliacion != null && !this.idAfiliacion.equals(other.idAfiliacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.Afiliacion[ idAfiliacion=" + idAfiliacion + " ]";
    }
    
}
