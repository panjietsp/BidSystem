package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.AsyncResult;
import entity.*;
import ejb.*;

@Stateless(name="ejb/Auction")
public class AuctionBean implements Auction {

    @PersistenceContext(unitName="pu1")
    private EntityManager em;

    public Obbject getObbjectById(int id){
        Query q = em.createQuery("select c from Obbject c where c.id = :name");
        q.setParameter("name", id);
        return (Obbject)q.getSingleResult();
    }
    public Bid AddBid(Obbject obbject, int price, int duration, int increment){
        Bid bid=new Bid();
        Query query = em.createQuery("select max(bid.id) from Bid bid");
        int max_id = (int)query.getSingleResult();
        bid.setId(max_id+1);
        bid.setStartingPrice(price);
        bid.setEndingPrice(price);
        bid.setDuration(duration);
        bid.setIncrement(increment);
        bid.setState("not started");
        em.persist(bid);
        Obbject c0=em.merge(obbject);
        c0.setBid(bid);
        em.persist(c0);
        return bid;
    }

    public Bid getBidById(int id){
        Query q = em.createQuery("select c from Bid c where c.id = :name");
        q.setParameter("name", id);
        return (Bid)q.getSingleResult();
    }

    public void StartBid(Bid bid){
        Bid c0=em.merge(bid);
        c0.setState("active");
        em.persist(c0);
    }
    public void EndBid(Bid bid){
        Bid c0=em.merge(bid);
        c0.setState("closed");
        em.persist(c0);
    }
    @Asynchronous
    public Future<String> Inbid(Bid bid){
        try{
                Bid c0=em.merge(bid);
                Thread.sleep(c0.getDuration()*1000);      
        } catch (Exception e){
            e.printStackTrace();
        }
        return new AsyncResult<String>("finished");
    }
    public void BidOnce(Bid bid){
        Bid c0=em.merge(bid);
        c0.setEndingPrice(c0.getEndingPrice()+c0.getIncrement());
        em.persist(c0);
    }


    public Uuser getUuserByEmail(String email){
        Query q = em.createQuery("select c from Uuser c where c.email = :name");
        q.setParameter("name", email);
        return (Uuser)q.getSingleResult();
    }
    
}
