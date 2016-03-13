
public class Utilities {
	
	public static boolean isPrime(HUI x){
		HUI half = x.division(2);
		int i = 2;
		while(half.greaterThan(i)){
			
			HUI modResult = x.Mod(i);
			if(modResult.Equals(0)){
				
				return false;
			}
			i++;
		}
		return true;	
	}
	
	public static boolean coPrime(HUI a,HUI b){
		if(gcd(a,b).Equals(1)){
			return true;
		}
		return false;
	}

	public static HUI gcd(HUI a,HUI b){
		if(b.Equals(0)){
			return a;
		}
		else{
			return gcd(b,(a.Mod(b)));
		}	
	}
}