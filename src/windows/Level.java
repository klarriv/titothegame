package windows;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

import objects.*;
import objects.Spring;

/**
 * This class loads the level file and creates the GUI with the objects that are
 * passed with the .lvl file.
 * @author Keven-Matthew
 */
public class Level extends JPanel implements ActionListener {
	
	/**
	 * This holds the background image
	 */
	private BufferedImage background;
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
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		
		try {
			background = ImageIO.read(new File("Resources/background.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Scanner reader = new Scanner(new File(LEVELDIRECTORY + "/level" + levelNumber + ".lvl"));
			System.out.println(LEVELDIRECTORY + "/level" + levelNumber + ".lvl");
			
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
			g.drawImage(treeList.get(i).texture, (int)(gUnit*treeList.get(i).position.x), (int)(gUnit*treeList.get(i).position.y), (int)(2.9*gUnit), (int)(3.5*gUnit), null);
		}
		for(int i=0; i<benchList.size(); i++){
			g.drawImage(benchList.get(i).texture, (int)(gUnit*benchList.get(i).position.x), (int)(gUnit*benchList.get(i).position.y), null);
		}
		for(int i=0; i<coneList.size(); i++){
			g.drawImage(coneList.get(i).texture, (int)(gUnit*coneList.get(i).position.x), (int)(gUnit*coneList.get(i).position.y), null);
		}
		for(int i=0; i<planeList.size(); i++){
			g.drawLine((int)(gUnit*(planeList.get(i).getX()[0])), (int)(gUnit*(planeList.get(i).getY()[0])), (int)(gUnit*(planeList.get(i).getX()[1])), (int)(gUnit*planeList.get(i).getY()[1]));
			//g.drawImage(planeList.get(i).texture, (int)planeList.get(i).getDp().x, (int)planeList.get(i).getDp().y, null);
		}
		for(int i=0; i<ropeList.size(); i++){
			g.drawImage(ropeList.get(i).texture, (int)(gUnit*ropeList.get(i).position.x), (int)(gUnit*ropeList.get(i).position.y), null);
		}
		for(int i=0; i<seesawList.size(); i++){
			g.drawImage(seesawList.get(i).texture, (int)(gUnit*seesawList.get(i).position.x), (int)(gUnit*seesawList.get(i).position.y), null);
		}
		for(int i=0; i<springList.size(); i++){
			g.drawImage(springList.get(i).texture, (int)(gUnit*springList.get(i).position.x), (int)(gUnit*springList.get(i).position.y), null);
		}
		for(int i=0; i<trashCanList.size(); i++){
			g.drawImage(trashCanList.get(i).texture, (int)(gUnit*trashCanList.get(i).getPosition().x), (int)(gUnit*trashCanList.get(i).getPosition().y), (int)(0.4*gUnit), (int)(gUnit*0.5), null);
		}
	}
	
}
