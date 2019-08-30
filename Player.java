import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
	private ArrayList<Card> pocketCards;
	private ArrayList<Card> allCards;
	private ArrayList<Card> downCards;
	private Hand hand = null;
	private int wins=0;
	
	public Player(  ArrayList<Card> pocketCards ) {
		
		this.pocketCards = new ArrayList<>(pocketCards);
		
	}
	
	
	public void newRound( ArrayList<Card> downCards ) {
		this.downCards = downCards;
		sortCards( downCards );
		findHand();
		
	}
	
	
	private void sortCards( ArrayList<Card> downCards ){
		
		allCards = new ArrayList<>(pocketCards);
		allCards.addAll(downCards);
		
		
		
		Collections.sort(allCards , 
                (o1, o2) -> Integer.compare(o1.getOrder(), o2.getOrder()) );

	}
	
	
	
	
	private void findHand() {
		
		Hand biggestHand=null ;
		Hand currentHand;
		ArrayList<Card> currentCards= new ArrayList<>();
		
		for ( Card i : downCards ) {
						
				for( Card j : downCards ) {
					
					if ( allCards.indexOf(i) < allCards.indexOf(j)  ) {
						
						
						currentCards = new ArrayList<>(allCards);
						currentCards.remove(i);
						currentCards.remove(j);
						currentHand = new Hand( currentCards );
						
						
						if ( biggestHand == null || new compareHands().compare(currentHand  , biggestHand ) > 0) {
							
							biggestHand = currentHand;				
							
						}
						
						
					}
					
				}	
				
				
			
		}
		
		
		hand = biggestHand;
		
		
		
	}
	
	
	public boolean holdsCard( Card c) {
		
		
		return c.isSame( pocketCards.get(0)) || c.isSame(pocketCards.get(1)) ;
	}
	
	
	
	
	public Hand getHand() {
		
		
		findHand();
		
		
		return hand;
	}
	
	
	public int getWins() {
		return wins;
	}
	
	public void newWin() {
		wins++;
	}
	
}
