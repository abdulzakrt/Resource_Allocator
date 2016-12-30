
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
public class GUI extends JFrame{
	
	
	
	Resource_Management_System system;

	Admin a;
	User u;
	
	public GUI(Resource_Management_System system) {
		this.system=system;
	}
	
	
	
	public void login( ){
		
		JFrame login =new JFrame("Login to Reservation System");
		login.setResizable(false);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel username_l = new JLabel("Username ");
		JLabel password_l = new JLabel("Password ");
		JTextField username = new JTextField("Enter here");
		JButton submit = new JButton("login");
		username.setForeground(Color.GRAY);
		username.setColumns(15);
		username.addFocusListener(new FocusListener() {

		    @Override 
		    public void focusGained(FocusEvent e) {
		    	username.setForeground(Color.BLACK);
		        username.setText(null); // Empty the text field when it receives focus
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		    }

		});
		JPasswordField password= new JPasswordField("123456");
		password.setForeground(Color.LIGHT_GRAY);
		password.setColumns(15);
		password.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        password.setText(null); // Empty the text field when it receives focus
		        password.setForeground(Color.BLACK);
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		    }

		});
		JPanel login_p = new JPanel();
		JPanel login_u = new JPanel();
		login_u.add(username_l);
		login_u.add(username);
		login_p.add(password_l);
		login_p.add(password);
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		fields.add(login_u);
		fields.add(login_p);
		login.add(fields, BorderLayout.CENTER);
		login.add(submit,BorderLayout.PAGE_END);
		login.pack();
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Member s=null;
				int ID,pass=0;
				try{
				ID=Integer.parseInt(username.getText());
				pass=Integer.parseInt(new String(password.getPassword()));
				}
				catch(Exception x){
					ID=pass=0;
				}
				s=system.login(ID,pass);
				if (s instanceof Admin ){
					//login.getContentPane().removeAll();
					login.dispose();
					 a=(Admin)s;
					new adminPanel(a,system);
				}
				
				else if(s instanceof User){ 
					login.dispose();
					u= (User) s;
					new userMenu(system,u);
				
				}
				else {
					JOptionPane error = new JOptionPane("Wrong Username/Password Combination");
					error.createDialog(login, "Login Failed").setVisible(true);
					
				}
			}
			
		});
		login.setSize(250, 150);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		
	}

}
