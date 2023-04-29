package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "URUN")
public class Urun {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String urunAdi;
	private double urunFiyati;
	private long urunTuruID;
	public Urun() {}
	public Urun(long id) {
		this.id = id;
	}
	public Urun(String urunAdi, double urunFiyati, long urunTuruID) {
		this.setUrunAdi(urunAdi);
		this.setUrunFiyati(urunFiyati);
		this.setUrunTuruID(urunTuruID);
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public void setUrunAdi(String urunTuruAdi) {
		this.urunAdi = urunTuruAdi;
	}
	public double getUrunFiyati() {
		return urunFiyati;
	}
	public void setUrunFiyati(double urunFiyati) {
		this.urunFiyati = urunFiyati;
	}
	public long getUrunTuruID() {
		return urunTuruID;
	}
	public void setUrunTuruID(long urunTuruID) {
		this.urunTuruID = urunTuruID;
	}
	public long getId() {
		return id;
	}
}