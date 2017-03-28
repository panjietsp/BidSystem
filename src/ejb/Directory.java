package ejb;

import javax.ejb.Remote;
import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.AsyncResult;
import entity.*;
import ejb.*;
import java.util.ArrayList;
import java.util.List;

@Remote 
public interface Directory {

    public Uuser AddUuser(String first, String last, String address, String email);
    public void RemoveUuser(Uuser uuser);
    public Uuser getUuserByEmail(String email);
    public Obbject AddObbject(String description, String category, Uuser uuser);
    public void RemoveObbject(Obbject obbject);
    public Obbject getObbjectById(int id);
    public boolean Lookup( Uuser uuser);
    public void Update(Uuser uuser, boolean riight);
    public List<String> ListAllUuser();
}
