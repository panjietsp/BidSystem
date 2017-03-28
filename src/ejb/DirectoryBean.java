package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.AsyncResult;
import entity.*;
import ejb.*;

@Stateless(name="ejb/Directory")
public class DirectoryBean implements Directory {

    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    public Uuser AddUuser(String first, String last, String address, String email){
        Uuser uuser=new Uuser();
        Query query = em.createQuery("select max(uuser.id) from Uuser uuser");
        int max_id = (int)query.getSingleResult();
        uuser.setId(max_id+1);
        uuser.setFirstName(first);
        uuser.setLastName(last);
        uuser.setAddress(address);
        uuser.setEmail(email);
        uuser.setRiight(true);
	uuser.setPseudonym(first+'_'+last+'_'+uuser.getId());
        em.persist(uuser);
        return uuser;
    }
   public Obbject AddObbject(String description, String category, Uuser uuser){
        Obbject obbject=new Obbject();
        Query query = em.createQuery("select max(obbject.id) from Obbject obbject");
        int max_id = (int)query.getSingleResult();
        obbject.setId(max_id+1);
        obbject.setDescription(description);
        obbject.setCategory(category);
        obbject.setUuser(uuser);
        em.persist(obbject);
        return obbject;
    }

    public void RemoveUuser(Uuser uuser){
        Uuser c0 = em.merge(uuser);
        em.remove(c0);
    }

    public Uuser getUuserByEmail(String email){
        
        Query q = em.createQuery("select c from Uuser c where c.email = :name");
        q.setParameter("name", email);
        return (Uuser)q.getSingleResult();
    }



    public void RemoveObbject(Obbject obbject){
        Obbject c0 = em.merge(obbject);
        em.remove(c0);
    }

    public Obbject getObbjectById(int id){
        Query q = em.createQuery("select c from Obbject c where c.id = :name");
        q.setParameter("name", id);
        return (Obbject)q.getSingleResult();
    }

    public boolean Lookup( Uuser uuser){
        Uuser c0 = em.merge(uuser);
        return c0.getRiight();
    }

    public void Update(Uuser uuser, boolean riight){
        Uuser c0 = em.merge(uuser);
        c0.setRiight(riight);
        em.persist(c0);
    }

    public List<String> ListAllUuser(){
	List<String> results= new ArrayList<String>();
	Query q = em.createQuery("select c.email from Uuser c");
	results=q.getResultList();
        return results;
    }
}







