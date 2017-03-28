package client;

import javax.naming.InitialContext;
import javax.ejb.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import ejb.Directory;
import entity.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Future;
import java.text.DateFormat;
import java.util.Date;

public class ManagerClient {

	

	private String Connextion(Directory sb)
	{	
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please type your Email: ");
		String answer=null;
		try {
			answer =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(answer.compareToIgnoreCase("Admin")==0){
			System.out.println("You are Administrateur");
			System.out.println("1.AddUser, 2.RemoveUser, 3.AddObject, 4.RemoveObject");
		}
		else{	
			Uuser uuser;
			uuser=sb.getUuserByEmail(answer);
			System.out.println("Welcom: "+uuser.getFirstName()+" "+uuser.getLastName());
			System.out.println("1.StartBid, 2.Bid for Object");
		}
		return answer;
	}

	private void Adduser(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String FirstName=null;
		String LastName=null;
		String Description=null;
		String Email=null;
		System.out.println("FirstName: ");
		try {
			FirstName =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LastName: ");
		try {
			LastName =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Address: ");
		try {
			Description =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Email( necessary ): ");
		try {
			Email =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uuser uuser=sb.AddUuser(FirstName, LastName, Description, Email);
		System.out.println("You added an user: "+uuser.getFirstName()+" "+uuser.getLastName());
	}

	private void RemoveUser(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String Email=null;
		System.out.println("List of User Email for referrence ... ");
		List<String> results=new ArrayList<String>();
		results=sb.ListAllUuser();
		for (String element: results) {
         		System.out.println(element);		
     			 }
		System.out.println("Please input Email: ");	
		String[] keyWords={"=", "drop", "delete", "update", "insert"};
		int keng=0;
		while(keng!=-1){
			try {
				Email =br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			keng=-1;
			for (String element: keyWords) {
         				if(Email.indexOf(element )!=-1){
         					keng =0;
         				}
     			 }
     			 if(keng!=-1){
     			 	System.out.println("Your input contain some sensible word, You want to do bad things? please retype:");
     			 }
		}
		

		Uuser uuser=sb.getUuserByEmail(Email);
		System.out.println("You deleted user: "+uuser.getFirstName()+" "+uuser.getLastName());
		sb.RemoveUuser(uuser);
	}

	private void AddObject(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String Description=null;
		String Category=null;
		String Email=null;
		System.out.println("Description: ");
		try {
			Description =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Category: ");
		try {
			Category =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("User Email: ");
		try {
			Email =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uuser uuser=sb.getUuserByEmail(Email);
		Obbject obj= sb.AddObbject(Description,Category, uuser);
		System.out.println("You added an Object: "+obj.getDescription()+" \nFor user:"+uuser.getFirstName()+" "+uuser.getLastName());
	}

	private void RemoveObject(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		int id=0;;
		System.out.println("Please input Object id: ");
		try {
			id = Character.getNumericValue((br.readLine().charAt(0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(id);
		Obbject obj = sb.getObbjectById(id);
		System.out.println("You deleted Obejct: "+obj.getDescription());
		sb.RemoveObbject(obj);
	}

	private void LooksUp(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String Email=null;
		System.out.println("Please input user Email: ");
		try {
			Email =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uuser uuser=sb.getUuserByEmail(Email);
		boolean hello =sb.Lookup(uuser);
		System.out.println("User: "+uuser.getFirstName()+" "+uuser.getLastName()+"\nRight for auction: "+hello);
	}

	private void Upsdate(Directory sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String Email=null;
		System.out.println("Please input user Email: ");
		try {
			Email =br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uuser uuser=sb.getUuserByEmail(Email);
		boolean hello =sb.Lookup(uuser);
		System.out.println("User: "+uuser.getFirstName()+" "+uuser.getLastName()+"\nRight for auction: "+hello);
		System.out.println("You want to update it to "+(!hello)+"? type <yes> or <no>, then return");
		try {
			Email=br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(Email.compareToIgnoreCase("yes")==0){
			sb.Update(uuser, (!hello));
			System.out.println("User: "+uuser.getFirstName()+" "+uuser.getLastName()+"\n Right for auction( updated ): "+(!hello));
		}
		else{
			;
		}

	}

	public static void main(String args[]) {
		Directory sb;
		Uuser uuser;
		Obbject obbj;
		Bid bid;
		String userIden;


	        try {	

			InitialContext ic = new InitialContext();
			sb = (Directory) ic.lookup("ejb.Directory");
			ManagerClient jc = new ManagerClient();

			System.out.println("You are Administrateur");
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(System.in));
			int order=0;
			while(order!=7){
				System.out.println("1.AddUser, 2.RemoveUser, 3.AddObject, 4.RemoveObject, 5.LookUpUserRight, 6.UpdateUserRight, 7.End Pro");
				try {
					order =Character.getNumericValue((br.readLine().charAt(0)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(order==1){
					jc.Adduser(sb);
				}
				else if(order==2){
					jc.RemoveUser(sb);
				}
				else  if(order==3){
					jc.AddObject(sb);
				}
				else if(order==4){
					jc.RemoveObject(sb);
				}
				else if(order==5){
					jc.LooksUp(sb);
				}
				else if(order==6){
					jc.Upsdate(sb);
				}
				else{
					System.out.println("error input, type [1-7] please");
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

