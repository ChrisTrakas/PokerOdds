import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
	private static ArrayList<Card> wholeDeck= new ArrayList<>();
	private static ArrayList<String> order= new ArrayList<>();
	private static ArrayList<String> suits= new ArrayList<>();
	private String rawCard;
	
	
	public Card(String rawCard) {
		this.rawCard = rawCard;

	}
	
	
	public String getValue() {
		
		if ( rawCard.substring(0, 1).equals("1") ) {
			
			return "10";
		}
		
		return rawCard.substring(0, 1);
	}
	
	public String getRawCard() {
		return rawCard;
	}
	
	public String getSuit() {
		
		if (getValue().equals("10") ) {
			return rawCard.substring(2);
		}
		return rawCard.substring(1);
		
	}
	
	
	public int getOrder() {
		if (order.isEmpty() ){
			setOrder();
		}
		
		return order.indexOf( getValue());
		
	}
	
	public static ArrayList<Card> getWholeDeck() {
		if ( wholeDeck.isEmpty() ) {
			setWholeDeck();
		}
		
		return wholeDeck;
		
	}
	
	private static void setOrder(){
		
		if ( order.isEmpty() ) {
			order.addAll( 
					Stream.of( "2", "3" ,"4" ,"5" ,"6" , "7", "8" , "9" , "10" , "J" , "Q" , "K" , "A").collect(Collectors.toList())
					);
		}
	}
	
	public boolean isSame( Card c2) {
		
		return   ( this.rawCard.equals(c2.getRawCard()));                              //( this.getValue().equals(c2.getValue())  &&  this.getSuit().equals(c2.getSuit())  );	
	}
	
	
	public static void setWholeDeck() {
		setOrder();
		suits.addAll( 
				Stream.of( "C" , "S" , "H" , "D" ).collect(Collectors.toList())
				);
		
		
		for ( String suit : suits) {
			for (String value : order ) {
				wholeDeck.add( new Card(value+suit));
			}
		}
	
	}
	
	
	
	public static ArrayList<Card> stringToCards( String cardString) {
		ArrayList<Card> array= new ArrayList<>();
		

		for ( int i=0; i<=cardString.length()-1 ; i+=2) {
			
			if ( cardString.substring(i, i+2).equals("10")) {
				array.add(new Card ( cardString.substring(i, i+3)   ) );
				i+=1;
			}else {
				array.add(new Card ( cardString.substring(i, i+2)   ) );
			}
			
		}
		
		
		return array;
	}
	
	
	
}
