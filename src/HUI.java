
public class HUI {
	private int len;
	private int number[];
	
	HUI(String input){
		len = input.length();
		input = checkInput(input);
		number = new int[len];
		praseInput(input);
		
	}
	private String checkInput(String input){
		int counter = 0;
		String temp="";
		while(counter < len && Character.getNumericValue(input.charAt(counter)) == 0){
			counter++;
		}
		if(counter == len){
			len = 1;
			temp ="0";
		}else{
			
			for(int i = counter;i<len;i++){
				temp += input.charAt(i);
			}
			len = temp.length();
		}
			
		return temp;
	}
	private void praseInput(String input){
		int backCounter = len-1;
		
		for(int i = 0;i<len;i++){
			number[backCounter] = Character.getNumericValue(input.charAt(i));
			backCounter--;
		}
	}
	
	private void display(){
		for(int i = 0;i<len;i++){
			System.out.printf("%d",number[i]);
		}
		System.out.printf("\n");
	}
	private void R_display(){
		for(int i = len-1;i>=0;i--){
			System.out.printf("%d",number[i]);
		}
		System.out.printf("\n");
	}
	
	public int getNumAtIndex(int index){
		if(index > len-1){
			return -1;
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
				sum[i] = input1.getNumAtIndex(i) + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else if(i>= len1){
				sum[i] = number[i] + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else{
				sum[i] = number[i] + input1.getNumAtIndex(i) + carry;
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
	

	public HUI subtraction(HUI input){
		boolean borrowflag = false;
		if(len < input.getNumLength()){
			//System.out.printf("Negative number \n");
			//System.exit(0);
			//change to exception later
			HUI negative = new HUI("-");
			return negative;
		}
		if(this.lessThan(input) == true){
			HUI negative = new HUI("-");
			return negative;
		}
		int difference[]= new int[len];
		for(int i=0;i<len;i++){
			
			if(input.getNumAtIndex(i)==-1){
				difference[i] = number[i];
			}
			else if(input.getNumAtIndex(i)>number[i]){
				int counter = i;

				number[i] = number[i] +10;
				number[i+1] = number[i+1]-1;

				
				if(number[i+1] == -1){
					borrowflag = true;
					while(borrowflag == true && counter != len){
						number[counter+1] = number[counter+1] +10;
						number[counter+2] = number[counter+2]-1;
		
						if(number[counter+2] != -1){
							borrowflag = false;
						}
						if(number[len-1] == -1){
							System.out.println("Neagtive number from subtraction");
							System.exit(0);
						}
						counter++;
					}
				}
				
				difference[i] = number[i]-input.getNumAtIndex(i);
				if(difference[i]<0 || difference[i]>=10){
					System.out.println("Error with subtraction");
					System.exit(0);
				}
			}
			else{
				difference[i] = number[i]-input.getNumAtIndex(i);
			}

		}
		
		boolean zeroflag;
		if(difference[len-1] == 0){
			zeroflag = true;
		}
		else{
			zeroflag = false;
		}
		int resultLength = len;
		while(zeroflag == true && resultLength >=0){
			if(resultLength == 0){
				resultLength--;
			}
			else if(difference[resultLength-1] != 0){
				zeroflag = false;
			}else{
				resultLength--;
			}
		}
		String result ="";
		if(resultLength == -1){
			result = "0";
		}
		else{
			for(int i = resultLength-1;i>=0;i--){
				result += Integer.toString(difference[i]);
			}
		}
		HUI returndifference = new HUI(result);

		
		return returndifference;
	}
	/*public HUI multiplication(HUI input){
		
		for(int i = input.getNumLength()-1;i>=0;i--){
			String signifcant = "1";
			for(int j=i;j>0;j--){
				signifcant+="0";
			}
			HUI multipler = new HUI(signifcant);
			
			
		}
		return input;
		
	}*/
	
	public HUI division(HUI input){
		if(len < input.getNumLength()){
			System.out.println("Divider is greater than number");
			System.exit(0);
		}
		//horrible way
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		int counter = 0;
		HUI tmp = this;
		while(zero.Equals(tmp) == false){
			//tmp.R_display();
			tmp = tmp.subtraction(input);
			
			if(tmp.Equals(negative)){
				tmp = zero;
			}
			else{
				counter++;
			}
		}
		String result = Integer.toString(counter);
		HUI difference = new HUI(result);
		return difference;
		
	}
	
	public HUI Mod(HUI input){
		if(len < input.getNumLength()){
			return this;
		}
		//horrible way
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		HUI tmp = this;
		HUI tracker = tmp;
		while(zero.Equals(tmp) == false){
			tracker = tmp;
			tmp = tmp.subtraction(input);
			if(tmp.Equals(negative)){
				tmp = zero;
			}
			else if(tmp.Equals(zero)){
				tracker = tmp;
			}
		}
		
		return tracker;
		
	}
	
	public boolean Equals(HUI input){
		
		if(input.getNumLength() == len ){
			for(int i=0;i<len;i++){
				if(number[i] != input.getNumAtIndex(i)){
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	public boolean greaterThanOrEqual(HUI input){
		if(this.Equals(input)){
			return true;
		}
		
		return (!(this.lessThan(input)));
	}
	
	public boolean lessThanOrEqual(HUI input){
		if(this.Equals(input)){
			return true;
		}
		
		return (this.lessThan(input));
	}
	
	public boolean greaterThan(HUI input){
		return (!(this.lessThan(input)));
	}
	
	
	public boolean lessThan(HUI input){
		
		if(input.getNumLength()>len){
			return true;
		}
		else if(input.getNumLength()<len){
			return false;
		}
		else{
			if(this.Equals(input)){
				return false;
			}
			for(int i =len-1;i>=0;i--){
				if(number[i]<input.getNumAtIndex(i)){
					return true;
				}
				else if(number[i]>input.getNumAtIndex(i)){
					return false;
				}
			}
		}
		
		return false;
		
	}
	
	
	public HUI multiplication(HUI input){
		HUI resultHUI = new HUI("0");
		for(int i=0;i<input.getNumLength();i++){
			int tmpResult [] = new int [len+1];
			int carry=0;
			int reminder=0;
			for(int j=0;j<len;j++){
				int value = (number[j] * input.getNumAtIndex(i))+carry;
				carry = value /10;
				reminder = value %10;
				tmpResult[j] = reminder;
			}
			
			String result ="";
			if(carry >= 1){
				result += Integer.toString(carry);
				for(int j = tmpResult.length-1;j>=0;j--){
					result += Integer.toString(tmpResult[j]);
					
				}
			}
			else{
				for(int j = tmpResult.length-1;j>=0;j--){
					result += Integer.toString(tmpResult[j]);
				}
			}
			for(int j=0;j<i;j++){
				result += "0";
			}
			
			HUI tmpHUI = new HUI(result);
			resultHUI = resultHUI.addition(tmpHUI);
			
		}
		return resultHUI;
	}	
	
	public static void main(String[] args) {
		//throw away 0
		HUI test = new HUI("1");
		HUI test2 = new HUI("3");
		//HUI sum = test.subtraction(test2);
		//sum.R_display();
		
		
		HUI test3 = new HUI("342");
		HUI test4 = new HUI("12");
		HUI diff = test3.division(test4);
		//diff.R_display();
		HUI test5 = new HUI("-");
		//test5.R_display();
		
		HUI test6 = new HUI("12");
		boolean temp = test6.lessThanOrEqual(new HUI("11"));
		System.out.println(temp);
		HUI tmp = test6.Mod(new HUI("10"));
		tmp.R_display();
		
		
	}

}


