import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/* 
 *     Plik: GraphEditor.java
 *           
 *           Okno glowne do edycji grafu
 *           
 *    Autor: Uladzimir Kaviaka
 *     Data: 18 grudzien 2020 r.
 */

public class GraphEditor extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static final String AUTHOR = "Autor: Uladzimir Kaviaka  Data: XX grudzien 2020";
	private static final String TITLE = "Edytor grafow kolorowanych";
	
	private static final String APP_INSTRUCTION =
			"Mozliwosci edytora \n\n" + 
	        "Aktywne klawisze:\n" +
			"Strazlki - przesuniecie grafu \n" +
			"SHIFT + strza³ki - szybkie przesuwanie grafu\n\n" +
			"Gdy kursor wskazuje wierzcholek:\n" +
			"DEL - usuniecie wierzholka\n" +
			"[+], [-] - zmiana rozmiaru wierzcholka\n" +
			"c,y,b - zmiana koloru wierzcholka\n\n" +
			"Gdy wskazuje na krawedz DEL - usuwanie krawedzi\n\n"+
			"Operacja za pomoca myszki:\n" +
			"LB + Hold - przesuwanie calego grafu\n" +
			"RB - tworzenie wierzcholka na pozycji myszki\n" +
	        "Gdy kursor wskazuje wierzcholek:\n" +
	        "LB + Hold - przesuwanie danego wierzcholka\n" +
			"RB - zmiana koloru wierzcholka\n" +
	        "         zmiana nazwy wierzcholka\n" +
			"         usuniecie wierzcholka"+
	        "Gdy kursor wszkazuje na krawedz\n\n" +
			"LB + Hold - przesuwanie danej krawedzi\n"+
			"RB - zmiana koloru krawedzi\n" +
	        "         usuwanie krawedzi";
	
	
	public static void main(String[] args) {
		new GraphEditor();
	}

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGraph = new JMenu("Graph");
	private JMenu menuHelp = new JMenu("Help");
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuNew = new JMenuItem("New");
	private JMenuItem menuOpen = new JMenuItem("Open Graph");
	private JMenuItem menuSave = new JMenuItem("Save Graph");
	private JMenuItem menuShowExample = new JMenuItem("Example");
	private JMenuItem menuExit = new JMenuItem("Exit",KeyEvent.VK_X);
	private JMenuItem menuListOfNodes = new JMenuItem("List of Nodes");
	private JMenuItem menuListOfEdges = new JMenuItem("List of Edges");
	private JMenuItem menuAuthor = new JMenuItem("Author");
	private JMenuItem menuInstruction = new JMenuItem("Instruction");
	
	private GraphPanel panel = new GraphPanel();
	
	
	public GraphEditor() {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		setContentPane(panel);
		createMenu();
		showDefaultExample();
		setResizable(false);
		setVisible(true);
	}

	private void showListOfNodes(Graph graph) {
		Node array[] = graph.getNodes();
		StringBuilder message = new StringBuilder("Liczba wierzcholkow: " + array.length + "\n");
		for (Node node : array) {
			message.append(node + "    " + "\n");
		}
		JOptionPane.showMessageDialog(this, message, TITLE + " - Lista wierzcholkow", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void showListOfEdges(Graph graph) {
		Edge array[] = graph.getEdges();
		StringBuilder message = new StringBuilder("Liczba krawedzi: " + array.length + "\n");
		for (Edge edge : array) {
			message.append(edge + "    " + "\n");
		}
		JOptionPane.showMessageDialog(this, message, TITLE + " - Lista krawedzi", JOptionPane.PLAIN_MESSAGE);
	}

	private void createMenu() {
		
		menuSave.addActionListener(this);
		menuOpen.addActionListener(this);
		menuListOfEdges.addActionListener(this);
		menuNew.addActionListener(this);
		menuShowExample.addActionListener(this);
		menuExit.addActionListener(this);
		menuListOfNodes.addActionListener(this);
		menuAuthor.addActionListener(this);
		menuInstruction.addActionListener(this);
		
		
		menuGraph.setMnemonic(KeyEvent.VK_G);
		menuGraph.add(menuListOfNodes);
		menuGraph.add(menuListOfEdges);
		
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuInstruction);
		menuHelp.add(menuAuthor);
		
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.add(menuNew);
		menuFile.add(menuOpen);
		menuFile.add(menuSave);
		menuFile.addSeparator();
		menuFile.add(menuShowExample);
		menuFile.addSeparator();
		menuFile.add(menuExit);
		
		menuBar.add(menuFile);
		menuBar.add(menuGraph);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == menuNew) {
			panel.setGraph(new Graph());
		}
		if (source == menuShowExample) {
			showDefaultExample();
		}
		if (source == menuListOfNodes) {
			showListOfNodes(panel.getGraph());
		}
		if (source == menuListOfEdges) {
			this.showListOfEdges(this.panel.getGraph());
		}
		if (source == menuSave) {
			String file = JOptionPane.showInputDialog("Podaj nazwe pliku");
			if (file == null || file.equals("")) {
				return;
			}
			try {
				Graph.saveToFile(file, panel.getGraph());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Blad podczas zapisyawania",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (source == menuOpen) {
			String file = JOptionPane.showInputDialog("Podaj nazwe pliku");
			if (file == null || file.equals("")) {
				return;
			}
			try {
				panel.setGraph(Graph.readFromFile(file));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Blad podczas odczytywania",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (source == menuAuthor) {
			JOptionPane.showMessageDialog(this, AUTHOR, TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == menuInstruction) {
			JOptionPane.showMessageDialog(this, APP_INSTRUCTION, TITLE, JOptionPane.PLAIN_MESSAGE);
		}
		if (source == menuExit) {
			System.exit(0);
		}
	}

	private void showDefaultExample() {
		
		Graph graph = new Graph();
		
		Node n1 = new Node(100, 50);
		n1.setColor(Color.MAGENTA);
		n1.setR(20);
		n1.setName("A");
		Node n2 = new Node(100, 200);
		n2.setColor(Color.CYAN);
		n2.setR(20);
		n2.setName("B");
		Node n3 = new Node(250, 50);
		n3.setColor(Color.PINK);
		n3.setR(20);
		n3.setName("C");
		Node n4 = new Node(250, 200);
		n4.setColor(Color.ORANGE);
		n4.setR(20);
		n4.setName("D");

		Edge e1 = new Edge(n1,n2);
		e1.setColor(Color.BLUE);
		Edge e2 = new Edge(n4,n3);
		e2.setColor(Color.GRAY);
		Edge e3 = new Edge(n2,n3);
		e3.setColor(Color.YELLOW);
		
		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		panel.setGraph(graph);
	}
}
