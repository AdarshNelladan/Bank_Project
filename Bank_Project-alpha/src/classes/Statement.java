package classes;

import javax.swing.JFrame;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import sql.SqlFunctions;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class Statement {
	
	SqlFunctions sql=new SqlFunctions();
	LinkedList<String> statement_type=new LinkedList<String>();
	LinkedList<Double> statement_value=new LinkedList<Double>();

	private JFrame frame;



	/**
	 * Create the application.
	 */
	public Statement(int id) {
		
		initialize(id);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		
		sql.statement(id);
		statement_type=sql.type;
		statement_value=sql.value;
		frame = new JFrame("Statement");
		frame.setBounds(100, 100, 720, 623);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);	
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblType = new JLabel("Type");
		springLayout.putConstraint(SpringLayout.NORTH, lblType, 79, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblType, 120, SpringLayout.WEST, frame.getContentPane());
		lblType.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(lblType);
		
		JLabel lblNewLabel = new JLabel("Value");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 2, SpringLayout.NORTH, lblType);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -166, SpringLayout.EAST, frame.getContentPane());
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 29, SpringLayout.SOUTH, lblType);
		springLayout.putConstraint(SpringLayout.WEST, panel, 74, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 370, SpringLayout.SOUTH, lblType);
		springLayout.putConstraint(SpringLayout.EAST, panel, -75, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setBackground(Color.GRAY);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 36, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -301, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		
		Iterator<String> itr_type=statement_type.descendingIterator();
		Iterator<Double> itr_value=statement_value.descendingIterator();
		
		JLabel stat_type[] = new JLabel[11];
		JLabel stat_value[] = new JLabel[11];
		int i=0,y=10;
		while(itr_type.hasNext() && itr_value.hasNext() && i<10) {
			String s=itr_type.next();
			stat_type[i]= new JLabel();
			stat_type[i].setText(s);
			stat_type[i].setBounds(49, y, 110, 16);
			stat_type[i].setFont(new Font("Segoe UI", Font.BOLD, 15));
			panel.add(stat_type[i]);
			double v=itr_value.next();
			String v1=new BigDecimal(v).toPlainString();
			stat_value[i]= new JLabel();
			if (s.equals("DEPOSIT") || s.equals("CREDITED")) {
				stat_value[i].setText("+"+v1+"");
			}
			else {
				stat_value[i].setText("-"+v1+"");
			}
			stat_value[i].setBounds(396, y, 110, 16);
			stat_value[i].setFont(new Font("Segoe UI", Font.BOLD, 14));
			panel.add(stat_value[i]);
			i++;
			y+=30;
		}
				

	}
}
