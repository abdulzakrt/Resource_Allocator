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
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ReservationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReservationPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(rdbtnNewRadioButton_1);

	}

}
