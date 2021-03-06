package lrraep.bean;
// Generated 16/10/2015 21:32:30 by Hibernate Tools 3.6.0


import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MdlLrraepReserva generated by hbm2java
 */
@Entity
@Table(name="mdl_lrraep_reserva"
    ,catalog="ead_gaire"
)
public class MdlLrraepReserva  implements java.io.Serializable {


     private Long id;
     private MdlUser Usuario;
     private Date data;
     private MdlLrraepHorario horario;
     private String situacao;
     private Integer lrraep;
    public MdlLrraepReserva() {
    }

    public MdlLrraepReserva(MdlUser Usuario, Date data, MdlLrraepHorario horario, String situacao) {
       this.Usuario = Usuario;
       this.data = data;
       this.horario = horario;
       this.situacao = situacao;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="usuario")
    public MdlUser getUsuario() {
        return this.Usuario;
    }
    
    public void setUsuario(MdlUser Usuario) {
        this.Usuario = Usuario;
    }

    
    @Column(name="data")
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="horario")
    public MdlLrraepHorario getHorario() {
        return this.horario;
    }
    
    public void setHorario(MdlLrraepHorario horario) {
        this.horario = horario;
    }
    
    @Column(name="situacao")
    public String getSituacao() {
        return this.situacao;
    }
    
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getLrraep() {
        return lrraep;
    }

    public void setLrraep(Integer lrraep) {
        this.lrraep = lrraep;
    }

}


