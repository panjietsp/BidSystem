package entity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Bid")

public class Bid implements Serializable{
    private int id;
    private int startingPrice;
    private String state;
    private int duration;
    private int increment;
    private int endingPrice;

    @Id
    @Column(name="BID_ID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="STARTINGPRICE")
    public int getStartingPrice() {
        return startingPrice;
    }
    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Column(name="ENDINGPRICE")
    public int getEndingPrice() {
        return endingPrice;
    }
    public void setEndingPrice(int endingPrice) {
        this.endingPrice = endingPrice;
    }

    @Column(name="DURATION")
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name="INCREMENT")
    public int getIncrement() {
        return increment;
    }
    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Column(name="STATE")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}