import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.LocalTime;

public class userPanel extends JPanel {

	Resource_Management_System system;
	User u;
	String resource_name;
	LocalDate date_of_res;
	/**
	 * Create the panel.
	 */
	public userPanel(Resource_Management_System system_obj, User x) {
		u = x;
		system = system_obj;
		setLayout(null);
		//Start Date Label
		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setBounds(20, 144, 63, 14);
		add(lblStartDate);
		
		
		
		//Resource Label Panel
		JLabel lblResource = new JLabel("Resource: ");
		lblResource.setBounds(20, 28, 88, 22);
		add(lblResource);
		
		//Drop down to display resource categories
		JPanel cards = new JPanel(new CardLayout());
		String[] menuitems={"Room","Equipments","Court"};
		JComboBox menu = new JComboBox(menuitems);
		menu.setSelectedIndex(0);
		JPanel ResourcesCategory = new JPanel();
		ResourcesCategory.setToolTipText("choose a resource");
		ResourcesCategory.setBounds(77, 26, 116, 30);
		ResourcesCategory.add(menu);
		add(ResourcesCategory);
		

		menu.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, (String)evt.getItem());
				}
		
		});
		cards.setBounds(10, 67, 430, 66);
		cards.add(DisplayRooms(), "Room");
		cards.add(DisplayEquipments(), "Equipments");
		cards.add(DisplayCourts(), "Court");
		add(cards);		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 190, 495, 253);
		add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblReservationPanel = DefaultComponentFactory.getInstance().createTitle("Reservation Panel:");
		lblReservationPanel.setBounds(10, 0, 116, 14);
		add(lblReservationPanel);
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
			panel_4.add(Calendar[i]);
			Calendar[i].setEnabled(false);
			//Calendar[i].setBackground(Color.red);
				
			s+=70;
		}
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(224, 503, 89, 23);
		add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//give feed back on the reservation
				Resource temp;
				int t1_flag=0;
				LocalTime t1=LocalTime.parse("00:00"), t2=LocalTime.parse("00:00");
				for(int j=0; j<24;j++)
				{
					if(Calendar[j].isSelected())
					{
						if(t1_flag==0)
						{
							if(j<9)
								t1 = LocalTime.parse("0"+(j+1)+":00");
							else 
								t1 = LocalTime.parse((j+1)+":00");
							t1_flag=1;
						}
						else if(t1_flag==1)
						{
							if(j<9)
								t2 = LocalTime.parse("0"+(j+1)+":00");
							else 
								t2 = LocalTime.parse((j+1)+":00");
							t1_flag=1;
							break;
						}
					}
				}
				for(int i=0; i<system.get_resource_count();i++)
				{
					if(system.get_resource_of_index(i) instanceof Room)
					{
						Room temp1 = (Room)system.get_resource_of_index(i);
						if(temp1.getID()==(Integer.parseInt(resource_name)))
						{
							system.Reserve(u, temp1, t1, t2, date_of_res);
							JOptionPane success = new JOptionPane("Reservation Successful");
							success.createDialog("Success").setVisible(true);
						}
						
					}
					else if(system.get_resource_of_index(i) instanceof Sports_Courts)
					{
						Sports_Courts temp1 = (Sports_Courts)system.get_resource_of_index(i);
						if(temp1.getID()==(Integer.parseInt(resource_name)))
						{
							system.Reserve(u, temp1, t1, t2, date_of_res);
							JOptionPane success = new JOptionPane("Reservation Successful");
							success.createDialog("Success").setVisible(true);
						}
					}/*BA3BOOOOOS MNAYYAK
					else if(system.get_resource_of_index(i) instanceof Room)
					{
						//to be continued
					}*/
				}
				
			}
			
			
			
		});
		//Start Date Picker inside Start Date Panel
		DatePicker startdate_l = new DatePicker();
		JPanel StartDatePanel = new JPanel();
		StartDatePanel.setBounds(77, 140, 162, 39);
		StartDatePanel.add(startdate_l);
		add(StartDatePanel);
		startdate_l.addDateChangeListener(new DateChangeListener()
				{

					@Override
					public void dateChanged(DateChangeEvent event) {
					
						// TODO Auto-generated method stub
						LocalDate x = event.getNewDate();
						//System.out.println(x);
						date_of_res= x;
						Resource r= system.get_resource_of_id(Integer.parseInt(resource_name));
						LocalTime[] times_reserved = system.check_source(Integer.parseInt(resource_name), x);
						
						if(r.getStart_date().isAfter(x) || r.getend_date().isBefore(x)){
							for(int j =0; j<24;j++)
							{
								Calendar[j].setEnabled(false);
							}
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
								if (r.getStart_Time().isBefore(temp) && r.getEnd_Time().isAfter(temp) || r.getEnd_Time().equals(temp) || r.getStart_Time().equals(temp))
								Calendar[i].setEnabled(true);
								Calendar[i].setBackground(UIManager.getColor ( "Button.background" ));
								for(int j=0;j<24;j++)
								{
									if(times_reserved[j]!=null)
									{
										if(times_reserved[j].equals(temp))
										{
											Calendar[i].setEnabled(false);
											Calendar[i].setBackground(Color.red);
										}
										
											
									}
								}
								
								System.out.println(temp);
								
							}
						}
						
						
					}
			
				});

	}
	//To display all resources of type Room
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
				if(temp.getResource_UserType(u.getUser_type())==u.getUser_type())
				{
					menuitems[j] = temp.getID()+""/*+" "+temp.getID()*/;
					j++;
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		roomCards.add(menun);
		resource_name=(String)menun.getSelectedItem();
		//menun.setMaximumSize(new Dimension(150,30));
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				resource_name = (String)evt.getItem();
				}
		
		});
		return roomCards;
	}
	//To display all resources of type Equipment
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
				if(temp.getResource_UserType(u.getUser_type())==u.getUser_type())
				{
					menuitems[j] =(temp.getID())+""/*+temp.getID()*/;
					j++;
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		EquipmentsCards.add(menun);
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				resource_name = (String)evt.getItem();
				}
		
		});
		//menun.setMaximumSize(new Dimension(150,30));
		
		return EquipmentsCards;
	}
	//To display all resources of type Court
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
				if(temp.getResource_UserType(u.getUser_type())==u.getUser_type())
				{
					menuitems[j] = temp.getID()+""/*+temp.getID()*/;
					j++;
				}
			}
		}
		JComboBox menun = new JComboBox(menuitems);
		CourtsCards.add(menun);
		//menun.setMaximumSize(new Dimension(150,30));
		menun.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				resource_name = (String)evt.getItem();
				}
		
		});
		return CourtsCards;
	}		
	
}
