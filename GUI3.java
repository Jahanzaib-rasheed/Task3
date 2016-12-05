package task3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI3 extends JFrame {
	private JPanel contentPane;
	private JTextField txtEnterYourText;
	private JTextField txtSubject;
	private JTextField txtPradicate;
	private static String Sub,Predicate;
	private static int index,score;
	private static List<Sentence> l;
	private static Task3 data;

	public static void main(String[] args) {
		index=0;
		data=new Task3();
		l=data.ReadFromFile();
		NewThread();
	}	
	public static void NewThread(){
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI3 frame = new GUI3();
				frame.setBounds(30, 30, 600, 700);				
				frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI3() {
		Sub=Predicate=null;
		score=1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 30, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
			
		txtEnterYourText = new JTextField();
		txtEnterYourText.setText(giveString());
		txtEnterYourText.setBounds(35, 30, 510, 100);
		panel.add(txtEnterYourText);
		txtEnterYourText.setColumns(10);
			
		txtSubject = new JTextField();
		String a="";
		txtSubject.setText(a);			
		txtSubject.setBounds(35, 170, 510, 40);
		panel.add(txtSubject);
		txtSubject.setColumns(10);
		txtSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Sub=txtSubject.getText();
			}
		});
			
		txtPradicate = new JTextField();
		txtPradicate.setText("");
		txtPradicate.setBounds(35, 250, 510, 40);
		panel.add(txtPradicate);
		txtPradicate.setColumns(10);
		txtSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Predicate=txtPradicate.getText();
				
			}
		});
		
		JButton btnNextComment = new JButton("Next Comment");
		btnNextComment.setBounds(35, 550, 150, 50);
		panel.add(btnNextComment);
		btnNextComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Submit();
			}
		});
		
		
		
		JButton btnSkip = new JButton("Skip");
		btnSkip.setBounds(400, 550, 150, 50);
		panel.add(btnSkip);
		 btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Skip();
			}
		});
		
		JList list = new JList();
		list.setBounds(359, 184, -62, -131);
		panel.add(list);
	   	
	   	final JComboBox comboBox = new JComboBox();
	   	
	   	comboBox.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) {
   			String x=((String)comboBox.getSelectedItem());
   			score = Integer.parseInt(x);
	   		}
	   	});
	   	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Score:", "1", "2", "3", "4", "5"}));
	   	comboBox.setBounds(215, 330, 150, 40);
	   
	   	panel.add(comboBox);
	   	JMenuItem mntmScore = new JMenuItem("Enter Score");
	   	mntmScore.setBounds(35, 335, 129, 22);
	   	panel.add(mntmScore);
	   	
	   	JMenuItem mntmComment = new JMenuItem("Comment");
	   	mntmComment.setBounds(35, 11, 129, 22);
	   	panel.add(mntmComment);
	   	
	   	JMenuItem mntmSubject = new JMenuItem("Subject");
	   	mntmSubject.setBounds(35, 152, 129, 22);
	   	panel.add(mntmSubject);
	   	
	   	JMenuItem mntmPradicate = new JMenuItem("Pradicate");
	   	mntmPradicate.setBounds(35, 230, 129, 22);
	   	panel.add(mntmPradicate);	    
		}

	public String giveString(){
		try{
		return l.get(index).getSentence();
		}catch (Exception e){
			index=0;
			return l.get(index).getSentence();
		}
	}

	public void Skip(){
		l.get(index).SkipIncrement();
		index++;
		NewThread();
	}

	public void Submit(){
			l.get(index).setPridicate(Predicate);
			l.get(index).setSubject(Sub);
			l.get(index).setScore(score);
		data.WriteInFile(l.get(index));
		index++;
		NewThread();
	}
}
