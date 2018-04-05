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
@Table(name = "reclamacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamacion.findAll", query = "SELECT r FROM Reclamacion r")
    , @NamedQuery(name = "Reclamacion.findByIdReclamacion", query = "SELECT r FROM Reclamacion r WHERE r.idReclamacion = :idReclamacion")
    , @NamedQuery(name = "Reclamacion.findByTipoReclamacion", query = "SELECT r FROM Reclamacion r WHERE r.tipoReclamacion = :tipoReclamacion")
    , @NamedQuery(name = "Reclamacion.findByMotivo", query = "SELECT r FROM Reclamacion r WHERE r.motivo = :motivo")
    , @NamedQuery(name = "Reclamacion.findByDescripcion", query = "SELECT r FROM Reclamacion r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Reclamacion.findByFecharegistro", query = "SELECT r FROM Reclamacion r WHERE r.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "Reclamacion.findByFechaSolucion", query = "SELECT r FROM Reclamacion r WHERE r.fechaSolucion = :fechaSolucion")})
public class Reclamacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Reclamacion")
    private Integer idReclamacion;
    @Column(name = "Tipo_Reclamacion")
    private String tipoReclamacion;
    @Column(name = "Motivo")
    private String motivo;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @Column(name = "fecha_solucion")
    @Temporal(TemporalType.DATE)
    private Date fechaSolucion;
    @JoinColumn(name = "afiliacion_Id_Afiliado", referencedColumnName = "Id_Afiliacion")
    @ManyToOne(fetch = FetchType.EAGER)
    private Afiliacion afiliacionIdAfiliado;
    @JoinColumn(name = "estado_procesos_Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoProceso estadoprocesosIdEstado;
    @JoinColumn(name = "usuario_Id_analista", referencedColumnName = "Id_Usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioIdanalista;

    public Reclamacion() {
    }

    public Reclamacion(Integer idReclamacion) {
        this.idReclamacion = idReclamacion;
    }

    public Integer getIdReclamacion() {
        return idReclamacion;
    }

    public void setIdReclamacion(Integer idReclamacion) {
        this.idReclamacion = idReclamacion;
    }

    public String getTipoReclamacion() {
        return tipoReclamacion;
    }

    public void setTipoReclamacion(String tipoReclamacion) {
        this.tipoReclamacion = tipoReclamacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Afiliacion getAfiliacionIdAfiliado() {
        return afiliacionIdAfiliado;
    }

    public void setAfiliacionIdAfiliado(Afiliacion afiliacionIdAfiliado) {
        this.afiliacionIdAfiliado = afiliacionIdAfiliado;
    }

    public EstadoProceso getEstadoprocesosIdEstado() {
        return estadoprocesosIdEstado;
    }

    public void setEstadoprocesosIdEstado(EstadoProceso estadoprocesosIdEstado) {
        this.estadoprocesosIdEstado = estadoprocesosIdEstado;
    }

    public Usuario getUsuarioIdanalista() {
        return usuarioIdanalista;
    }

    public void setUsuarioIdanalista(Usuario usuarioIdanalista) {
        this.usuarioIdanalista = usuarioIdanalista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReclamacion != null ? idReclamacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamacion)) {
            return false;
        }
        Reclamacion other = (Reclamacion) object;
        if ((this.idReclamacion == null && other.idReclamacion != null) || (this.idReclamacion != null && !this.idReclamacion.equals(other.idReclamacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.entidades.Reclamacion[ idReclamacion=" + idReclamacion + " ]";
    }
    
}
