package lrraep.bean;
// Generated 16/10/2015 21:32:30 by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MdlLrraepFeedback generated by hbm2java
 */
@Entity
@Table(name="mdl_lrraep_feedback"
    ,catalog="ead_gaire"
)
public class MdlLrraepFeedback  implements java.io.Serializable {


     private Long id;
     private String mensagem;
     private Long execucao;

    public MdlLrraepFeedback() {
    }

    public MdlLrraepFeedback(String mensagem, Long execucao) {
       this.mensagem = mensagem;
       this.execucao = execucao;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="mensagem")
    public String getMensagem() {
        return this.mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
    @Column(name="execucao")
    public Long getExecucao() {
        return this.execucao;
    }
    
    public void setExecucao(Long execucao) {
        this.execucao = execucao;
    }




}


