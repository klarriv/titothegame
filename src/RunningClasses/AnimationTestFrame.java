package RunningClasses;
import javax.swing.JFrame;

public class AnimationTestFrame extends JFrame {
	public static int gUnit;
/**	private PlaneTestPanel p = new PlaneTestPanel();
	private GravityTestPanel g = new GravityTestPanel();
	private TestAnimationPanel t = new TestAnimationPanel();
	private PhysicsTestPanel pt = new PhysicsTestPanel();
	private VectorTestPanel v = new VectorTestPanel();*/
	private LevelTestPanel l = new LevelTestPanel();
	private RopesTestPanel r = new RopesTestPanel();

	public AnimationTestFrame(){
		//gUnit = g.getWidth()/24;
		//.setVisible(true);
		add(l);
	}
	public static void main(String[] args) {
		AnimationTestFrame frame = new AnimationTestFrame();
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}