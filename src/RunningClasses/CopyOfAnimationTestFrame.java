package RunningClasses;
import javax.swing.JFrame;

public class CopyOfAnimationTestFrame extends JFrame {
	public static int gUnit;
	private PlaneTestPanel p = new PlaneTestPanel();
	private GravityTestPanel g = new GravityTestPanel();
	private TestAnimationPanel t = new TestAnimationPanel();
	private PhysicsTestPanel pt = new PhysicsTestPanel();
	private VectorTestPanel v = new VectorTestPanel();
	private CopyOfLevelTestPanel l = new CopyOfLevelTestPanel();

	public CopyOfAnimationTestFrame(){
		//gUnit = g.getWidth()/24;
		l.setVisible(true);
		add(l);
	}
	public static void main(String[] args) {
		CopyOfAnimationTestFrame frame = new CopyOfAnimationTestFrame();
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}