package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import comp401sushi.Plate;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver, ActionListener {

	private Belt belt;
	private JButton[] belt_labels;
	public ArrayList<JFrame> button = new ArrayList<JFrame>();
	private JButton[] plabel2 = new JButton[20];
	private JFrame[] mainframe = new JFrame[20];
	private JPanel[] mainpanels = new JPanel[20];

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_labels = new JButton[belt.getSize()];
		
		for (int i = 0; i < belt.getSize(); i++) {
			JButton plabel = new JButton("");
			plabel.setMinimumSize(new Dimension(250, 20));
			plabel.setPreferredSize(new Dimension(250, 20));
			plabel.setMaximumSize(new Dimension(250, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.PINK);
			add(plabel);
			belt_labels[i] = plabel;
		}

		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			

			plabel2[i] = belt_labels[i];
			plabel2[i].setText("");
			
	        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	        
	        mainpanels[i] = new JPanel();
	        
			if (p == null) {
				
				plabel2[i].setText("");
				plabel2[i].setBackground(Color.PINK);
				plabel2[i].setBorder(border);
				
				
				mainframe[i] = null;
				mainpanels[i] = null;
			} else {
				
				String text = "<html>";
				
				text += "<h1>"
						+ p.getColor() + " Plate</h1>";
				text += "<br>";
				
				text += "Type of sushi: " + capitalize(p.getContents().getName()) + "<br>";
				text += "<br>";
				
				for (int i1 = 0; i1 < p.getContents().getIngredients().length; i1++) {
					text += "Ingredient " + (i1 + 1) + " Name: " + capitalize(p.getContents().getIngredients()[i1].getName()) + 
							" ||  Amount: " +  ((int) ((p.getContents().getIngredients()[i1].getAmount() * 100.0)+0.5))/100.0 +
							"<br>";
				}
				text += "<br>";
				
				text += "Chef Name: " + capitalize(p.getChef().getName()) + "<br>";
				
				text += "Plate Age: " + belt.getAgeOfPlateAtPosition(i);
				
				JLabel templabel = new JLabel(text);				
				mainpanels[i].setLayout(new BoxLayout(mainpanels[i], 1));
				mainpanels[i].add(templabel);
				
				
				plabel2[i].setText(p.getContents().getClass().getTypeName() + " at position " + i);
				
				mainframe[i] = new JFrame("Plate Info at Position " + i);
				mainframe[i].setSize(300, 400);
				mainframe[i].setResizable(true);
				
				mainframe[i].add(mainpanels[i]);
				
				plabel2[i].setActionCommand("info" + i);
				plabel2[i].addActionListener(this);
				plabel2[i].setBorder(border);
		        
				switch (p.getColor()) {
				case RED:
					plabel2[i].setBackground(Color.RED);
					break;
				case GREEN:					
					plabel2[i].setBackground(Color.GREEN); 
					break;
				case BLUE:
					plabel2[i].setBackground(Color.BLUE);
					break;
				case GOLD:
					plabel2[i].setBackground(Color.YELLOW); break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		for (int i = 0; i < 20; i++) {
			if (arg0.getActionCommand().equals("info" + i)) {
				mainframe[i].setVisible(true);
			}
		}
	}
	
	private String capitalize(String string) {
	    String[] arr = string.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}
	
}
