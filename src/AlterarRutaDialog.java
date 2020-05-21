import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AlterarRutaDialog {
	
	public interface Interface{
		public String[] getNombres();
		public Relacion[] getRelaciones(String c1,String c2);
		public void setAlteracion(Alteracion alteracion);
	}

	private JFrame frame;
	private static Interface data;
	private JComboBox cbC1,cbC2;
	private JList list;
	private Relacion[] r;
	private int elegido = 0;
	private JLabel lblNewLabel;
	private JTextField txtRuta;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the applicat
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarRutaDialog window = new AlterarRutaDialog(data);
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
	public AlterarRutaDialog(Interface data) {
		this.data = data;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 513, 515);
		
		JLabel lblRutaDe = new JLabel("Ruta de");
		lblRutaDe.setBounds(12, 30, 57, 16);
		frame.getContentPane().add(lblRutaDe);
		////////////cbCiudad1.setModel(new DefaultComboBoxModel(control.getCiudades()));
		cbC1 = new JComboBox();
		cbC1.setBounds(68, 30, 184, 22);
		cbC1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateList(cbC1.getSelectedItem().toString(), cbC2.getSelectedItem().toString());
			}
		});
		frame.getContentPane().add(cbC1);
		cbC1.setModel(new DefaultComboBoxModel(data.getNombres()));
		
		JLabel label_1 = new JLabel("a");
		label_1.setBounds(259, 33, 21, 16);
		frame.getContentPane().add(label_1);
		
		cbC2 = new JComboBox();
		cbC2.setBounds(281, 30, 184, 22);
		frame.getContentPane().add(cbC2);
		cbC2.setModel(new DefaultComboBoxModel(data.getNombres()));
		cbC2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateList(cbC1.getSelectedItem().toString(), cbC2.getSelectedItem().toString());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 75, 485, 169);
		frame.getContentPane().add(scrollPane);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.getSelectedIndex()>=0) {
					elegido = list.getSelectedIndex();
					txtRuta.setText(list.getSelectedValue().toString());
				}
			}
		});
		
		scrollPane.setViewportView(list);
		
		lblNewLabel = new JLabel("Ruta:");
		lblNewLabel.setBounds(13, 259, 41, 16);
		frame.getContentPane().add(lblNewLabel);
		
		txtRuta = new JTextField();
		txtRuta.setBounds(53, 257, 444, 22);
		frame.getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		txtRuta.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Razon de Alteracion:");
		lblNewLabel_1.setBounds(12, 292, 133, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 308, 483, 112);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textArea.getText().toString().isEmpty() && list.isSelectedIndex(elegido)) {
					frame.dispose();	
					data.setAlteracion(new Alteracion(textArea.getText(),r[elegido]));
					
				}else {
					JOptionPane.showMessageDialog(null, "Razon de alteracion vacia");
				}
			}
		});
		btnNewButton.setBounds(189, 442, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		frame.setLocationRelativeTo(null);
		
	}
	
	
	private void updateList(String c1,String c2) {
		 r = data.getRelaciones(c1, c2);
		String[] rr = new String [r.length];
		
		for (int i =0;i<r.length;i++) {
			rr[i] = r[i].getCiudad1() +" -> "+r[i].getCiudad2() + " = "+r[i].getDistancia()+"km";
		}
		
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return rr.length;
			}
			public Object getElementAt(int index) {
				return rr[index];
			}
		});
	}
}
