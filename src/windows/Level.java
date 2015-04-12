package windows;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;
import RunningClasses.SpriteSheet;
import objects.*;
import objects.Spring;

/**
 * This class loads the level file and creates the GUI with the objects that are
 * passed with the .lvl file.
 * @author Keven-Matthew
 */
public class Level extends JPanel implements ActionListener{
	
	/**
	 * This holds the object that represents Tito in the game.
	 */
	private Tito tito;
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
	 * List of springs read from the level file.
	 */
	private ArrayList<Spring> springList = new ArrayList<Spring>();
	/**
	 * List of trashcans read from the level file.
	 */
	private ArrayList<TrashCan> trashCanList =  new ArrayList<TrashCan>();
	//TODO set other Maisons
	private Maison m;
	/**
	 * The song used in the levels
	 */
	private Clip levelSong;
	/**
	 * Contains the location of the level files
	 */
	private final String LEVELDIRECTORY = "Resources/Levels/";
	/**
	 * This contains the game unit to resize the textures when the window is resized.
	 */
	private double gUnit;
	/**
	 * 
	 */
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	/**
	 * 
	 */
	private int[] rollingy = { 1, 1, 2, 0, 2 };
	
	private BufferedImage spriteSheet = null;
	private BufferedImage sprite;     
	private int counter = 0;
	private int i = 0;
	private int j = 0;
	private boolean isPaused = false;
	private JButton jbtExitGame, jbtBackToGame, jbtBackToLevelSelect, jbtPlay, jbtRestart;
	
	/**
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		addMouseMotionListener(new DragListener());
		this.levelNumber = levelNumber;
		
		// START OF PLAY/RESTART BUTTONS
		jbtPlay = new JButton("Play");
		jbtPlay.setBorder(BorderFactory.createEmptyBorder());
		jbtPlay.setContentAreaFilled(false);
		jbtPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				t.start();
			}
			
		});
		jbtRestart = new JButton("Restart");
		jbtRestart.setBorder(BorderFactory.createEmptyBorder());
		jbtRestart.setContentAreaFilled(false);
		jbtRestart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Reset the level and variables
			}
			
		});
		
		add(jbtPlay);
		add(jbtRestart);
		// END OF PLAY/RESTART BUTTONS
		
		// START OF PAUSE MENU ITEMS
		// This block to the next comment is for the pause menu
		jbtBackToGame = new JButton("Back to Game");
		jbtBackToLevelSelect = new JButton("Exit to level menu");
		jbtExitGame = new JButton("Exit Game");
		
		jbtBackToGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isPaused = false;
				t.start();
				repaint();
				jbtBackToGame.setVisible(false);
				jbtBackToLevelSelect.setVisible(false);
				jbtExitGame.setVisible(false);
			}
			
		});
		jbtBackToLevelSelect.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isPaused = false;
				jbtBackToGame.setVisible(false);
				jbtBackToLevelSelect.setVisible(false);
				jbtExitGame.setVisible(false);
				t.start();
				repaint();
				// TODO need to reset the level
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}
		});
		jbtExitGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		add(jbtBackToGame);
		add(jbtBackToLevelSelect);
		add(jbtExitGame);
		jbtBackToGame.setVisible(false);
		jbtBackToLevelSelect.setVisible(false);
		jbtExitGame.setVisible(false);
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "openPauseMenu");
		getActionMap().put("openPauseMenu", new AbstractAction(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!isPaused){	
					isPaused = true;
					t.stop();
					repaint();
					jbtBackToGame.setVisible(true);
					jbtBackToLevelSelect.setVisible(true);
					jbtExitGame.setVisible(true);
				}
				else{
					isPaused = false;
					t.start();
					repaint();
					jbtBackToGame.setVisible(false);
					jbtBackToLevelSelect.setVisible(false);
					jbtExitGame.setVisible(false);
				}
				
			}
		});
		// END OF PAUSE MENU ITEMS
		
		// START OF LOADING IMAGES AND OBJECTS
		// From the next line to the next comment is code for loading the images and the objects from the .lvl file.
		try {
			Scanner reader = new Scanner(new File(LEVELDIRECTORY + "/level" + levelNumber + ".lvl"));
			
			tito = new Tito(reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), t);
			
			double numberOfTree = reader.nextDouble();
			for(int i=0; i<numberOfTree; i++)
				treeList.add(new Tree(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfBench = reader.nextDouble();
			for(int i=0; i<numberOfBench; i++)
				benchList.add(new Bench(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfCone = reader.nextDouble();
			for(int i=0; i<numberOfCone; i++)
				coneList.add(new Cone(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfPlane = reader.nextDouble();
			for(int i=0; i<numberOfPlane; i++)
				planeList.add(new Plane(reader.nextDouble(), reader.nextDouble(), Math.toRadians(reader.nextDouble()), reader.nextDouble()));

			double numberOfRope = reader.nextDouble();
			for(int i=0; i<numberOfRope; i++)
				ropeList.add(new Rope(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfSeesaw = reader.nextDouble();
			for(int i=0; i<numberOfSeesaw; i++)
				seesawList.add(new SeeSaw(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfSpring = reader.nextDouble();
			for(int i=0; i<numberOfSpring; i++)
				springList.add(new Spring(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfTrashCan = reader.nextDouble();
			for(int i=0; i<numberOfTrashCan; i++)
				trashCanList.add(new TrashCan(reader.nextDouble(), reader.nextDouble()));
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m = new Maison(3.1, 1.6, 0.7, 2, "1" );
		
		spriteSheet = tito.getTexture();
		// END OF LOADING STUFF
		
		// START OF TIMER FOR MAKING TITO MOVE
		t = new Timer(1000 / 25, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteSheet ss = new SpriteSheet(spriteSheet);
				
				if (i < rollingx.length) {
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					if (counter % 2 == 0){
					i++;
					j++;
					}
				} else {
					i = 0;
					j = 0;
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					i++;
					j++;
				}
				
				counter++;
				repaint();	
			}
		});
		// END OF TIMER FOR MAKING TITO MOVE
		
	}

	/**
	 * This starts the timer
	 */
	public void start() {
		t.start();
	}

