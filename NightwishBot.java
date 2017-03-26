/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package nightwishbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author gregoryking
 */
public class NightwishBot {
    
    public static void main(String[] args) throws TwitterException, FileNotFoundException, InterruptedException    { //throws TwitterException 

        String consumerKey= "YOUR_KEY";
	String consumerSecret="YOUR_SECRET_KEY";
	String accessToken="YOUR_ACCESS_TOKEN";
	String accessTokenSecret="YOUR_SECRET_TOKEN";
		
	TwitterFactory twitterFactory = new TwitterFactory();
	Twitter twitter = twitterFactory.getInstance();
	
	twitter.setOAuthConsumer (consumerKey, consumerSecret) ;
	twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        
        //it runs forever. And ever. And ever. 
        boolean check = true;
        while ( check = true) {
	    
	    tweet(twitter); //That's all folks 
            
        }
    }
        
        public static String randomizer() throws FileNotFoundException{

	//Input Section
	File infile = new File("lyrics_main.txt"); //1715 lines
		
	//Reservoir Sampling courtesy of:
	//http://stackoverflow.com/questions/2218005/how-to-get-a-random-line-of-a-text-file-in-java 
	String result = null;
	Random rand = new Random();
	int n = 0;
	for(Scanner sc = new Scanner(infile); sc.hasNext();){
	     ++n;
	        
	    String line = sc.nextLine();
	        
	     if(rand.nextInt(n) == 0 && !line.isEmpty())
	     result = line;         
	     }
	return result;      
    }
        
    private static void tweet(Twitter twitter) throws FileNotFoundException, InterruptedException, TwitterException {
        //get working directory
        String directory = System.getProperty("user.dir");
        

        //image decision
        Random random = new Random();
        int num = random.nextInt(44); //44 pictures in the picture library thus far
        
        //image addition
        String pic = (directory + "/pics/" + num + ".jpeg");
        File file = new File(pic); 

        //Tweet Tweet!! :) :) 
        String statusMessage = (randomizer());
        StatusUpdate status = new StatusUpdate(statusMessage);
        status.setMedia(file); // set the image to be uploaded here.
        twitter.updateStatus(status);
        
        //confirmation and waiting
        System.out.println("Tweeted \"" + statusMessage+ "\" "+ file + " "  +" at " + LocalDateTime.now() + ".");

        //sleep Minutes * seconds * milliseconds
        Thread.sleep(60*60*1000);
    }
    
}

