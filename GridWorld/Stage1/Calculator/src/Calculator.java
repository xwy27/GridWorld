import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Calculator extends JFrame {
	private String[] contents = {"+", "-", "*", "/", "%", "x²", "√","OK"};
	private String[] text = {"", "", "", "=", ""};
	private JButton[] btn = new JButton[contents.length];
	private JTextField[] edit = new JTextField[text.length];

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
	}

	public Calculator() {
		initGUI();
		addListener();
	}

	private void initGUI() {
		JPanel north = new JPanel(new GridLayout(1,5));
		JPanel center = new JPanel(new GridLayout(2,4));
		//initialize edit area
		for (int i = 0; i < text.length; ++i) {
			edit[i] = new JTextField(text[i]);
			edit[i].setHorizontalAlignment(JTextField.CENTER);
			
			if (i == 1 || i == 3 || i == 4) {
				edit[i].setEnabled(false);
				edit[i].setBackground(new Color(250, 250, 250));
			}

			north.add(edit[i]);
		}
		north.setPreferredSize(new Dimension(0,100));

		//initialize operating area
		for (int i = 0; i < contents.length; ++i) {
			btn[i] = new JButton(contents[i]);
			center.add(btn[i]);
		}
		
		//set GUI with BorderLayout
		this.setLayout(new BorderLayout(5, 5));
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.setTitle("My Simple Calculator");
		this.setSize(600, 300);
		//set close to stop the application
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Listener for operator
	private class OpListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String op = e.getActionCommand();
			edit[1].setText(op);
			//Disable the right operant while single operator
			if (op.equals("x²") || op.equals("√")) {
				edit[2].setText("");
				edit[2].setEditable(false);
			} else {
				edit[2].setEditable(true);
			}
		}
	}

	//Listener for OK
	private class OKListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				String op = edit[1].getText();
				double num1 = Double.parseDouble(edit[0].getText());
				double num2 = 0;
				//get right operant when not single operator
				if(!(op.equals("x²") || op.equals("√"))) {
					num2 = Double.parseDouble(edit[2].getText());
				}
				double result = 0;
				DecimalFormat df = new DecimalFormat("#.00");

				if (op.equals("+")) {
					result = num1 + num2;
				} else if (op.equals("-")) {
					result = num1 - num2;
				} else if (op.equals("*")) {
					result = num1 * num2;
				} else if (op.equals("/")) {
					//divide 0, throw error
					if (num2 == 0) {
						throw(new Exception());
					}
					result = num1 / num2;
				} else if (op.equals("%")) {
					result = num1 % num2;
				} else if (op.equals("x²")) {
					result = num1 * num1;
				} else if (op.equals("√")) {
					result = Math.sqrt(num1);
				}
				edit[text.length - 1].setText(df.format(result));
			} catch (Exception ex) {
				System.out.println("Wrong Operand!");
				return;
			}
		}
	}

	private void addListener() {
		OpListener listener1 = new OpListener();
		OKListener listener2 = new OKListener();
		for (int i = 0; i < contents.length; ++i) {
			if (i == contents.length - 1) {
				btn[contents.length - 1].addActionListener(listener2);
			} else {
				btn[i].addActionListener(listener1);
			}
		}
	}
}
