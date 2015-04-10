package windows;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

import RunningClasses.RunGame;
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
	 * This holds the background image
	 */
	private BufferedImage background;
	/**
	 * This holds the title for the pause menu
	 */
	private BufferedImage pauseTitle;
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
	private JButton jbtExitGame, jbtBackToGame, jbtBackToLevelSelect;
	
	
	/**
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		addMouseMotionListener(new DragListener());
		this.levelNumber = levelNumber;
		
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
		
		try {
			background = ImageIO.read(new File("Resources/background.png"));
			pauseTitle = ImageIO.read(new File("Resources/Menus/PauseMenu/pauseTitle.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Scanner reader = new Scanner(new File(LEVELDIRECTORY + "/level" + levelNumber + ".lvl"));
			System.out.println(LEVELDIRECTORY + "/level" + levelNumber + ".lvl");
			
			tito = new Tito(reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), reader.nextDouble(), t);
			
			double numberOfTree = reader.nextDouble();
			System.out.println("Number of trees" + numberOfTree);
			for(int i=0; i<numberOfTree; i++)
				treeList.add(new Tree(reader.nextDouble(), reader.nextDouble()));
			
			double numberOfBench = reader.nextDouble();
			System.out.println("Number of Benches" + numberOfBench);
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
		
		
		spriteSheet = tito.getTexture();
		t = new Timer(1000 / 25, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteSheet ss = new SpriteSheet(spriteSheet);
				
				if (i < rollingx.length) {

					// sprite width: 300 height: 250 and 27 pixel from paws to bottom
					//sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					if (counter % 2 == 0){
					i++;
					j++;
					}
				} else {
					i = 0;
					j = 0;
					// sprite width: 300 height: 250 and 27 pixel from paws to bottom
					//sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					i++;
					j++;
				}
				
				counter++;
				repaint();
				
			}
			
		});
		t.start();
		
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
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		
		for(int i=0; i<treeList.size(); i++){
			g.drawImage(treeList.get(i).getTexture(), (int)(gUnit*treeList.get(i).getPosition().x), (int)(gUnit*treeList.get(i).getPosition().y), (int)(2.9*gUnit), (int)(3.5*gUnit), null);
		}
		for(int i=0; i<benchList.size(); i++){
			g.drawImage(benchList.get(i).getTexture(), (int)(gUnit*benchList.get(i).position.x), (int)(gUnit*benchList.get(i).position.y), null);
		}
		for(int i=0; i<coneList.size(); i++){
			g.drawImage(coneList.get(i).getTexture(), (int)(gUnit*coneList.get(i).position.x), (int)(gUnit*coneList.get(i).position.y), null);
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
			g.drawImage(springList.get(i).getTexture(), (int)(gUnit*springList.get(i).position.x), (int)(gUnit*springList.get(i).position.y), null);
		}
		for(int i=0; i<trashCanList.size(); i++){
			g.drawImage(trashCanList.get(i).getTexture(), (int)(gUnit*trashCanList.get(i).getPosition().x), (int)(gUnit*trashCanList.get(i).getPosition().y), (int)(0.4*gUnit), (int)(gUnit*0.5), null);
		}
		
		g.drawImage(sprite, (int)(gUnit*tito.getPosition().x), (int)(gUnit*tito.getPosition().y), (int)(gUnit*75/256), (int)(gUnit*75/256), null);

		g.drawRect((int)(trashCanList.get(0).r.x/256*RunGame.getgUnit()), (int)(trashCanList.get(0).r.y/256*RunGame.getgUnit()), (int)(trashCanList.get(0).r.width/256*RunGame.getgUnit()), (int)(trashCanList.get(0).r.height/256*RunGame.getgUnit()));
		System.out.println("BOB" + RunGame.getgUnit());
		if(isPaused){
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.drawImage(pauseTitle, 248*getWidth()/1280, 10*getHeight()/720, pauseTitle.getWidth()*getWidth()/1280, pauseTitle.getHeight()*getHeight()/720, null);
		}
	}
	
	class DragListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			if(trashCanList.get(0).r.contains(arg0.getPoint())){
				trashCanList.get(0).setX(arg0.getX());
				trashCanList.get(0).setY(arg0.getY());
				trashCanList.get(0).r.x = (arg0.getX());
				trashCanList.get(0).r.y = (arg0.getY());
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
