
public class HUI {
	private int len;
	private char number[];
	
	HUI(String input){
		len = input.length();
		number = new char[len];
		praseInput(input);
		
	}
	
	private void praseInput(String input){
		int backCounter = len-1;
		
		for(int i = 0;i<len;i++){
			number[backCounter] = input.charAt(i);
			backCounter--;
		}
	}
	
	
	
	private void display(){
		for(int i = 0;i<len;i++){
			System.out.printf("%c",number[i]);
		}
	}
	private void R_display(){
		for(int i = len-1;i>=0;i--){
			System.out.printf("%c",number[i]);
		}
	}
	
	private char getNumAtIndex(int index){
		if(index > len-1){
			return 'F';
		}
		return number[index];
	}
	
	public int getNumLength(){
		return len;
	}
	
	
	public HUI addition(HUI input1){
		int sum[];
		int len1 = input1.getNumLength();
		if(len1>=len){
			sum = new int[len1];
		}
		else{
			sum = new int[len];
		}
		int sumLen = sum.length;
		
		int carry =0;
		
		for(int i = 0;i<sumLen;i++){
			
			if(i>= len){
				sum[i] = Character.getNumericValue(input1.getNumAtIndex(i)) + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else if(i>= len1){
				sum[i] = Character.getNumericValue(number[i]) + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else{
				sum[i] = Character.getNumericValue(number[i]) + Character.getNumericValue(input1.getNumAtIndex(i)) + carry;
				//System.out.printf("%d" ,sum[i]);
			}
		
			
			carry = 0;
			if(sum[i] >= 10){
				carry++;
				sum[i] = sum[i]%10;
			}
		}
		

		
		String result="";
		if(carry >= 1){
			result += Integer.toString(carry);
			for(int i = sum.length-1;i>=0;i--){
				result += Integer.toString(sum[i]);
				
			}
		}
		else{
			for(int i = sum.length-1;i>=0;i--){
				result += Integer.toString(sum[i]);
			}
		}

		HUI returnSum = new HUI(result);

		
		return returnSum;
		
	}
	
	
	
	public HUI addition(HUI input1, int input2){
		
		return input1;
		
	}
	
	public static void main(String[] args) {
		
		HUI test = new HUI("1529");
		HUI test2 = new HUI("1191981");
		HUI sum = test.addition(test2);
		sum.R_display();
	}

}


