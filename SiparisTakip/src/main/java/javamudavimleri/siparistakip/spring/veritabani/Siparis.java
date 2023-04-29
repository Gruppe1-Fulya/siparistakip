package javamudavimleri.siparistakip.spring.veritabani;

import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SIPARIS")
public class Siparis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private long personelID;
	private String masaAdi;
	private long siparisZamani;
	private String urunler;
	private double toplamTutar;
	public Siparis() {}
	public Siparis(long id) {
		this.id = id;
	}
	public Siparis(long personelID, long siparisZamani, String masaAdi) {
		this.setPersonelID(personelID);
		this.siparisZamani = siparisZamani;
		this.setMasaAdi(masaAdi);
		urunler = new JSONObject().toString();
		toplamTutar = 0;
	}
	public long getPersonelID() {
		return personelID;
	}
	public void setPersonelID(long personelID) {
		this.personelID = personelID;
	}
	public String getMasaAdi() {
		return masaAdi;
	}
	public void setMasaAdi(String masaAdi) {
		this.masaAdi = masaAdi;
	}
	public String getUrunler() {
		return urunler;
	}
	public void siparisGuncelle(long urunID, double urunFiyati, int adet) {
		int eskiAdet = 0;
		JSONObject gecici = new JSONObject(urunler);
		try {
			eskiAdet = gecici.getInt(""+urunID);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(eskiAdet+adet>=0) {
			gecici.remove(""+urunID);
			toplamTutar -= eskiAdet*urunFiyati;
			if(eskiAdet+adet>0) {
				toplamTutar += (eskiAdet+adet)*urunFiyati;
				gecici.put(""+urunID, eskiAdet+adet);
				
			}
			urunler = gecici.toString();
		}
	}
	public long getSiparisZamani() {
		return siparisZamani;
	}
	public double getToplamTutar() {
		return toplamTutar;
	}
	public long getId() {
		return id;
	}
}