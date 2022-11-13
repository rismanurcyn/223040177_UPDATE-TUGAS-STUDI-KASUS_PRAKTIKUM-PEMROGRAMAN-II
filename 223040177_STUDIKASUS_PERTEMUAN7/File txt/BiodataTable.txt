package biodata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;





public class BiodataTable extends JFrame{
	
	public BiodataTable() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		initListener();
		
		JLabel lblNama = new JLabel("Nama: ");
		lblNama.setBounds(15,10,350,30);
		JTextField txtNama= new JTextField();
		txtNama.setBounds(15,40,350,30);
		
		JLabel labelRadio = new JLabel("Jenis Kelamin: "); 
		labelRadio.setBounds(15,80,350,10);
		JRadioButton radioButton1 = new JRadioButton("Perempuan", true);
		radioButton1.setBounds(15,90,350,30);
		JRadioButton radioButton2 = new JRadioButton("Laki-laki");
		radioButton2.setBounds(15,111,350,30);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton1);
		bg.add(radioButton2);
		
		JLabel lblNomorHP= new JLabel("Nomor Handphone: ");
		lblNomorHP.setBounds(15,132,350,30);
		JTextField txtNomor = new JTextField();
		txtNomor.setBounds(15,162,350,30);
		
		JLabel lblAlamat = new JLabel("Alamat: ");
		lblAlamat.setBounds(15, 190, 350, 30);
		JTextArea txtAlamat = new JTextArea();
		txtAlamat.setBounds(15,220,350,30);
		

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(15,265,80,40);
		JButton btnUbah = new JButton("Edit");
		btnUbah.setBounds(100,265,80,40);
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(185,265,80,40);
		
		javax.swing.JTable table = new JTable();
		JScrollPane scrollableTable = new JScrollPane(table);
		scrollableTable.setBounds(15, 320, 350, 200);
		
		MyTableModel tableModel = new MyTableModel();
		table.setModel(tableModel);
		
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String jenisKelamin = "";
				if(radioButton1.isSelected()) {
					jenisKelamin = radioButton1.getText();
				}
				if(radioButton2.isSelected()) {
					jenisKelamin = radioButton2.getText();
				}
				String nama  = txtNama.getText();
				String nomor = txtNomor.getText();
				String alamat = txtAlamat.getText();
				Member member = new Member();
				member.setNama(nama);
				member.setJenisKelamin(jenisKelamin);
				member.setNomor(nomor);
				member.setAlamat(alamat);
				
				if (txtNama.getText().trim().equals("") || jenisKelamin.trim().equals("") || txtNomor.getText().trim().equals("") || txtAlamat.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Data Wajib Diisi Lengkap!");
				} else {
					int opsi = JOptionPane.showConfirmDialog(null, 
							"Yakin Tambahkan Data?",
							"Tambahkan Data",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (opsi == JOptionPane.YES_NO_OPTION) {
						tableModel.add(member); 
						JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan!");
					} else {
						JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan!");
					}
					
				}	
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent a) {
			
				int value = table.getSelectedRow();
				txtNama.setText(tableModel.getValueAt(1, 0).toString());
				radioButton1.setText(tableModel.getValueAt(1, 0).toString());
				radioButton2.setText(tableModel.getValueAt(1, 0).toString());
				txtNomor.setText(tableModel.getValueAt(1, 0).toString());
				txtAlamat.setText(tableModel.getValueAt(1, 0).toString());
		
			}
		});
		
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = table.getSelectedRow();
				String jenisKelamin = "";
				if(radioButton1.isSelected()) {
					jenisKelamin = radioButton1.getText();
				}
				if(radioButton2.isSelected()) {
					jenisKelamin = radioButton2.getText();
				}
				
				if(table.getSelectedRow()>= 0) {
					int ubah = JOptionPane.showConfirmDialog(null, 
							"Yakin Ubah Data ini?",
							"Ubah Data",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (ubah == JOptionPane.YES_NO_OPTION) {
							tableModel.setValueAt(txtNama.getText(), table.getSelectedRow(), 0);
							tableModel.setValueAt(jenisKelamin, table.getSelectedRow(), 1);
							tableModel.setValueAt(txtNomor.getText(), table.getSelectedRow(), 2);
							tableModel.setValueAt(txtAlamat.getText(), table.getSelectedRow(), 3);
							JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
					} else {
							JOptionPane.showMessageDialog(null, "Data Gagal Diubah!");
					}
						
					}
				}
			});
		
	
		
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				int row = table.getSelectedRow();
					
				if(table.getSelectedRow() >= 0) {	
					int hapus = JOptionPane.showConfirmDialog(null, 
							"Yakin Hapus Data ini?",
							"Konfirmasi Hapus Data",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (hapus == JOptionPane.YES_NO_OPTION) {
						if(hapus == 0)
							tableModel.removeRow(table.getSelectedRow());
								JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
							} else {
								JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!");
								
							}
						}
					}
				});
		
		this.add(btnSimpan);
		this.add(btnUbah);
		this.add(btnHapus);
		this.add(txtNama);
		this.add(txtNomor);
		this.add(txtAlamat);
		this.add(lblNama);
		this.add(lblNomorHP);
		this.add(lblAlamat);
		this.add(labelRadio);
		this.add(radioButton1);
		this.add(radioButton2);
		this.add(scrollableTable);
		this.setSize(400, 500);
		this.setLayout(null);
			
	}
	
	private void initListener() {
		// TODO Auto-generated method stub
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				doExit();
			}
		});
	}
	
	public void doExit() {
		int confirm = JOptionPane.showConfirmDialog(this, 
				"Yakin Keluar dari Form ini?",
				"Pesan Konfirmasi",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if (confirm == JOptionPane.YES_NO_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BiodataTable h = new BiodataTable();
				h.setVisible(true);
			}
		});
	}
}
