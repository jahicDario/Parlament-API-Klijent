package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domen.Poslanik;

public class ParlamentAPIKomunikacija {

	public static final String url = "http://147.91.128.71:9090/parlament/api/members";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");	
	
	private String sendGET(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection konekcija = (HttpURLConnection) obj.openConnection();
		konekcija.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(konekcija.getInputStream()));
		boolean endOfStream = false;
		String rezultat = "";
		
		while (!endOfStream) {
			String s = reader.readLine();
			if (s != null) {
				rezultat += s;
			} else {
				endOfStream = true;
			}
		}
		reader.close();
		return rezultat;
	}
	
	public void serijalizujGET() throws IOException{
		String rezultat = sendGET(url);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")));		
		out.println(rezultat);
		out.close();
	}
	
	public List<Poslanik> vratiPoslanike() throws FileNotFoundException, ParseException{
		FileReader in = new FileReader("data/serviceMembers.json");
		Gson gson = new GsonBuilder().create();
		JsonArray poslanici = gson.fromJson(in, JsonArray.class);
		
		List<Poslanik> listaPoslanika = new LinkedList<>();
		for (int i = 0; i < poslanici.size(); i++) {			
			JsonObject objekat = (JsonObject) poslanici.get(i);
			
			Poslanik p = new Poslanik();
			p.setId(objekat.get("id").getAsInt());
			p.setIme(objekat.get("name").getAsString());
			p.setPrezime(objekat.get("lastName").getAsString());
			if (objekat.get("birthDate") != null) {
				Date d = sdf.parse(objekat.get("birthDate").getAsString());
				p.setDatumRodjenja(d);
			}			
			listaPoslanika.add(p);
		}
		return listaPoslanika;
	}
	
	private void serijalizuj(JsonArray clanovi) throws IOException{
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(clanovi);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/updatedMembers.json")));
		out.println(json);
		out.close();
	}
	
	public void serijalizujListu(List<Poslanik> poslanici) throws IOException{
		JsonArray clanovi = new JsonArray();
		for (Poslanik p : poslanici) {
			JsonObject objekat = new JsonObject();
			objekat.addProperty("id", p.getId());
			objekat.addProperty("name", p.getIme());
			objekat.addProperty("lastName", p.getPrezime());
			if (p.getDatumRodjenja() != null) {
				String datum = sdf.format(p.getDatumRodjenja());
				objekat.addProperty("birthDate", datum);
			}
			clanovi.add(objekat);
		}
		serijalizuj(clanovi);
	}

	
}
