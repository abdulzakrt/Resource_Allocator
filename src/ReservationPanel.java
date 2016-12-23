import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;

public class ReservationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReservationPanel() {
		setLayout(new MigLayout("", "[69px][121px]", "[23px][][]"));
		
		JToggleButton nine = new JToggleButton("9:00am");
		nine.setForeground(Color.BLACK);
		add(nine, "cell 0 0,alignx left,aligny center");
		nine.setBackground(Color.RED);
		nine.setDisabledSelectedIcon(new ImageIcon(ReservationPanel.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/vk-light.png")));
		JToggleButton ten = new JToggleButton("10:00am");
		add(ten, "cell 0 1,alignx left,aligny center");

	}

}
