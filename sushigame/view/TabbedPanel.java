package sushigame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TabbedPanel extends JPanel implements ActionListener {
	private JTabbedPane tabbedPane;
	private double goldplateprice = 0.0;
	private JTextField goldslider;
	private JButton button;
	
	public TabbedPanel() {
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanel("Red Plate");
        tabbedPane.addTab("<html><span style=\"color:#ff0000;\">Red</span></html>\r\n", panel1);
        JComponent panel2 = makeTextPanel("Green Plate");
        tabbedPane.addTab("<html><span style=\"color:#32CD32;\">Green</span></html>\r\n", panel2);
        JComponent panel3 = makeTextPanel("Blue Plate");
        tabbedPane.addTab("<html><span style=\"color:#1E90FF;\">Blue</span></html>\r\n", panel3);
        JComponent panel4 = makeTextPanel("Gold Plate");
        tabbedPane.addTab("<html><span style=\"color:#CFB53B;\">Gold</span></html>\r\n", panel4);
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
	
	public JComponent makeTextPanel(String text) {
        if (!(text.equals("Gold Plate"))) {
			JPanel panel = new JPanel();
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
        } else {
        	goldslider = new JTextField(3);
        	goldslider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					goldplateprice = Double.parseDouble(goldslider.getText());
					System.out.println(goldplateprice);
				}
        		
        	});
        	JPanel goldpanel = new JPanel();
        	JLabel goldtext = new JLabel("Set Price b/w $5 - $10");
        	goldtext.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        	goldpanel.add(goldtext);
        	goldpanel.add(goldslider);
        	button = new JButton("Set");
        	button.setActionCommand("price");
        	button.addActionListener(this);
        	
        	goldpanel.add(button);
        	return goldpanel;
        }
    }
	
	public JTabbedPane returnTabs() {
		return tabbedPane;
	}
	
	public void setTooltipText(String x) {
		this.setTooltipText(x);
	}
	
	public double getgoldprice() {
		return goldplateprice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "price":
			button.setBackground(Color.GREEN);
			goldplateprice = Double.parseDouble(goldslider.getText());
			if (goldplateprice < 5 || goldplateprice > 10) {
				button.setBackground(null);
				JFrame error_frame = new JFrame("Error");
				error_frame.setSize(100, 200);
				error_frame.setResizable(true);
				JOptionPane.showMessageDialog(error_frame,
					"Set price between $5.00 to $10.00",
				    "ERROR",
				    JOptionPane.ERROR_MESSAGE);
				break;
			}
			
			break;
		}
	}

}
