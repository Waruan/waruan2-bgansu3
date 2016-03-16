/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * TwoRadioButtonPanel.java:  Class that deals with checking for prime or coPrime.
 */

public class Utilities {

  public static boolean isPrime(HUI x){
	  if(x.lessThanOrEqual(1))
	    return false;
	  
		HUI half = x.division(2);
		int i = 2;
		while(half.greaterThan(i)){
			
			HUI modResult = x.Mod(i);
			if(modResult.HUIequals(0)){
				
				return false;
			}
			i++;
		}
		return true;	
	}
	
	public static boolean coPrime(HUI a,HUI b){
		if(gcd(a,b).HUIequals(1)){
			return true;
		}
		return false;
	}

	public static HUI gcd(HUI a,HUI b){
		if(b.HUIequals(0)){
			return a;
		}
		else{
			return gcd(b,(a.Mod(b)));
		}	
	}
}
