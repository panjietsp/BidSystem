package client;

import javax.naming.InitialContext;
import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.Auction;
import entity.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Future;
import java.text.DateFormat;
import java.util.Date;

public class AuctionClient {

	private void AddABid(Auction sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		int id=0;
		int price=0;
		int duration=0;
		int increment=0;
		System.out.println("Please input Object id: ");
		try {
			id =Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(id);
		Obbject obj = sb.getObbjectById(id);
		System.out.println("Your obejct: "+obj.getDescription());
		System.out.println("Starting Price: ");
		try {
			price =Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Duration of bid: ");
		try {
			duration =Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("increment of evry bid: ");
		try {
			increment =Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bid bbs=sb.AddBid(obj, price, duration, increment);
		System.out.println("You added a bid: "+obj.getDescription()+" \nStarting price:"+bbs.getStartingPrice()+"\nDuration: "+bbs.getDuration()+"\nIncrement: "+bbs.getIncrement());
	}

	private void StartsBid(Auction sb){
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		int id=0;
		char bitss='a';
		System.out.println("Please input Bid id: ");
		try {
			id =Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bid bis=sb.getBidById(id);
		System.out.println("BidState: "+bis.getState()+" ...");

		sb.StartBid(bis);
		bis=sb.getBidById(id);
		System.out.println("BidState: "+bis.getState()+" ...");
		long times=bis.getDuration();
		DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
        		long time1= (new Date()).getTime( )/1000;
		Future future = sb.Inbid(bis);
		while (!future.isDone()){
	                	System.out.println("enter <b> for bid, <i>for info, then return: ");
			try {
				bitss =br.readLine().charAt(0);
			     } catch (IOException e) {
				e.printStackTrace();
			     }
			     if(bitss=='b'){
			     	bis=sb.getBidById(id);
			     	sb.BidOnce(bis);
			     }
			     if(bitss=='i'){
			     	bis=sb.getBidById(id);
			     	long time2= (new Date()).getTime( )/1000;

			     	System.out.println("Time rest: "+(int)(times-(time2-time1)));
			     	System.out.println("Price now: "+bis.getEndingPrice());

			     }

           		 }
           		sb.EndBid(bis);
           		bis=sb.getBidById(id);
		System.out.println("BidState: "+bis.getState()+" ...");
		//System.out.println("Your Bid has started, and it will end in "+bis.getDuration()+" minutes");
	}
	public static void main(String args[]) {
		Auction sb;
		Uuser uuser;
		Obbject obbj;
		Bid bid;
		String userIden;

	        try {
			InitialContext ic = new InitialContext();
			sb = (Auction) ic.lookup("ejb.Auction");

			AuctionClient jc = new AuctionClient();

			/*--------------------------------------*/

			System.out.println("You are Normal user");
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(System.in));
			int order=0;
			while(order!=3){
				System.out.println("1.Add a bid, 2.Start a bid,  3.End Pro");
				try {
					order =Character.getNumericValue((br.readLine().charAt(0)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(order==1){
					jc.AddABid(sb);
				}
				else if(order==2){
					jc.StartsBid(sb);
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

