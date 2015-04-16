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
	private int titoXSprite = 0;
	private int titoYSprite = 0;
	private boolean isPaused = false;
	private JButton jbtExitGame, jbtBackToGame, jbtBackToLevelSelect, jbtPlay, jbtRestart;
	//private Maison m;
	
	/**
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		addMouseMotionListener(new DragListener());
		this.levelNumber = levelNumber;
		
		// START OF PLAY/RESTART BUTTONS
		jbtPlay = new JButton(new ImageIcon(MainFrame.getTl().levelPlayTexture));
		jbtPlay.setBorder(BorderFactory.createEmptyBorder());
		jbtPlay.setContentAreaFilled(false);
		jbtPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < ropeList.size(); i++){
					if (ropeList.get(i).isUsed() == 2)
						ropeList.get(i).setLength3();
					else if (ropeList.get(i).isUsed() == 1)
						ropeList.get(i).setLength2();
				}
				t.start();
			}
			
		});
		jbtRestart = new JButton("Restart");
		jbtRestart.setBorder(BorderFactory.createEmptyBorder());
		jbtRestart.setContentAreaFilled(false);
		jbtRestart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				loadObjects();
				repaint();
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
		
		// START OF LOADING STUFF
		loadObjects();
		
		//m = new Maison(3.1, 1.6, 0.7, 2, 1 );
		
		spriteSheet = tito.getTexture();
		// END OF LOADING STUFF
		
		// START OF TIMER FOR MAKING TITO MOVE
		t = new Timer(1000 / 25, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteSheet ss = new SpriteSheet(spriteSheet);
				
				if (titoXSprite < rollingx.length) {
					sprite = ss.grabSprite(rollingx[titoXSprite] * 300, rollingy[titoYSprite] * 250, 289, 250);
					if (counter % 2 == 0){
						titoXSprite++;
						titoYSprite++;
					}
				} else {
					titoXSprite = 0;
					titoYSprite = 0;
					sprite = ss.grabSprite(rollingx[titoXSprite] * 300, rollingy[titoYSprite] * 250, 289, 250);
					titoXSprite++;
					titoYSprite++;
				}
				
				counter++;
				repaint();	
			}
		});
		// END OF TIMER FOR MAKING TITO MOVE
		
	}

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

			tito = new Tito(reader.nextDouble(), reader.nextDouble(),
					reader.nextDouble(), reader.nextDouble(), t);
			//1
			double numberOfTree = reader.nextDouble();
			for (int i = 0; i < numberOfTree; i++)
				treeList.add(new Tree(reader.nextDouble(), reader.nextDouble()));
			//2
			double numberOfBench = reader.nextDouble();
			for (int i = 0; i < numberOfBench; i++)
				benchList.add(new Bench(reader.nextDouble(), reader.nextDouble()));
			//3
			double numberOfCone = reader.nextDouble();
			for (int i = 0; i < numberOfCone; i++)
				coneList.add(new Cone(reader.nextDouble(), reader.nextDouble()));
			//4
			double numberOfPlane = reader.nextDouble();
			for (int i = 0; i < numberOfPlane; i++)
				planeList.add(new Plane(reader.nextDouble(), reader.nextDouble(), Math.toRadians(reader.nextDouble()), reader.nextDouble()));
			//5
			double numberOfRope = reader.nextDouble();
			for (int i = 0; i < numberOfRope; i++)
				ropeList.add(new Rope(reader.nextDouble(), reader.nextDouble()));
			//6
			double numberOfSeesaw = reader.nextDouble();
			for (int i = 0; i < numberOfSeesaw; i++)
				seesawList.add(new SeeSaw(reader.nextDouble(), reader.nextDouble()));
			//7
			double numberOfSpring = reader.nextDouble();
			for (int i = 0; i < numberOfSpring; i++)
				springList.add(new Spring(reader.nextDouble(), reader.nextDouble()));
			//8
			double numberOfTrashCan = reader.nextDouble();
			for (int i = 0; i < numberOfTrashCan; i++)
				trashCanList.add(new TrashCan(reader.nextDouble(), reader.nextDouble()));
			//9
			double numberOfPulley = reader.nextDouble();
			for (int i = 0; i<numberOfPulley; i++)
				pulleyList.add(new Pulley(reader.nextDouble(), reader.nextDouble(), reader.nextBoolean()));
			//10
			double numberOfMaison = reader.nextDouble();
			for (int i = 0; i<numberOfMaison; i++)
				maisonList.add(new Maison(reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextInt()));

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//1 TREE
		for(int i=0; i<treeList.size(); i++){
			g.drawImage(treeList.get(i).getTexture(), (int)(gUnit*treeList.get(i).getPosition().x), (int)(gUnit*treeList.get(i).getPosition().y), (int)(2.9*gUnit), (int)(3.5*gUnit), null);
		}
		//2 BENCH 
		for(int i=0; i<benchList.size(); i++){
			if(benchList.get(i).isVisible())
				g.drawImage(benchList.get(i).getTexture(), (int)(gUnit*benchList.get(i).getPosition().x), (int)(gUnit*benchList.get(i).getPosition().y), (int)(Bench.WIDTH*gUnit), (int)(Bench.HEIGHT*gUnit), null);
		}
		//3 CONE
		for(int i=0; i<coneList.size(); i++){
			if(coneList.get(i).isVisible())
				g.drawImage(coneList.get(i).getTexture(), (int)(gUnit*coneList.get(i).getPosition().x), (int)(gUnit*coneList.get(i).getPosition().y), (int)(Cone.WIDTH*gUnit), (int)(Cone.HEIGHT*gUnit),  null);
		}
		//4 PLANE
		for(int i=0; i<planeList.size(); i++){
			g.drawLine((int)(gUnit*(planeList.get(i).getX()[0])), (int)(gUnit*(planeList.get(i).getY()[0])), (int)(gUnit*(planeList.get(i).getX()[1])), (int)(gUnit*planeList.get(i).getY()[1]));
		}
		//TODO image
		//5 ROPE
		for(int i=0; i<ropeList.size(); i++){
			//g.drawImage(ropeList.get(i).texture, (int)(gUnit*ropeList.get(i).position.x), (int)(gUnit*ropeList.get(i).position.y), null);
			if (ropeList.get(i).isUsed() == -1){
				g.fillRect((int)(gUnit*ropeList.get(i).getAnchor2().x), (int)(gUnit*ropeList.get(i).getAnchor2().y), 20, 20);
			}
			else if (ropeList.get(i).isUsed() == 1){
				int[] xPoints = {(int)(gUnit*ropeList.get(i).getAnchor1().x) + 50, (int)(gUnit*ropeList.get(i).getAnchor2().x) + 50};
				int[] yPoints = {(int)(gUnit*ropeList.get(i).getAnchor1().y), (int)(gUnit*ropeList.get(i).getAnchor2().y)};
				g.drawPolyline(xPoints, yPoints, 2);
			}
			else if (ropeList.get(i).isUsed() == 2){
				int[] xPoints = {(int)(gUnit*ropeList.get(i).getAnchor1().x) + 50, (int)(gUnit*ropeList.get(i).getAnchor2().x) + 50, (int)(gUnit*ropeList.get(i).getAnchor3().x) + 50};
				int[] yPoints = {(int)(gUnit*ropeList.get(i).getAnchor1().y), (int)(gUnit*ropeList.get(i).getAnchor2().y), (int)(gUnit*ropeList.get(i).getAnchor3().y)};
				g.drawPolyline(xPoints, yPoints, 3);
			}
			else if (ropeList.get(i).isUsed() == -2){
				g.drawLine((int)(gUnit*ropeList.get(i).getAnchor2().x) + 50, (int)(gUnit*ropeList.get(i).getAnchor2().y), (int)(gUnit*ropeList.get(i).getAnchor2().x) + 50 , (int)(gUnit*ropeList.get(i).getAnchor2().y) + 75);
				
			}
		}
		//6 SEESAW
		for(int i=0; i<seesawList.size(); i++){
			g.drawImage(seesawList.get(i).getTexture(), (int)(gUnit*seesawList.get(i).getPosition().x), (int)(gUnit*seesawList.get(i).getPosition().y), null);
		}
		//7 SPRING HAHAHAHAHAHAHA
		for(int i=0; i<springList.size(); i++){
			g.drawImage(springList.get(i).getTexture(), (int)(gUnit*springList.get(i).getPosition().x), (int)(gUnit*springList.get(i).getPosition().y), null);
		}
		//8 TRASHCAN
		for(int i=0; i<trashCanList.size(); i++){
			if(trashCanList.get(i).isVisible())
				g.drawImage(trashCanList.get(i).getTexture(), (int)(gUnit*trashCanList.get(i).getPosition().x), (int)(gUnit*trashCanList.get(i).getPosition().y), (int)(TrashCan.WIDTH*gUnit), (int)(TrashCan.HEIGHT*gUnit), null);
		}
		//TODO integers
		//9 PULLEY
		for (int i=0; i<pulleyList.size(); i++){
			if(pulleyList.get(i).isVisible())
				g.drawImage(pulleyList.get(i).getTexture(), (int)(gUnit*pulleyList.get(i).getPosition().x) + 15, (int)(gUnit*pulleyList.get(i).getPosition().y), null);
		}
		//10 MAISON
		for (int i=0; i<maisonList.size(); i++){
			// TODO fix the height of the house so it is the same used to draw it. 
			g.drawImage(maisonList.get(i).getTexture(), (int)(gUnit*maisonList.get(i).getPosition().x), (int)(gUnit*maisonList.get(i).getPosition().y), (int)(maisonList.get(i).getWidth()*gUnit), (int)(maisonList.get(i).getHeight()*gUnit), null);
		}
		
		//g.drawImage(m.getTexture(), (int)(gUnit*m.getPosition().x), (int)(gUnit*m.getPosition().y)- 48, (int)(0.7*gUnit), (int)(1.1*gUnit), null);
		
		g.drawImage(sprite, (int)(gUnit*tito.getPosition().x), (int)(gUnit*tito.getPosition().y), (int)(gUnit*0.25), (int)(gUnit*0.25), null);

		jbtPlay.setBounds(10, 10, 40, 40);
		jbtRestart.setBounds(60, 10, 40, 40);
		
		if(isPaused){
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(MainFrame.getTl().levelPauseHeaderTexture, 248*getWidth()/1280, 10*getHeight()/720, MainFrame.getTl().levelPauseHeaderTexture.getWidth()*getWidth()/1280, MainFrame.getTl().levelPauseHeaderTexture.getHeight()*getHeight()/720, null);
		}
		
		if(t.isRunning()){
			//TODO create the see-saw....
			//Collision and movements
			if (trashCanList.get(0).getPosition().y >= 2 && trashCanList.get(0).single == 0){
				tito.setEnergyVelocity(trashCanList.get(0).getVy(), trashCanList.get(0).getWeight(), tito.getWeight());
				tito.setVx();
				tito.setVy();
				//(trashCanList.get(0).getWeight());
				trashCanList.get(0).single++;
				////(" vx:" + tito.getVx() + " vy: " + tito.getVy() + " vyy: " + trashCanList.get(0).getVy());
			}
			for (int i = 0; i < trashCanList.size(); i++){
				if (trashCanList.get(i).getPosition().y < 2 && !trashCanList.get(i).isUsed()){
					//(trash.getVx() + " " + trash.getVy());
					projectileMotion(trashCanList.get(i));
					basicMove(trashCanList.get(i));
					////(" vyy: " + trashCanList.get(0).getVy());
				}
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
				////(" vx:" + tito.getVx() + " vy: " + tito.getVy() );
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
			for (int i=0; i<maisonList.size(); i++){
				if(maisonList.get(i).colliding(tito.getPosition()))
					tito.setVx(-1*tito.getVx());
			}
			//ropes
			for(int i=0; i<ropeList.size(); i++){
				ropeList.get(i).setXAnchored();
				//System.out.println(ropeList.get(i).getOb1() == ropeList.get(i).getOb2());
				if (ropeList.get(i).isUsed() == 2){
					double y = ropeList.get(i).getOb1().projectileMotions(ropeList.get(i).getOb1().getWeight(),
							ropeList.get(i).getOb1().getPosition().y, ropeList.get(i).getOb1().getVy(), t.getDelay());
					
					
					if (!ropeList.get(i).isMaxed() && t.isRunning() ){
						ropeList.get(i).getOb1().setY(y);
						ropeList.get(i).getOb1().setVy();
						
						ropeList.get(i).pulleyMove(ropeList.get(i).getOb1().getPosition().x, y);
					}
				
				}
				ropeList.get(i).setTotalForce();
			}
			
			
		}
		
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
		public void mouseDragged(MouseEvent arg0) throws IndexOutOfBoundsException{
			double x = (double)arg0.getX()/gUnit;
			double y = (double)arg0.getY()/gUnit;
			DoublePoint p = new DoublePoint(x, y);
			
			
			for(int i=0; i<benchList.size(); i++){
				if(benchList.get(i).getR()!=null && benchList.get(i).getR().contains(p)){
					benchList.get(i).setX(x-Bench.WIDTH/2);
					benchList.get(i).setY(y-Bench.HEIGHT/2);
					benchList.get(i).getPosition().x = x-Bench.WIDTH/2;
					benchList.get(i).getPosition().y = y-Bench.HEIGHT/2;
				}			}
			for(int i=0; i<coneList.size(); i++){
				if(coneList.get(i).getR()!=null && coneList.get(i).getR().contains(p)){
					coneList.get(i).setX(x-Cone.WIDTH/2);
					coneList.get(i).setY(y-Cone.HEIGHT/2);
					coneList.get(i).getPosition().x = x-Cone.WIDTH/2;
					coneList.get(i).getPosition().y = y-Cone.HEIGHT/2;
				}
			}
			for(int i=0; i<planeList.size(); i++){
				// TODO make the planes move
				/*
				if(planeList.get(i).getR().contains(p)){
					planeList.get(i).setX(x-TrashCan.WIDTH/2);
					planeList.get(i).setY(y-TrashCan.HEIGHT/2);
					planeList.get(i).getPosition().x = x-TrashCan.WIDTH/2;
					planeList.get(i).getPosition().y = y-TrashCan.HEIGHT/2;
				}
					*/
			}
			////(x + " " + ropeList.size());
			for(int i=0; i<ropeList.size(); i++){
				ropeList.get(i).setXAnchored();
				if (x >= ropeList.get(i).getAnchor2().x && x <= ropeList.get(i).getAnchor2().x +(20/gUnit) && y >= ropeList.get(i).getAnchor2().y
						&& y <= ropeList.get(i).getAnchor2().y + (20/gUnit) && ropeList.get(i).isUsed() == -1){
					ropeList.get(i).getAnchor2().x = x - (10/gUnit);
					ropeList.get(i).getAnchor2().y = y - (10/gUnit);
					
					for (int j = 0; j < pulleyList.size(); j++){
						if (ropeList.get(i).getAnchor2().distance(pulleyList.get(j).getPosition()) <= 0.3)
							ropeList.get(i).setPulley(pulleyList.get(j));
					}
					
				}
				
				////(i + " " + ropeList.get(i).isUsed());
			}
			
			for(int i=0; i<trashCanList.size(); i++){
			if(trashCanList.get(i).getR()!=null && trashCanList.get(i).getR().contains(p)){
				// USED TO MOVE THE TRASHCAN AROUND
				trashCanList.get(i).setX(x-TrashCan.WIDTH/2);
				trashCanList.get(i).setY(y-TrashCan.HEIGHT/2);
				trashCanList.get(i).getPosition().x = x-TrashCan.WIDTH/2;
				trashCanList.get(i).getPosition().y = y-TrashCan.HEIGHT/2;
				////("Tra" + i + " weight " + trashCanList.get(i).getWeight());
				
				// USED TO PUT OBJECTS IN THE TRASHCAN
				for(int j = i+1; j<trashCanList.size(); j++){
					if(trashCanList.get(j).getR()!=null && trashCanList.get(j).getR()!=null && trashCanList.get(i).getR().contains(trashCanList.get(j).getR())
							&& !trashCanList.get(j).isUsed()){
						trashCanList.get(i).setWeight(trashCanList.get(i).getWeight() + trashCanList.get(j).getWeight());
						trashCanList.get(j).setVisible(false);
						trashCanList.get(j).setR(null);
						trashCanList.get(j).setUsed(true);
					}
				}
				for(int j = 0; j<coneList.size(); j++){
					if(coneList.get(j).getR()!=null && trashCanList.get(i).getR().contains(coneList.get(j).getR())){
						trashCanList.get(i).setWeight(trashCanList.get(i).getWeight() + coneList.get(j).getWeight());
						coneList.get(j).setVisible(false);
						coneList.get(j).setR(null);
					}
				}
				for(int j = 0; j<benchList.size(); j++){
					if(benchList.get(j).getR()!=null && trashCanList.get(i).getR().contains(benchList.get(j).getR())){
						trashCanList.get(i).setWeight(trashCanList.get(i).getWeight() + benchList.get(j).getWeight());
						benchList.get(j).setVisible(false);
						benchList.get(j).setR(null);
					}
				}
				for(int j = 0; j < ropeList.size(); j++){
					if (trashCanList.get(i).getPosition().distance(ropeList.get(j).getAnchor2()) <= 0.3){
						if (ropeList.get(j).isUsed() == 0 && !trashCanList.get(i).isUsed()){
							ropeList.get(j).setOb1(trashCanList.get(i));
							trashCanList.get(i).setUsed(true);
							
						}
						else if (ropeList.get(j).isUsed() == 1 && !trashCanList.get(i).isUsed()){
							ropeList.get(j).setOb2(trashCanList.get(i));
							trashCanList.get(i).setUsed(true);
						}
					}
					
				}
			}
			if(!t.isRunning())
				repaint();
		}
	}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}