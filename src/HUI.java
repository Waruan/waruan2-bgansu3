
public class HUI {
	private int len;
	private int number[]; 
	private String string;
	
	HUI(String input){
		len = input.length();
		input = checkInput(input);
		number = new int[len];
		string = input;
		praseInput(input);
		
	}
	
	//check length of input and remove leading zero
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
	
	// parse the input string into array
	private void praseInput(String input){
		int backCounter = len-1;
		
		for(int i = 0;i<len;i++){
			number[backCounter] = Character.getNumericValue(input.charAt(i));
			backCounter--;
		}
	}
	//display the array in regular order
	public void display(){
		for(int i = 0;i<len;i++){
			System.out.printf("%d",number[i]);
		}
		System.out.printf("\n");
	}
	
	//display the array in reverse order
	public void R_display(){
		for(int i = len-1;i>=0;i--){
			System.out.printf("%d",number[i]);
		}
		System.out.printf("\n");
	}
	//return the string version of the number
	public String getString()
	{
	  return this.string;
	}
	
	// return value in array at a given index
	public int getNumAtIndex(int index){
		if(index > len-1){
			return -1;
		}
		return number[index];
	}

	// return length of array
	public int getNumLength(){
		return len;
	}
	

	
	// take HUI input and add it to this HUI and return a new HUI
	public HUI addition(HUI input){
		int sum[];
		int len1 = input.getNumLength();
		// create a new int array of with the length of input
		if(len1>=len){
			sum = new int[len1];
		}
		// create a new int array of with the length of this HUI
		else{
			sum = new int[len];
		}
		int sumLen = sum.length;
		int carry =0;
		
		//loop through HUI and add the value of input and this add index i
		//combine value is greater than 9 then it will be carry to next index
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
		
		// create a new string with the array of int
		// use to create a new HUI
		
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
	
	/* parameter overloading of addition 
	 * addition can take a int as the parameter*/
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
	

	
	// take HUI input and subtract it to this HUI and return a new HUI
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
		
		/*
		 * loop through HUI array and subtract the value of input and this at index i 
		 * if this HUI at index i is less than input at index i 
		 * it "borrow" 10 from the next index then the value at next index is subtracted by 1
		 * if the next value is negative then it "borrow" from it next index this continue until there no 
		 * negative or exit if it there no next index
		*/
		
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
	
	/* parameter overloading of subtraction 
	 * subtraction can take a int as the parameter*/
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
	
	/* Divide this HUI by input
	 * 
	 * Loop through this HUI from the most significant to least significant
	 * Check the value at index i and see is the value greater than or equal to input
	 * create a new HUI with value at index i
	 * if greater loop subtraction on the value until it is less than
	 * Take the number of time that subtraction was done and add it to the predefine array at index i
	 * 
	 * if there is reminder from loop of subtraction
	 * take that value and add the next most significant to it
	 * and create a new HUI with it 
	 * 
	 * 
	 * Example
	 * 	reminder equal to 1
	 *  next value is 2
	 *  new HUI of 12
	 *  
	 *  
	 *  
	 *  the string create from the result array will contain the answer
	 *  
	 */
	
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
	
	/* parameter overloading of division 
	 * division can take a int as the parameter*/
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
	
	
	/*
	 * Same as division exact that it return the new HUI create with the reminder instead
	 * of the result
	 * 
	 */
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
	/* parameter overloading of Mod 
	 * Mod can take a int as the parameter*/
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
	
	/*
	 * Compare if two HUI are equal
	 * is so return true else return false
	 */
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
	/* parameter overloading of HUIequals 
	 * HUIequals can take a int as the parameter*/
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
	/*
	 * Compare if this HUI is greater than or equal to input
	 * is so return true else return false
	 */
	public boolean greaterThanOrEqual(HUI input){
		if(this.HUIequals(input)){
			return true;
		}
		
		return (!(this.lessThan(input)));
	}
	/* parameter overloading of greaterThanOrEqual 
	 * greaterThanOrEqual can take a int as the parameter*/
	public boolean greaterThanOrEqual(int input){
		if(this.HUIequals(input)){
			return true;
		}
		
		return (!(this.lessThan(input)));
	}
	
	/*
	 *  Compare if this HUI is less than or equal to input
	 * is so return true else return false
	 */
	public boolean lessThanOrEqual(HUI input){
		if(this.HUIequals(input)){
			return true;
		}
		
		return (this.lessThan(input));
	}
	
	/* parameter overloading of greaterThanOrEqual 
	 * greaterThanOrEqual can take a int as the parameter*/
	public boolean lessThanOrEqual(int num){
		HUI input = new HUI(Integer.toString(num) );	
		if(this.HUIequals(input)){
			return true;
		}
		
		return (this.lessThan(input));
	}
	/*
	 *  Compare if this HUI are greater to input
	 * is so return true else return false
	 */
	public boolean greaterThan(HUI input){
		return (!(this.lessThanOrEqual(input)));
	}
	/* parameter overloading of greaterThan 
	 * greaterThan can take a int as the parameter*/
	public boolean greaterThan(int num){
		
		HUI input = new HUI(Integer.toString(num) );	
		return (!(this.lessThanOrEqual(input)));
	}
	
	
	/*
	 *  Compare if this HUI are less than to input
	 * is so return true else return false
	 */
	
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
	/* parameter overloading of lessThan 
	 * lessThan can take a int as the parameter*/
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
	/*
	 * loop through input HUI array and multple every individual value in the HUI array
	 * with the value at index i in input
	 * 
	 * if result is greater than 9 
	 * mod the result with 10 to get value for new value 
	 * and result value divide by 10 for the carry over value
	 * 
	 * create a new HUI with result array and add that to the resultHUI
	 * 
	 */
	
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
	 /*
  34 x 46
  -------
     204          // these values are stored in the
    136           // two dimensional array mat[][];
  -------
   1564   // this the value stored in "Bignum ret"
*/       
	/* parameter overloading of multiplication 
	 * multiplication can take a int as the parameter*/
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
	
	
	

}


