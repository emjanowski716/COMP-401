package sushigame.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401sushi.*;
import comp401sushi.Nigiri;
import comp401sushi.Sashimi;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private String sushi_type = "";
	private int tab_index = 0;
	private JTextField[] text_field = new JTextField[8];
	private JPanel mainPanel = new JPanel(); 
	private double[] ingredients = new double[8];
	private int position = 1;
	private JPanel goldpanel;
	private double goldprice = 0.0;
	private TabbedPanel content;
	JButton button1;
	JButton button2;
	JButton button3;
	private String type = "";
	private boolean pricething = true;
	
	public PlayerChefView(int belt_size) {
		listeners = new ArrayList<ChefViewListener>();
		
		setup();
	}
	
	public void setup() {
		tab_index = 0;
		position = 1;
		goldprice = 0.0;
		setPreferredSize(new Dimension(250, 400));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel space = new JLabel(" ");
		
		add(new JLabel("<html>" + "<h2>" + "<font color=" + "red>" + "Create a Plate" + "</font>" + "</h2>"));
		
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(1, 3));
		
		button1 = new JButton("Sashimi");
		button2 = new JButton("Nigiri");
		button3 = new JButton("Roll");
		
		button1.setActionCommand("sashimi");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(Color.GREEN);
				button2.setBackground(null);
				button3.setBackground(null);
		        JButton selection = (JButton) e.getSource();
		        sushi_type = (String) selection.getActionCommand();
		        System.out.println(sushi_type);
		        createsushi();
			}
		});
		
		button2.setActionCommand("nigiri");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(null);
				button2.setBackground(Color.GREEN);
				button3.setBackground(null);
		        JButton selection = (JButton) e.getSource();
		        sushi_type = (String) selection.getActionCommand();
		        System.out.println(sushi_type);
		        createsushi();
			}
		});
		
		
		button3.setActionCommand("roll");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				if (mainPanel != null) {
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				button1.setBackground(null);
				button2.setBackground(null);
				button3.setBackground(Color.GREEN);
		        JButton selection = (JButton) e.getSource();
		        sushi_type = (String) selection.getActionCommand();
		        System.out.println(sushi_type);
		        rollIngredientsCreate();
		        mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		
		button_panel.add(button1);
		button_panel.add(button2);
		button_panel.add(button3);
		
		button_panel.setMaximumSize(new Dimension(300, 30));

		add(button_panel);
		
		content = new TabbedPanel();
        content.setMaximumSize(new Dimension(300, 100));
        content.returnTabs().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    tab_index = pane.getSelectedIndex();
                    System.out.println(tab_index);
                    if (tab_index != 3) {
                    	if (goldpanel != null) {
                    		mainPanel.remove(goldpanel);
                    		mainPanel.revalidate();
                    		mainPanel.repaint();
                    	}
                    }
				}
			}
        });
		add(content);
		
		add(space);
		
		JButton restart = new JButton("Restart");
		restart.setActionCommand("restart");
		restart.addActionListener(this);
		
		add(restart);
		
		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "restart":
			reset();
			break;
		case "red_crab_sashimi_at_3":
			makeRedPlateRequest(crab_sashimi, 3);
			break;
		case "blue_eel_nigiri_at_8":
			makeBluePlateRequest(eel_nigiri, 8);
			break;
		case "gold_kmp_roll_at_5":
			makeGoldPlateRequest(kmp_roll, 5, 5.00);
			break;
		case "create2":
			this.goldprice = content.getgoldprice();
			if (sushi_type.equals("sashimi")) {
				Sashimi newSashimi = null;
				if (type.equals("tuna")) {
					newSashimi = new Sashimi(Sashimi.SashimiType.TUNA);
				} else if (type.equals("salmon")) {
					newSashimi = new Sashimi(Sashimi.SashimiType.SALMON);
				} else if (type.equals("eel")) {
					newSashimi = new Sashimi(Sashimi.SashimiType.EEL);
				} else if (type.equals("crab")) {
					newSashimi = new Sashimi(Sashimi.SashimiType.CRAB);
				} else if (type.equals("shrimp")) {
					newSashimi = new Sashimi(Sashimi.SashimiType.SHRIMP);
				}
				createsashimi(newSashimi); 
			} else if (sushi_type.equals("nigiri")) {
				Nigiri newNigiri = null;
				if (type.equals("tuna")) {
					newNigiri = new Nigiri(Nigiri.NigiriType.TUNA);
				} else if (type.equals("salmon")) {
					newNigiri = new Nigiri(Nigiri.NigiriType.SALMON);
				} else if (type.equals("eel")) {
					newNigiri = new Nigiri(Nigiri.NigiriType.EEL);
				} else if (type.equals("crab")) {
					newNigiri = new Nigiri(Nigiri.NigiriType.CRAB);
				} else if (type.equals("shrimp")) {
					newNigiri = new Nigiri(Nigiri.NigiriType.SHRIMP);
				}
				createNigiri(newNigiri); 
			}
			break;
		case "create":
			for (int i=0; i < 8; i++) {
				if (this.ingredients[i] != 0) {
					this.ingredients[i] = 0;
				}
				if (!(this.text_field[i].getText().equals(""))) {
					try {
						this.ingredients[i] = Double.parseDouble(this.text_field[i].getText()); 
					} catch (NumberFormatException e1) {
						JFrame error_frame = new JFrame("Error");
						error_frame.setSize(100, 200);
						error_frame.setResizable(true);
						JOptionPane.showMessageDialog(error_frame,
							"Invalid number.",
						    "ERROR",
						    JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
			}
			for (int i=0; i<8; i++) {
				if (ingredients[i] > 1.5) {
					JFrame error_frame = new JFrame("Error");
					this.pricething = false;
					error_frame.setSize(100, 200);
					error_frame.setResizable(true);
					JOptionPane.showMessageDialog(error_frame,
						"Amount cannot be more than 1.5 ounces.",
					    "ERROR",
					    JOptionPane.ERROR_MESSAGE);
					break;
				} else {
					this.pricething = true;
				}
			}

			if (this.pricething == true) {
				this.goldprice = content.getgoldprice();
				this.createrollPlate(); 
			}
		}
		
	}
	
	public void rollIngredientsCreate() {
		JLabel nameLabel = new JLabel("<html>Set Ingredients: " + "</html>");
		JPanel[] ingredients = new JPanel[8];
		mainPanel.add(nameLabel);
		String[] ingredient_array = {
				new Avocado().getName(), new Crab().getName(), 
				new Eel().getName(), new Rice().getName(),
				new Shrimp().getName(), new Tuna().getName(),
		};
		
		for (int i = 0; i<8; i++) {
			JLabel templabel = new JLabel(" oz. " + capitalize(ingredient_array[i]));
			ingredients[i] = new JPanel();
			ingredients[i].setLayout(new GridLayout(1, 3));
			this.text_field[i] = new JTextField();
			this.text_field[i].setMaximumSize(new Dimension(10, 20));
			this.text_field[i].setEditable(true);
			ingredients[i].add(this.text_field[i]);
			
			ingredients[i].add(templabel);
			ingredients[i].setAlignmentX(LEFT_ALIGNMENT);
			ingredients[i].add(new JPanel());
			
			mainPanel.add(ingredients[i]);
			mainPanel.setAlignmentX(CENTER_ALIGNMENT);
		}
		
		JButton create_plate = new JButton("Create Plate");
		create_plate.setActionCommand("create");
		create_plate.addActionListener(this);
		
		mainPanel.add(createPosition());
		mainPanel.add(new JLabel("Set Position"));
		add(create_plate);
		
		add(mainPanel);
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

	public void reset() {
		this.removeAll();
		this.setup();
		this.revalidate();
		this.repaint();
		if (sushi_type.equals("sashimi")) {
			button1.setBackground(Color.GREEN);
		} else if (sushi_type.equals("nigiri")) {
			button2.setBackground(Color.GREEN);
		}
	}
	
	public void createrollPlate() {
		if (tab_index == 0) {
			ArrayList<IngredientPortion> ingredient_array = new ArrayList<IngredientPortion>();
			if (ingredients[0] != 0) {
				ingredient_array.add(new AvocadoPortion(ingredients[0]));
			}
			if (ingredients[1] != 0) {
				ingredient_array.add(new CrabPortion(ingredients[1]));
			}
			if (ingredients[2] != 0) {
				ingredient_array.add(new EelPortion(ingredients[2]));
			}
			if (ingredients[3] != 0) {
				ingredient_array.add(new RicePortion(ingredients[3]));
			}
			if (ingredients[4] != 0) {
				ingredient_array.add(new SeaweedPortion(ingredients[5]));
			}
			if (ingredients[5] != 0) {
				ingredient_array.add(new ShrimpPortion(ingredients[6]));
			}
			if (ingredients[6] != 0) {
				ingredient_array.add(new TunaPortion(ingredients[7]));
			}
	
			IngredientPortion[] new_ingredients = ingredient_array.toArray(new IngredientPortion[ingredient_array.size()]);
			
			double price_check = 0;
			for (int i=0; i<new_ingredients.length; i++) {
				price_check += new_ingredients[i].getCost();
			}
			
			if (price_check > 1) {
				JFrame error_frame = new JFrame("Error");
				error_frame.setSize(100, 200);
				error_frame.setResizable(true);
				JOptionPane.showMessageDialog(error_frame,
					"Your price for the plate is $" +  ((int) ((price_check * 100.0) + 0.5)) / 100.0
					+ ". Price of your ingredients should be less than $1",
				    "ERROR",
				    JOptionPane.ERROR_MESSAGE);
			}
			Roll new_roll = new Roll("New Roll", new_ingredients);
			this.makeRedPlateRequest(new_roll, position);
			
		} else if (tab_index == 1) {
			ArrayList<IngredientPortion> ingredient_array = new ArrayList<IngredientPortion>();
			if (ingredients[0] != 0) {
				ingredient_array.add(new AvocadoPortion(ingredients[0]));
			}
			if (ingredients[1] != 0) {
				ingredient_array.add(new CrabPortion(ingredients[1]));
			}
			if (ingredients[2] != 0) {
				ingredient_array.add(new EelPortion(ingredients[2]));
			}
			if (ingredients[3] != 0) {
				ingredient_array.add(new RicePortion(ingredients[3]));
			}
			if (ingredients[4] != 0) {
				ingredient_array.add(new SeaweedPortion(ingredients[5]));
			}
			if (ingredients[5] != 0) {
				ingredient_array.add(new ShrimpPortion(ingredients[6]));
			}
			if (ingredients[6] != 0) {
				ingredient_array.add(new TunaPortion(ingredients[7]));
			}
			
			IngredientPortion[] new_ingredients = ingredient_array.toArray(new IngredientPortion[ingredient_array.size()]);
			double price_check = 0;
			for (int i=0; i<new_ingredients.length; i++) {
				price_check += new_ingredients[i].getCost();
			}
			
			if (price_check > 2) {
				JFrame error_frame = new JFrame("Error");
				error_frame.setSize(100, 200);
				error_frame.setResizable(true);
				JOptionPane.showMessageDialog(error_frame,
					"Your price for the plate is $" +  ((int) ((price_check * 100.0) + 0.5)) / 100.0
					+ ". Price of your ingredients should be less than $2",
				    "ERROR",
				    JOptionPane.ERROR_MESSAGE);
			}
			Roll new_roll = new Roll("New Roll", new_ingredients);
			this.makeGreenPlateRequest(new_roll, position);
			
		} else if (tab_index == 2) {
			ArrayList<IngredientPortion> ingredient_array = new ArrayList<IngredientPortion>();
			if (ingredients[0] != 0) {
				ingredient_array.add(new AvocadoPortion(ingredients[0]));
			}
			if (ingredients[1] != 0) {
				ingredient_array.add(new CrabPortion(ingredients[1]));
			}
			if (ingredients[2] != 0) {
				ingredient_array.add(new EelPortion(ingredients[2]));
			}
			if (ingredients[3] != 0) {
				ingredient_array.add(new RicePortion(ingredients[3]));
			}
			if (ingredients[4] != 0) {
				ingredient_array.add(new SeaweedPortion(ingredients[5]));
			}
			if (ingredients[5] != 0) {
				ingredient_array.add(new ShrimpPortion(ingredients[6]));
			}
			if (ingredients[6] != 0) {
				ingredient_array.add(new TunaPortion(ingredients[7]));
			}
			
			IngredientPortion[] new_ingredients = ingredient_array.toArray(new IngredientPortion[ingredient_array.size()]);
			Roll new_roll = new Roll("New Roll", new_ingredients);
			double price_check = 0;
			for (int i=0; i<new_ingredients.length; i++) {
				price_check += new_ingredients[i].getCost();
			}
			
			if (price_check > 4) {
				JFrame error_frame = new JFrame("Error");
				error_frame.setSize(100, 200);
				error_frame.setResizable(true);
				JOptionPane.showMessageDialog(error_frame,
					"Your price for the plate is $" +  ((int) ((price_check * 100.0) + 0.5)) / 100.0
					+ ". Price of your ingredients should be less than $4",
				    "ERROR",
				    JOptionPane.ERROR_MESSAGE);
			}
			this.makeBluePlateRequest(new_roll, position);
			
		} else if (tab_index == 3) {
			ArrayList<IngredientPortion> ingredient_array = new ArrayList<IngredientPortion>();
			if (ingredients[0] != 0) {
				ingredient_array.add(new AvocadoPortion(ingredients[0]));
			}
			if (ingredients[1] != 0) {
				ingredient_array.add(new CrabPortion(ingredients[1]));
			}
			if (ingredients[2] != 0) {
				ingredient_array.add(new EelPortion(ingredients[2]));
			}
			if (ingredients[3] != 0) {
				ingredient_array.add(new RicePortion(ingredients[3]));
			}
			if (ingredients[4] != 0) {
				ingredient_array.add(new SeaweedPortion(ingredients[5]));
			}
			if (ingredients[5] != 0) {
				ingredient_array.add(new ShrimpPortion(ingredients[6]));
			}
			if (ingredients[6] != 0) {
				ingredient_array.add(new TunaPortion(ingredients[7]));
			}
			
			IngredientPortion[] new_ingredients = ingredient_array.toArray(new IngredientPortion[ingredient_array.size()]);
			double price_check = 0;
			for (int i=0; i<new_ingredients.length; i++) {
				price_check += new_ingredients[i].getCost();
			}
			if (price_check > goldprice) {
				JFrame error_frame = new JFrame("Error");
				error_frame.setSize(100, 200);
				error_frame.setResizable(true);
				JOptionPane.showMessageDialog(error_frame,
					"Your price for the plate is $" +  ((int) ((price_check * 100.0)+0.5))/100.0
					+ ". Price of your ingredients should be less than $" + goldprice,
				    "ERROR",
				    JOptionPane.ERROR_MESSAGE);
			}
			Roll new_roll = new Roll("New Roll", new_ingredients);
			this.makeGoldPlateRequest(new_roll, position, goldprice);
		}
	}
	
	// Creates JSlider for Position
	public JSlider createPosition() {
		final int FPS_MIN = 0;
		final int FPS_MAX = 20;
		final int FPS_INIT = 1;  
		
		JSlider positions = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
		positions.setToolTipText("Set Position");
		positions.setName("Set Position");
		positions.setMajorTickSpacing(2);
		positions.setMinorTickSpacing(1);
		positions.setPaintTicks(true);
		positions.setPaintLabels(true);
		positions.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				 position = positions.getValue();
				 System.out.println(positions.getValue());
			}
		});
		return positions;
	}
	
	public void createsushi() {
		reset();
		JPanel mainpanel = new JPanel();
		JLabel space = new JLabel("<html><br></html>");
		add(space);
		JSeparator temp = new JSeparator();
		add(temp);
		mainpanel.setLayout(new GridLayout(2, 3, 5, 7));
		JLabel label = new JLabel("Choose Type");
		mainpanel.add(label);
		JButton button1 = new JButton("<html><i>Tuna</i></html>");
		mainpanel.add(button1);
		JButton button2 = new JButton("<html><i>Salmon</i></html>");
		mainpanel.add(button2);
		JButton button3 = new JButton("<html><i>Eel</i></html>");
		mainpanel.add(button3);
		JButton button4 = new JButton("<html><i>Crab</i></html>");
		mainpanel.add(button4);
		JButton button5 = new JButton("<html><i>Shrimp</i></html>");
		mainpanel.add(button5);
		
		JPanel spacepanel = new JPanel();
		spacepanel.setLayout(new GridLayout(1, 1, 5, 5));
		
		button1.setActionCommand("tuna");
		button2.setActionCommand("salmon");
		button3.setActionCommand("eel");
		button4.setActionCommand("crab");
		button5.setActionCommand("shrimp");
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = e.getActionCommand();
				button1.setBackground(Color.GREEN);
				button2.setBackground(null);
				button3.setBackground(null);
				button4.setBackground(null);
				button5.setBackground(null);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = e.getActionCommand();
				button1.setBackground(null);
				button2.setBackground(Color.GREEN);
				button3.setBackground(null);
				button4.setBackground(null);
				button5.setBackground(null);
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = e.getActionCommand();
				button1.setBackground(null);
				button2.setBackground(null);
				button3.setBackground(Color.GREEN);
				button4.setBackground(null);
				button5.setBackground(null);
			}
		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = e.getActionCommand();
				button1.setBackground(null);
				button2.setBackground(null);
				button3.setBackground(null);
				button4.setBackground(Color.GREEN);
				button5.setBackground(null);
			}
		});
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = e.getActionCommand();
				button1.setBackground(null);
				button2.setBackground(null);
				button3.setBackground(null);
				button4.setBackground(null);
				button5.setBackground(Color.GREEN);
			}
		});
		
		mainpanel.setMaximumSize(mainpanel.getMinimumSize());
		
		add(mainpanel);
		
		add(space);
		
		JSeparator temp2 = new JSeparator();
		add(temp2);
		
		add(createPosition());
		add(new JLabel("<html><h3><i>Set Position</i></h3></html>"));
		
		add(spacepanel);
		add(space);
		JButton create_plate = new JButton("Create Plate");
		create_plate.setActionCommand("create2");
		create_plate.addActionListener(this);
		
		add(create_plate);
	}
	
	public void createsashimi(Sashimi x) {
		if (tab_index == 0) {
			this.makeRedPlateRequest(x, position);
		} else if (tab_index == 1) {
			this.makeGreenPlateRequest(x, position);
		} else if (tab_index == 2) {
			this.makeBluePlateRequest(x, position);
		} else if (tab_index == 3) {
			this.makeGoldPlateRequest(x, position, goldprice);
		}
	}
	
	public void createNigiri(Nigiri x) {
		if (tab_index == 0) {
			this.makeRedPlateRequest(x, position);
		} else if (tab_index == 1) {
			this.makeGreenPlateRequest(x, position);
		} else if (tab_index == 2) {
			this.makeBluePlateRequest(x, position);
		} else if (tab_index == 3) {
			this.makeGoldPlateRequest(x, position, goldprice);
		}
	}
}
