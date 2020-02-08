package FumsifolEsp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gen75kVA extends JDialog {
	private static List<Integer> scores = new ArrayList<>();
	private final JPanel contentPanel = new JPanel();
	private static List<Integer> plots = new ArrayList<>();
	GraphLines graph;
	public static void main(String[] args) {
		try {
			Gen75kVA dialog = new Gen75kVA();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JPanel buttonPane = new JPanel();
	public Gen75kVA() {
		setSize(800, 600);
		setLocationRelativeTo(getParent());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Random random = new Random();
        int maxDataPoints = 100;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((int) i);
        }
		
		int a = 150;
        for (int i = 0; i < 60 ; i++) {
            int x1 = a+=3;
            plots.add(x1);
        }
		
        graph = new GraphLines(plots, "g75", 800, 600);
		
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					Timer mm; int a=0, f =3;
					public void actionPerformed(ActionEvent arg0) {
						mm = new Timer(100, new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								
								if(plots.size()>(a+5)){
								graph.update(plots, 1000, false);
								a++;
								}else if(plots.size()>a){
									System.out.println("Stopping");
									graph.update(plots, 1000, true);
									a++;
								}else if(plots.size() == a){
									graph.update(plots, 1000, true);
									System.out.println("Working world");
									mm.stop();
								}
							}
				        });
						mm.start();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(graph, BorderLayout.CENTER);
		
	}

}
