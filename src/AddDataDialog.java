import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDataDialog {
	
	public interface addData {
		public void addData(Relacion relacion);
	}

	private JFrame frame;
	private JTextField txtC1;
	private JTextField txtC2;
	private JTextField txtD;
	private static addData data ;
	private TextAutoCompleter completer1,completer2;
	private static String[] lugares;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDataDialog window = new AddDataDialog(data,lugares);
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
	public AddDataDialog(addData data,String[] lugares) {
		this.data = data;
		this.lugares = lugares;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 506, 333);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Agregar Nueva Ruta");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(139, 0, 247, 78);
		frame.getContentPane().add(lblNewLabel);
		
		txtC1 = new JTextField();
		txtC1.setBounds(97, 89, 236, 30);
		frame.getContentPane().add(txtC1);
		txtC1.setColumns(10);
		completer1 = new TextAutoCompleter(txtC1);
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad1:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(22, 91, 74, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCiudad = new JLabel("Ciudad2:");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCiudad.setBounds(22, 136, 74, 24);
		frame.getContentPane().add(lblCiudad);
		
		txtC2 = new JTextField();
		txtC2.setColumns(10);
		txtC2.setBounds(97, 134, 236, 30);
		frame.getContentPane().add(txtC2);
		completer2 = new TextAutoCompleter(txtC2);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistancia.setBounds(22, 182, 80, 24);
		frame.getContentPane().add(lblDistancia);
		
		txtD = new JTextField();
		txtD.setColumns(10);
		txtD.setBounds(107, 180, 116, 30);
		frame.getContentPane().add(txtD);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtC1.getText().isEmpty() && !txtC2.getText().isEmpty() && !txtD.getText().isEmpty()) {
					if (!(txtC1.getText().trim().equals(txtC2.getText().trim()))) {
						try {
							long distancia = (long)Float.parseFloat(txtD.getText());
							data.addData(new Relacion(txtC1.getText(),txtC2.getText(),distancia));
							txtC1.setText(null);
							txtC2.setText(null);
							txtD.setText(null);
							frame.dispose();
							JOptionPane.showMessageDialog(null, "Se agrego relacion exitosamente");
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Distancia no valida");
						}
					}else {
						JOptionPane.showMessageDialog(null, "La ciudad1 y ciudad2 no pueden ser iguales");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Campos vacios");
				}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(178, 236, 125, 37);
		frame.getContentPane().add(btnNewButton);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		
		for (String s:lugares) {
			completer1.addItem(s);
			completer2.addItem(s);
		}
		
		
	}
}
