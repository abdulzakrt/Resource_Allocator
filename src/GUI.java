
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
	JFrame frame;
	Admin a;
	User u;
	
	public GUI(Resource_Management_System system) {
		this.system=system;
	}
	
	
	
	public void login( ){
		
		JFrame login =new JFrame("Login to Reservation System");
		login.setResizable(false);
		login.setLocation(550, 300);
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
					frame=new adminPanel(a,system);
				}
				
				else if(s instanceof User){ 
					login.dispose();
					u= (User) s;
					UserGUI();
				
				}
				else {
					JOptionPane error = new JOptionPane("Wrong Username/Password Combination");
					error.createDialog(login, "Login Failed").setVisible(true);
					
					//login.add(error,BorderLayout.CENTER);
					
				}
			}
			
		});
		login.setSize(250, 150);
		login.setVisible(true);
		
	}
	
	void UserGUI(){
		frame= new JFrame();
		frame.setLocation(340, 90);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//JPanel panel= new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	system.closeSystem();
		            System.exit(0);
		        }
		    }
		});
		//userPanel panel = new userPanel(system, u);
		userMenu user = new userMenu(system,u);

		//panel.setLocation(200,200);
	/*DatePicker startdate_l = new DatePicker();
		TimePicker starTime_l = new TimePicker();
		TimePicker endTime_l = new TimePicker();
		JPanel date=new JPanel();
		//User will enter start date
		date.add(new JLabel("Start Date: "));
		date.add(startdate_l);
		date.setAlignmentX(Component.CENTER_ALIGNMENT);
		date.add(new JLabel("Start Time: "));
		date.add(starTime_l);
		panel.add(date);*/
		
		
		
		TimePickerSettings s= new TimePickerSettings();
		DateTimePicker d = new DateTimePicker(null,s);
		//to set a policy for allowed times
		 class SampleTimeVetoPolicy implements TimeVetoPolicy {

		        /**
		         * isTimeAllowed, Return true if a time should be allowed, or false if a time should be
		         * vetoed.
		         */
		        @Override
		        public boolean isTimeAllowed(LocalTime time) {
		            // Only allow times from 9a to 5p, inclusive.
//		            return PickerUtilities.isLocalTimeInRange(
//		                    time, LocalTime.of(9, 00), LocalTime.of(17, 00), true);
		        	LocalTime s=LocalTime.parse("14:00");
		        	
		        	if (time.isAfter(s)){
		        		return true;
		        	}
		        	return false;
		        }
		    }
		
		s.setVetoPolicy(new SampleTimeVetoPolicy());
	//	s.setColor(area, color);
		JToggleButton nine = new JToggleButton("9:00am");
		nine.setForeground(Color.BLACK);
		frame.add(nine);
		nine.setEnabled(false);
		nine.setBackground(Color.RED);
		//nine.setDisabledSelectedIcon(new ImageIcon(ReservationPanel.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/vk-light.png")));
		for (int i=0;i<10;i++){
			JToggleButton temp=new JToggleButton(""+i);
			if(i%2==0){
				temp.setBackground(Color.red);
				temp.setEnabled(false);
			}
			//user.add(temp);
			
		
		}
		
		
		
		frame.add(user);
		frame.pack();
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	
	
	
}
