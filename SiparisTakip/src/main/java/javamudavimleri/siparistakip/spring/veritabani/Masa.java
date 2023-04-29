package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MASA")
public class Masa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String masaAdi;
	private long siparisID;
	public Masa() {}
	public Masa(long id) {
		this.id = id;
	}
	public Masa(String masaAdi, long siparisID) {
		this.setMasaAdi(masaAdi);
		this.setSiparisID(siparisID);
	}
	public String getMasaAdi() {
		return masaAdi;
	}
	public void setMasaAdi(String masaAdi) {
		this.masaAdi = masaAdi;
	}
	public long getSiparisID() {
		return siparisID;
	}
	public void setSiparisID(long siparisID) {
		this.siparisID = siparisID;
	}
	public long getId() {
		return id;
	}
}