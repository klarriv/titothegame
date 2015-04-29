import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import RunningClasses.SpriteSheet;
import objects.Bench;
import objects.Cone;
import objects.Jason;
import objects.Maison;
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.SeeSaw;
import objects.Spring;
import objects.Tito;
import objects.TrashCan;
import objects.Tree;
import windows.ButtonResizeListener;
import windows.Enemy;
import windows.MainFrame;



public class Level2_0 extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This holds the object that represents Tito in the game.
	 */
	private Tito tito;
	/**
	 * This creates the nemesis: Jason Gerard the zookeeper
	 */
	private Jason jason;
	/**
	 * This is a variable that holds the level number
	 */
	public int levelNumber;
	/**
	 * The timer used to repaint and used for the delay in the physics formulas
	 */
	public Timer t;
	/**
	 * List of trees read from the level file.
	 */
	private ArrayList<Tree> treeList = new ArrayList<Tree>();
	/**
	 * List of the benches read from the level file.
	 */
	private ArrayList<Bench> benchList = new ArrayList<Bench>();
	/**
	 * List of cones read from the level file.
	 */
	private ArrayList<Cone> coneList = new ArrayList<Cone>();
	/**
	 * List of plane read from the level file.
	 */
	private ArrayList<Plane> planeList = new ArrayList<Plane>();
	/**
	 * List of rope read from the level file.
	 */
	private ArrayList<Rope> ropeList = new ArrayList<Rope>();
	/**
	 * List of seesaw read from the level file.
	 */
	private ArrayList<SeeSaw> seesawList = new ArrayList<SeeSaw>();
	/**
	 * List of trashcans read from the level file.
	 */
	private ArrayList<TrashCan> trashCanList = new ArrayList<TrashCan>();
	/**
	 * List of pulleys read from the level file
	 */
	private ArrayList<Pulley> pulleyList = new ArrayList<Pulley>();
	/**
	 * List of houses in the level read from the level file
	 */
	private ArrayList<Maison> maisonList = new ArrayList<Maison>();
	/**
	 * The song used in the levels
	 */
	private Clip levelSong;
	/**
	 * Contains the location of the level files
	 */
	private final String LEVELDIRECTORY = "Resources/Levels/";
	/**
	 * This contains the game unit to resize the textures when the window is
	 * resized.
	 */
	private double gUnit;
	
	//TODO Tito's sprite sheet loading shit
	
	/**
	 * 
	 */
	private boolean isPaused = false;
	/**
	 * 
	 */
	private boolean hasBeenCompleted = false;
	/**
	 * 
	 */
	private JButton jbtExitGame, jbtBackToGame, jbtBackToLevelSelect, jbtPlay, jbtRestart, jbtPause;
	
	
	
	/**
	 * Making the magic happen
	 */
	
	/**
	 * This creates a new instance of level
	 */
	public Level2_0(int levelNumber) {
		setLayout(null);
		
		DragListener bob = new DragListener();
		addMouseMotionListener(bob);
		addMouseListener(bob);
		
		this.levelNumber = levelNumber;
		
		// START OF PLAY/PAUSE/RESTART BUTTONS
		jbtPlay = new JButton(new ImageIcon(MainFrame.getTl().levelPlayTexture));
		jbtPlay.setBorder(BorderFactory.createEmptyBorder());
		jbtPlay.setContentAreaFilled(false);
		jbtPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < ropeList.size(); i++) {
					if (ropeList.get(i).isUsed() == 2)
						ropeList.get(i).setLength3();
					else if (ropeList.get(i).isUsed() == 1)
						ropeList.get(i).setLength2();
				}
				t.start();
			}

		});
		
		
		jbtPlay.addComponentListener(new ButtonResizeListener());
		jbtPause = new JButton(new ImageIcon(MainFrame.getTl().levelPlayTexture));
		jbtPause.setBorder(BorderFactory.createEmptyBorder());
		jbtPause.setContentAreaFilled(false);
		jbtPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseGameAction();
			}

		});
		
		
		
		jbtPause.addComponentListener(new ButtonResizeListener());
		
		jbtRestart = new JButton(new ImageIcon(MainFrame.getTl().levelRestartTexture));
		jbtRestart.setBorder(BorderFactory.createEmptyBorder());
		jbtRestart.setContentAreaFilled(false);
		jbtRestart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				loadObjects();
				repaint();
			}

		});
		
		add(jbtPlay);
		add(jbtPause);
		add(jbtRestart);
		
		// END OF PLAY/RESTART BUTTONS

		
		// START OF PAUSE MENU ITEMS
		// This block to the next comment is for the pause menu
		jbtBackToGame = new JButton(new ImageIcon(MainFrame.getTl().pauseMenuBackToGameTexture));
		jbtBackToGame.setBorder(BorderFactory.createEmptyBorder());
		jbtBackToGame.setContentAreaFilled(false);
		jbtBackToGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isPaused = false;
				repaint();
				jbtBackToGame.setVisible(false);
				jbtBackToLevelSelect.setVisible(false);
				jbtExitGame.setVisible(false);
				jbtPause.setVisible(true);
				jbtPlay.setVisible(true);
				jbtRestart.setVisible(true);
			}

		});
		
		jbtBackToGame.addComponentListener(new ButtonResizeListener());
		
		jbtBackToLevelSelect = new JButton(new ImageIcon(MainFrame.getTl().pauseMenuLevelSelectionTexture));
		jbtBackToLevelSelect.setBorder(BorderFactory.createEmptyBorder());
		jbtBackToLevelSelect.setContentAreaFilled(false);
		jbtBackToLevelSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				t.stop();
				isPaused = false;
				jbtBackToGame.setVisible(false);
				jbtBackToLevelSelect.setVisible(false);
				jbtExitGame.setVisible(false);
				jbtPause.setVisible(true);
				jbtPlay.setVisible(true);
				jbtRestart.setVisible(true);
				repaint();
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}
		});
		
		jbtBackToLevelSelect.addComponentListener(new ButtonResizeListener());
		
		jbtExitGame = new JButton(new ImageIcon(MainFrame.getTl().pauseMenuExitGameTexture));
		jbtExitGame.setBorder(BorderFactory.createEmptyBorder());
		jbtExitGame.setContentAreaFilled(false);
		jbtExitGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = MainFrame.getLevels().length-1; i>=0; i--){
					if (MainFrame.getLevels()[i].hasBeenCompleted()){
						try {
							PrintWriter writer = new PrintWriter(new File("Resources/gameSave.sav"));
							writer.print(i+1);
							writer.close();
						} catch (FileNotFoundException e) {
							// 
							e.printStackTrace();
						}
					}
				}
				System.exit(0);
			}

		});
		
		jbtExitGame.addComponentListener(new ButtonResizeListener());

		add(jbtBackToGame);
		add(jbtBackToLevelSelect);
		add(jbtExitGame);
		jbtBackToGame.setVisible(false);
		jbtBackToLevelSelect.setVisible(false);
		jbtExitGame.setVisible(false);

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "openPauseMenu");
		getActionMap().put("openPauseMenu", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pauseGameAction();
			}
		});
		
		// END OF PAUSE MENU ITEMS

		
		
		// START OF LOADING STUFF
		loadObjects();
		
		
		
		//TODO work on the timer :) Tito
		
		// END OF LOADING STUFF
	
		// START OF TIMER FOR MAKING TITO MOVE
		t = new Timer(1000 / 25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (t.isRunning());
					engine();
				
				repaint();
			}
		});
		// END OF TIMER FOR MAKING TITO MOVE
	}
	
	
	/**
	 * 
	 */
	protected void pauseGameAction(){
		if (!isPaused) {
			isPaused = true;
			t.stop();
			repaint();
			jbtBackToGame.setVisible(true);
			jbtBackToLevelSelect.setVisible(true);
			jbtExitGame.setVisible(true);
			jbtPause.setVisible(false);
			jbtPlay.setVisible(false);
			jbtRestart.setVisible(false);
		} else {
			isPaused = false;
			repaint();
			jbtBackToGame.setVisible(false);
			jbtBackToLevelSelect.setVisible(false);
			jbtExitGame.setVisible(false);
			jbtPause.setVisible(true);
			jbtPlay.setVisible(true);
			jbtRestart.setVisible(true);
		}
	}
	/**
	 * Loads all of the objects in the Level
	 */
	protected void loadObjects() {

		treeList.clear();
		benchList.clear();
		coneList.clear();
		planeList.clear();
		ropeList.clear();
		seesawList.clear();
		springList.clear();
		trashCanList.clear();
		pulleyList.clear();
		maisonList.clear();

		try {
			Scanner reader = new Scanner(new File(LEVELDIRECTORY + "/level" + levelNumber + ".lvl"));

			tito = new Tito(reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), t);
			// 1
			double numberOfTree = reader.nextDouble();
			for (int i = 0; i < numberOfTree; i++) 
				treeList.add(new Tree(reader.nextDouble(), reader.nextDouble()));
			// 2
			double numberOfBench = reader.nextDouble();
			for (int i = 0; i < numberOfBench; i++) 
				benchList.add(new Bench(reader.nextDouble(), reader.nextDouble()));
			// 3
			double numberOfCone = reader.nextDouble();
			for (int i = 0; i < numberOfCone; i++)
				coneList.add(new Cone(reader.nextDouble(), reader.nextDouble()));
			// 4
			double numberOfPlane = reader.nextDouble();
			for (int i = 0; i < numberOfPlane; i++)
				planeList.add(new Plane(reader.nextDouble(), reader.nextDouble(), Math.toRadians(reader.nextDouble()), reader.nextDouble(),  reader.nextInt()));
			// 5
			double numberOfRope = reader.nextDouble();
			for (int i = 0; i < numberOfRope; i++)
				ropeList.add(new Rope(reader.nextDouble(), reader.nextDouble()));
			// 6
			double numberOfSeesaw = reader.nextDouble();
			for (int i = 0; i < numberOfSeesaw; i++)
				seesawList.add(new SeeSaw(reader.nextDouble(), reader.nextDouble()));
			// 7
			double numberOfTrashCan = reader.nextDouble();
			for (int i = 0; i < numberOfTrashCan; i++)
				trashCanList.add(new TrashCan(reader.nextDouble(), reader.nextDouble()));
			// 8
			double numberOfPulley = reader.nextDouble();
			for (int i = 0; i < numberOfPulley; i++)
				pulleyList.add(new Pulley(reader.nextDouble(), reader.nextDouble(), reader.nextBoolean()));
			// 9
			double numberOfMaison = reader.nextDouble();
			for (int i = 0; i < numberOfMaison; i++)
				maisonList.add(new Maison(reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextInt()));
			// 10
			jason = new Jason(reader.nextDouble(), reader.nextDouble());
			
			
			//setting the planes to the maison
			for (int i = 0; i < numberOfPlane; i++)
				if (planeList.get(i).getMaisonNumber() > -1 && planeList.get(i).getMaisonNumber() < maisonList.size())
					maisonList.get(planeList.get(i).getMaisonNumber()).addPlanes(planeList.get(i));
			reader.close();
		} catch (FileNotFoundException e) {
			/
			e.printStackTrace();
		}
	}
	
		
	
}
