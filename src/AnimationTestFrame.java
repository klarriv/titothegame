import javax.swing.JFrame;

public class AnimationTestFrame extends JFrame {
	private GravityTestPanel g = new GravityTestPanel();
	private TestAnimationPanel p = new TestAnimationPanel();

	public AnimationTestFrame(){
		g.setVisible(true);
		add(g);
	}
	public static void main(String[] args) {
		AnimationTestFrame frame = new AnimationTestFrame();
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
