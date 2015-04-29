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

import RunningClasses.SpriteSheet;
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
import objects.Spring;
import objects.Tito;
import objects.TrashCan;
import objects.Tree;
import windows.ButtonResizeListener;
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
					//engine();
				
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
		
		jbtBackToGame.setBounds(541*getWidth()/1280, 200*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		jbtBackToLevelSelect.setBounds(541*getWidth()/1280, 300*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		jbtExitGame.setBounds(541*getWidth()/1280, 400*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);

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
			g.drawLine((int) (gUnit * (planeList.get(i).getAnchor1().x)), (int) (gUnit * (planeList.get(i).getAnchor1().y)), (int) (gUnit * (planeList.get(i).getAnchor2().x)), (int) (gUnit * planeList.get(i).getAnchor2().y));
		}
		// 5 ROPE
		g.setColor(Color.yellow);
		
		for (int i = 0; i < ropeList.size(); i++) {
			if (ropeList.get(i).isUsed() == -1) {
				g.drawImage(ropeList.get(i).getTexture(), (int) (gUnit * ropeList.get(i).getAnchor2().x), (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (Rope.WIDTH * gUnit), (int) (Rope.HEIGHT * gUnit), null);
				
			} else if (ropeList.get(i).isUsed() == 1 || ropeList.get(i).isUsed() == 3) {
				int[] xPoints = { (int) (gUnit * ropeList.get(i).getAnchor1().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50 };
				int[] yPoints = { (int) (gUnit * ropeList.get(i).getAnchor1().y), (int) (gUnit * ropeList.get(i).getAnchor2().y) };
				g.drawPolyline(xPoints, yPoints, 2);
			} else if (ropeList.get(i).isUsed() == 2 || ropeList.get(i).isUsed() == 4) {
				int[] xPoints = { (int) (gUnit * ropeList.get(i).getAnchor1().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor3().x) + 50 };
				//TODO check if this works
				if (ropeList.get(i).isUsed() == 4){
					ropeList.get(i).getPlane().getPosition().x += (50/gUnit);
					ropeList.get(i).getPlane().setAnchor2X();
					xPoints[2] = (int)(gUnit * ropeList.get(i).getAnchor3().x);
				}
				int[] yPoints = { (int) (gUnit * ropeList.get(i).getAnchor1().y), (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (gUnit * ropeList.get(i).getAnchor3().y) };
				g.drawPolyline(xPoints, yPoints, 3);
			} else if (ropeList.get(i).isUsed() == -2) {
				g.drawLine((int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().y), (int) (gUnit * ropeList.get(i).getAnchor2().x) + 50, (int) (gUnit * ropeList.get(i).getAnchor2().y) + 75);

			}
		}
		
		g.setColor(Color.black);
		// 6 SEESAW
		for (int i = 0; i < seesawList.size(); i++) {
			g.drawImage(seesawList.get(i).getTexture(), (int) (gUnit * seesawList.get(i).getPosition().x), (int) (gUnit * seesawList.get(i).getPosition().y), (int)(gUnit * 1.1),(int)(gUnit * 0.33), null);
		}
		
		// 7 TRASHCAN
		for (int i = 0; i < trashCanList.size(); i++) {
			if (trashCanList.get(i).isVisible()){
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
		}
		// 10 ENEMY
		if (jason != null)
			g.drawImage(jason.getTexture(), (int) (gUnit * jason.getPosition().x), (int) (gUnit * jason.getPosition().y), (int) (Jason.WIDTH * gUnit), (int) (Jason.HEIGHT * gUnit), null);
		
		//TITO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(tito != null)
			g.drawImage(tito.getTexture(), (int) (gUnit * tito.getPosition().x), (int) (gUnit * tito.getPosition().y), (int) (gUnit * 0.25), (int) (gUnit * 0.25), null);
		
		
		if (isPaused) {
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(MainFrame.getTl().levelPauseHeaderTexture, 248 * getWidth() / 1280, 10 * getHeight() / 720, MainFrame.getTl().levelPauseHeaderTexture.getWidth() * getWidth() / 1280, MainFrame.getTl().levelPauseHeaderTexture.getHeight() * getHeight() / 720, null);
		}
		
	}
	
	
	/*
	 *  Physics moving and colliding methods
	 */
	
	/**
	 * Makes Tito bounce on a plane according to the angle relative to the plane
	 * @param plane
	 */
	public void planeCollision(Plane plane) {

		double angle = plane.angleOfContact(tito.getVx(), tito.getVy());

		if (tito.getVx() >= 0)
			tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
		else
			tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());

		tito.setVx();
		tito.setVy();

	}
	
	
	//TODO Check whether this can be improved
	/**
	 * Determines if Tito is colliding with a plane
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
	 * Makes an object move with no friction and collide with the frame
	 */
	public void xMove() {
		double x = tito.motion(tito.getPosition().x, -tito.getVx(), t.getDelay());

		if (x <= 5 && x >= 0)
			tito.setX(x);
		
		else if (Math.abs(0 - x) <= 0.1) {
			tito.setX(0);
			
		} 
		else if (Math.abs(5 - x) <= 0.1) {
			tito.setX(5);
		} 
		else
			tito.setVx(-1 * tito.getVx());
	}
	
	/**
	 * Projectile motion of an object falling and hitting the ground
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
	 * @param ob1
	 * @param p
	 */
	public void planeContact(TrashCan ob1, Plane p){
		double x = ob1.getPosition().x;
		double ty = p.getY(x);
		
		double height = ob1.getHeight();
		
		if (isOnPlane(ob1, p)){
			ob1.setUsed(true);
			ob1.setY(ty - height);
			ob1.setPlaneVariable(p.getPlaneVariable());
			setAcceleration(ob1, p);
		}
		else{
			ob1.setUsed(false);
			ob1.setPlaneVariable(-1);
			ob1.setAcceleration(0, 0, 0);
		}
	}
		
	/**
	 * Determines whether an object is on the plane or not
	 * @param ob1
	 * @param p
	 * @return
	 */
	public boolean isOnPlane(Physics ob1, Plane p){
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double tx1 = p.getAnchor1().x;
		double tx2 = p.getAnchor2().x;
		double ty = p.getY(x);
		
		//TODO sss
		double width = Math.abs((ob1.getWidth()/2.0) * Math.cos(p.getAngle()));
		
		if (y < ty && (x + width) > (tx1) && (x + width) < (tx2) && p.pointDistance(ob1.getPosition()) < 1)
			return true;
		else
			return false;
	}
	
	/**
	   * Sets the acceleration of an object on a plane
	   * @param ob1
	   * @param p
	   */
	  public void setAcceleration(Physics ob1, Plane p){
		  ob1.setAcceleration(p.getAngle(), ob1.getWeight(), 0.5); 
	  }
	  
	  
	  /**
		  * 
		  * @return
		  */
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
		 * @author Keven-Matthew & Charles-Philippe
		 *
		 */
		class DragListener implements MouseListener, MouseMotionListener {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
}
