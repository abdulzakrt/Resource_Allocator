import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;

public class adminPanel extends JFrame {
	JFrame frame=this;
	Admin a;
	Resource_Management_System system;
	JPanel UserSettings,ResourceSettings,AdminSettings;
	public adminPanel(Admin s,Resource_Management_System system) {
		this.setTitle("Adminstrator Settings");
		a=s;
		this.system=system;
		AdminGUI();
	}
	
	
	void AdminGUI(){
		this.setLocation(340, 90);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
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
		String[] menuitems={"User settings","Resources settings","Admins settings","View Reservations","View Schedule","Display Users"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSelectedIndex(0);
		JPanel cards = new JPanel(new CardLayout());
		UserSettingsGUI();
		ResourceSettingsGUI();
		JScrollPane s= new JScrollPane(ResourceSettings);
		JScrollPane u= new JScrollPane(UserSettings);
		DisplayAllResources displayallresources= new DisplayAllResources(system);
		DisplayAllReservationsPanel displayallreservations =new DisplayAllReservationsPanel(system);
		DisplayUsers displayusers =new DisplayUsers(system);
		this.add(cards, BorderLayout.CENTER);
		cards.add(u,"User settings");
		cards.add(s,"Resources settings");
		cards.add(AdminSettings(),"Admins settings");
		cards.add(new JScrollPane(displayallreservations),"View Reservations");
		cards.add(new JScrollPane(displayallresources),"View Schedule");
		cards.add(new JScrollPane(displayusers),"Display Users");
		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
		        if(((String)evt.getItem()).equals("View Reservations") || ((String)evt.getItem()).equals("View Schedule") || ((String)evt.getItem()).equals("Display Users")){
		        	displayallresources.Display_Resources();
		        	displayallreservations.Display_Reservations();
		        	displayusers.Display_Users();
		        	frame.setSize(1000, 600);
		        }
		        else
		        	frame.setSize(600, 600);
				}
		
		});
		this.add(menu,BorderLayout.PAGE_START);
		this.setSize(600, 600);
		this.setVisible(true);
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
					try {
						system.addAdmin_to_system(temp);
						JOptionPane success = new JOptionPane("Admin Created");
						success.createDialog(frame, "Success").setVisible(true);
					} catch (IDexisterror e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
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
		//add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
		 GroupLayout layout = new GroupLayout(add);
		 add.setLayout(layout);
		// Turn on automatically adding gaps between components
		   layout.setAutoCreateGaps(true);

		   // Turn on automatically creating gaps between components that touch
		   // the edge of the container and the container.
		   layout.setAutoCreateContainerGaps(true);
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
				if(first.getText().isEmpty()|| last.getText().isEmpty()){
					JOptionPane prompt = new JOptionPane("First or Last name can't be empty");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				int type=-1;
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
				if(type==-1){
					JOptionPane prompt = new JOptionPane("You have to select a user type");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				try{
				 temp=new User(first.getText(),last.getText(),Integer.parseInt(age.getText()),userType.values()[type-1],Integer.parseInt(ID.getText()),Integer.parseInt(pass.getText()));			
				}
			catch(NumberFormatException s){
				JOptionPane prompt = new JOptionPane("ID,password,age should be an integer");
				prompt.createDialog(frame, "Fail").setVisible(true);
				return;
				}
				try {
					system.addUser_to_system(temp);
					JOptionPane error = new JOptionPane("User Created");
					error.createDialog(frame, "Success").setVisible(true);
					
				} catch (IDexisterror e) {
					//do nothing
				}
				
				
			}
			
		});

		
		
		//with grouping
		// Create a sequential group for the horizontal axis.

		   GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		   // The sequential group in turn contains two parallel groups.
		   // One parallel group contains the labels, the other the text fields.
		   // Putting the labels in a parallel group along the horizontal axis
		   // positions them at the same x location.
		   //
		   // Variable indentation is used to reinforce the level of grouping.
		   hGroup.addGroup(layout.createParallelGroup().
		            addComponent(ID_l).addComponent(pass_l).addComponent(first_l).addComponent(last_l).addComponent(age_l).addComponent(type_l));
		   hGroup.addGroup(layout.createParallelGroup().
		            addComponent(ID).addComponent(pass).addComponent(first).addComponent(last).addComponent(age).addComponent(type_p).addComponent(submit));
		   layout.setHorizontalGroup(hGroup);

		   // Create a sequential group for the vertical axis.
		   GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		   // The sequential group contains two parallel groups that align
		   // the contents along the baseline. The first parallel group contains
		   // the first label and text field, and the second parallel group contains
		   // the second label and text field. By using a sequential group
		   // the labels and text fields are positioned vertically after one another.
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(ID_l).addComponent(ID));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(pass_l).addComponent(pass));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(first_l).addComponent(first));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(last_l).addComponent(last));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(age_l).addComponent(age));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(type_l).addComponent(type_p));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
		            addComponent(submit));
		   layout.setVerticalGroup(vGroup);
		  
		
		
		
		
		
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
		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
				}
		
		});
		
		//adding different card to cards
		cards.add(AddResourceSettings(),"Add Resource");
		cards.add(modifyResource(),"Modify Resource");
		
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
		//addRoom.setLayout(new BoxLayout(addRoom, BoxLayout.Y_AXIS));
		 GroupLayout roomlayout = new GroupLayout(addRoom);
		 addRoom.setLayout(roomlayout);
		 roomlayout.setAutoCreateGaps(true);
		 roomlayout.setAutoCreateContainerGaps(true);
		JPanel addEquipment = new JPanel();
		//addEquipment.setLayout(new BoxLayout(addEquipment, BoxLayout.Y_AXIS));
		
		JPanel addCourt = new JPanel();

		//addCourt.setLayout(new BoxLayout(addCourt, BoxLayout.Y_AXIS));
		
		String[] menuitems={"Room","Equipment","Sports_Courts"};
		JComboBox menun = new JComboBox(menuitems);
		menun.setMaximumSize(new Dimension(150,30));
		
		JLabel menu =new JLabel("Choose a Resource Type: ");

	
		
		//Room fields
		//setting up fields that can be multiused 
		//ID
		JLabel ID_l=new JLabel("ID                ");
		JTextField ID= new JTextField();
		ID.setColumns(15);
		JPanel ID_p=new JPanel();

		//RoomName
		JLabel roomname_l=new JLabel("Room Name");
		JTextField roomname= new JTextField();
		roomname.setColumns(15);
		JPanel roomname_p=new JPanel();

		//number of seats
		JLabel seatsnum_l=new JLabel("Number of Seats");
		JTextField seatsnum= new JTextField();
		seatsnum.setColumns(15);

		//Location
		JLabel loc_l=new JLabel("Location  ");
		JTextField loc= new JTextField();
		loc.setColumns(15);

		//projector availability
		JLabel proj_l=new JLabel("Is projector available?");
		JToggleButton proj_t = new JToggleButton("Yes");

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
		JLabel startdate=new JLabel("startDate");

		//end Date
		DatePicker enddate_l = new DatePicker();
		JLabel enddate=new JLabel("End Date");
		class SampleTimeVetoPolicy implements TimeVetoPolicy {

	        /**
	         * isTimeAllowed, Return true if a time should be allowed, or false if a time should be
	         * vetoed.
	         */
	        @Override
	        public boolean isTimeAllowed(LocalTime time) {
	        	
	        	if (time.getMinute()==30){
	        		return false;
	        	}
	        	return true;
	        }
	    }
		TimePickerSettings timesetting= new TimePickerSettings();
		
		//start Time
		TimePicker starttime_l = new TimePicker(timesetting);
		JLabel starttime=new JLabel("Start Time");
		
		
		//end Time
		TimePicker endtime_l = new TimePicker(timesetting);
		JLabel endtime=new JLabel("End Time");
		timesetting.setVetoPolicy(new SampleTimeVetoPolicy());
		
		
		//Allowance Time
		JLabel Allowance_l=new JLabel("Allowance Time ( in minutes)");
		String allowanceTimes[]={"1","2","3","4","5"};
		JComboBox Allowance=new JComboBox(allowanceTimes);
		Allowance.setSelectedIndex(0);
		
		
		
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

		//indoorcourt
		JLabel indoor_l=new JLabel("Is it Indoor?");
		JToggleButton indoor_t = new JToggleButton("Yes");

		//submit button
		JButton submit=new JButton("Submit");	
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		//submit action
		submit.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(loc.getText().isEmpty()|| startdate_l.getDate()==null || enddate_l.getDate()==null ||starttime_l.getTime()==null ||endtime_l.getTime()==null){
					JOptionPane prompt = new JOptionPane("Chosen loc,date, time and location can't be empty");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				if(startdate_l.getDate().isBefore(LocalDate.now())){
					JOptionPane prompt = new JOptionPane("Start Date should be after Current date");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				if(startdate_l.getDate().isAfter(enddate_l.getDate())){
					JOptionPane prompt = new JOptionPane("End Date can't be before Start Date");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				
				if(starttime_l.getTime().isAfter(endtime_l.getTime()) ||starttime_l.getTime().equals(endtime_l.getTime())){
					JOptionPane prompt = new JOptionPane("End Time can't be before Start Time");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}
				Resource temp = null;
				int type=-1;
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
				if(type==-1){
					JOptionPane prompt = new JOptionPane("Please choose a correct user type");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}

				try{
				//setting specific fields for each class type
				if(((String)menun.getSelectedItem()).equals("Room")){
					if(roomname.getText().isEmpty()){
						JOptionPane prompt = new JOptionPane("Room name can't be empty");
						prompt.createDialog(frame, "Fail").setVisible(true);
						return;
					}	
				temp=new Room();
				//setting common attributes
				
				temp.setID(Integer.parseInt(ID.getText()));
				
				temp.setResource_UserType(userType.values()[type-1]);
				temp.setStart_date(startdate_l.getDate());
				temp.setEnd_date(enddate_l.getDate());
				temp.setStart_Time(starttime_l.getTime());
				temp.setEnd_Time(endtime_l.getTime());
				temp.setResourceLocation(loc.getText());
				temp.setAllowance_time(Integer.parseInt((String)Allowance.getSelectedItem()));
				temp.setResource_Status(false);//by default a resource is running i.e. not under maintenance

				//finding the room type
				int roomtype=-1;
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
				if(roomtype==-1){
					JOptionPane prompt = new JOptionPane("Please choose a correct room type");
					prompt.createDialog(frame, "Fail").setVisible(true);
					return;
				}

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
					temp.setAllowance_time(Integer.parseInt((String)Allowance.getSelectedItem()));
					temp.setResource_Status(false);//by default a resource is running i.e. not under maintenance

					int equiptype=-1;
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
					if(equiptype==-1){
						JOptionPane prompt = new JOptionPane("Please choose a correct equipment type");
						prompt.createDialog(frame, "Fail").setVisible(true);
						return;
					}
					((Equipment)temp).setEquipmentType(equipmentType.values()[equiptype]);
				}
				else if(((String)menun.getSelectedItem()).equals("Sports_Courts")){
					if(courtname.getText().isEmpty()){
						JOptionPane prompt = new JOptionPane("Court name can't be empty");
						prompt.createDialog(frame, "Fail").setVisible(true);
						return;
					}	
					temp=new Sports_Courts();
					
					//common attributes
					temp.setID(Integer.parseInt(ID.getText()));
					temp.setResource_UserType(userType.values()[type-1]);
					temp.setStart_date(startdate_l.getDate());
					temp.setEnd_date(enddate_l.getDate());
					temp.setStart_Time(starttime_l.getTime());
					temp.setEnd_Time(endtime_l.getTime());
					temp.setResourceLocation(loc.getText());
					temp.setAllowance_time(Integer.parseInt((String)Allowance.getSelectedItem()));
					temp.setResource_Status(false);//by default a resource is running i.e. not under maintenance
					int courttype=-1;
					if(ba.isSelected())
						courttype=0;
					else if (tee.isSelected())
						courttype=1;
					else if (fo.isSelected())
						courttype=2;
					else if (sq.isSelected())
						courttype=3;
					if(courttype==-1){
						JOptionPane prompt = new JOptionPane("Please choose a correct court type");
						prompt.createDialog(frame, "Fail").setVisible(true);
						return;
					}
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
				
				try {
					system.add_resource_to_array(temp);
					JOptionPane prompt = new JOptionPane("Resource Created Successfully");
					prompt.createDialog(frame, "Success").setVisible(true);
				} catch (IDexisterror e) {
				}
				
			}
			
			
		});
		
		//initial display of fields for room
//		addRoom.add(menu);
//		addRoom.add(ID_p);
//		addRoom.add(roomname_p);
//		addRoom.add(loc_p);
//		addRoom.add(Allowance_p);
//		addRoom.add(seatsnum_p);
//		addRoom.add(proj_p);
//		addRoom.add(startdate);
//		addRoom.add(enddate);
//		addRoom.add(starttime);
//		addRoom.add(endtime);
//		addRoom.add(type_p);
//		addRoom.add(Room_p);				
//		addRoom.add(submit);
		
		// Create a sequential group for the horizontal axis.

		   GroupLayout.SequentialGroup hGroup = roomlayout.createSequentialGroup();

		   // The sequential group in turn contains two parallel groups.
		   // One parallel group contains the labels, the other the text fields.
		   // Putting the labels in a parallel group along the horizontal axis
		   // positions them at the same x location.
		   //
		   // Variable indentation is used to reinforce the level of grouping.
		   hGroup.addGroup(roomlayout.createParallelGroup().addComponent(menu).
		            addComponent(ID_l).addComponent(roomname_l)
		            .addComponent(loc_l).addComponent(Allowance_l)
		            .addComponent(seatsnum_l).addComponent(proj_l)
		            .addComponent(startdate).addComponent(enddate)
		            .addComponent(starttime).addComponent(endtime)
		            .addComponent(type_l).addComponent(Room_l)
				   
				   
				   );
		   hGroup.addGroup(roomlayout.createParallelGroup().addComponent(menun).
				   addComponent(ID).addComponent(roomname)
		            .addComponent(loc).addComponent(Allowance)
		            .addComponent(seatsnum).addComponent(proj_t)
		            .addComponent(startdate_l).addComponent(enddate_l)
		            .addComponent(starttime_l).addComponent(endtime_l)
		            .addComponent(type_p).addComponent(Room_p)
		            .addComponent(submit));
		   
		   roomlayout.setHorizontalGroup(hGroup);

		   // Create a sequential group for the vertical axis.
		   GroupLayout.SequentialGroup vGroup = roomlayout.createSequentialGroup();

		   // The sequential group contains two parallel groups that align
		   // the contents along the baseline. The first parallel group contains
		   // the first label and text field, and the second parallel group contains
		   // the second label and text field. By using a sequential group
		   // the labels and text fields are positioned vertically after one another.
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		           addComponent(menu).addComponent(menun));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(ID_l).addComponent(ID));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(roomname_l).addComponent(roomname));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(loc_l).addComponent(loc));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(Allowance_l).addComponent(Allowance));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(seatsnum_l).addComponent(seatsnum));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(proj_l).addComponent(proj_t));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(startdate).addComponent(startdate_l));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(enddate).addComponent(enddate_l));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(starttime).addComponent(starttime_l));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(endtime).addComponent(endtime_l));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(type_l).addComponent(type_p));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(Room_l).addComponent(Room_p));
		   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
		            addComponent(submit));
		   roomlayout.setVerticalGroup(vGroup);
		
	
		
		
		
		//adding cards to addCards
		addcards.add(addRoom,"Room");
		addcards.add(addEquipment,"Equipment");
		addcards.add(addCourt,"Sports_Courts");
		
		menun.setSelectedItem("Room");
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(addcards.getLayout());
				addRoom.setLayout(null);
				addEquipment.setLayout(null);
				addCourt.setLayout(null);
				if(((String)evt.getItem()).equals("Room")){
					GroupLayout roomlayout=new GroupLayout(addRoom);
					 addRoom.setLayout(roomlayout);
					 roomlayout.setAutoCreateGaps(true);
					 roomlayout.setAutoCreateContainerGaps(true);
					// Create a sequential group for the horizontal axis.
					   
					   GroupLayout.SequentialGroup hGroup = roomlayout.createSequentialGroup();

					   // The sequential group in turn contains two parallel groups.
					   // One parallel group contains the labels, the other the text fields.
					   // Putting the labels in a parallel group along the horizontal axis
					   // positions them at the same x location.
					   //
					   // Variable indentation is used to reinforce the level of grouping.
					   hGroup.addGroup(roomlayout.createParallelGroup().addComponent(menu).
					            addComponent(ID_l).addComponent(roomname_l)
					            .addComponent(loc_l).addComponent(Allowance_l)
					            .addComponent(seatsnum_l).addComponent(proj_l)
					            .addComponent(startdate).addComponent(enddate)
					            .addComponent(starttime).addComponent(endtime)
					            .addComponent(type_l).addComponent(Room_l)
							   
							   
							   );
					   hGroup.addGroup(roomlayout.createParallelGroup().addComponent(menun).
							   addComponent(ID).addComponent(roomname)
					            .addComponent(loc).addComponent(Allowance)
					            .addComponent(seatsnum).addComponent(proj_t)
					            .addComponent(startdate_l).addComponent(enddate_l)
					            .addComponent(starttime_l).addComponent(endtime_l)
					            .addComponent(type_p).addComponent(Room_p)
					            .addComponent(submit));
					   
					   roomlayout.setHorizontalGroup(hGroup);

					   // Create a sequential group for the vertical axis.
					   GroupLayout.SequentialGroup vGroup = roomlayout.createSequentialGroup();

					   // The sequential group contains two parallel groups that align
					   // the contents along the baseline. The first parallel group contains
					   // the first label and text field, and the second parallel group contains
					   // the second label and text field. By using a sequential group
					   // the labels and text fields are positioned vertically after one another.
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					           addComponent(menu).addComponent(menun));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(ID_l).addComponent(ID));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(roomname_l).addComponent(roomname));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(loc_l).addComponent(loc));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(Allowance_l).addComponent(Allowance));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(seatsnum_l).addComponent(seatsnum));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(proj_l).addComponent(proj_t));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(startdate).addComponent(startdate_l));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(enddate).addComponent(enddate_l));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(starttime).addComponent(starttime_l));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(endtime).addComponent(endtime_l));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(type_l).addComponent(type_p));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(Room_l).addComponent(Room_p));
					   vGroup.addGroup(roomlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(submit));
					   roomlayout.setVerticalGroup(vGroup);
					   

				}
				else if (((String)evt.getItem()).equals("Equipment")){
					 GroupLayout equiplayout = new GroupLayout(addEquipment);
					 addEquipment.setLayout(equiplayout);
					 equiplayout.setAutoCreateGaps(true);
					 equiplayout.setAutoCreateContainerGaps(true);
					//adding panels to addEquipment
	
					// Create a sequential group for the horizontal axis.

					   GroupLayout.SequentialGroup hGroup = equiplayout.createSequentialGroup();

					   // The sequential group in turn contains two parallel groups.
					   // One parallel group contains the labels, the other the text fields.
					   // Putting the labels in a parallel group along the horizontal axis
					   // positions them at the same x location.
					   //
					   // Variable indentation is used to reinforce the level of grouping.
					   hGroup.addGroup(equiplayout.createParallelGroup().addComponent(menu).
					            addComponent(ID_l)
					            .addComponent(loc_l).addComponent(Allowance_l)
					            .addComponent(startdate).addComponent(enddate)
					            .addComponent(starttime).addComponent(endtime)
					            .addComponent(type_l).addComponent(equip_l)
							   
							   
							   );
					   hGroup.addGroup(equiplayout.createParallelGroup().addComponent(menun).
							   addComponent(ID)
					            .addComponent(loc).addComponent(Allowance)					           
					            .addComponent(startdate_l).addComponent(enddate_l)
					            .addComponent(starttime_l).addComponent(endtime_l)
					            .addComponent(type_p).addComponent(equip_p)
					            .addComponent(submit));
					   
					   equiplayout.setHorizontalGroup(hGroup);

					   // Create a sequential group for the vertical axis.
					   GroupLayout.SequentialGroup vGroup = equiplayout.createSequentialGroup();

					   // The sequential group contains two parallel groups that align
					   // the contents along the baseline. The first parallel group contains
					   // the first label and text field, and the second parallel group contains
					   // the second label and text field. By using a sequential group
					   // the labels and text fields are positioned vertically after one another.
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					           addComponent(menu).addComponent(menun));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(ID_l).addComponent(ID));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(loc_l).addComponent(loc));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(Allowance_l).addComponent(Allowance));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(startdate).addComponent(startdate_l));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(enddate).addComponent(enddate_l));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(starttime).addComponent(starttime_l));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(endtime).addComponent(endtime_l));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(type_l).addComponent(type_p));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(equip_l).addComponent(equip_p));
					   vGroup.addGroup(equiplayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(submit));
					   equiplayout.setVerticalGroup(vGroup);

				}
				else if (((String)evt.getItem()).equals("Sports_Courts")){
					//setting layout of sports court
					 GroupLayout courtlayout = new GroupLayout(addCourt);
					 addCourt.setLayout(courtlayout);
					 courtlayout.setAutoCreateGaps(true);
					 courtlayout.setAutoCreateContainerGaps(true);
					
					// Create a sequential group for the horizontal axis.
					   
					   GroupLayout.SequentialGroup hGroup = courtlayout.createSequentialGroup();

					   // The sequential group in turn contains two parallel groups.
					   // One parallel group contains the labels, the other the text fields.
					   // Putting the labels in a parallel group along the horizontal axis
					   // positions them at the same x location.
					   //
					   // Variable indentation is used to reinforce the level of grouping.
					   hGroup.addGroup(courtlayout.createParallelGroup().addComponent(menu).
					            addComponent(ID_l).addComponent(courtname_l)
					            .addComponent(loc_l).addComponent(Allowance_l)
					            .addComponent(light_l).addComponent(indoor_l)
					            .addComponent(startdate).addComponent(enddate)
					            .addComponent(starttime).addComponent(endtime)
					            .addComponent(type_l).addComponent(court_l)
							   
							   
							   );
					   hGroup.addGroup(courtlayout.createParallelGroup().addComponent(menun).
							   addComponent(ID).addComponent(courtname)
					            .addComponent(loc).addComponent(Allowance)
					            .addComponent(light_t).addComponent(indoor_t)
					            .addComponent(startdate_l).addComponent(enddate_l)
					            .addComponent(starttime_l).addComponent(endtime_l)
					            .addComponent(type_p).addComponent(court_p)
					            .addComponent(submit));
					   
					   courtlayout.setHorizontalGroup(hGroup);

					   // Create a sequential group for the vertical axis.
					   GroupLayout.SequentialGroup vGroup = courtlayout.createSequentialGroup();

					   // The sequential group contains two parallel groups that align
					   // the contents along the baseline. The first parallel group contains
					   // the first label and text field, and the second parallel group contains
					   // the second label and text field. By using a sequential group
					   // the labels and text fields are positioned vertically after one another.
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					           addComponent(menu).addComponent(menun));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(ID_l).addComponent(ID));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(courtname_l).addComponent(courtname));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(loc_l).addComponent(loc));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(Allowance_l).addComponent(Allowance));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(light_l).addComponent(light_t));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(indoor_l).addComponent(indoor_t));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(startdate).addComponent(startdate_l));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(enddate).addComponent(enddate_l));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(starttime).addComponent(starttime_l));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(endtime).addComponent(endtime_l));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(type_l).addComponent(type_p));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(court_l).addComponent(court_p));
					   vGroup.addGroup(courtlayout.createParallelGroup(Alignment.BASELINE).
					            addComponent(submit));
					   courtlayout.setVerticalGroup(vGroup);
					   
					

				}
		        cl.show(addcards, (String)evt.getItem());
				}
		
		});
		
		return addcards;
	}
	
	JPanel modifyResource(){

		JPanel modify= new JPanel();
		//modify.setLayout(new BoxLayout(modify, BoxLayout.Y_AXIS));
		//ID
		JLabel ID_lm=new JLabel("ID of resource to change");
		JTextField IDm= new JTextField();
		IDm.setColumns(15);
		JPanel ID_pm=new JPanel();
		ID_pm.add(ID_lm);
		ID_pm.add(IDm);
		
		JButton submitm = new JButton("Submit");
		submitm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (system.Does_resourse_exist(Integer.parseInt(IDm.getText()))!=false){
					//password
					
					JLabel Maintenance=new JLabel("Under Maintenance?");
					JToggleButton MaintenancebtnYes= new JToggleButton("Yes");
					JPanel Maintenance_pn=new JPanel();
					Maintenance_pn.add(Maintenance);
					Maintenance_pn.add(MaintenancebtnYes);
					JDialog d= new JDialog(frame,"New Password");
					d.setLocationRelativeTo(frame);
					JButton submitms = new JButton("Submit");
					submitms.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							Resource temp;
							temp=system.get_resource_of_id(Integer.parseInt(IDm.getText()));
							if(MaintenancebtnYes.isSelected() == true){
								temp.setResource_Status(true);
								JOptionPane error = new JOptionPane("Resource under maintanece");
								error.createDialog(frame, "Success").setVisible(true);
							}
							else 
							{
								temp.setResource_Status(false);
								JOptionPane error = new JOptionPane("Resource is not under maintanece");
								error.createDialog(frame, "Success").setVisible(true);
								
							}
							d.dispose();
						}	
					});
					d.add(Maintenance_pn,BorderLayout.PAGE_START);
					d.add(submitms,BorderLayout.AFTER_LAST_LINE);
					d.setSize(300, 200);
					d.setVisible(true);
					
					
				}
				else{
					JOptionPane error = new JOptionPane("Rresource Not Found");
					error.createDialog(frame, "Fail").setVisible(true);
				}
				
				
			}
			
			
		});		
		//adding panels to card
		modify.add(new JLabel(" "));
		modify.add(ID_pm);
		modify.add(submitm);
		
		modify.add(new JLabel(" "));

		return modify;	
	}
	
	
}
