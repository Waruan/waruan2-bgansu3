
public class HUI {
	private int len;
	private int number[];
	private String text;
	HUI(String input){
		text = input;
		len = input.length();
		input = checkInput(input);
		number = new int[len];
		praseInput(input);
		
	}
	public String getString(){
		return text;
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
	
	public void display(){
		for(int i = 0;i<len;i++){
			System.out.printf("%d",number[i]);
		}
		System.out.printf("\n");
	}
	public void R_display(){
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
	

	
	
	public HUI addition(HUI input){
		int sum[];
		int len1 = input.getNumLength();
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
				sum[i] = input.getNumAtIndex(i) + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else if(i>= len1){
				sum[i] = number[i] + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else{
				sum[i] = number[i] + input.getNumAtIndex(i) + carry;
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
	
	public HUI addition(int num){
		HUI input = new HUI(Integer.toString(num) );
		int sum[];
		int len1 = input.getNumLength();
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
				sum[i] = input.getNumAtIndex(i) + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else if(i>= len1){
				sum[i] = number[i] + carry;
				//System.out.printf("%c" ,sum[i]);
			}
			else{
				sum[i] = number[i] + input.getNumAtIndex(i) + carry;
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
	
	
	public HUI subtraction(int num){
		
		HUI input = new HUI(Integer.toString(num) );
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
	

	public HUI division(HUI input){
		HUI tracker;
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		HUI reminder = null;
		String numChunck = "";
		int counter = 0;
		int[] result =  new int[len]; 
		for(int i = 0;i<len;i++){
			result[i] = 0;
		}
		
		
		for(int i = len-1;i>=0;i--){
			
			numChunck += Integer.toString(this.number[i]);
			tracker = new HUI(numChunck);
			
			if(tracker.greaterThanOrEqual(input)){
				while(zero.HUIequals(tracker) == false){
					reminder = tracker;
					tracker = tracker.subtraction(input);
					if(tracker.HUIequals(negative)){
						tracker = zero;
					}
					else if(tracker.HUIequals(zero)){
						reminder = tracker;
						counter++;
					}
					else{
						counter++;
					}
				}
				result[i] = counter;
				//System.out.println(result[(len-1)-i]);
				counter = 0;
				numChunck="";
				if(!(reminder.HUIequals(zero))){
					for(int j = reminder.getNumLength()-1;j>=0;j--){
						numChunck += Integer.toString(reminder.getNumAtIndex(j));
					}
				}
			}
			
		}
			numChunck = "";
			for(int i = len-1;i>= 0;i--){
				numChunck += Integer.toString(result[i]);
				//System.out.println(result[i]);
			}
		
		HUI quo = new HUI(numChunck);
		
		return quo;
		
	}
	
	
	public HUI division(int num){
		HUI input = new HUI(Integer.toString(num) );
		HUI tracker;
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		HUI reminder = null;
		String numChunck = "";
		int counter = 0;
		int[] result =  new int[len]; 
		for(int i = 0;i<len;i++){
			result[i] = 0;
		}
		
		
		for(int i = len-1;i>=0;i--){
			
			numChunck += Integer.toString(this.number[i]);
			tracker = new HUI(numChunck);
			
			if(tracker.greaterThanOrEqual(input)){
				while(zero.HUIequals(tracker) == false){
					reminder = tracker;
					tracker = tracker.subtraction(input);
					if(tracker.HUIequals(negative)){
						tracker = zero;
					}
					else if(tracker.HUIequals(zero)){
						reminder = tracker;
						counter++;
					}
					else{
						counter++;
					}
				}
				result[i] = counter;
				//System.out.println(result[(len-1)-i]);
				counter = 0;
				numChunck="";
				if(!(reminder.HUIequals(zero))){
					for(int j = reminder.getNumLength()-1;j>=0;j--){
						numChunck += Integer.toString(reminder.getNumAtIndex(j));
					}
				}
			}
			
		}
			numChunck = "";
			for(int i = len-1;i>= 0;i--){
				numChunck += Integer.toString(result[i]);
				//System.out.println(result[i]);
			}
		
		HUI quo = new HUI(numChunck);
		
		return quo;
		
	}
	
	
	
	public HUI Mod(HUI input){
		HUI tracker;
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		HUI reminder = null;
		String numChunck = "";
		int counter = 0;
		int[] result =  new int[len]; 
		for(int i = 0;i<len;i++){
			result[i] = 0;
		}
		if(this.lessThan(input)){
			return this;
		}
		
		for(int i = len-1;i>=0;i--){
			
			numChunck += Integer.toString(this.number[i]);
			tracker = new HUI(numChunck);
			reminder = tracker;
			if(tracker.greaterThanOrEqual(input)){
				while(zero.HUIequals(tracker) == false){
					reminder = tracker;
					tracker = tracker.subtraction(input);
					if(tracker.HUIequals(negative)){
						tracker = zero;
	
					}
					else if(tracker.HUIequals(zero)){
						reminder = tracker;
						counter++;
					}
					else{
						counter++;
					}
				}
				result[i] = counter;
				//System.out.println(result[(len-1)-i]);
				counter = 0;
				numChunck="";
				if(!(reminder.HUIequals(zero))){
					for(int j = reminder.getNumLength()-1;j>=0;j--){
						numChunck += Integer.toString(reminder.getNumAtIndex(j));
					}
				}
			}
			
		}
		numChunck = "";
		for(int i = len-1;i>= 0;i--){
			numChunck += Integer.toString(result[i]);
			//System.out.println(result[i]);
		}
		
		
		return reminder;
		
	}

	public HUI Mod(int num){
		HUI input = new HUI(Integer.toString(num) );
		HUI tracker;
		HUI zero = new HUI("0");
		HUI negative = new HUI("-");
		HUI reminder = null;
		String numChunck = "";
		int counter = 0;
		int[] result =  new int[len]; 
		for(int i = 0;i<len;i++){
			result[i] = 0;
		}
		if(this.lessThan(input)){
			return this;
		}
		
		for(int i = len-1;i>=0;i--){
			
			numChunck += Integer.toString(this.number[i]);
			tracker = new HUI(numChunck);
			reminder = tracker;
			if(tracker.greaterThanOrEqual(input)){
				while(zero.HUIequals(tracker) == false){
					reminder = tracker;
					tracker = tracker.subtraction(input);
					if(tracker.HUIequals(negative)){
						tracker = zero;
					}
					else if(tracker.HUIequals(zero)){
						reminder = tracker;
						counter++;
					}
					else{
						counter++;
					}
				}
				result[i] = counter;
				//System.out.println(result[(len-1)-i]);
				counter = 0;
				numChunck="";
				if(!(reminder.HUIequals(zero))){
					for(int j = reminder.getNumLength()-1;j>=0;j--){
						numChunck += Integer.toString(reminder.getNumAtIndex(j));
					}
				}
			}
			
		}
		numChunck = "";
		for(int i = len-1;i>= 0;i--){
			numChunck += Integer.toString(result[i]);
			//System.out.println(result[i]);
		}
		
		
		return reminder;
		
	}
	

	public boolean HUIequals(HUI input){
		
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
	
	public boolean HUIequals(int num){
		
		HUI input = new HUI(Integer.toString(num) );
		
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
		if(this.HUIequals(input)){
			return true;
		}
		
		return (!(this.lessThan(input)));
	}

	public boolean greaterThanOrEqual(int input){
		if(this.HUIequals(input)){
			return true;
		}
		
		return (!(this.lessThan(input)));
	}
	public boolean lessThanOrEqual(HUI input){
		if(this.HUIequals(input)){
			return true;
		}
		
		return (this.lessThan(input));
	}
	
	public boolean lessThanOrEqual(int num){
		HUI input = new HUI(Integer.toString(num) );	
		if(this.HUIequals(input)){
			return true;
		}
		
		return (this.lessThan(input));
	}
	
	public boolean greaterThan(HUI input){
		return (!(this.lessThan(input)));
	}
	public boolean greaterThan(int num){
		
		HUI input = new HUI(Integer.toString(num) );	
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
			if(this.HUIequals(input)){
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
	
	public boolean lessThan(int num){
		HUI input = new HUI(Integer.toString(num) );
		if(input.getNumLength()>len){
			return true;
		}
		else if(input.getNumLength()<len){
			return false;
		}
		else{
			if(this.HUIequals(input)){
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
			int tmpResult [] = new int [len];
	
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
				System.out.println(result);
				for(int j = tmpResult.length-1;j>=0;j--){
					result += Integer.toString(tmpResult[j]);
					//System.out.println(tmpResult[j]);
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
			//System.out.println(result);
			HUI tmpHUI = new HUI(result);
			
			resultHUI = resultHUI.addition(tmpHUI);
			
		}
		return resultHUI;
	}	

	
	public HUI multiplication(int num){
		HUI input = new HUI(Integer.toString(num) );	
		HUI resultHUI = new HUI("0");
		for(int i=0;i<input.getNumLength();i++){
			int tmpResult [] = new int [len];
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
	/*	HUI test = new HUI("1");
		HUI test2 = new HUI("3");
		//HUI sum = test.subtraction(test2);
		//sum.R_display();
		
		
		HUI test3 = new HUI("342");
		HUI test4 = new HUI("12");
		HUI diff = test3.division(test4);
		//diff.R_display();
		HUI test5 = new HUI("-");
		//test5.R_display();
		
		HUI test6 = new HUI("100000000");
		boolean temp = test6.lessThanOrEqual(new HUI("2"));
		System.out.println(temp);
		HUI tmp = test6.Mod(new HUI("2"));
		tmp.R_display();*/
		
		//HUI result = test.division(new HUI("3"));
		/*System.out.println("Main");
		HUI test = new HUI("16420");
		HUI tmp = test.Mod(new HUI("2"));
		tmp.R_display();*/
		
		
		//result.R_display();
		boolean coPrime =  Utilities.coPrime(new HUI("6"),new HUI("25"));
		System.out.println(coPrime);
		boolean prime =  Utilities.isPrime(new HUI("16420"));
		System.out.println(prime);
		HUI test3 = new HUI("300");
		test3 = test3.multiplication(new HUI("125"));
		String temp = test3.getString();
		System.out.println(temp);

		
	}

}


