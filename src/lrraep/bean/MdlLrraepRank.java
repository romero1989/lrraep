/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Romero
 */

@Entity
@Table(name="mdl_lrraep_rank"
    ,catalog="ead_gaire")
public class MdlLrraepRank implements Serializable {
    
    @Id @GeneratedValue(strategy=IDENTITY)    
    @Column(name="id", unique=true, nullable=false)
    private Long id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usuario")
    private MdlUser Usuario;
    
    @Column(name="cenario")
    private Integer cenario;
    
    @Column(name="pontos")
    private Integer pontos;
    
    @Column(name="hora_ini")
    private Timestamp hora_inicio;
    
    @Column(name="hora_fim")
    private Timestamp hora_fim;
    
    @Column(name="status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MdlUser getUsuario() {
        return Usuario;
    }

    public void setUsuario(MdlUser Usuario) {
        this.Usuario = Usuario;
    }

    public Integer getCenario() {
        return cenario;
    }

    public void setCenario(Integer cenario) {
        this.cenario = cenario;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Timestamp getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Timestamp hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Timestamp getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Timestamp hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MdlLrraepRank other = (MdlLrraepRank) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
