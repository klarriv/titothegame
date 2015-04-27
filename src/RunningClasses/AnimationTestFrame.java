package RunningClasses;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AnimationTestFrame extends JFrame {
	public static int gUnit;
/**	private PlaneTestPanel p = new PlaneTestPanel();
	private GravityTestPanel g = new GravityTestPanel();
	private TestAnimationPanel t = new TestAnimationPanel();
	private PhysicsTestPanel pt = new PhysicsTestPanel();
	private VectorTestPanel v = new VectorTestPanel();
	private LevelTestPanel l = new LevelTestPanel();*/
	//private RopesTestPanel r = new RopesTestPanel();
	//private TrashCanTestPanel t = new TrashCanTestPanel();
	//private PlaneTestPanel2 p = new PlaneTestPanel2();
	private PlaneIncliningTestPanel pit = new PlaneIncliningTestPanel();
	//private RotationTestPanel r = new RotationTestPanel();
	
	
	public AnimationTestFrame(){
		
		add(pit);
	}
	public static void main(String[] args) {
		AnimationTestFrame frame = new AnimationTestFrame();
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	} 

}