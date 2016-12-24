
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
				
				else if(s instanceof User){ 
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
		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
			panel.add(temp);
			
		
		}
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	void AdminGUI(){
		frame= new JFrame();
		frame.setLocation(340, 90);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		JScrollPane s= new JScrollPane(ResourceSettings);
		JScrollPane u= new JScrollPane(UserSettings);
		frame.add(cards, BorderLayout.CENTER);
		cards.add(u,"User settings");
		cards.add(s,"Resources settings");
		cards.add(AdminSettings(),"Admins settings");
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
	JPanel AdminSettings(){
		JPanel addadmin= new JPanel();
		addadmin.setLayout(new BoxLayout(addadmin, BoxLayout.Y_AXIS));
		//ID
		JLabel ID_l=new JLabel("ID                ");
		JTextField ID= new JTextField();
		ID.setColumns(15);
		JPanel ID_p=new JPanel();
		ID_p.add(ID_l);
		ID_p.add(ID);
		ID_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//password
		JLabel pass_l=new JLabel("Password  ");
		JTextField pass= new JTextField();
		pass.setColumns(15);
		JPanel pass_p=new JPanel();	
		pass_p.add(pass_l);
		pass_p.add(pass);
		pass_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton submit=new JButton("Submit");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Admin temp;
				temp=new Admin();
				try{
				temp.setLog_in_ID(Integer.parseInt(ID.getText()));
				temp.setPassword(Integer.parseInt(pass.getText()));
				}
				catch(NumberFormatException s){
					JOptionPane prompt = new JOptionPane("ID,password,age should be an integer");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
					}
					system.addAdmin_to_system(temp);
					JOptionPane success = new JOptionPane("Admin Created");
					success.createDialog(frame, "Success").setVisible(true);
				
			}
			
		});
		
		addadmin.add(new JLabel("\n\n"));
		JLabel title =new JLabel("Add another adminstrator:");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		addadmin.add(title);
		addadmin.add(new JLabel("\n\n"));
		addadmin.add(ID_p);
		addadmin.add(pass_p);
		addadmin.add(submit);
		addadmin.add(new JLabel("\n\n\n\n\n\n\n\n"));
		
		
		return addadmin;
		
	}
	
	void UserSettingsGUI() {
		UserSettings=new JPanel();
		UserSettings.setLayout(new BoxLayout(UserSettings, BoxLayout.Y_AXIS));
		String[] menuitems={"Add User","Modify A user"};
		JComboBox menu = new JComboBox(menuitems);
		JPanel cards = new JPanel(new CardLayout());
		menu.setMaximumSize(new Dimension(250,30));
		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
				}
		
		});
		
		
		
		
		
		//adding a user panel
		JPanel add= new JPanel();
		add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
		//ID
		JLabel ID_l=new JLabel("ID                ");
		JTextField ID= new JTextField();
		ID.setColumns(15);
		JPanel ID_p=new JPanel();
		ID_p.add(ID_l);
		ID_p.add(ID);
		//password
		JLabel pass_l=new JLabel("Password  ");
		JTextField pass= new JTextField();
		pass.setColumns(15);
		JPanel pass_p=new JPanel();
		pass_p.add(pass_l);
		pass_p.add(pass);
		//First name 
		JLabel first_l=new JLabel("First Name  ");
		JTextField first= new JTextField();
		first.setColumns(15);
		JPanel first_p=new JPanel();
		first_p.add(first_l);
		first_p.add(first);		
		//Last name 
		JLabel last_l=new JLabel("Last Name   ");
		JTextField last= new JTextField();
		last.setColumns(15);
		JPanel last_p=new JPanel();
		last_p.add(last_l);
		last_p.add(last);	
		//Age 
		JLabel age_l=new JLabel("  Age                ");
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
				User temp;
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
				try{
				 temp=new User(first.getText(),last.getText(),Integer.parseInt(age.getText()),userType.values()[type-1],Integer.parseInt(ID.getText()),Integer.parseInt(pass.getText()));			
				}
			catch(NumberFormatException s){
				JOptionPane prompt = new JOptionPane("ID,password,age should be an integer");
				prompt.createDialog(frame, "Fail").setVisible(true);
				return;
				}
				system.addUser_to_system(temp);
				JOptionPane error = new JOptionPane("User Created");
				error.createDialog(frame, "Success").setVisible(true);
				
			}
			
		});
		//adding panels to add
		add.add(new JLabel(" "));
		add.add(ID_p);
		add.add(pass_p);
		add.add(first_p);
		add.add(last_p);
		add.add(age_p);
		add.add(type_p);
		add.add(new JLabel(" "));
		add.add(submit);
		add.add(new JLabel(" "));
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
		modify.add(new JLabel(" "));
		modify.add(ID_pm);
		modify.add(submitm);
		
		modify.add(new JLabel(" "));
		
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
		ResourceSettings.setLayout(new BoxLayout(ResourceSettings, BoxLayout.Y_AXIS));
		String[] menuitems={"Add Resource","Modify Resource"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setMaximumSize(new Dimension(250,30));
		JPanel cards = new JPanel(new CardLayout());
		
		//adding different card to cards
		cards.add(AddResourceSettings(),"Add Resource");
		
		ResourceSettings.add(menu);
		ResourceSettings.add(cards);
		
	}
	
	JPanel AddResourceSettings() {
		//adding a resource panel
		JPanel addcards= new JPanel(new CardLayout());
		//There are 3 different cards addRoom, addEquipment and addCourt each will have different contents 
		//and will show based on the selected option
		//some fields are used commonly like ID that are the general resource resources
		JPanel addRoom = new JPanel();
		addRoom.setLayout(new BoxLayout(addRoom, BoxLayout.Y_AXIS));
		JPanel addEquipment = new JPanel();
		addEquipment.setLayout(new BoxLayout(addEquipment, BoxLayout.Y_AXIS));
		JPanel addCourt = new JPanel();
		addCourt.setLayout(new BoxLayout(addCourt, BoxLayout.Y_AXIS));
		
		String[] menuitems={"Room","Equipment","Sports_Courts"};
		JComboBox menun = new JComboBox(menuitems);
		menun.setMaximumSize(new Dimension(150,30));
		
		JPanel menu =new JPanel();
		menu.add(new JLabel("Choose a Resource Type: "));
		menu.add(menun);
		
	
		
		//Room fields
		//setting up fields that can be multiused 
		//ID
		JLabel ID_l=new JLabel("ID                ");
		JTextField ID= new JTextField();
		ID.setColumns(15);
		JPanel ID_p=new JPanel();
		ID_p.add(ID_l);
		ID_p.add(ID);
		ID_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//RoomName
		JLabel roomname_l=new JLabel("Room Name");
		JTextField roomname= new JTextField();
		roomname.setColumns(15);
		JPanel roomname_p=new JPanel();
		roomname_p.add(roomname_l);
		roomname_p.add(roomname);	
		roomname_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//number of seats
		JLabel seatsnum_l=new JLabel("Number of Seats");
		JTextField seatsnum= new JTextField();
		seatsnum.setColumns(15);
		JPanel seatsnum_p=new JPanel();
		seatsnum_p.add(seatsnum_l);
		seatsnum_p.add(seatsnum);	
		seatsnum_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Location
		JLabel loc_l=new JLabel("Location  ");
		JTextField loc= new JTextField();
		loc.setColumns(15);
		JPanel loc_p=new JPanel();
		loc_p.add(loc_l);
		loc_p.add(loc);		
		loc_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//projector availability
		JLabel proj_l=new JLabel("Is projector available?");
		JToggleButton proj_t = new JToggleButton("Yes");
		JPanel proj_p = new JPanel();
		proj_p.add(proj_l);
		proj_p.add(proj_t);
		proj_p.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		type_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Room_Type 
		JLabel Room_l=new JLabel("Room type:               ");
		JRadioButton cr =new JRadioButton("Conferance Room");
		JRadioButton clr =new JRadioButton("Class Room");
		JRadioButton mr =new JRadioButton("MeetingRoom");
		JRadioButton cl =new JRadioButton("Computer lab");
		JRadioButton el =new JRadioButton("Electrical lab");
		JRadioButton ml =new JRadioButton("Mechanical lab");
		ButtonGroup g= new ButtonGroup();
		g.add(cr);
		g.add(clr);			
		g.add(mr);	
		g.add(cl);	
		g.add(el);	
		g.add(ml);	
		JPanel Room_p=new JPanel();
		Room_p.setLayout(new BoxLayout(Room_p, BoxLayout.Y_AXIS));
		Room_p.add(Room_l);
		Room_p.add(cr);
		Room_p.add(clr);			
		Room_p.add(mr);	
		Room_p.add(cl);	
		Room_p.add(el);	
		Room_p.add(ml);	
		Room_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//start Date
		DatePicker startdate_l = new DatePicker();
		JPanel startdate=new JPanel();
		startdate.add(new JLabel("Start Date: "));
		startdate.add(startdate_l);
		startdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		//end Date
		DatePicker enddate_l = new DatePicker();
		JPanel enddate=new JPanel();
		enddate.add(new JLabel("End Date: "));
		enddate.add(enddate_l);
		enddate.setAlignmentX(Component.CENTER_ALIGNMENT);
		//start Time
		TimePicker starttime_l = new TimePicker();
		JPanel starttime=new JPanel();
		starttime.add(new JLabel("Start Time: "));
		starttime.add(starttime_l);
		starttime.setAlignmentX(Component.CENTER_ALIGNMENT);
		//end Date
		TimePicker endtime_l = new TimePicker();
		JPanel endtime=new JPanel();
		endtime.add(new JLabel("End Time: "));
		endtime.add(endtime_l);		
		endtime.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Allowance Time
		JLabel Allowance_l=new JLabel("Allowance Time");
		JTextField Allowance= new JTextField();
		Allowance.setColumns(15);
		JPanel Allowance_p=new JPanel();
		Allowance_p.add(Allowance_l);
		Allowance_p.add(Allowance);		
		Allowance_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Equipment Fields
		//Equipment_Type 
		JLabel equip_l=new JLabel("Equipment type:               ");
		JRadioButton pr =new JRadioButton("Printer");
		JRadioButton co =new JRadioButton("Computer");
		JRadioButton sp =new JRadioButton("Speaker");
		JRadioButton te =new JRadioButton("Tennis Racket");
		JRadioButton bi =new JRadioButton("Bicycle");
		ButtonGroup g1= new ButtonGroup();
		g1.add(pr);
		g1.add(co);			
		g1.add(sp);	
		g1.add(te);	
		g1.add(bi);		
		JPanel equip_p=new JPanel();
		equip_p.setLayout(new BoxLayout(equip_p, BoxLayout.Y_AXIS));
		equip_p.add(equip_l);
		equip_p.add(pr);
		equip_p.add(co);			
		equip_p.add(sp);	
		equip_p.add(te);	
		equip_p.add(bi);			
		equip_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Court Fields 
		//Court name
		JLabel courtname_l=new JLabel("Court Name");
		JTextField courtname= new JTextField();
		courtname.setColumns(15);
		JPanel courtname_p=new JPanel();
		courtname_p.add(courtname_l);
		courtname_p.add(courtname);	
		courtname_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Court_Type 
		JLabel court_l=new JLabel("Court type:               ");
		JRadioButton ba =new JRadioButton("BasketBall");
		JRadioButton tee =new JRadioButton("Tennis");
		JRadioButton fo =new JRadioButton("FootBall");
		JRadioButton sq =new JRadioButton("Squash");
		ButtonGroup g2= new ButtonGroup();
		g2.add(ba);
		g2.add(fo);	
		g2.add(tee);
		g2.add(sq);	
		JPanel court_p=new JPanel();
		court_p.setLayout(new BoxLayout(court_p, BoxLayout.Y_AXIS));
		court_p.add(court_l);
		court_p.add(ba);
		court_p.add(tee);	
		court_p.add(fo);			
		court_p.add(sq);
		court_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//lights available
		JLabel light_l=new JLabel("Are lights available?");
		JToggleButton light_t = new JToggleButton("Yes");
		JPanel light_p = new JPanel();
		light_p.add(light_l);
		light_p.add(light_t);
		light_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//indoorcourt
		JLabel indoor_l=new JLabel("Is it Indoor?");
		JToggleButton indoor_t = new JToggleButton("Yes");
		JPanel indoor_p = new JPanel();
		indoor_p.add(indoor_l);
		indoor_p.add(indoor_t);
		indoor_p.setAlignmentX(Component.CENTER_ALIGNMENT);
		//submit button
		JButton submit=new JButton("Submit");	
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		//submit action
		submit.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Resource temp = null;
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
				

				try{
				//setting specific fields for each class type
				if(((String)menun.getSelectedItem()).equals("Room")){
				temp=new Room();
				//setting common attributes
				
				temp.setID(Integer.parseInt(ID.getText()));
				
				temp.setResource_UserType(userType.values()[type-1]);
				temp.setStart_date(startdate_l.getDate());
				temp.setEnd_date(enddate_l.getDate());
				temp.setStart_Time(starttime_l.getTime());
				temp.setEnd_Time(endtime_l.getTime());
				temp.setResourceLocation(loc.getText());
				//finding the room type
				int roomtype=0;
				if(cr.isSelected())
					roomtype=0;
				else if (clr.isSelected())
					roomtype=1;
				else if (mr.isSelected())
					roomtype=2;
				else if (cl.isSelected())
					roomtype=3;
				else if (el.isSelected())
					roomtype=4;
				else if (el.isSelected())
					roomtype=4;
				else if (ml.isSelected())
					roomtype=5;


				((Room)temp).setRoomType(RoomType.values()[roomtype]);
				((Room)temp).setNumberOfSeats(Integer.parseInt(seatsnum.getText()));
				((Room)temp).setPojectorAvil(proj_t.isSelected());
				((Room)temp).setRoomName(roomname.getText());
				}
				
				
				
				//if equipment is selected
				else if(((String)menun.getSelectedItem()).equals("Equipment")){
					temp=new Equipment();
					//common attributes
					temp.setID(Integer.parseInt(ID.getText()));
					temp.setResource_UserType(userType.values()[type-1]);
					temp.setStart_date(startdate_l.getDate());
					temp.setEnd_date(enddate_l.getDate());
					temp.setStart_Time(starttime_l.getTime());
					temp.setEnd_Time(endtime_l.getTime());
					temp.setResourceLocation(loc.getText());
					int equiptype=0;
					if(pr.isSelected())
						equiptype=0;
					else if (co.isSelected())
						equiptype=1;
					else if (sp.isSelected())
						equiptype=2;
					else if (te.isSelected())
						equiptype=3;
					else if (bi.isSelected())
						equiptype=4;
					((Equipment)temp).setEquipmentType(equipmentType.values()[equiptype]);
				}
				else if(((String)menun.getSelectedItem()).equals("Sports_Courts")){
					temp=new Sports_Courts();
					
					//common attributes
					temp.setID(Integer.parseInt(ID.getText()));
					temp.setResource_UserType(userType.values()[type-1]);
					temp.setStart_date(startdate_l.getDate());
					temp.setEnd_date(enddate_l.getDate());
					temp.setStart_Time(starttime_l.getTime());
					temp.setEnd_Time(endtime_l.getTime());
					temp.setResourceLocation(loc.getText());
					int courttype=0;
					if(ba.isSelected())
						courttype=0;
					else if (tee.isSelected())
						courttype=1;
					else if (fo.isSelected())
						courttype=2;
					else if (sq.isSelected())
						courttype=3;
					((Sports_Courts)temp).setCourtName(courtname.getText());
					((Sports_Courts)temp).setLightAvailable(light_t.isSelected());
					((Sports_Courts)temp).setIndoorCourt(indoor_t.isSelected());
					((Sports_Courts)temp).setCourt_Type(courtType.values()[courttype]);
				}
				}
				catch(NumberFormatException s){
					JOptionPane prompt = new JOptionPane("ID,number of seats should be an integer");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				JOptionPane prompt = new JOptionPane("Resource Created Successfully");
				prompt.createDialog(frame, "Success").setVisible(true);
				system.add_resource_to_array(temp);
				
			}
			
			
		});
		
		//initial display of fields for room
		addRoom.add(menu);
		addRoom.add(ID_p);
		addRoom.add(roomname_p);
		addRoom.add(loc_p);
		addRoom.add(Allowance_p);
		addRoom.add(seatsnum_p);
		addRoom.add(proj_p);
		addRoom.add(startdate);
		addRoom.add(enddate);
		addRoom.add(starttime);
		addRoom.add(endtime);
		addRoom.add(type_p);
		addRoom.add(Room_p);				
		addRoom.add(submit);
		
		
	
		
		
		
		//adding cards to addCards
		addcards.add(addRoom,"Room");
		addcards.add(addEquipment,"Equipment");
		addcards.add(addCourt,"Sports_Courts");
		
		menun.setSelectedItem("Room");
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(addcards.getLayout());
				if(((String)evt.getItem()).equals("Room")){
						
					addRoom.add(menu);
					addRoom.add(ID_p);
					addRoom.add(roomname_p);
					addRoom.add(loc_p);
					addRoom.add(Allowance_p);
					addRoom.add(seatsnum_p);
					addRoom.add(proj_p);
					addRoom.add(startdate);
					addRoom.add(enddate);
					addRoom.add(starttime);
					addRoom.add(endtime);	
					addRoom.add(type_p);
					addRoom.add(Room_p);			
					addRoom.add(submit);

				}
				else if (((String)evt.getItem()).equals("Equipment")){
					//adding panels to addEquipment
	
					addEquipment.add(menu);
					addEquipment.add(ID_p);
					addEquipment.add(loc_p);
					addEquipment.add(Allowance_p);
					addEquipment.add(startdate);
					addEquipment.add(enddate);
					addEquipment.add(starttime);					
					addEquipment.add(endtime);
					addEquipment.add(type_p);
					addEquipment.add(equip_p);				
					addEquipment.add(submit);

				}
				else if (((String)evt.getItem()).equals("Sports_Courts")){
					//adding panels to addEquipment
	
					addCourt.add(menu);
					addCourt.add(ID_p);
					addCourt.add(courtname_p);
					addCourt.add(loc_p);
					addCourt.add(light_p);
					addCourt.add(indoor_p);
					addCourt.add(Allowance_p);
					addCourt.add(startdate);
					addCourt.add(enddate);
					addCourt.add(starttime);					
					addCourt.add(endtime);				
					addCourt.add(type_p);
					addCourt.add(court_p);
					addCourt.add(submit);
					
					

				}
		        cl.show(addcards, (String)evt.getItem());
				}
		
		});
		
		return addcards;
	}
	
}
