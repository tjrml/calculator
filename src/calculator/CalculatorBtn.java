package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalculatorBtn implements ActionListener {
	private String text;
	private JTextField tf;
	private JLabel lbl;
	private String[] number;

	public CalculatorBtn(JTextField tf, String text, JLabel lbl) {
		this.tf = tf;
		this.text = text;
		this.lbl = lbl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (tf.getText().equals("0")) {
			tf.setText(text);
			lbl.setText(text);
		} else if (text.equals("C")) {
			tf.setText("0");
			lbl.setText("0");
		} else if (text.equals("←")) {
			if (tf.getText().length() == 1) {
				tf.setText("0");
				lbl.setText("0");
			} else {
				tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
				lbl.setText(lbl.getText().substring(0, lbl.getText().length() - 1));
			}
		} else if (text.equals("=")) {
			char[] arr = tf.getText().toCharArray(); // 문자를 1개씩 넣어줌
			for (int i = 0; i < arr.length; i++) {
				switch (arr[i]) {
				case '+':
					add();
					break;
				case '-':
					sub();
					break;
				case '*':
					mul();
					break;
				case '/':
					div();
					break;
				default:

				}
			}
		} else {
			tf.setText(tf.getText() + text);
			lbl.setText(lbl.getText() + text);
		}
	}

	// 덧셈
	public int add() {
		number = tf.getText().split("\\+");
		int add = Integer.valueOf(number[0]);
		for (int i = 1; i < number.length; i++) {
			add = add += Integer.valueOf(number[i]);
			tf.setText(add + "");
		}
		return add;
	}

	// 뺄셈
	public int sub() {
		number = tf.getText().split("\\-");
		int sub = Integer.valueOf(number[0]);
		for (int i = 1; i < number.length; i++) {
				sub = sub - Integer.valueOf(number[i]);
				tf.setText(sub + "");
		}
		return sub;
	}

	// 곱셈
	public int mul() {
		number = tf.getText().split("\\*");
		int mul = Integer.valueOf(number[0]);
		for (int i = 1; i < number.length; i++) {
			mul = mul *= Integer.valueOf(number[i]);
			tf.setText(mul + "");
		}
		
		return mul;
	}

	// 나누기
	public double div() {
		number = tf.getText().split("\\/");
		double div = Integer.valueOf(number[0]);
		for (int i = 1; i < number.length; i++) {
			div = div / Integer.valueOf(number[i]);
			tf.setText(div + "");
		}
		return div;
	}
}
