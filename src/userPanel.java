import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JFrame;


import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class userPanel extends JFrame{
	
	JFrame frame = this;
	JPanel Calendar_panel1, Calendar_panel, Resource_panel, startdate_panel;
	int num_of_equips=0, num_of_rooms=0, num_of_courts=0;
	JToggleButton[] CalendarTimes;
	DatePicker strtdte;
	Resource_Management_System system;
	User u;
	String resource_name, equip_name, room_name, court_name;
	LocalDate date_of_res;
	/**
	 * Create the panel.
	 */
	public userPanel(Resource_Management_System system_obj, User x) {
		u = x;
		system = system_obj;
		frame.setLocation(340, 90);
		//frame.setSize(227, 290);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Calendar_panel1 = new JPanel(new FlowLayout());
		Calendar_panel = new JPanel(new FlowLayout());
		Resource_panel = new JPanel(new FlowLayout());
		startdate_panel = new JPanel(new FlowLayout());
		//JPanel panel= new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	frame.dispose();
		        	//system.closeSystem();
		            //System.exit(0);
		        }
		    }
		});		
		//setLayout(null);
		//Start Date Label
		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setBounds(20, 144, 63, 14);
		startdate_panel.add(lblStartDate, BorderLayout.WEST);
		
		
		
		//Resource Label Panel
		JLabel lblResource = new JLabel("Resource: ");
		lblResource.setBounds(20, 28, 88, 22);
		Resource_panel.add(lblResource);
		
		//Drop down to display resource categories
		JPanel cards = new JPanel(new CardLayout());
		String[] menuitems={"Room","Equipments","Court"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSelectedIndex(0);
		//JPanel ResourcesCategory = new JPanel();
		Resource_panel.setToolTipText("choose a resource");
		Resource_panel.setBounds(77, 26, 116, 30);
		Resource_panel.add(menu);
		//Resource_panel.add(ResourcesCategory);
		cards.setBounds(10, 67, 430, 66);
		JPanel Room_card = DisplayRooms();
		JPanel Equipment_card = DisplayEquipments();
		JPanel Courts_card = DisplayCourts();
		cards.add(Room_card, "Room");
		cards.add(Equipment_card, "Equipments");
		cards.add(Courts_card, "Court");
		Resource_panel.add(cards);	

		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
		        if(((String)evt.getItem()=="Equipments")&&(num_of_equips==0))
		        	strtdte.setDate(null);
		        if(((String)evt.getItem()=="Room")&&(num_of_rooms==0))
		        	strtdte.setDate(null);
		        if(((String)evt.getItem()=="Court")&&(num_of_courts==0))
		        	strtdte.setDate(null);
		        for(int k =0; k<24;k++)
				{
					CalendarTimes[k].setEnabled(false);
				}
				}
		
		});
			
		
		
		
		//JPanel panel_4 = new JPanel();
		Calendar_panel.setBounds(20, 190, 495, 253);
		//frame.getContentPane().add(panel_4);
		//Calendar_panel.setLayout(null);
		
		//JLabel lblReservationPanel = DefaultComponentFactory.getInstance().createTitle("Reservation Panel:");
		//lblReservationPanel.setBounds(10, 0, 116, 14);
		//frame.getContentPane().add(lblReservationPanel);
		int s=0,y=0;
		JToggleButton[] Calendar = new JToggleButton[24];
		for(int i=0; i<24; i++)
		{
			if((i)%7 == 0)
			{
				s=0;
				if(i!=0)
					y+=69;
			}
			
			
			if(i<9)
				Calendar[i]=new JToggleButton("0"+(i+1)+":00");
			else 
			{
				Calendar[i]=new JToggleButton((i+1)+":00");
			}
			Calendar[i].setBounds(s,y,71,23);
			Calendar_panel.add(Calendar[i], BorderLayout.CENTER);
			Calendar[i].setEnabled(false);
			//Calendar[i].setBackground(Color.red);
				
			s+=70;
		}
		CalendarTimes = Calendar;
		//Calendar_panel1.add(Calendar_panel, BorderLayout.CENTER);
		//Calendar_panel.add(Calendar);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(224, 503, 89, 23);
		//btnSubmit.setMaximumSize(new Dimension(528, 15));
		JPanel submitPanel = new JPanel();
		submitPanel.add(btnSubmit, BorderLayout.AFTER_LAST_LINE);
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(resource_name==null)
						throw new Exception();
				}
				catch (Exception e)
				{
					JOptionPane error = new JOptionPane("you didnt make a Reservation (empty reservation)!");
					
					error.createDialog("error").setVisible(true);
					return;
				}
				try{
				
					if(system.check_user_reserved_on_date(Integer.parseInt(resource_name), date_of_res, u.getLog_in_ID())==true)
						{throw new Exception();}
					}
					catch(Exception e){					
					
						for(int j=0;j<24;j++)
						{	
									Calendar[j].setSelected(false);
									Calendar[j].setEnabled(false);
									//Calendar[j].setBackground(Color.red);
						}
						JOptionPane success = new JOptionPane("user "+u.getLog_in_ID()+" is not allowed to reserve resource "+resource_name+" on "+date_of_res);
						success.createDialog("Fail").setVisible(true);
						strtdte.setDate(null);
						return;
						
					}
				//give feed back on the reservation
				Resource temp=null;
				for(int i=0; i<system.get_resource_count();i++)
				{
					if(system.get_resource_of_index(i) instanceof Room)
					{
						Room temp1 = (Room)system.get_resource_of_index(i);
						if(temp1.getID()==(Integer.parseInt(resource_name)))
						{
							temp=temp1;
						}
						
					}
					else if(system.get_resource_of_index(i) instanceof Sports_Courts)
					{
						Sports_Courts temp1 = (Sports_Courts)system.get_resource_of_index(i);
						if(temp1.getID()==(Integer.parseInt(resource_name)))
						{
							temp=temp1;
						}
					}
					else if(system.get_resource_of_index(i) instanceof Equipment)
					{
						Equipment temp1 = (Equipment)system.get_resource_of_index(i);
						if(temp1.getID()==(Integer.parseInt(resource_name)))
						{
							temp=temp1;
						}
					}
				}
				int num_of_hours = (int)temp.getAllowance_time();
				num_of_hours/=60;
				
				int hours_selected=0;
				
				try{
					for(int j=0; j<24;j++)
					{
						if(Calendar[j].isSelected())
						{
							hours_selected++;
						}
					}
					if(hours_selected>num_of_hours)
					{
						throw new Exception();
					}
				}
				catch (Exception e){
					
					JOptionPane error = new JOptionPane("number of hours has to be less than or equal to "+num_of_hours);
					
					error.createDialog("error").setVisible(true);
					for(int i=0;i<24;i++)
					{
						Calendar[i].setSelected(false);
						Calendar[i].setEnabled(false);
					}
					strtdte.setDate(null);
					return;
					
				}
				try{
					int space=0, zeros=0, selected=0;
					for(int j=0; j<24;j++)
					{
						if(Calendar[j].isSelected())
						{
							selected=1;
							if(zeros>0)
								selected=0;

						}
						if(Calendar[j].isSelected()==false && selected==1) zeros++;
						if(selected==0&&zeros>0)space=1;
						if(space>0){throw new Exception();}
					}
				}
				catch (Exception e){
					
					JOptionPane error = new JOptionPane("Non-sequential reservations are not allowed! ");
					
					error.createDialog("error").setVisible(true);
					
					for(int i=0;i<24;i++)
					{
						Calendar[i].setSelected(false);
						Calendar[i].setEnabled(false);
					}
					strtdte.setDate(null);
					return;
				}
				LocalTime[] hours_to_reserve;
				if(hours_selected<num_of_hours)
					hours_to_reserve =  new LocalTime[hours_selected];
				hours_to_reserve = new LocalTime[num_of_hours];
				int i=0;
				for(int j=0; j<24;j++)
				{
					if(Calendar[j].isSelected())
					{
						if(j<9)
						{
							hours_to_reserve[i]= LocalTime.parse("0"+(j+1)+":00");
							i++;
						}
						else 
						{
							hours_to_reserve[i] = LocalTime.parse((j+1)+":00");
							i++;
						}
					}
				}//two last cases that causes errors is when he selects two hours with space in between or multiple hours with space in between 
				try{//and we need to check if the user booked in this day or not previously
					if(ChronoUnit.MINUTES.between(hours_to_reserve[0], hours_to_reserve[hours_to_reserve.length-1])>temp.getAllowance_time())
						throw new Exception();
				}
				catch (Exception e)
				{
					JOptionPane error = new JOptionPane("number of hours has to be less than or equal to "+num_of_hours);
					
					error.createDialog("error").setVisible(true);
					for(int j=0;j<24;j++)
					{
						Calendar[j].setSelected(false);
						Calendar[j].setEnabled(false);
					}
					strtdte.setDate(null);
					return;
				}
				system.Reserve(u, temp, hours_to_reserve, date_of_res);
				JOptionPane success = new JOptionPane("Reservation Successful");
				success.createDialog("Success").setVisible(true);
				for(i=0;i<24;i++)
				{
					Calendar[i].setSelected(false);
					Calendar[i].setEnabled(false);
				}
				strtdte.setDate(null);
			}
		});
		//Start Date Picker inside Start Date Panel
		DatePicker startdate_l = new DatePicker();
		strtdte=startdate_l;
		//JPanel StartDatePanel = new JPanel();
		startdate_panel.setBounds(77, 140, 162, 39);
		startdate_panel.add(startdate_l, BorderLayout.EAST);
		//startdate_panel.add(lblStartDate);
		//startdate_panel.add(StartDatePanel);
		startdate_l.addDateChangeListener(new DateChangeListener()
				{
					@Override
					public void dateChanged(DateChangeEvent event) {
						
						// TODO Auto-generated method stub
						LocalDate x = event.getNewDate();
						//System.out.println(x);
						if(x!=null){
						date_of_res= x;
						String y = (String)menu.getSelectedItem();
						try{
						if(y=="Equipments")
						{
							 resource_name=equip_name;
						}
						else if(y=="Room")
						{
							 resource_name=room_name;
						}
						else if(y=="Court")
						{
							 resource_name=court_name;
						}
						
						if(resource_name==null)
							throw new Exception();
						}
						catch(Exception e)
						{
							JOptionPane error = new JOptionPane("Resource "+y+" is empty!");
							
							error.createDialog("error").setVisible(true);
							strtdte.setDate(null);
							return;
						}
						//int not_allowed_flag=0;
						Resource r= system.get_resource_of_id(Integer.parseInt(resource_name));
						LocalTime[] times_reserved = system.check_source(Integer.parseInt(resource_name), date_of_res);
						
						if(r.getStart_date().isAfter(date_of_res) || r.getend_date().isBefore(date_of_res)||(date_of_res.isBefore(LocalDate.now()))){
							for(int j =0; j<24;j++)
							{
								Calendar[j].setEnabled(false);
							}
							strtdte.setDate(null);
							JOptionPane success = new JOptionPane("Resource not available on Selected Date");
							success.createDialog("Fail").setVisible(true);
						}
						else{
							
							for(int i=0; i<24;i++)
							{
								LocalTime temp;
								if(i<9)
									temp = LocalTime.parse("0"+(i+1)+":00");
								else if(i<23)
									temp = LocalTime.parse((i+1)+":00");
								else 
									temp = LocalTime.parse("00:00");
								if ((r.getStart_Time().isBefore(temp) && r.getEnd_Time().isAfter(temp)) || r.getEnd_Time().equals(temp) || r.getStart_Time().equals(temp))
								{	
									Calendar[i].setEnabled(true);
									Calendar[i].setBackground(UIManager.getColor ( "Button.background" ));
								}
								
								for(int j=0;j<24;j++)
								{
									if(times_reserved[j]!=null)
									{
										if(times_reserved[j].equals(temp))
										{
											Calendar[i].setBackground(Color.RED);
											Calendar[i].setEnabled(false);
										}	
									}
								}
								
							}
							
							
						}
					}}
				});
		
		//frame.pack();
		frame.setSize(528, 600);
		GridLayout grid = new GridLayout(4,0);
		frame.getContentPane().setLayout(grid);
		//frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
		frame.getContentPane().add(Resource_panel);
		frame.getContentPane().add(startdate_panel);
		frame.getContentPane().add(Calendar_panel);
		frame.getContentPane().add(submitPanel);
		frame.setVisible(true);

	}
	//To display all resources of type Room in JcomboBox
	JPanel DisplayRooms(){
		
		//JPanel roomCards= new JPanel(new CardLayout());
		JPanel roomCards = new JPanel();		
		//roomCards.setToolTipText("choose the resource u want");
		String[] menuitems=new String[system.get_resource_count()];
		int j=0;
		for(int i=0; i<system.get_resource_count();i++)
		{
			if(system.get_resource_of_index(i) instanceof Room)
			{
				Room temp = (Room)system.get_resource_of_index(i);
				if(temp.getUser_ResourceType(u.getUser_type())==u.getUser_type())
				{
					menuitems[j] = temp.getID()+""/*+" "+temp.getID()*/;
					j++;
					num_of_rooms++;
					
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		
		roomCards.add(menun);
		if(num_of_rooms!=0)
			room_name=(String)menun.getSelectedItem();
		else room_name=null;
			 
		//menun.setMaximumSize(new Dimension(150,30));
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				room_name = (String)evt.getItem();
				for(int k =0; k<24;k++)
				{
					CalendarTimes[k].setEnabled(false);
				}
				strtdte.setDate(null);
				}
		
		});
		return roomCards;
	}
	//To display all resources of type Equipment in JcomboBox
	JPanel DisplayEquipments(){
		
		//JPanel roomCards= new JPanel(new CardLayout());
		JPanel EquipmentsCards = new JPanel();		
		String[] menuitems=new String[system.get_resource_count()];
		int j=0;
		for(int i=0; i<system.get_resource_count();i++)
		{
			if(system.get_resource_of_index(i) instanceof Equipment)
			{
				Equipment temp = (Equipment)system.get_resource_of_index(i);
				if(temp.getUser_ResourceType(u.getUser_type())==u.getUser_type())
				{
					menuitems[j] =(temp.getID())+""/*+temp.getID()*/;
					j++;
					num_of_equips++;
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		EquipmentsCards.add(menun);
		if(num_of_equips!=0)
			equip_name=(String)menun.getSelectedItem(); 
		else equip_name=null;
			
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				equip_name = (String)evt.getItem();
				for(int k =0; k<24;k++)
				{
					CalendarTimes[k].setEnabled(false);
				}
				
				
				strtdte.setDate(null);
				}
		
		});
		//menun.setMaximumSize(new Dimension(150,30));
		
		return EquipmentsCards;
	}
	//To display all resources of type Court in JcomboBox
	JPanel DisplayCourts(){
		
		//JPanel roomCards= new JPanel(new CardLayout());
		JPanel CourtsCards = new JPanel();		
		String[] menuitems=new String[system.get_resource_count()];
		int j=0;
		for(int i=0; i<system.get_resource_count();i++)
		{
			if(system.get_resource_of_index(i) instanceof Sports_Courts)
			{
				Sports_Courts temp = (Sports_Courts)system.get_resource_of_index(i);
				if((temp.getUser_ResourceType(u.getUser_type())==u.getUser_type())&&(temp.getResource_Status()==true))
				{
					menuitems[j] = temp.getID()+"";
					j++;
					num_of_courts++;
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		CourtsCards.add(menun);
		//menun.setMaximumSize(new Dimension(150,30));
		if(num_of_courts!=0)
			court_name=(String)menun.getSelectedItem(); 

		else court_name=null;
			
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				court_name = (String)evt.getItem();
				for(int k =0; k<24;k++)
				{
					CalendarTimes[k].setEnabled(false);
				}
				strtdte.setDate(null);

				}
		
		});
		return CourtsCards;
	}		
	
}
