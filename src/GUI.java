
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.*;

import com.github.lgooddatepicker.components.CalendarPanel;
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
	JPanel UserSettings,ResourceSettings,AdminSettings;
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
					AdminGUI();
				}
				
				else if(s instanceof User){ System.out.println("USER");
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
		frame=new JFrame();
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
		frame.add(d);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	void AdminGUI(){
		frame= new JFrame();
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(340, 90);
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
		String[] menuitems={"User settings","Resources settings","Admins settings"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSelectedIndex(0);
		JPanel cards = new JPanel(new CardLayout());
		UserSettingsGUI();
		ResourceSettingsGUI();
		frame.add(cards, BorderLayout.CENTER);
		cards.add(UserSettings,"User settings");
		cards.add(ResourceSettings,"Resources settings");
		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
				}
		
		});
		frame.add(menu,BorderLayout.PAGE_START);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	
	void UserSettingsGUI() {
		UserSettings=new JPanel();
		UserSettings.setLayout(new BoxLayout(UserSettings, BoxLayout.Y_AXIS));
		String[] menuitems={"Add User","Modify A user"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSize(150, 50);
		JPanel cards = new JPanel(new CardLayout());
		
		
		
		
		//adding a user panel
		JPanel add= new JPanel();
		add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
		//ID
		JLabel ID_l=new JLabel("ID");
		JTextField ID= new JTextField();
		ID.setColumns(15);
		JPanel ID_p=new JPanel();
		ID_p.add(ID_l);
		ID_p.add(ID);
		//password
		JLabel pass_l=new JLabel("Password");
		JTextField pass= new JTextField();
		pass.setColumns(15);
		JPanel pass_p=new JPanel();
		pass_p.add(pass_l);
		pass_p.add(pass);
		//First name 
		JLabel first_l=new JLabel("First Name");
		JTextField first= new JTextField();
		first.setColumns(15);
		JPanel first_p=new JPanel();
		first_p.add(first_l);
		first_p.add(first);		
		//Last name 
		JLabel last_l=new JLabel("Last Name");
		JTextField last= new JTextField();
		last.setColumns(15);
		JPanel last_p=new JPanel();
		last_p.add(last_l);
		last_p.add(last);	
		//Age 
		JLabel age_l=new JLabel("Age");
		JTextField age= new JTextField();
		age.setColumns(15);
		JPanel age_p=new JPanel();
		age_p.add(age_l);
		age_p.add(age);			
		//User_Type 
		JLabel type_l=new JLabel("User type:");
		JCheckBox p =new JCheckBox("Professor");
		JCheckBox s =new JCheckBox("Student");
		JCheckBox st =new JCheckBox("Staff");
		JPanel type_p=new JPanel();
		type_p.setLayout(new BoxLayout(type_p, BoxLayout.Y_AXIS));
		type_p.add(type_l);
		type_p.add(p);
		type_p.add(s);			
		type_p.add(st);	
		
		//submit button
		JButton submit=new JButton("Submit");
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int type=0;
				if(p.isSelected() && s.isSelected() && st.isSelected())
					type=7;
				else if(st.isSelected() && s.isSelected())
					type=6;
				else if(p.isSelected() && s.isSelected())
					type=5;	
				else if(p.isSelected() && st.isSelected())
					type=4;	
				else if(s.isSelected() )
					type=3;	
				else if(st.isSelected() )
					type=2;	
				else if(p.isSelected() )
					type=1;	
				User temp=new User(first.getText(),last.getText(),Integer.parseInt(age.getText()),userType.values()[type-1],Integer.parseInt(ID.getText()),Integer.parseInt(pass.getText()));					
				system.addUser_to_system(temp);
				JOptionPane error = new JOptionPane("User Created");
				error.createDialog(frame, "Success").setVisible(true);
				
			}
			
		});
		//adding panels to add
		add.add(ID_p);
		add.add(pass_p);
		add.add(first_p);
		add.add(last_p);
		add.add(age_p);
		add.add(type_p);
		add.add(new JLabel(" "));
		add.add(submit);
		
		//Modify a user Panel
		JPanel modify= new JPanel();
		modify.setLayout(new BoxLayout(modify, BoxLayout.Y_AXIS));
		//ID
		JLabel ID_lm=new JLabel("ID to change");
		JTextField IDm= new JTextField();
		IDm.setColumns(15);
		JPanel ID_pm=new JPanel();
		ID_pm.add(ID_lm);
		ID_pm.add(IDm);
		
		JButton submitm = new JButton("Submit");
		submitm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (system.searchUser(Integer.parseInt(IDm.getText()))!=null){
					//password
					JLabel pass_lm=new JLabel("New Password");
					JTextField passm= new JTextField();
					passm.setColumns(15);
					JPanel pass_pm=new JPanel();
					pass_pm.add(pass_lm);
					pass_pm.add(passm);
					JDialog d= new JDialog(frame,"New Password");
					d.setLocationRelativeTo(frame);
					JButton submitms = new JButton("Submit");
					submitms.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							a.modify_user_password(Integer.parseInt(IDm.getText()), Integer.parseInt(passm.getText()), system);
							JOptionPane error = new JOptionPane("Password Changed Successfully");
							error.createDialog(frame, "Success").setVisible(true);
						}
						
					});
					d.add(pass_pm,BorderLayout.PAGE_START);
					d.add(submitms,BorderLayout.PAGE_END);
					d.setSize(300, 200);
					d.setVisible(true);
					
					
				}
				else{
					JOptionPane error = new JOptionPane("User Not Found");
					error.createDialog(frame, "Fail").setVisible(true);
				}
				
				
			}
			
			
		});
		
		
		
		//adding panels to card
		modify.add(ID_pm);
		modify.add(submitm);
		
		
		
		//adding different card to cards
		cards.add(add,"Add User");
		cards.add(modify,"Modify A user");
		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
				}
		
		});
		UserSettings.add(menu);
		UserSettings.add(cards);
	}
	
	
	
	void ResourceSettingsGUI(){
		ResourceSettings=new JPanel();
		String[] menuitems={"Add Resource","Modify Resource"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSize(100, 50);
		ResourceSettings.add(menu);
	}
	
	
	
}
