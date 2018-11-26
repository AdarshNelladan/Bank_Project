package classes;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import sql.SqlFunctions;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserDetails extends SqlFunctions{

	SqlFunctions sql =new SqlFunctions();
	private JFrame frame;


	/**
	 * Create the application.
	 */
	public UserDetails(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		frame = new JFrame("User Details");
		frame.setFont(new Font("SansSerif", Font.PLAIN, 15));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setVisible(true);
		
		
		sql.userDetails(id);
		
		
		JLabel name = new JLabel("Name              :");
		name.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(name);
		
		JLabel user = new JLabel("User Name      :");
		springLayout.putConstraint(SpringLayout.WEST, user, 48, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, user);
		user.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(user);
		
		JLabel age = new JLabel("Age                 :");
		springLayout.putConstraint(SpringLayout.WEST, age, 50, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, user, -17, SpringLayout.NORTH, age);
		age.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(age);
		
		JLabel gender = new JLabel("Gender           :");
		springLayout.putConstraint(SpringLayout.NORTH, gender, 24, SpringLayout.SOUTH, age);
		springLayout.putConstraint(SpringLayout.WEST, gender, 50, SpringLayout.WEST, frame.getContentPane());
		gender.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(gender);
		
		JLabel address = new JLabel("Address          :");
		springLayout.putConstraint(SpringLayout.WEST, address, 50, SpringLayout.WEST, frame.getContentPane());
		address.setFont(new Font("SansSerif", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.NORTH, address, 21, SpringLayout.SOUTH, gender);
		frame.getContentPane().add(address);
		
		JLabel name_det = new JLabel(sql.name);
		springLayout.putConstraint(SpringLayout.WEST, name_det, 53, SpringLayout.EAST, name);
		springLayout.putConstraint(SpringLayout.NORTH, name, -1, SpringLayout.NORTH, name_det);
		springLayout.putConstraint(SpringLayout.NORTH, name_det, 140, SpringLayout.NORTH, frame.getContentPane());
		name_det.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(name_det);
		
		JLabel user_det = new JLabel(sql.username);
		springLayout.putConstraint(SpringLayout.NORTH, user_det, 1, SpringLayout.NORTH, user);
		springLayout.putConstraint(SpringLayout.WEST, user_det, 0, SpringLayout.WEST, name_det);
		user_det.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(user_det);
		
		JLabel age_det = new JLabel(""+sql.age+"");
		springLayout.putConstraint(SpringLayout.NORTH, age_det, 64, SpringLayout.SOUTH, name_det);
		springLayout.putConstraint(SpringLayout.NORTH, age, -1, SpringLayout.NORTH, age_det);
		age_det.setFont(new Font("Tahoma", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.WEST, age_det, 0, SpringLayout.WEST, name_det);
		frame.getContentPane().add(age_det);
		
		JLabel gender_det = new JLabel(sql.gender);
		gender_det.setFont(new Font("Tahoma", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.WEST, gender_det, 0, SpringLayout.WEST, name_det);
		springLayout.putConstraint(SpringLayout.SOUTH, gender_det, 0, SpringLayout.SOUTH, gender);
		frame.getContentPane().add(gender_det);
		
		JTextArea address_det = new JTextArea(sql.address);
		springLayout.putConstraint(SpringLayout.NORTH, address_det, 25, SpringLayout.SOUTH, gender_det);
		springLayout.putConstraint(SpringLayout.WEST, address_det, 50, SpringLayout.EAST, address);
		springLayout.putConstraint(SpringLayout.SOUTH, address_det, 184, SpringLayout.SOUTH, gender_det);
		address_det.setFont(new Font("Tahoma", Font.PLAIN, 20));
		address_det.setEditable(false);
		address_det.setBackground(SystemColor.controlHighlight);
		frame.getContentPane().add(address_det);
		
		JLabel heading = new JLabel("User Details");
		springLayout.putConstraint(SpringLayout.EAST, address_det, 0, SpringLayout.EAST, heading);
		springLayout.putConstraint(SpringLayout.NORTH, heading, 30, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, heading, 289, SpringLayout.WEST, frame.getContentPane());
		heading.setFont(new Font("Tahoma", Font.BOLD, 34));
		frame.getContentPane().add(heading);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, -46, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 331, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 419, SpringLayout.WEST, frame.getContentPane());
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblAccNo = new JLabel("ACC NO          :");
		lblAccNo.setFont(new Font("SansSerif", Font.BOLD, 20));
		springLayout.putConstraint(SpringLayout.WEST, lblAccNo, 0, SpringLayout.WEST, name);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAccNo, -21, SpringLayout.NORTH, name);
		frame.getContentPane().add(lblAccNo);
		
		JLabel lblaccnodet = new JLabel(""+sql.accno+"");
		lblaccnodet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, lblaccnodet, 0, SpringLayout.NORTH, lblAccNo);
		springLayout.putConstraint(SpringLayout.WEST, lblaccnodet, 0, SpringLayout.WEST, name_det);
		frame.getContentPane().add(lblaccnodet);
	}
}
