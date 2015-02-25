package windows;

import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

public class Level extends JPanel implements ActionListener{

	public Timer t;
	private ArrayList objectList;
	private Clip levelSong;
	
	Level(){
		
	}
	
	public void start(){
		t.start();
	}
	
	public void stop(){
		t.stop();
	}
	
	public void startLevelMusic(){
		levelSong.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
