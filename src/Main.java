import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JScrollPane;

public class Main implements DialogChange.DataReceive,AddDataDialog.addData,AlterarRutaDialog.Interface{

	private JFrame frame;
	private Controller control;
	private JComboBox cbCiudad1,cbCiudad2;
	private JPanel panelShow;
	private JTextArea txtrAsdasd;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		control = new Controller();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		frame.setResizable(false);
		
		JButton btnTXT = new JButton("Cargar Archivo .txt");
		btnTXT.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				control.grafoTXT();
				control.crearGrafo();
				
				cbCiudad1.setModel(new DefaultComboBoxModel(control.getCiudades()));
				cbCiudad2.setModel(new DefaultComboBoxModel(control.getCiudades()));
				panelShow.setVisible(true);
			}
		});
		btnTXT.setBackground(Color.LIGHT_GRAY);
		btnTXT.setBounds(12, 13, 156, 34);
		frame.getContentPane().add(btnTXT);
		
		panelShow = new JPanel();
		panelShow.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelShow.setBackground(Color.WHITE);
		panelShow.setBounds(12, 60, 938, 347);
		frame.getContentPane().add(panelShow);
		panelShow.setLayout(null);
	
		
		cbCiudad1 = new JComboBox();
		cbCiudad1.setBounds(51, 13, 184, 22);
		panelShow.add(cbCiudad1);
		
		cbCiudad2 = new JComboBox();
		cbCiudad2.setBounds(264, 13, 184, 22);
		panelShow.add(cbCiudad2);
		
		JLabel lblNewLabel = new JLabel("Ir de");
		lblNewLabel.setBounds(12, 16, 38, 16);
		panelShow.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("a");
		lblNewLabel_1.setBounds(242, 16, 21, 16);
		panelShow.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buscar Ruta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] resultado = control.getRuta(cbCiudad1.getSelectedIndex(), cbCiudad2.getSelectedIndex());
				if (resultado.length == 1) {
					txtrAsdasd.setText("Ruta no disponible");
				}else {
					String distancia = control.getDistance(resultado);
					if (distancia.equals("0km")) {
						txtrAsdasd.setText("Ruta no disponible");
					}else {
						String centro = control.getCenter();
						String texto = "";
						texto += "La ruta para llegar de "+cbCiudad1.getSelectedItem()+" a "+cbCiudad2.getSelectedItem()+" es:\n\nRuta: ";
						
						for (int i = 0;i<resultado.length;i++) {
							if (i < resultado.length-1) {
								texto += resultado[i]+" -> ";
							}else {
								texto += resultado[i];
							}
						}
						
						texto += "\n\nKilometros estimados para esta ruta: "+distancia;
						texto +="\n\nEl centro del grafo es: '"+centro+"', por lo que se recomienda que las oficinas \nse ubiquen en ese lugar.";
						txtrAsdasd.setText(texto);
					}
				}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(477, 12, 116, 25);
		panelShow.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 49, 914, 243);
		panelShow.add(scrollPane);
		
		txtrAsdasd = new JTextArea();
		scrollPane.setViewportView(txtrAsdasd);
		txtrAsdasd.setEditable(false);
		txtrAsdasd.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrAsdasd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		btnNewButton_1 = new JButton("Alteraciones");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogChange dialog = new DialogChange(Main.this);
				dialog.main(null);
			}
		});
		btnNewButton_1.setBounds(22, 309, 130, 25);
		panelShow.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Agregar ruta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDataDialog dialog = new AddDataDialog(Main.this,control.getCiudades());
				dialog.main(null);
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(166, 309, 130, 25);
		panelShow.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Alterar Ruta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarRutaDialog dialog = new AlterarRutaDialog(Main.this);
				dialog.main(null);
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(309, 309, 130, 25);
		panelShow.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Matriz de Adyacencia");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatrixDialog dialog = new MatrixDialog(control.getMatrix());
				dialog.main(null);
			}
		});
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setBounds(452, 309, 194, 25);
		panelShow.add(btnNewButton_4);
		frame.setBounds(100, 100, 967, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	
	private void updateUI() {
		control.crearGrafo();
		cbCiudad1.setModel(new DefaultComboBoxModel(control.getCiudades()));
		cbCiudad2.setModel(new DefaultComboBoxModel(control.getCiudades()));
	}

	// DialogChange
	@Override
	public void changeData(Alteracion alteracion){
		control.changeAlteracion(alteracion);
		updateUI();
	}
	
	@Override
	public Alteracion[] getalteraciones() {
		return control.getAlteraciones();
	}


	// AddDataDialog
	@Override
	public void addData(Relacion relacion) {
		control.addRelation(relacion);
		updateUI();
	}

	// AlterarRutaDialog
	@Override
	public String[] getNombres() {
		return control.getCiudades();
	}

	@Override
	public Relacion[] getRelaciones(String c1, String c2) {
		return control.getRelacion(c1, c2);
	}

	@Override
	public void setAlteracion(Alteracion alteracion) {
		control.setAlteracion(alteracion);
		updateUI();
	}

	

}
