import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class SandDisplay extends JComponent implements MouseListener,
		MouseMotionListener, ActionListener, ChangeListener {
	private final Image image;
	private final int cellSize, numRows, numCols;
	private int[] mouseLoc;
	private final JButton[] buttons;
	private final JSlider speedSlider, sizeSlider;
	private int speed, size, tool;

	public SandDisplay(String title, int numRows, int numCols, String[] buttonNames) {
		this.numRows = numRows;
		this.numCols = numCols;
		tool = 1;
		mouseLoc = null;
		speed = computeSpeed(100);
		size = 3;

		//determine cell size
		cellSize = 3;
		image = new BufferedImage(numCols * cellSize, numRows * cellSize, BufferedImage.TYPE_INT_RGB);

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(topPanel);

		setPreferredSize(new Dimension(numCols * cellSize, numRows * cellSize));
		addMouseListener(this);
		addMouseMotionListener(this);
		topPanel.add(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(42, 42, 50));
		buttonPanel.setLayout(new GridLayout(6, 6));
		topPanel.add(buttonPanel);

		buttons = new JButton[buttonNames.length];

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new DarkJButton(buttonNames[i]);
			buttons[i].setMaximumSize(new Dimension(100, 21));
			buttons[i].setMinimumSize(new Dimension(100, 21));
			buttons[i].setPreferredSize(new Dimension(100, 21));
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(this);
			buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
			try {
				buttons[i].setIcon(new ImageIcon(ImageIO.read(new File(String.format("resources/%s.png", buttonNames[i].toLowerCase())))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			buttonPanel.add(buttons[i]);
		}
		buttons[tool].setSelected(true);

		JLabel slow = new JLabel("Slow"), fast = new JLabel("Fast"),
				small = new JLabel("Small"), large = new JLabel("Large");

		small.setForeground(new Color(200, 203, 207));
		large.setForeground(new Color(200, 203, 207));
		slow.setForeground(new Color(200, 203, 207));
		fast.setForeground(new Color(200, 203, 207));

		speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
		speedSlider.addChangeListener(this);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(0, slow);
		labelTable.put(200, fast);
		speedSlider.setLabelTable(labelTable);
		speedSlider.setPaintLabels(true);
		speedSlider.setBackground(new Color(42, 42, 50));

		frame.getContentPane().add(speedSlider);

		sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 25, 3);
		sizeSlider.addChangeListener(this);
		sizeSlider.setMajorTickSpacing(2);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setBackground(new Color(42, 42, 50));
		labelTable = new Hashtable<>();
		labelTable.put(0, small);
		labelTable.put(25, large);
		sizeSlider.setLabelTable(labelTable);
		sizeSlider.setPaintLabels(true);

		frame.getContentPane().add(sizeSlider);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	public void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int[] getMouseLocation() {
		return mouseLoc;
	}

	public int getTool() {
		return tool;
	}

	public void setColor(int row, int col, Color color) {
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mouseLoc = toLocation(e);
	}

	public void mouseReleased(MouseEvent e) {
		mouseLoc = null;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		mouseLoc = toLocation(e);
	}

	private int[] toLocation(MouseEvent e) {
		int row = e.getY() / cellSize;
		int col = e.getX() / cellSize;
		if (row < 0 || row >= numRows || col < 0 || col >= numCols)
			return null;
		int[] loc = new int[2];
		loc[0] = row;
		loc[1] = col;
		return loc;
	}

	public void actionPerformed(ActionEvent e) {
		tool = Integer.parseInt(e.getActionCommand());
		for (JButton button : buttons)
			button.setSelected(false);
		((JButton) e.getSource()).setSelected(true);
	}

	public void stateChanged(ChangeEvent e) {
		size = sizeSlider.getValue();
		speed = computeSpeed(speedSlider.getValue());
	}

	//returns number of times to step between repainting and processing mouse input
	public int getSpeed() {
		return speed;
	}

	public int getPenSize() {
		return size;
	}

	//returns speed based on sliderValue
	//speed of 0 returns 10^3
	//speed of 100 returns 10^6
	private int computeSpeed(int sliderValue) {
		return (int) Math.pow(10, 0.015 * sliderValue + 3);
	}
}