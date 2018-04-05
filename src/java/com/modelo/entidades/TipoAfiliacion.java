/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "tipo_afiliacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAfiliacion.findAll", query = "SELECT t FROM TipoAfiliacion t")
    , @NamedQuery(name = "TipoAfiliacion.findByIdCodigo", query = "SELECT t FROM TipoAfiliacion t WHERE t.idCodigo = :idCodigo")
    , @NamedQuery(name = "TipoAfiliacion.findByRegimen", query = "SELECT t FROM TipoAfiliacion t WHERE t.regimen = :regimen")})
public class TipoAfiliacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Codigo")
    private Integer idCodigo;
    @Column(name = "Regimen")
    private String regimen;
    @OneToMany(mappedBy = "tipoafiliacionIdCodigo", fetch = FetchType.EAGER)
    private List<Afiliacion> afiliacionList;

    public TipoAfiliacion() {
    }

    public TipoAfiliacion(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public Integer getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    @XmlTransient
    public List<Afiliacion> getAfiliacionList() {
        return afiliacionList;
    }

    public void setAfiliacionList(List<Afiliacion> afiliacionList) {
        this.afiliacionList = afiliacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCodigo != null ? idCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAfiliacion)) {
            return false;
        }
        TipoAfiliacion other = (TipoAfiliacion) object;
        if ((this.idCodigo == null && other.idCodigo != null) || (this.idCodigo != null && !this.idCodigo.equals(other.idCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.TipoAfiliacion[ idCodigo=" + idCodigo + " ]";
    }
    
}
