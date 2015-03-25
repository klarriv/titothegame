package RunningClasses;
import javax.swing.JFrame;

public class AnimationTestFrame extends JFrame {
	public static int gUnit;
	private PlaneTestPanel p = new PlaneTestPanel();
	private GravityTestPanel g = new GravityTestPanel();
	private TestAnimationPanel t = new TestAnimationPanel();
	private PhysicsTestPanel pt = new PhysicsTestPanel();

	public AnimationTestFrame(){
		//gUnit = g.getWidth()/24;
		pt.setVisible(true);
		add(pt);
	}
	public static void main(String[] args) {
		AnimationTestFrame frame = new AnimationTestFrame();
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}