import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import com.sun.javafx.embed.swing.Disposer;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;

public class DialogChange {
	
	public interface DataReceive{
		public void changeData(Alteracion alteracion);
		public Alteracion[] getalteraciones();
		
	}

	private static DataReceive main;
	protected JFrame frame;
	private JList list;
	private Alteracion[] alteraciones;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogChange window = new DialogChange(main);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DialogChange(DataReceive inter) {
		main = inter;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		frame.setBounds(100, 100, 478, 516);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(12, 37, 448, 172);
		 frame.getContentPane().add(scrollPane);
		
		 list = new JList();
		 list.addListSelectionListener(new ListSelectionListener() {
		 	public void valueChanged(ListSelectionEvent arg0) {
		 		if (list.getSelectedIndex()>=0) {
		 			textArea.setText(alteraciones[list.getSelectedIndex()].getCausa());
		 		}
		 	}
		 });
		 scrollPane.setViewportView(list);
		 
		 scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(12, 251, 448, 154);
		 frame.getContentPane().add(scrollPane_1);
		 
		 textArea = new JTextArea();
		 scrollPane_1.setViewportView(textArea);
		 textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
			textArea.setLineWrap(true);
			
			btnNewButton = new JButton("Eliminar Alteracion");
			btnNewButton.setBackground(Color.LIGHT_GRAY);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (list.getSelectedIndex()>=0) {
						frame.dispose();
						main.changeData(alteraciones[list.getSelectedIndex()]);
					}
				}
			});
			btnNewButton.setBounds(145, 443, 169, 25);
			frame.getContentPane().add(btnNewButton);
			
			lblNewLabel = new JLabel("Razon de alteracion:");
			lblNewLabel.setBounds(12, 222, 152, 16);
			frame.getContentPane().add(lblNewLabel);
			
			lblNewLabel_1 = new JLabel("Alteraciones:");
			lblNewLabel_1.setBounds(12, 13, 128, 16);
			frame.getContentPane().add(lblNewLabel_1);
		setInfo();
	}
	
	
	private void setInfo() {
		alteraciones = main.getalteraciones();
		String[] alt = new String[alteraciones.length];
		
		for (int i = 0;i<alteraciones.length;i++) {
			Relacion r = alteraciones[i].getRelacion();
			alt[i] = r.getCiudad1() +" -> "+r.getCiudad2()+" = "+r.getDistancia()+"km";
		}
		
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return alt.length;
			}
			public Object getElementAt(int index) {
				return alt[index];
			}
		});
	}
}
