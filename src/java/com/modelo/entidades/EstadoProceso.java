/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "estado_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProceso.findAll", query = "SELECT e FROM EstadoProceso e")
    , @NamedQuery(name = "EstadoProceso.findByIdEstado", query = "SELECT e FROM EstadoProceso e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "EstadoProceso.findByNombreEstado", query = "SELECT e FROM EstadoProceso e WHERE e.nombreEstado = :nombreEstado")})
public class EstadoProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Estado")
    private Integer idEstado;
    @Column(name = "Nombre_Estado")
    private String nombreEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoprocesosIdEstado", fetch = FetchType.EAGER)
    private List<Reclamacion> reclamacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoprocesosIdEstado", fetch = FetchType.EAGER)
    private List<Traslado> trasladoList;

    public EstadoProceso() {
    }

    public EstadoProceso(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
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
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProceso)) {
            return false;
        }
        EstadoProceso other = (EstadoProceso) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.EstadoProceso[ idEstado=" + idEstado + " ]";
    }
    
}
