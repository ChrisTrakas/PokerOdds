import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
	
	private static ArrayList<String> order= new ArrayList<>();
	private ArrayList<Card> cards;
	public HashMap< String , Integer> record;
	private String cardValue1;
	private String cardValue2;
	private String handName;
	
	public Hand(ArrayList<Card> cards) {
		this.cards = cards;
		findHandName();
		
	}
	
	
	public void findHandName() {
		
		if ( isStraightFlush() ) {
			
		}else if ( isFourOfaKind() ) {
			
		}else if ( isFullHouse() ) {
			
		}else if ( isFlush() ) {
			
		}else if ( isStraight() ) {
			
		}else if ( isThreeOfaKind()  ) {
			
		}else if ( isTwoPairs()  ) {
			
		}else if ( isPair()  ) {
			
		}else if (isHighCard() ) {
			
		}

	}
	

	
	public int getOrder() {
		
		if (order.isEmpty() ){
			setOrder();
		}
		
		return order.indexOf( handName );
		
	}
	
	
	
	public Boolean isStraight() {
		cardValue2 = "";
		
		if ( cards.get(0).getOrder()==0  && cards.get(1).getOrder()==1    &&  cards.get(2).getOrder()==2   &&  cards.get(3).getOrder()==3   
				&&  cards.get(4).getOrder()==12  ) {
			cardValue1 = "5";
			handName = "Straight";
			return true;
			
		}
		
		
		for ( Card c : cards) {
			
			if ( !( c.getOrder() - cards.get(0).getOrder()  ==  cards.indexOf(c)  )) {   //οσο προχωραμε προχωραει το ορντερ
				
				
				return false;
			}
			
		}
		cardValue1 = cards.get(4).getValue();
		handName = "Straight";
		return true;
	}
	

	
	
	public Boolean isFlush() {
		
		
		for ( Card c : cards) {
			
			if ( !( c.getSuit() .equals( cards.get(0).getSuit() ) )) {   //οσο προχωραμε προχωραει το ορντερ
				
				
				return false;
			}
		}
		handName = "Flush";
		cardValue1 = cards.get(4).getValue();
		cardValue2 = cards.get(3).getValue();
		return true;
			
	}
	
	
	
	
	
	public Boolean isStraightFlush() {
		
		if (  isFlush() && isStraight() ) {
			
			handName = "Straight Flush";
			return true;
		}
		
		return false;
	}
	
	
	
	
	public Boolean isFullHouse() {
		
		if ( record.containsValue(3) && record.containsValue(2)  ) {
			
			handName = "Full House";
			
			for ( String val : record.keySet() ) {
				
				if( record.get(val) == 3   ) {
					
					cardValue1 = val;
					
				}else {
					cardValue2 = val;
				}			
			}
			return true;
		}
		return false;
	}
	
	
	
	
	public Boolean isFourOfaKind() {
		valueRecord();
		
		if (  record.containsValue(4)  ) {
			handName = "Four of a Kind";
			
			for ( String val : record.keySet() ) {
				
				if( record.get(val) == 4   ) {
					
					cardValue1 = val;
					
				}else {
					cardValue2 = val;
				}			
			}
			return true;
		}				
		return false;
	}
	
	
	public Boolean isThreeOfaKind() {
		
		int maxOrder=-1;
		
		if ( record.containsValue(3)  ) {
			handName = "Three of a Kind";
			
			
			for ( String val : record.keySet() ) {
				
				if( record.get(val) == 3 )  {
					
					cardValue1 = val;
					
				}else {
					
					Card toCard = new Card(val);
					if ( toCard.getOrder() > maxOrder ) {
						maxOrder = toCard.getOrder();					//to find the order
						cardValue2 = val;
					}
				}			
			}
			return true;
		}				
		return false;
	}
	
	
	

	public Boolean isPair() {
		int maxOrder=-1;
		
		for ( String val : record.keySet() ) {
			
			if ( record.get(val) == 2) {
				cardValue1 = val;
				handName = "Pair";				
				return true;
			}else {
				
				Card toCard = new Card(val);
				if ( toCard.getOrder() > maxOrder ) {
					maxOrder = toCard.getOrder();					//to find the order
					cardValue2 = val;
				}
				
			}
		}	
		return false;
	}
	
	
	
	
	
	
	public Boolean isTwoPairs() {
		cardValue1 = "";
		cardValue2 = "";
						
		for ( String val : record.keySet() ) {
			
			if ( record.get(val) == 2) {
								
				if ( cardValue1 == "" ) {			
					
					cardValue1 = val;
								
				}else {
					
					handName = "Two Pairs";	
					Card toCard1 = new Card( val );
					Card toCard2 = new Card(cardValue1);
					
					if ( toCard1.getOrder() < toCard2.getOrder() )   {
						
						cardValue2 = val;
						
					}else {
						cardValue2 = cardValue1;
						cardValue1 = val;
					}
					return true;
				}								
			}			
		}
		return false;
	}
	
	
	public Boolean isHighCard() {
		
		if ( record.size() == 5  ) {
			
			String max1Value = "2";
			String max2Value = "2";
			
			for ( Card c : cards) {
				
				if ( c.getOrder() >= new Card(max1Value).getOrder() ) {
					
					max1Value = c.getValue();
					cardValue1 = c.getValue();
				}else if ( c.getOrder() >= new Card(max2Value).getOrder() )
				
					max2Value = c.getValue();	
					cardValue2 = c.getValue();
			}
			
			handName = "High Card";
			return true;
		}
		return false;
	}
	
	
	
	

	public HashMap< String , Integer> valueRecord() {
		
		record = new HashMap<String , Integer>();
	
		for (Card i : cards) {
			
			if ( !record.containsKey(i.getValue())) {
				
				record.put(i.getValue(), 0 );
				
				for (Card j : cards) {
					
					if( i.getValue() .equals( j.getValue() )   ) {
						
						record.put(i.getValue() , record.get(i.getValue() )+1 );
						
					}
				}			
			}
		
		}
		
		return record;

	}
	
	
	private void setOrder(){
		
		order.addAll( 
				Stream.of( "High-Card" , "Pair", "Two Pairs" , "Three-of-a-kind" , "Straight" , 
						"Flush " , "Full House" , "Four of a Kind" , "Straight Flush").collect(Collectors.toList())
				);
	}
	
	
	
	public String getCardValue1() {
		return cardValue1;
	}

	

	public String getCardValue2() {
		return cardValue2;
	}

	

	public String getHandName() {
		return handName;
	}


	}	




	class compareHands  implements Comparator<Hand>{
		
		@Override
		public int compare(Hand h1, Hand h2) {
			
			
			if (  h1.getOrder() > h2.getOrder() ) {
				return 1;
			}else if ( h1.getOrder() < h2.getOrder() ) {
				return -1;
				
			}else {
				
				if (  new Card(h1.getCardValue1()).getOrder() > new Card( h2.getCardValue1()).getOrder() ) {
					return 1;
				}else if (  new Card(h1.getCardValue1()).getOrder() < new Card(h2.getCardValue1()).getOrder() ){
					return -1;
					
				}else if ( !h1.getCardValue2().trim().equals("")  &&  !h2.getCardValue2().trim().equals("") ){
					
					if (  new Card(h1.getCardValue2()).getOrder() > new Card(h2.getCardValue2()).getOrder() ) {
						return 1;
					}else if (  new Card(h1.getCardValue2()).getOrder() < new Card(h2.getCardValue2()).getOrder() ){
						return -1;
						
					}else {
						
						return 0;
				
					}
					
					
				}else {
					
					
				return 0;
			}

			}

		}
	}
	