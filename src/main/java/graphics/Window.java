package graphics;

import creatorFigure.CircleCreator;
import creatorFigure.FigureCreator;
import creatorFigure.SquareCreator;
import creatorFigure.TriangleCreator;
import figures.Figure;
import src.Point;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class Window extends JFrame implements ActionListener {

//	JOptionPane.showMessageDialog(new Frame("hgbdnt"), "This is a Message Box.");

	private HashMap<String, HashMap<String, JComponent>> components;
	private HashMap<String, JComponent> addButtonComponents;

	private ArrayList<Figure> figures;
	GraphicCanvas graph;

	private Container contentPane;
	private SpringLayout layout;

	public Window(ArrayList<Figure> figures) {
		super("Программа");
		Dimension screen = this.getToolkit().getScreenSize();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(screen.width * 3 / 4, screen.height * 3 / 4);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);

		components = new HashMap<>();
		this.figures = figures;
		graph = new GraphicCanvas(figures);
		contentPane = this.getContentPane();
		layout = new SpringLayout();
		addButtonComponents = new HashMap<>();
		panel();
		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent evt) {
				Component c = (Component)evt.getSource();
				c.revalidate();
				c.repaint();
			}
		});

	}

	public void panel() {


		contentPane.setLayout(layout);

		graph.setBackground(Color.pink);
		layout.getConstraints(graph).setX(Spring.constant(50));
		layout.getConstraints(graph).setY(Spring.constant(50));
		layout.getConstraints(graph).setHeight(Spring.constant(750));
		layout.getConstraints(graph).setWidth(Spring.constant(750));
		contentPane.add(graph);

		JButton addButton = new JButton("Создать фигуру");
		addButton.addActionListener(this);

		JButton changeButton = new JButton("Изменить фигуру");
		changeButton.addActionListener(this);

		JButton deleteButton = new JButton("Удалить фигуру");
		deleteButton.addActionListener(this);

		JButton exitButton = new JButton("Назад");
		exitButton.addActionListener(this);

		addButton.setBackground(Color.yellow);
		layout.getConstraints(addButton).setHeight(Spring.constant(25));
		layout.getConstraints(addButton).setWidth(Spring.constant(200));
		layout.putConstraint(SpringLayout.WEST, addButton, 100, SpringLayout.EAST, graph);
		contentPane.add(addButton);

		changeButton.setBackground(Color.cyan);
		layout.getConstraints(changeButton).setHeight(Spring.constant(25));
		layout.getConstraints(changeButton).setWidth(Spring.constant(200));
		layout.putConstraint(SpringLayout.WEST, changeButton, 100, SpringLayout.EAST, addButton);
		contentPane.add(changeButton);

		deleteButton.setBackground(Color.green);
		layout.getConstraints(deleteButton).setHeight(Spring.constant(25));
		layout.getConstraints(deleteButton).setWidth(Spring.constant(200));
		layout.putConstraint(SpringLayout.WEST, deleteButton, 100, SpringLayout.EAST, changeButton);
		contentPane.add(deleteButton);

		HashMap<String, JComponent> mainButtons = new HashMap<>();
		mainButtons.put("addButton", addButton);
		mainButtons.put("changeButton", changeButton);
		mainButtons.put("deleteButton", deleteButton);
		mainButtons.put("exitButton", exitButton);
		components.put("mainButtons", mainButtons);


	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (components.get("mainButtons").containsValue(event.getSource())) {

			if (event.getSource().equals(components.get("mainButtons").get("addButton"))) {

				JButton newPointButton = new JButton("+");
				newPointButton.addActionListener(this);

				JButton doneButton = new JButton("Готово");
				doneButton.addActionListener(this);

				JButton removePointButton = new JButton("-");
				removePointButton.addActionListener(this);

				JLabel currentRowId = new JLabel("1");
				String id = currentRowId.getText();

				JLabel Xlabel = new JLabel(id+ ". X= ");
				JLabel Ylabel = new JLabel("Y= ");
				JTextField Xtext = new JTextField(10);
				JTextField Ytext = new JTextField(10);

				Xlabel.setBackground(Color.YELLOW);
				layout.getConstraints(Xlabel).setHeight(Spring.constant(25));
				layout.getConstraints(Xlabel).setWidth(Spring.constant(40));
				layout.putConstraint(SpringLayout.NORTH, Xlabel, 25, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				layout.putConstraint(SpringLayout.WEST, Xlabel, 100, SpringLayout.EAST, graph);
				contentPane.add(Xlabel);

				Xtext.setBackground(Color.YELLOW);
				layout.getConstraints(Xtext).setHeight(Spring.constant(25));
				layout.getConstraints(Xtext).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.WEST, Xtext, 0, SpringLayout.EAST, Xlabel);
				layout.putConstraint(SpringLayout.NORTH, Xtext, 25, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(Xtext);

				Ylabel.setBackground(Color.YELLOW);
				layout.getConstraints(Ylabel).setHeight(Spring.constant(25));
				layout.getConstraints(Ylabel).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.NORTH, Ylabel, 25, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				layout.putConstraint(SpringLayout.WEST, Ylabel, 10, SpringLayout.EAST, Xtext);
				contentPane.add(Ylabel);

				Ytext.setBackground(Color.YELLOW);
				layout.getConstraints(Ytext).setHeight(Spring.constant(25));
				layout.getConstraints(Ytext).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.WEST, Ytext, 0, SpringLayout.EAST, Ylabel);
				layout.putConstraint(SpringLayout.NORTH, Ytext, 25, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(Ytext);

				newPointButton.setBackground(Color.YELLOW);
				layout.getConstraints(newPointButton).setHeight(Spring.constant(25));
				layout.getConstraints(newPointButton).setWidth(Spring.constant(45));
				layout.putConstraint(SpringLayout.WEST, newPointButton, 15, SpringLayout.EAST, Ytext);
				layout.putConstraint(SpringLayout.NORTH, newPointButton, 25, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(newPointButton);

				doneButton.setBackground(Color.YELLOW);
				layout.getConstraints(doneButton).setHeight(Spring.constant(25));
				layout.putConstraint(SpringLayout.WEST, doneButton, 0, SpringLayout.WEST, Xtext);
				layout.putConstraint(SpringLayout.EAST, doneButton, 0, SpringLayout.EAST, Ytext);
				layout.putConstraint(SpringLayout.NORTH, doneButton, 75, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(doneButton);


				addButtonComponents.put("newPointButton", newPointButton);
				addButtonComponents.put("doneButton", doneButton);
				addButtonComponents.put("removePointButton", removePointButton);
				addButtonComponents.put("Xlabel" + id, Xlabel);
				addButtonComponents.put("Ylabel" + id, Ylabel);
				addButtonComponents.put("Xtext" + id, Xtext);
				addButtonComponents.put("Ytext" + id, Ytext);
				addButtonComponents.put("currentRowId", currentRowId);

				components.put("addButtonComponents", addButtonComponents);


				this.revalidate();
			}

		} else if (components.get("addButtonComponents").containsValue(event.getSource())) {

			if (event.getSource().equals(components.get("addButtonComponents").get("newPointButton"))) {

				String id = ((JLabel)(addButtonComponents.get("currentRowId"))).getText();
				int y = addButtonComponents.get("Xlabel"+id).getY() + 25;

				id=String.valueOf(Integer.valueOf(id) +1);
				((JLabel)(addButtonComponents.get("currentRowId"))).setText(id);

				JLabel Xlabel = new JLabel(id + ". X= ");
				JLabel Ylabel = new JLabel("Y= ");
				JTextField Xtext = new JTextField(10);
				JTextField Ytext = new JTextField(10);

				Xlabel.setBackground(Color.YELLOW);
				layout.getConstraints(Xlabel).setHeight(Spring.constant(25));
				layout.getConstraints(Xlabel).setWidth(Spring.constant(40));
				layout.putConstraint(SpringLayout.NORTH, Xlabel, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				layout.putConstraint(SpringLayout.WEST, Xlabel, 100, SpringLayout.EAST, graph);
				contentPane.add(Xlabel);

				Xtext.setBackground(Color.YELLOW);
				layout.getConstraints(Xtext).setHeight(Spring.constant(25));
				layout.getConstraints(Xtext).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.WEST, Xtext, 0, SpringLayout.EAST, Xlabel);
				layout.putConstraint(SpringLayout.NORTH, Xtext, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(Xtext);

				Ylabel.setBackground(Color.YELLOW);
				layout.getConstraints(Ylabel).setHeight(Spring.constant(25));
				layout.getConstraints(Ylabel).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.NORTH, Ylabel, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				layout.putConstraint(SpringLayout.WEST, Ylabel, 10, SpringLayout.EAST, Xtext);
				contentPane.add(Ylabel);

				Ytext.setBackground(Color.YELLOW);
				layout.getConstraints(Ytext).setHeight(Spring.constant(25));
				layout.getConstraints(Ytext).setWidth(Spring.constant(25));
				layout.putConstraint(SpringLayout.WEST, Ytext, 0, SpringLayout.EAST, Ylabel);
				layout.putConstraint(SpringLayout.NORTH, Ytext, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
				contentPane.add(Ytext);

				layout.getConstraints(addButtonComponents.get("newPointButton")).setY(Spring.constant(addButtonComponents.get("newPointButton").getY() + 50));
				layout.getConstraints(addButtonComponents.get("doneButton")).setY(Spring.constant(addButtonComponents.get("doneButton").getY() + 50));

				addButtonComponents.put("Xlabel"+id, Xlabel);
				addButtonComponents.put("Ylabel"+id, Ylabel);
				addButtonComponents.put("Xtext"+id, Xtext);
				addButtonComponents.put("Ytext"+id, Ytext);
				components.put("addButtonComponents", addButtonComponents);


				this.revalidate();
			} else if (event.getSource().equals(components.get("addButtonComponents").get("doneButton"))) {
				ArrayList<src.Point> points = new ArrayList<>();
				for (int i = 1; i <= Integer.valueOf(((JLabel)(addButtonComponents.get("currentRowId"))).getText()); i++) {
					Double x = Double.valueOf(((JTextField)(addButtonComponents.get("Xtext"+i))).getText());
					Double y = Double.valueOf(((JTextField)(addButtonComponents.get("Ytext"+i))).getText());
					points.add(new Point(x, y));
				}
				FigureCreator figureCreator = null;
				switch (points.size()) {
					case 2:
						figureCreator = new CircleCreator();
						break;

					case 3:
						figureCreator = new TriangleCreator();
						break;

					case 4:
						figureCreator = new SquareCreator();
						break;

					default:
						JOptionPane.showMessageDialog(new Frame("ОПАСНО!"), "Некорректное значение.");
						break;
				}
				if (figureCreator != null) {
					Figure figure = figureCreator.createFigure(points);
					figures.add(figure);
					for(JComponent component: addButtonComponents.values()){
						contentPane.remove(component);
					}
					addButtonComponents.clear();
					graph.repaintGraphics(figures);
					revalidate();
					repaint();
				}
			}
		}

	}

}
