/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.bean;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "mdl_lrraep_reserva", catalog = "moodle")
public class MdlLrraepReserva implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario")
    private MdlUser Usuario;
    
    @Column(name = "data")
    private Date data;
    
    @Column(name = "situacao")
    private String situacao;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario")
    private MdlLrraepHorario horario;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public MdlLrraepHorario getHorario() {
        return horario;
    }

    public void setHorario(MdlLrraepHorario horario) {
        this.horario = horario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final MdlLrraepReserva other = (MdlLrraepReserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
