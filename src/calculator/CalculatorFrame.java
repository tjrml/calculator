package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorFrame extends JFrame {

	public CalculatorFrame() {
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel("0");
		JTextField tf = new JTextField("0");
		JButton[] btn = new JButton[17];
		JPanel btnPnl = new JPanel(new GridLayout(0, 4));
		List<String> text = new ArrayList<>();
		int num = 1;

		lbl.setPreferredSize(new Dimension(384, 20));
		lbl.setFont(new Font("Areal", Font.BOLD, 15));
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);

		tf.setPreferredSize(new Dimension(385, 80));
		tf.setFont(new Font("Areal", Font.BOLD, 25));
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setEditable(false);
		tf.setBackground(Color.white);

		pnl.add(lbl, "North");
		pnl.add(tf);
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();
			btn[i].setPreferredSize(new Dimension(100, 71));
			btn[i].setFont(new Font("Areal", Font.BOLD, 20));
			btnPnl.add(btn[i]);
			if (i % 4 != 3) {
				btn[i].setBackground(Color.white);
			}
			
			switch (i) {
			case 3:
				btn[i].setText("/");
				break;
			case 7:
				btn[i].setText("*");
				break;
			case 11:
				btn[i].setText("-");
				break;
			case 12:
				btn[i].setText("C");
				break;
			case 13:
				btn[i].setText("0");
				break;
			case 14:
				btn[i].setText("←");
				break;
			case 15:
				btn[i].setText("+");
				break;
			case 16:
				btn[i].setText("=");
				break;
			default:
				btn[i].setText(num++ + "");
			}
		}
		// 텍스트 입력
		for (int i = 0; i < btn.length; i++) {
			text.add(btn[i].getText());
			btn[i].addActionListener(new CalculatorBtn(tf, text.get(i), lbl));
		}

		add(pnl);
		add(btnPnl, "South");
		setSize(400, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
