/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * Button.java:  Class that extends JButton.
 *               Adds more functionality to a JButton.
 */

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	private int num;
	Button(String text,int type){
		super(text);
		num = type;
	}
	
	public int getNumber (){
		return num;
	}
	
	
}
