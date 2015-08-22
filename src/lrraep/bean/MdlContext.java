package lrraep.bean;
// Generated 18/08/2015 18:22:00 by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * MdlContext generated by hbm2java
 */
@Entity
@Table(name="mdl_context"
    ,catalog="ead_gaire"
    , uniqueConstraints = @UniqueConstraint(columnNames={"contextlevel", "instanceid"}) 
)
public class MdlContext  implements java.io.Serializable {


     private Long id;
     private long contextlevel;
     private long instanceid;
     private String path;
     private byte depth;

    public MdlContext() {
    }

	
    public MdlContext(long contextlevel, long instanceid, byte depth) {
        this.contextlevel = contextlevel;
        this.instanceid = instanceid;
        this.depth = depth;
    }
    public MdlContext(long contextlevel, long instanceid, String path, byte depth) {
       this.contextlevel = contextlevel;
       this.instanceid = instanceid;
       this.path = path;
       this.depth = depth;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="contextlevel", nullable=false)
    public long getContextlevel() {
        return this.contextlevel;
    }
    
    public void setContextlevel(long contextlevel) {
        this.contextlevel = contextlevel;
    }

    
    @Column(name="instanceid", nullable=false)
    public long getInstanceid() {
        return this.instanceid;
    }
    
    public void setInstanceid(long instanceid) {
        this.instanceid = instanceid;
    }

    
    @Column(name="path")
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    
    @Column(name="depth", nullable=false)
    public byte getDepth() {
        return this.depth;
    }
    
    public void setDepth(byte depth) {
        this.depth = depth;
    }




}

