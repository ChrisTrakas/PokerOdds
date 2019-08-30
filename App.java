import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
	
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Card> pair= new ArrayList<>();
		ArrayList<Card> downCards = new ArrayList<>();
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Integer> wins = new ArrayList<>();
		ArrayList<Card> untouchedCards = new ArrayList<>();
		Hand highestHand;
		Player currentWinner=null;
		
		System.out.println( "Give the 3 downcards" );
		String cardsString = scan.nextLine();
		downCards =  Card.stringToCards( cardsString) ;
		for (Card v : downCards) {
			
		}
		
		
		for ( int i=0 ; i <4 ;i++) {
			System.out.println( "Give the player " + ( i+1) + " cards" );
			cardsString = scan.nextLine();
			pair.clear();
			players.add(new Player( Card.stringToCards( cardsString) ));
		}
		

		
		
		
		for ( Card c1 : Card.getWholeDeck() ) {
			untouchedCards.add(c1);
			

			
			for ( Player p : players ) {						//extracting the used cards from the rest of the deck
				
				if (  p.holdsCard(c1) ) {
					untouchedCards.remove(c1);
					break;
				}			
			}
			
			
			for (Card c2 : downCards) {
				if(c1.isSame(c2)) {
					untouchedCards.remove(c1);
					break;
				}
				
			}
			
		}
		
		
		
		
		
		for ( Card c1 : untouchedCards) {	
			downCards.add(c1);
			for ( Card c2 : untouchedCards ) {
				downCards.add(c2);
				highestHand = null;
				
				if ( untouchedCards.indexOf(c1) < untouchedCards.indexOf(c2) ) {
					
					
					
					for ( Player p : players) {			
						p.newRound(downCards);
						
						if ( highestHand == null || new compareHands().compare(p.getHand()  , highestHand ) > 0) {	
							highestHand = p.getHand();	
							currentWinner = p;
							
						}

					}
					
					
					currentWinner.newWin();
			
					
				}	
				downCards.remove(c2);
			}
			downCards.remove(c1);
		}
		
		
		System.out.println( "\nOdds to win:\n" );
		for ( Player p : players) {			
			System.out.println( "Player " + (players.indexOf(p)+1) + ":  " + (float)p.getWins()/820 *100   + "%");
		}
		
		
		
    }
	
	//for( Card c2 : untouchedCards.subList( untouchedCards.indexOf(c1), untouchedCards.size()-1) ) {
		
		
	
	
	public void game() {
		
		
	}
	
}
