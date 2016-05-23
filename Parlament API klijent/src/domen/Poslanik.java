package domen;

import java.util.Date;

public class Poslanik {
	private int id;
	private String ime;
	private String prezime;
	private Date datumRodjenja;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Override
	public String toString() {
		return "Poslanik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Poslanik) {
			Poslanik p = (Poslanik) obj;

			if (p.getId() == getId() && p.getIme().equals(getIme()) && p.getPrezime().equals(getPrezime())
					&& p.getDatumRodjenja().equals(getDatumRodjenja())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
