package ejb;

import javax.ejb.Remote;
import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.AsyncResult;
import entity.*;
import ejb.*;

@Remote 
public interface Auction {
    public Bid AddBid(Obbject obbject, int price, int duration, int increment);

    public Bid getBidById(int id);

    public void StartBid(Bid bid);

    @Asynchronous
    public Future<String> Inbid(Bid bid);

    public void EndBid(Bid bid);

    public void BidOnce(Bid bid);

    public Obbject getObbjectById(int id);

    public Uuser getUuserByEmail(String email);

}
