package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import domen.Poslanik;
import gui.models.PoslanikTableModel;

public class ParlamentGUI extends JFrame {

	private JPanel contentPane;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;
	private JScrollPane scrollPane_1;
	private JTextArea textAreaStatus;

	

	/**
	 * Create the frame.
	 */
	public ParlamentGUI() {
		setTitle("Parlament Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getEastPanel(), BorderLayout.EAST);
		contentPane.add(getSouthPanel(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setPreferredSize(new Dimension(120, 10));
			eastPanel.add(getBtnGetMembers());
			eastPanel.add(getBtnFillTable());
			eastPanel.add(getBtnUpdateMembers());
		}
		return eastPanel;
	}
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			southPanel.setPreferredSize(new Dimension(10, 70));
			southPanel.setLayout(new BorderLayout(0, 0));
			southPanel.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return southPanel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			PoslanikTableModel tbm = new PoslanikTableModel();
			table.setModel(tbm);
		}
		return table;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("GET members");
			btnGetMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIKontroler.vratiISerijalizujPoslanike();
					textAreaStatus.append("Poslanici uspesno preuzeti sa servisa.\n");
					
				}
			});
			btnGetMembers.setPreferredSize(new Dimension(115, 23));
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					List<Poslanik> poslanici = GUIKontroler.vratiListuPoslanika(); 
					PoslanikTableModel tbm = (PoslanikTableModel) table.getModel();
					tbm.osveziTabelu(poslanici);

					textAreaStatus.append("Tabela je popunjena sa podacima preuzetim sa servisa.");
					
				}
			});
			btnFillTable.setPreferredSize(new Dimension(115, 23));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PoslanikTableModel tbm = (PoslanikTableModel) table.getModel();
					List<Poslanik> poslanici = tbm.vratiListu();
					GUIKontroler.serijalizujPromenu(poslanici);
					textAreaStatus.append("Izmenjeni podaci su sacuvani.\n");
					
				}
			});
			btnUpdateMembers.setPreferredSize(new Dimension(115, 23));
		}
		return btnUpdateMembers;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTextAreaStatus());
		}
		return scrollPane_1;
	}
	private JTextArea getTextAreaStatus() {
		if (textAreaStatus == null) {
			textAreaStatus = new JTextArea();
		}
		return textAreaStatus;
	}
}
