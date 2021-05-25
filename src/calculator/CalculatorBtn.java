package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalculatorBtn implements ActionListener {
	private String text;
	private JTextField tf;
	private JLabel lbl;
	private List<String> list = new ArrayList<>();
	private String num = "";
	private String result;

	public CalculatorBtn(JTextField tf, String text, JLabel lbl) {
		this.tf = tf;
		this.text = text;
		this.lbl = lbl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (tf.getText().equals("0")) {
			zeroException(tf, text, lbl);
		} else if (text.equals("C")) {
			tf.setText("0");
			lbl.setText("0");
		} else if (text.equals("←")) {
			if (tf.getText().length() > 1) {
				tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
				lbl.setText(lbl.getText().substring(0, lbl.getText().length() - 1));
			} else {
				tf.setText("0");
				lbl.setText("0");
			}
		} else if (text.equals("=")) {
			result = Integer.toString(calculate(tf.getText())); // int형 숫자를 String으로 변환
//			result = Double.toString(calculate(tf.getText())); // double형 숫자를 String으로 변환
			tf.setText(result);
			num = "";
		} else if (text.equals("+") || text.equals("-") ||
				text.equals("*") || text.equals("/")){
			if (tf.getText().equals("+") || tf.getText().equals("-") ||
					tf.getText().equals("*") || tf.getText().equals("/")) {
				tf.setText(tf.getText());
				lbl.setText(lbl.getText());
			} else {
				System.out.println(tf.getText());
				tf.setText(tf.getText() + text);
				lbl.setText(lbl.getText() + text);
			}
		} else {
			tf.setText(tf.getText() + text);
			lbl.setText(lbl.getText() + text);
		}
	}

	// 문자열 저장
	private void fullTextParsing(String text) {
		list.clear(); // list 초기화

		for (int i = 0; i < text.length(); i++) {
			char ch = tf.getText().charAt(i); // 문자열의 인덱스번호를 반환

			if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
				list.add(num); // 사칙연산기호 이전 첫번째 문자열 저장
				num = ""; // 초기화
				list.add(ch + ""); // 사칙연산기호 저장
			} else {
				num = num + ch;
			}
		}
		list.add(num); // 사칙연산기호 이후 두번째 문자열 저장
		list.remove(""); // 이부분은 좀 이해가 안감
	}

	// 연산
	private int calculate(String text) {
		fullTextParsing(text);

		int prev = 0;
		int current = 0;
		String mode = "";

		for (String s : list) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("-")) {
				mode = "sub";
			} else if (s.equals("*")) {
				mode = "mul";
			} else if (s.equals("/")) {
				mode = "div";
			} else {
				current = Integer.parseInt(s); // 사칙연산기호 를 제외한 문자열을 int형 변환
//				current = Double.parseDouble(s);
				if (mode.equals("add")) {
					prev += current;
				} else if (mode.equals("sub")) {
					prev -= current;
				} else if (mode.equals("mul")) {
					prev *= current;
				} else if (mode.equals("div")) {
					prev /= current;
				} else {
					prev = current;
				}
			}
		}
		return prev;
	}

	// 0일때 처리
	private String zeroException(JTextField tf, String text, JLabel lbl) {
		if (tf.getText().length() > 1) {
			tf.setText(tf.getText() + text);
			lbl.setText(lbl.getText() + text);
			return tf.getText();
		} else if (text.equals("C") || text.equals("←") || text.equals("=")) {
			tf.setText("0");
			lbl.setText("0");
			return tf.getText();
		} else if (text.equals("+") || text.equals("*") || text.equals("/")) {
			tf.setText("0" + text);
			lbl.setText("0" + text);
			return tf.getText();
		} else {
			tf.setText(text);
			lbl.setText(text);
			return tf.getText();
		}
	}
}
