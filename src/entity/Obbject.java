package entity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Obbject")
public class Obbject implements Serializable{

    private int id;
    private String description;
    private String category;
    private Uuser uuser;
    private Bid bid;

    @Id
    @Column(name="ID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="CATEGORY")
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @ManyToOne()
    @JoinColumn(name="UUSER_ID")
    public Uuser getUuser() {
        return uuser;
    }
    public void setUuser(Uuser uuser) {
        this.uuser = uuser;
    }

    @OneToOne()
    @JoinColumn(name="BID_ID")
    public Bid getBid() {
        return bid;
    }
    public void setBid(Bid bid) {
        this.bid = bid;
    }
}
