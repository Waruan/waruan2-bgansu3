import javax.swing.JButton;

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
