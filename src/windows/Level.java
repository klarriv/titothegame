package windows;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import objects.Bench;
import objects.Cone;
import objects.DoublePoint;
import objects.Jason;
import objects.Maison;
import objects.Physics;
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.SeeSaw;
import objects.Tito;
import objects.TrashCan;
import objects.Tree;

public class Level extends JPanel {

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
	/**
	 * Counts the time elapsed since the start of the timer
	 */
	private int resetChrono = 0;
	private int achievementChrono = 0;
	private int numberOfStars;

	// TODO Tito's sprite sheet loading shit
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
	private boolean engineOn;
	
	/**
	 * Making the magic happen
	 */

	/**
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
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
				engineOn=true;
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
				t.restart();
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
				t.start();
				tito.setCounter(1);
			}

		});
		
		jbtBackToGame.addComponentListener(new ButtonResizeListener());
		jbtBackToLevelSelect = new JButton(new ImageIcon(MainFrame.getTl().pauseMenuLevelSelectionTexture));
		jbtBackToLevelSelect.setBorder(BorderFactory.createEmptyBorder());
		jbtBackToLevelSelect.setContentAreaFilled(false);
		jbtBackToLevelSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadObjects();
				t.restart();
				isPaused = false;
				jbtBackToGame.setVisible(false);
				jbtBackToLevelSelect.setVisible(false);
				jbtExitGame.setVisible(false);
				jbtPause.setVisible(true);
				jbtPlay.setVisible(true);
				jbtRestart.setVisible(true);
				repaint();
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(),MainFrame.getLevelselectpanel());
			}
		});

		jbtBackToLevelSelect.addComponentListener(new ButtonResizeListener());

		jbtExitGame = new JButton(new ImageIcon(MainFrame.getTl().pauseMenuExitGameTexture));
		jbtExitGame.setBorder(BorderFactory.createEmptyBorder());
		jbtExitGame.setContentAreaFilled(false);
		jbtExitGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter writer = new PrintWriter(new File("Resources/gameSave.sav"));
					for (int i = MainFrame.getLevels().length - 1; i >= 0; i--) {
						if (MainFrame.getLevels()[i].hasBeenCompleted()) {
							writer.println((i + 1));
							break;
						}
					}
					for(int i = 0; i < MainFrame.getLevels().length; i++){
						writer.println(MainFrame.getLevels()[i].numberOfStars);
					}
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
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

		// END OF LOADING STUFF

		// START OF TIMER FOR MAKING TITO MOVE
		t = new Timer(1000 / 25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!engineOn){
					achievementChrono += t.getDelay();
					repaint();	
				}
				
				else if(engineOn){
					resetChrono += t.getDelay();
					if(resetChrono >= 2000){
						loadObjects();
						t.stop();
						pauseGameAction();
					}
					engine();
					repaint();
				}
			}
		});
		// END OF TIMER FOR MAKING TITO MOVE
		t.start();
	}

	/**
	 * 
	 */
	protected void pauseGameAction() {
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

		resetChrono = 0;
		achievementChrono = 0;
		engineOn = false;
		treeList.clear();
		benchList.clear();
		coneList.clear();
		planeList.clear();
		ropeList.clear();
		seesawList.clear();
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
				planeList.add(new Plane(reader.nextDouble(), reader.nextDouble(), Math.toRadians(reader.nextDouble()), reader.nextDouble(), reader.nextInt()));
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

			//TODO setting the planes to the maison
			for (int i = 0; i < numberOfPlane; i++)
				if (planeList.get(i).getMaisonNumber() > -1 && planeList.get(i).getMaisonNumber() < maisonList.size())
					maisonList.get(planeList.get(i).getMaisonNumber()).addPlanes(planeList.get(i));
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This starts the music in the levels
	 */
	public void startLevelMusic() {
		levelSong.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gUnit = getWidth() / 5;

		jbtPlay.setBounds(10, 10, 30, 30);
		jbtPause.setBounds(50, 10, 30, 30);
		jbtRestart.setBounds(90, 10, 30, 30);

		jbtBackToGame.setBounds((int) (2.11 * gUnit), (int) (0.78 * gUnit), (int) (0.91 * gUnit), (int) (0.27 * gUnit));
		jbtBackToLevelSelect.setBounds((int) (2.11 * gUnit), (int) (1.17 * gUnit), (int) (0.91 * gUnit), (int) (0.27 * gUnit));
		jbtExitGame.setBounds((int) (2.11 * gUnit), (int) (1.56 * gUnit), (int) (0.91 * gUnit), (int) (0.27 * gUnit));

		g.drawImage(MainFrame.getTl().levelBackgroundTexture, 0, 0, getWidth(), getHeight(), null);

		// 1 TREE
		for (int i = 0; i < treeList.size(); i++) {
			g.drawImage(treeList.get(i).getTexture(), (int) (gUnit * treeList.get(i).getPosition().x), (int) (gUnit * treeList.get(i).getPosition().y), (int) (2.9 * gUnit), (int) (3.5 * gUnit), null);
		}
		// 2 BENCH
		for (int i = 0; i < benchList.size(); i++) {
			if (benchList.get(i).isVisible())
				g.drawImage(benchList.get(i).getTexture(), (int) (gUnit * benchList.get(i).getPosition().x), (int) (gUnit * benchList.get(i).getPosition().y), (int) (Bench.WIDTH * gUnit), (int) (Bench.HEIGHT * gUnit), null);
		}
		// 3 CONE
		for (int i = 0; i < coneList.size(); i++) {
			if (coneList.get(i).isVisible())
				g.drawImage(coneList.get(i).getTexture(), (int) (gUnit * coneList.get(i).getPosition().x), (int) (gUnit * coneList.get(i).getPosition().y), (int) (Cone.WIDTH * gUnit), (int) (Cone.HEIGHT * gUnit), null);
		}
		// 4 PLANE
		for (int i = 0; i < planeList.size(); i++) {
			g.setColor(Color.WHITE);
			g.drawLine((int) (gUnit * (planeList.get(i).getAnchor1().x)), (int) (gUnit * (planeList.get(i).getAnchor1().y)), (int) (gUnit * (planeList.get(i).getAnchor2().x)), (int) (gUnit * planeList.get(i).getAnchor2().y));
			g.setColor(Color.BLACK);
		}
		// 5 ROPE
		g.setColor(Color.yellow);

		for (int i = 0; i < ropeList.size(); i++) {
			if (ropeList.get(i).isUsed() == -1) {
				g.drawImage(ropeList.get(i).getTexture(), (int) (gUnit * ropeList.get(i).getAnchor2().x), (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (Rope.WIDTH * gUnit), (int) (Rope.HEIGHT * gUnit), null);

			} else if (ropeList.get(i).isUsed() == 1 || ropeList.get(i).isUsed() == 3) {
				int[] xPoints = {(int) (gUnit * ropeList.get(i).getAnchor1().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50 };
				int[] yPoints = {(int) (gUnit * ropeList.get(i).getAnchor1().y), (int) (gUnit * ropeList.get(i).getAnchor2().y) };
				g.drawPolyline(xPoints, yPoints, 2);
			} else if (ropeList.get(i).isUsed() == 2 || ropeList.get(i).isUsed() == 4) {
				int[] xPoints = {(int) (gUnit * ropeList.get(i).getAnchor1().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor3().x) + 50 };
				// TODO check if this works
				if (ropeList.get(i).isUsed() == 4) {
					ropeList.get(i).getPlane().getAnchor1().x += (50 / gUnit);
					ropeList.get(i).getPlane().setAnchor2X();
					xPoints[2] = (int) (gUnit * ropeList.get(i).getAnchor3().x);
				}
				int[] yPoints = {(int) (gUnit * ropeList.get(i).getAnchor1().y), (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (gUnit * ropeList.get(i).getAnchor3().y) };
				g.drawPolyline(xPoints, yPoints, 3);
			} else if (ropeList.get(i).isUsed() == -2) {
				g.drawLine((int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().y) + 75);

			}
		}

		g.setColor(Color.black);
		// 6 SEESAW
		for (int i = 0; i < seesawList.size(); i++) {
			g.drawImage(seesawList.get(i).getTexture(), (int) (gUnit * seesawList.get(i).getPosition().x), (int) (gUnit * seesawList.get(i).getPosition().y), (int) (gUnit * 1.1), (int) (gUnit * 0.33), null);
		}

		// 7 TRASHCAN
		for (int i = 0; i < trashCanList.size(); i++) {
			if (trashCanList.get(i).isVisible()) {
				g.drawImage(trashCanList.get(i).getTexture(), (int) (gUnit * trashCanList.get(i).getPosition().x), (int) (gUnit * trashCanList.get(i).getPosition().y), (int) (trashCanList.get(i).getWidth() * gUnit), (int) (trashCanList.get(i).getHeight() * gUnit), null);
			}
		}
		// TODO integers
		// 8 PULLEY
		for (int i = 0; i < pulleyList.size(); i++) {
			if (pulleyList.get(i).isVisible())
				g.drawImage(pulleyList.get(i).getTexture(), (int) (gUnit * pulleyList.get(i).getPosition().x) + 15, (int) (gUnit * pulleyList.get(i).getPosition().y), (int) (gUnit * Pulley.WIDTH), (int) (gUnit * Pulley.HEIGHT), null);
		}
		// 9 MAISON
		for (int i = 0; i < maisonList.size(); i++) {
			g.drawImage(maisonList.get(i).getTexture(), (int) (gUnit * maisonList.get(i).getPosition().x), (int) (gUnit * maisonList.get(i).getPosition().y), (int) (maisonList.get(i).getWidth() * gUnit), (int) (maisonList.get(i).getHeight() * gUnit), null);
			g.setColor(Color.WHITE);
			g.drawRect((int) (gUnit*maisonList.get(i).getR().getPosition().x), (int) (gUnit*maisonList.get(i).getR().getPosition().y), (int) (gUnit*maisonList.get(i).getR().getWidth()), (int) (gUnit*maisonList.get(i).getR().getHeight()));
		}
		// 10 ENEMY
		if (jason != null)
			g.drawImage(jason.getTexture(), (int) (gUnit * jason.getPosition().x), (int) (gUnit * jason.getPosition().y), (int) (Jason.WIDTH * gUnit), (int) (Jason.HEIGHT * gUnit), null);

		// TITO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (tito != null)
			g.drawImage(tito.getTexture(), (int) (gUnit * tito.getPosition().x), (int) (gUnit * tito.getPosition().y), (int) (gUnit * tito.getHeight()), (int) (gUnit * tito.getHeight()), null);
		
		switch (numberOfStars){
		case 0: g.drawImage(MainFrame.getTl().level0star, (int)(gUnit * 4.35), (int)(gUnit * 0.05), (int) (0.59 * gUnit), (int) (gUnit * 0.275), null);
				break;
		case 1: g.drawImage(MainFrame.getTl().level1star, (int)(gUnit * 4.35), (int)(gUnit * 0.05), (int) (0.59 * gUnit), (int) (gUnit * 0.275), null);
				break;
		case 2: g.drawImage(MainFrame.getTl().level2stars, (int)(gUnit * 4.35), (int)(gUnit * 0.05), (int) (0.59 * gUnit), (int) (gUnit * 0.275), null);
				break;
		case 3: g.drawImage(MainFrame.getTl().level3stars, (int)(gUnit * 4.35), (int)(gUnit * 0.05), (int) (0.59 * gUnit), (int) (gUnit * 0.275), null);
				break;
		}
		
		if (isPaused) {
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(MainFrame.getTl().levelPauseHeaderTexture, 248 * getWidth() / 1280, 10 * getHeight() / 720, MainFrame.getTl().levelPauseHeaderTexture.getWidth() * getWidth() / 1280, MainFrame.getTl().levelPauseHeaderTexture.getHeight() * getHeight() / 720, null);
		}
		g.setColor(Color.WHITE);
		g.drawLine((int) (gUnit * (planeList.get(0).getAnchor1().x)), (int) (gUnit * (planeList.get(0).getAnchor1().y)), (int) (gUnit * (planeList.get(0).getAnchor2().x)), (int) (gUnit * planeList.get(0).getAnchor2().y));
	}

	/*
	 * Physics moving and colliding methods
	 */
	
	public void engine(){
		
		//making the trashcans fall
		tito.setCounter(tito.getCounter()+1);
		
		for ( int i = 0; i < trashCanList.size(); i++){
			//looking if it falls on a seesaw
		
			//Checking contact with a see-saw
			if(seesawList.size()!=0)//This is because I use seesawList in the condition...
				if (trashCanList.get(i).single == 0 && seesawList.get(0).getContact(trashCanList.get(i))){
					trashCanList.get(i).setPlaneVariable(1);
					
					//Throwing Tito in the air
					if(seesawList.get(0).objectOn(tito)){
						//System.out.println(530);
						tito.setEnergyVelocity(trashCanList.get(i).getVy(), trashCanList.get(i).getWeight(), tito.getWeight());
						tito.setVx();
						tito.setVy();
					}
					
					trashCanList.get(i).single++;
				}
				
			//plane free fall
			if (trashCanList.get(i).getPosition().y < (2.5 - TrashCan.HEIGHTFLAT) && !trashCanList.get(i).isUsed()){
				projectileMotion(trashCanList.get(i));
				basicMove(trashCanList.get(i));
			}
			
			//plane incline plane fall
			else if (trashCanList.get(i).isUsed() && trashCanList.get(i).getPlaneVariable() > -1 && trashCanList.get(i).getPlane() > -1){
				System.out.println(551);
				if(isOnPlane(trashCanList.get(i), planeList.get(trashCanList.get(i).getPlane())))
					frictionMove(trashCanList.get(i));
				else{
					System.out.println(555);
					trashCanList.get(i).setUsed(false);
					trashCanList.get(i).setPlaneVariable(-1);
					trashCanList.get(i).setPlane(-1);
					projectileMotion(trashCanList.get(i));
					basicMove(trashCanList.get(i));
				}
				
			}
		}//end trashcans
		
		
		//Tito physics
		for(int i = 0; i < planeList.size(); i++){
			if (isOnPlane(tito,planeList.get(i)) && tito.getVy() == 0){
				tito.setOnPlane(true);
				tito.frictionMotion(planeList.get(i), tito, (double)t.getDelay()/1000);
				tito.setVx();
				break;
			}
			else
				tito.setOnPlane(false);
			
		}
		
		//hitting the walls of a Maison
		for (int i = 0; i < maisonList.size(); i++) {
			if(maisonList.get(i).getR().contains(tito.getR())){
				MainFrame.getTl().playSound(MainFrame.getTl().bouncingSound);
				tito.setVx(-1 * tito.getVx());
				break;
			}
		}
		
		//making Tito bounce on planes
		if (!tito.isOnPlane()){
			boolean planeCollided = false;
			for(int i = 0; i< planeList.size(); i++){
				
				planeCollided = planeColliding(planeList.get(i));
				//Tito's projectile motion
				
					projectileMotion(tito);
					xMove();
	
					//if Tito hits a plane
					if (planeCollided){
						MainFrame.getTl().playSound(MainFrame.getTl().bouncingSound);
						planeCollision(planeList.get(i));
						projectileMotion(tito);
						xMove();
					}
				//}
				//tito.setVx();
			}
		}
		
		
		
		// ropes Physics 
		//TODO check w/ planes... wtf is going on
		for (int i = 0; i < ropeList.size(); i++){
			ropeList.get(i).setXAnchored();
		
			//projectile motion of a trashcan attached to a pulley and another object
			if (ropeList.get(i).isUsed() == 2 || ropeList.get(i).isUsed() == 4 && ropeList.get(i).getOb1().getPosition().y <= (2.5 - ropeList.get(i).getOb1().getHeight())){
				double y = ropeList.get(i).getOb1().projectileMotions(ropeList.get(i).getOb1().getWeight(), ropeList.get(i).getOb1().getPosition().y, ropeList.get(i).getOb1().getVy(), t.getDelay());
				
				if (!ropeList.get(i).isMaxed()){
					ropeList.get(i).getOb1().setY(y);
					ropeList.get(i).getOb1().setVy();
					ropeList.get(i).pulleyMove(ropeList.get(i).getOb1().getPosition().x, y);
				}
			}
			
			ropeList.get(i).setTotalForce();
		}
		
		// checks if there is a collision with the enemies
		// TODO quoi faire quand tito se fait pogner? genre jouer un son or something?
		if(tito!= null && tito.getR().contains(jason.getR())){
			int x=(Math.random()<0.5)?0:1; 
			if(x==1)
				MainFrame.getTl().playSound(MainFrame.getTl().heySound);
			else
				MainFrame.getTl().playSound(MainFrame.getTl().nonono);
			tito = null;
			t.restart();
			loadObjects();
		}
					
		// checks if tito touches the right boundary to change level!
		if(tito != null && tito.getPosition().x + tito.getHeight() >= 5 && levelNumber != 9){
			attributeStars(achievementChrono);
			t.stop();
			loadObjects();
			hasBeenCompleted = true;
			LevelSelectMenu.getLvlButtons()[levelNumber+1].setEnabled(true);
			MainFrame.getTl().playSound(MainFrame.getTl().levelChange);
			CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
			cardLayout.show(MainFrame.getMenus(), "LEVEL" + (levelNumber+1));
		}
		
		else if(tito != null && tito.getPosition().x + tito.getHeight() >= 5 && levelNumber == 9){
			attributeStars(achievementChrono);
			t.stop();
			loadObjects();
			hasBeenCompleted = true;
			LevelSelectMenu.getLvlButtons()[levelNumber].setEnabled(true);
			CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
			cardLayout.show(MainFrame.getMenus(), "CREDITSPANEL");
		}
	}

	
	
	private void attributeStars(int timeTaken){
		if(timeTaken<7000)
			numberOfStars = 3;
		else if(timeTaken<14000)
			numberOfStars = 2;
		else
			numberOfStars = 1;
	}
	
	/**
	 * Makes Tito bounce on a plane according to the angle relative to the plane
	 * 
	 * @param plane
	 */
	public void planeCollision(Plane plane) {

		double angle = plane.angleOfContact(tito.getVx(), tito.getVy());
		//System.out.println(Math.toDegrees(angle));
		if (!(Math.toDegrees(angle) < 10 && Math.toDegrees(angle) > -10))
			if (tito.getVx() >= 0)
				tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
			else
				tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
		else { //if (isOnPlane(tito,plane))
				tito.frictionMotion(plane, tito, (double)t.getDelay()/1000);
		}

		tito.setVx();
		tito.setVy();

	}

	// TODO Check whether this can be improved
	/**
	 * Determines if Tito is colliding with a plane
	 * 
	 * @param plane
	 * @return
	 */
	public boolean planeColliding(Plane plane) {
		double r = 40.0 / gUnit;

		DoublePoint dp = new DoublePoint(tito.getPosition().x + r, tito.getPosition().y + r);

		double d = plane.pointDistance(dp);

		if (plane.getWidth() > 0) {
			if (d <= r && (dp.x) <= plane.getAnchor2().x && dp.x >= plane.getAnchor1().x)
				return true;
		} 
		else if (plane.getWidth() < 0) {
			if (d <= r && dp.x > plane.getAnchor2().x && dp.x < plane.getAnchor1().x)
				return true;
		}
		return false;

	}
	
	/**
	 * Makes an object move with no friction
	 * @param ob1
	 */
	public void basicMove(Physics ob1) {
		double x = ob1.motion(ob1.getPosition().x, ob1.getVx(), t.getDelay());
		ob1.setX(x);
	}

	/**
	 * Makes an object move with no friction and collide with the frame
	 */
	public void xMove() {
		double x = tito.motion(tito.getPosition().x, tito.getVx(), t.getDelay());

		if (x <= 5 && x >= 0)
			tito.setX(x);

		else if (Math.abs(0 - x) <= 0.1) {
			tito.setX(0);

		} else if (Math.abs(5 - x) <= 0.1) {
			tito.setX(5);
		} else
			tito.setVx(-1 * tito.getVx());
	}

	/**
	 * Projectile motion of an object falling and hitting the ground
	 * 
	 * @param ob1
	 */
	public void projectileMotion(Physics ob1) {
		double y = ob1.projectileMotions(ob1.getWeight(), ob1.getPosition().y, ob1.getVy(), t.getDelay());

		if (ob1.getVy() < 0 && y >= 2.5 - ob1.getHeight()) {

			ob1.setY(2.5 - ob1.getHeight());
			ob1.setVy(-1 * ob1.getVy() - 1.5);
			// //(loader.getPosition().y);
		}

		else if (y <= 2.5 - ob1.getHeight()) {
			ob1.setY(y);
			ob1.setVy();
		}
	}

	/**
	 * Puts the trashcan on the plane if it is near enough
	 * 
	 * @param ob1
	 * @param p
	 */
	public void planeContact(TrashCan ob1, Plane p, int planeNumber) {
		double x = ob1.getPosition().x;
		double ty = p.getY(x);

		double height = ob1.getHeight();

		if (isOnPlane(ob1, p)) {
			ob1.setUsed(true);
			ob1.setPlane(planeNumber);
			ob1.setY(ty - height);
			ob1.setPlaneVariable(p.getPlaneVariable());
			setAcceleration(ob1, p);
		} else if (ob1.getPlaneVariable() > -1) {
			System.out.println(784);
			ob1.setUsed(false);
			ob1.setPlane(-1);
			ob1.setPlaneVariable(-1);
			ob1.setAcceleration(0, 0, 0);
		}
	}

	/**
	 * Determines whether an object is on the plane or not
	 * 
	 * @param ob1
	 * @param p
	 * @return
	 */
	public boolean isOnPlane(Physics ob1, Plane p) {
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double tx1 = p.getAnchor1().x;
		double tx2 = p.getAnchor2().x;
		double ty = p.getY(x);

		// TODO sss
		double width = Math.abs((ob1.getWidth() / 2.0) * Math.cos(p.getAngle()));
		//System.out.println((y < ty) + " " + ((x+width) > tx1) + " " +((x+width) < tx2)  + " " + ( p.pointDistance(ob1.getPosition()) < 1) );
		if (y < ty && (x + width) > (tx1) && (x + width) < (tx2) && p.pointDistance(ob1.getPosition()) < 1){
			ob1.setUsed(true);
			return true;
		}
		else if (ob1.getPlaneVariable() > -1){
			
			ob1.setUsed(false);
			return false;
		}
		else
			return false;
	}
	
	/**
	 * Makes the object move with friction
	 * @param ob1
	 */
	 public void frictionMove(Physics ob1){
		 ob1.frictionMotion(ob1.getPosition(),ob1.getVx(), ob1.getVy(), t.getDelay());
		 ob1.setVy();
		 ob1.setVx();
		 
	}

	/**
	 * Sets the acceleration of an object on a plane
	 * 
	 * @param ob1
	 * @param p
	 */
	public void setAcceleration(Physics ob1, Plane p) {
		ob1.setAcceleration(p.getAngle(), ob1.getWeight(), 0.5);
	}

	public boolean hasBeenCompleted() {
		return hasBeenCompleted;
	}

	/**
	 * 
	 * @param hasBeenCompleted
	 */
	public void setHasBeenCompleted(boolean hasBeenCompleted) {
		this.hasBeenCompleted = hasBeenCompleted;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtExitGame() {
		return jbtExitGame;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtBackToGame() {
		return jbtBackToGame;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtBackToLevelSelect() {
		return jbtBackToLevelSelect;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(int numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtPlay() {
		return jbtPlay;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtRestart() {
		return jbtRestart;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getJbtPause() {
		return jbtPause;
	}

	/**
	 * Moving the objects with the mouse
	 * 
	 * @author Keven-Matthew & Charles-Philippe
	 *
	 */
	class DragListener implements MouseListener, MouseMotionListener {

		boolean isClicked;
		double x;
		double y;
		DoublePoint mousePoint = new DoublePoint(0,0);
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			isClicked = true;
			
			x = (double) arg0.getX() / gUnit;
			y = (double) arg0.getY() / gUnit;
			mousePoint = new DoublePoint(x, y);
			
			for (int i = 0; i < benchList.size(); i++) {
				if (benchList.get(i).getR() != null	&& benchList.get(i).getR().contains(mousePoint)) {
					benchList.get(i).isMoving = true;
					break;
				}
			}
			for (int i = 0; i < coneList.size(); i++) {
				if (coneList.get(i).getR() != null && coneList.get(i).getR().contains(mousePoint)) {
					coneList.get(i).isMoving = true;
					break;
				}
			}
			for (int i = 0; i < trashCanList.size(); i++) {
				if (trashCanList.get(i).getR() != null && trashCanList.get(i).getR().contains(mousePoint)) {
					trashCanList.get(i).isMoving = true;
					break;
				}
			}
			for (int i = 0; i < ropeList.size(); i++){
				if (ropeList.get(i).getR() != null && ropeList.get(i).getR().contains(mousePoint)){
					ropeList.get(i).isMoving = true;
					break;
				}
			}
			for (int i = 0; i < planeList.size(); i++){
				if(planeList.get(i).pointDistance(mousePoint) <= 0.3){
					planeList.get(i).setMoving(true);
					break;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(!engineOn){
				isClicked = false;
			
				for (int i = 0; i < benchList.size(); i++) {
					benchList.get(i).isMoving = false;
					if (!benchList.get(i).isUsed()){
						benchList.get(i).resetPosition();
						repaint();
					}	
				}
				for (int i = 0; i < coneList.size(); i++) {
					coneList.get(i).isMoving = false;
					if (!coneList.get(i).isUsed()){
						coneList.get(i).resetPosition();
						repaint();
					}
				}
				for (int i = 0; i < trashCanList.size(); i++) {
					trashCanList.get(i).isMoving = false;
					if (!trashCanList.get(i).isUsed()){
						trashCanList.get(i).resetPosition();
						repaint();
					}
				}
				for (int i = 0; i < ropeList.size(); i++) {
					ropeList.get(i).isMoving = false;
					if (ropeList.get(i).isUsed() == -1){
						ropeList.get(i).resetPosition();
						repaint();
					}
				}
				/**
				for (int i = 0; i < planeList.size(); i++){
					planeList.get(i).setMoving(false);
					//TODO isUsed of the plane !?
					if(planeList.get(i).isUsed() == -1){
						planeList.get(i).resetPosition();
						repaint();
					}
				}*/
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			//if(isClicked && !t.isRunning()){	
			if(isClicked && !engineOn){	
	
				double x = (double) arg0.getX() / gUnit;
				double y = (double) arg0.getY() / gUnit;
				DoublePoint dp = new DoublePoint(x, y);
				for (int i = 0; i < benchList.size(); i++) {
					if (benchList.get(i).getR() != null	&& benchList.get(i).isMoving) {
						benchList.get(i).setX(x - Bench.WIDTH / 2);
						benchList.get(i).setY(y - Bench.HEIGHT / 2);
					}
					for(int j = 0; j<trashCanList.size(); j++){
						if (trashCanList.get(j).getR() != null && benchList.get(i).getR() != null && trashCanList.get(j).getR().contains(benchList.get(i).getR())) {
							MainFrame.getTl().playSound(MainFrame.getTl().addObjectTrashcanSound);
							trashCanList.get(j).setWeight(trashCanList.get(j).getWeight() + benchList.get(i).getWeight());
							benchList.get(i).setVisible(false);
							benchList.get(i).setR(null);
							benchList.get(i).setUsed(true);
						}
					}
				}
				for (int i = 0; i < coneList.size(); i++) {
					if (coneList.get(i).getR() != null && coneList.get(i).isMoving) {
						coneList.get(i).setX(x - Cone.WIDTH / 2);
						coneList.get(i).setY(y - Cone.HEIGHT / 2);
					}
					for(int j = 0; j<trashCanList.size(); j++){
						if (trashCanList.get(j).getR() != null && coneList.get(i).getR() != null && trashCanList.get(j).getR().contains(coneList.get(i).getR())) {
							MainFrame.getTl().playSound(MainFrame.getTl().addObjectTrashcanSound);
							trashCanList.get(j).setWeight(trashCanList.get(j).getWeight() + coneList.get(i).getWeight());
							coneList.get(i).setVisible(false);
							coneList.get(i).setR(null);
							coneList.get(i).setUsed(true);
						}
					}
				}
				for (int i = 0; i < planeList.size(); i++) {
					if(planeList.get(i).isUsed() !=0 && planeList.get(i).isMoving() && x >= planeList.get(i).getAnchor1().x && x <= planeList.get(i).getAnchor2().x && planeList.get(i).pointDistance(dp) < 0.3){
						x = x - planeList.get(i).getWidth()/2;
						//y = planeList.get(i).getY(x);
						planeList.get(i).getAnchor1().x = x;
						if(planeList.get(i).getPlaneVariable() == 0)
							planeList.get(i).getAnchor1().y = y + planeList.get(i).getHeight()/2;
						else
							planeList.get(i).getAnchor1().y = y - planeList.get(i).getHeight()/2;
							
						planeList.get(i).setAnchor2();
					}
					
				/*	for ( int j = 0; j < ropeList.size(); j++){
						if (ropeList.get(j).isUsed() == 1 && ropeList.get(j).getAnchor2().distance(planeList.get(i).getAnchor1()) <= 0.3){
							planeList.get(i).setUsed(1);
							ropeList.get(j).setPlane(planeList.get(i));
							ropeList.get(j).setXAnchored();
							ropeList.get(j).setLength3();
						}
					}*/
				}
				
				for (int i = 0; i < ropeList.size(); i++) {
					
					if(ropeList.get(i).getR() != null && ropeList.get(i).isMoving){
						ropeList.get(i).setX(x- Rope.WIDTH /2);
						ropeList.get(i).setY(y - Rope.HEIGHT / 2);
	
						for (int j = 0; j < pulleyList.size(); j++) {
							if (ropeList.get(i).isUsed() != 0 &&ropeList.get(i).getAnchor2().distance(pulleyList.get(j).getPosition()) <= 0.3){
								MainFrame.getTl().playSound(MainFrame.getTl().attachingRopeSound);
								ropeList.get(i).setPulley(pulleyList.get(j));
							}
						}
	
					}
					ropeList.get(i).setXAnchored();
				}
	
				for (int i = 0; i < trashCanList.size(); i++) {
					if (trashCanList.get(i).getR() != null && trashCanList.get(i).isMoving) {
						trashCanList.get(i).setX(x - TrashCan.WIDTHFLAT / 2);
						trashCanList.get(i).setY(y - TrashCan.HEIGHTFLAT / 2);
						
						for(int j = 0; j < ropeList.size() || ropeList.size() == 0; j++)
							if (ropeList.size() != 0){
								//System.out.println(trashCanList.get(i).isUsed());
								if (trashCanList.get(i).equals(ropeList.get(j).getOb1()) || trashCanList.get(i).equals(ropeList.get(j).getOb2())){
									trashCanList.get(i).setUsed(true);
									ropeList.get(j).setXAnchored();
									MainFrame.getTl().playSound(MainFrame.getTl().attachingRopeSound);
								}
								else//TODO why do we check plane contacts in the rope loop? O(n^3)...?
									for (int jj = 0; jj < planeList.size(); jj++){
										planeContact(trashCanList.get(i), planeList.get(jj), jj);
										if(trashCanList.get(i).isUsed())
											break;
									}
								
							}
						/*	else
								for (int jj = 0; jj < planeList.size(); jj++){
									planeContact(trashCanList.get(i), planeList.get(jj), jj);
									System.out.println(1089);
									if(trashCanList.get(i).isUsed())	
										break;
								}
						*/
						for (int j = 0; j < trashCanList.size(); j++) {
							if ((i!=j) && trashCanList.get(i).getR() != null && trashCanList.get(j).getR() != null && !trashCanList.get(i).isUsed() && !trashCanList.get(j).isUsed() && (trashCanList.get(i).getR().contains(trashCanList.get(j).getR()) || trashCanList.get(j).getR().contains(trashCanList.get(i).getR())) && (!trashCanList.get(j).isUsed() || !trashCanList.get(i).isUsed())) {
								MainFrame.getTl().playSound(MainFrame.getTl().addObjectTrashcanSound);
								trashCanList.get(i).setWeight(trashCanList.get(i).getWeight() + trashCanList.get(j).getWeight());
								trashCanList.get(j).setVisible(false);
								trashCanList.get(j).setR(null);
								trashCanList.get(j).setUsed(true);
							}
						}
						for (int j = 0; j < ropeList.size(); j++) {
							if (trashCanList.get(i).getPosition().distance(ropeList.get(j).getAnchor2()) <= 0.3) {
								if (ropeList.get(j).isUsed() == 0 && !trashCanList.get(i).isUsed()) {
									ropeList.get(j).setOb1(trashCanList.get(i));
									trashCanList.get(i).setUsed(true);
	
								} else if (ropeList.get(j).isUsed() == 1 && !trashCanList.get(i).isUsed()) {
									ropeList.get(j).setOb2(trashCanList.get(i));
									trashCanList.get(i).setUsed(true);
								}
							}
						}
					}
				}	
				//repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}
}
