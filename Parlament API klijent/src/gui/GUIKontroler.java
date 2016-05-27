package gui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import domen.Poslanik;
import util.ParlamentAPIKomunikacija;

public class GUIKontroler {

	private static ParlamentGUI glavniProzor;
	private static ParlamentAPIKomunikacija api;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					api = new ParlamentAPIKomunikacija();
					glavniProzor = new ParlamentGUI();
					glavniProzor.setVisible(true);
					glavniProzor.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void vratiISerijalizujPoslanike() {
		try {
			api.serijalizujGET();
		} catch (IOException e) {
			System.out.println("Greska: " + e.getMessage());
		}

	}

	public static List<Poslanik> vratiListuPoslanika() {
		List<Poslanik> p = null;
		try {
			p = api.vratiPoslanike();
		} catch (FileNotFoundException | ParseException e) {
			System.out.println("Greska: " + e.getMessage());
		}
		if(p == null){
			throw new RuntimeException("Nije uspesno vracena lista poslanika.");
		}
		return p;
	}

	public static void serijalizujPromenu(List<Poslanik> poslanici) {
		try {
			api.serijalizujListu(poslanici);
		} catch (IOException e) {
			System.out.println("Greska:" + e.getMessage());
		}
	}

}
