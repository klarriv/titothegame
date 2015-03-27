package windows;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		
		try {
			Scanner reader = new Scanner(new File(LEVELDIRECTORY + "/level" + levelNumber + ".lvl"));
		
			int numberOfTree = reader.nextInt();
			for(int i=0; i<numberOfTree; i++)
				treeList.add(new Tree(reader.nextInt(), reader.nextInt()));
			
			int numberOfBench =  reader.nextInt();
			for(int i=0; i<numberOfBench; i++)
				benchList.add(new Bench(reader.nextInt(), reader.nextInt()));
			
			int numberOfCone =  reader.nextInt();
			for(int i=0; i<numberOfCone; i++)
				coneList.add(new Cone(reader.nextInt(), reader.nextInt()));
			
			int numberOfPlane =  reader.nextInt();
			for(int i=0; i<numberOfPlane; i++)
				planeList.add(new Plane(reader.nextDouble(), reader.nextDouble(), reader.nextDouble()));
			
			int numberOfRope =  reader.nextInt();
			for(int i=0; i<numberOfRope; i++)
				ropeList.add(new Rope(reader.nextInt(), reader.nextInt()));
			
			int numberOfSeesaw =  reader.nextInt();
			for(int i=0; i<numberOfSeesaw; i++)
				seesawList.add(new SeeSaw(reader.nextInt(), reader.nextInt()));
			
			int numberOfSpring =  reader.nextInt();
			for(int i=0; i<numberOfSpring; i++)
				springList.add(new Spring(reader.nextInt(), reader.nextInt()));
			
			int numberOfTrashCan =  reader.nextInt();
			for(int i=0; i<numberOfTrashCan; i++)
				trashCanList.add(new TrashCan(reader.nextInt(), reader.nextInt()));
			
			
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
		
		for(int i=0; i<treeList.size(); i++){
			g.drawImage(treeList.get(i).texture, treeList.get(i).x, treeList.get(i).y, null);
		}
		
	}
	
}