	/**
	 * This stops the timer
	 */
	public void stop() {
		t.stop();
	}

	/**
	 * This starts the music in the levels
	 */
	public void startLevelMusic() {
		levelSong.start();
	}

	/**
	 * This repaints the level and calls the calculations
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		
		g.drawImage(MainFrame.getTl().levelBackgroundTexture, 0, 0, getWidth(), getHeight(), null);
		
		for(int i=0; i<treeList.size(); i++){
			g.drawImage(treeList.get(i).getTexture(), (int)(gUnit*treeList.get(i).getPosition().x), (int)(gUnit*treeList.get(i).getPosition().y), (int)(2.9*gUnit), (int)(3.5*gUnit), null);
		}
		for(int i=0; i<benchList.size(); i++){
			g.drawImage(benchList.get(i).getTexture(), (int)(gUnit*benchList.get(i).getPosition().x), (int)(gUnit*benchList.get(i).getPosition().y), (int)(Bench.WIDTH*gUnit), (int)(Bench.HEIGHT*gUnit), null);
		}
		for(int i=0; i<coneList.size(); i++){
			g.drawImage(coneList.get(i).getTexture(), (int)(gUnit*coneList.get(i).getPosition().x), (int)(gUnit*coneList.get(i).getPosition().y), (int)(Cone.WIDTH*gUnit), (int)(Cone.HEIGHT*gUnit),  null);
		}
		for(int i=0; i<planeList.size(); i++){
			g.drawLine((int)(gUnit*(planeList.get(i).getX()[0])), (int)(gUnit*(planeList.get(i).getY()[0])), (int)(gUnit*(planeList.get(i).getX()[1])), (int)(gUnit*planeList.get(i).getY()[1]));
		}
		for(int i=0; i<ropeList.size(); i++){
			//g.drawImage(ropeList.get(i).texture, (int)(gUnit*ropeList.get(i).position.x), (int)(gUnit*ropeList.get(i).position.y), null);
		}
		for(int i=0; i<seesawList.size(); i++){
			g.drawImage(seesawList.get(i).getTexture(), (int)(gUnit*seesawList.get(i).getPosition().x), (int)(gUnit*seesawList.get(i).getPosition().y), null);
		}
		for(int i=0; i<springList.size(); i++){
			g.drawImage(springList.get(i).getTexture(), (int)(gUnit*springList.get(i).getPosition().x), (int)(gUnit*springList.get(i).getPosition().y), null);
		}
		for(int i=0; i<trashCanList.size(); i++){
			g.drawImage(trashCanList.get(i).getTexture(), (int)(gUnit*trashCanList.get(i).getPosition().x), (int)(gUnit*trashCanList.get(i).getPosition().y), (int)(TrashCan.WIDTH*gUnit), (int)(TrashCan.HEIGHT*gUnit), null);
		}
		
		g.drawImage(m.getTexture(), (int)(gUnit*m.getPosition().x), (int)(gUnit*m.getPosition().y)- 48, (int)(0.7*gUnit), (int)(1.1*gUnit), null);
		
		g.drawImage(sprite, (int)(gUnit*tito.getPosition().x), (int)(gUnit*tito.getPosition().y), (int)(gUnit*0.25), (int)(gUnit*0.25), null);

		jbtPlay.setBounds(10, 10, 55, 35);
		jbtRestart.setBounds(70, 10, 55, 35);
		
		if(isPaused){
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(MainFrame.getTl().levelPauseHeaderTexture, 248*getWidth()/1280, 10*getHeight()/720, MainFrame.getTl().levelPauseHeaderTexture.getWidth()*getWidth()/1280, MainFrame.getTl().levelPauseHeaderTexture.getHeight()*getHeight()/720, null);
		}
		
		//Collision and movements
		if (trashCanList.get(0).getPosition().y >= 2 && trashCanList.get(0).single == 0){
			tito.setEnergyVelocity(trashCanList.get(0).getVy(), trashCanList.get(0).getWeight(), tito.getWeight());
			tito.setVx();
			tito.setVy();
			//(trashCanList.get(0).getWeight());
			trashCanList.get(0).single++;
			//System.out.println(" vx:" + tito.getVx() + " vy: " + tito.getVy() + " vyy: " + trashCanList.get(0).getVy());
		}
		
		if (trashCanList.get(0).getPosition().y < 2){
			//(trash.getVx() + " " + trash.getVy());
			projectileMotion(trashCanList.get(0));
			basicMove(trashCanList.get(0));
			//System.out.println(" vyy: " + trashCanList.get(0).getVy());
		}
		
		boolean planeCollided = false;
		Plane p = planeList.get(0);
		for (int i = 0; i < planeList.size(); i++){
			planeCollided = planeColliding(planeList.get(i));
			p = planeList.get(i);
		}
		
		if (tito.getVy() > 0.5 && tito.getPosition().y <= 2){
			projectileMotion(tito);
			xMove();
			//System.out.println(" vx:" + tito.getVx() + " vy: " + tito.getVy() );
		}
		else if (planeCollided){
			planeCollision(p);
			projectileMotion(tito);
			xMove();
			
		}
		else{
			projectileMotion(tito);
			xMove();
		}
		
		if(m.colliding(tito.getPosition()))
			tito.setVx(-1*tito.getVx());
		
	}
	
	//Physics moving and colliding methods
	
	
		public void planeCollision(Plane plane){
			
			double angle = plane.angleOfContact(tito.getVx(), tito.getVy());
			
			
				if (tito.getVx() >= 0)
					tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
				else
					tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
				
				tito.setVx();
				tito.setVy();
				
			
		}
		
		public boolean planeColliding(Plane plane){
			double r = 40.0/gUnit;
			DoublePoint dp = new DoublePoint(tito.getPosition().x + r, tito.getPosition().y + r);
			double d = plane.pointDistance(dp);
			if (plane.getWidth() > 0){
				if (d <=  r && (dp.x) <= plane.getX()[1] && dp.x >= plane.getX()[0] )
					return true;
			}
			else if (plane.getWidth() < 0){
				if (d <= r && dp.x > plane.getX()[1] && dp.x < plane.getX()[0] )
					return true;
			}
			return false;
				
		}
		
		public void basicMove(Physics ob1){
			double x = ob1.motion( ob1.getPosition().x, ob1.getVx(), t.getDelay());
			ob1.setX(x);
		}
		
		public void xMove(){
			double x = tito.motion( tito.getPosition().x, -tito.getVx(), t.getDelay());
			
			if (x <=5 && x>= 0)
				tito.setX(x);
			else if (Math.abs(0-x) <= 0.1){
				tito.setX(0);
			}
			else if (Math.abs(5 - x) <= 0.1 ){
				tito.setX(5);
			}
			else
				tito.setVx(-1*tito.getVx());
		}
		
		//TODO work on this bouncy thing
		//Faudrait ajouter le diametre ou le height pour faire les contacts avec le sol.... faudrait que ca soit genre une variable dans les objects
		public void projectileMotion(Physics ob1){
			double y = ob1.projectileMotions(ob1.getWeight(), ob1.getPosition().y, ob1.getVy(), t.getDelay());
			if (ob1.getVy() < 0 && y >= 2.5 - ob1.getHeight()){
				
				
				ob1.setY(2.5- ob1.getHeight());
				ob1.setVy(-1*ob1.getVy() - 1);
				////(loader.getPosition().y);
			}
			
			else if (y <= 2.5- ob1.getHeight()){
				ob1.setY(y);
				ob1.setVy();
			}
		}
		
		//TODO 
		/**public void frictionMove(){
			trash.frictionMotion(trash.getPosition(), trash.getVx(), trash.getVy(),  t.getDelay());	
			trash.setVy();
			trash.setVx();
		}
		
		public void setAcceleration(int aa){
			////( aa);
			trash.setAcceleration(Math.toRadians(aa), trash.getWeight(), 0.5);
		}*/
	
	
	
	
	class DragListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			DoublePoint p = new DoublePoint(arg0.getX()/gUnit, arg0.getY()/gUnit);
			for(int i=0; i<benchList.size(); i++){
				if(benchList.get(i).getR().contains(p)){
					benchList.get(i).setX(arg0.getX()/gUnit-Bench.WIDTH/2);
					benchList.get(i).setY(arg0.getY()/gUnit-Bench.HEIGHT/2);
					benchList.get(i).getPosition().x = arg0.getX()/gUnit-Bench.WIDTH/2;
					benchList.get(i).getPosition().y = arg0.getY()/gUnit-Bench.HEIGHT/2;
				}			}
			for(int i=0; i<coneList.size(); i++){
				if(coneList.get(i).getR().contains(p)){
					coneList.get(i).setX(arg0.getX()/gUnit-Cone.WIDTH/2);
					coneList.get(i).setY(arg0.getY()/gUnit-Cone.HEIGHT/2);
					coneList.get(i).getPosition().x = arg0.getX()/gUnit-Cone.WIDTH/2;
					coneList.get(i).getPosition().y = arg0.getY()/gUnit-Cone.HEIGHT/2;
				}
			}
			for(int i=0; i<planeList.size(); i++){
				// TODO make the planes move
				/*
				if(planeList.get(i).getR().contains(p)){
					planeList.get(i).setX(arg0.getX()/gUnit-TrashCan.WIDTH/2);
					planeList.get(i).setY(arg0.getY()/gUnit-TrashCan.HEIGHT/2);
					planeList.get(i).getPosition().x = arg0.getX()/gUnit-TrashCan.WIDTH/2;
					planeList.get(i).getPosition().y = arg0.getY()/gUnit-TrashCan.HEIGHT/2;
				}
					*/
			}
			for(int i=0; i<ropeList.size(); i++){
				//TODO make the ropes move
			}
			for(int i=0; i<trashCanList.size(); i++){
			if(trashCanList.get(i).getR().contains(p)){
				trashCanList.get(i).setX(arg0.getX()/gUnit-TrashCan.WIDTH/2);
				trashCanList.get(i).setY(arg0.getY()/gUnit-TrashCan.HEIGHT/2);
				trashCanList.get(i).getPosition().x = arg0.getX()/gUnit-TrashCan.WIDTH/2;
				trashCanList.get(i).getPosition().y = arg0.getY()/gUnit-TrashCan.HEIGHT/2;
			}
		}
	}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}