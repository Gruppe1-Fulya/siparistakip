package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ODEME")
public class Odeme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private long siparisID;
	private long odemeYontemiID;
	private long personelID;
	private double bahsis;
	private double toplam;
	private String odemeNotu;
	private long odemeZamani;
	public Odeme() {}
	public Odeme(long id) {
		this.id = id;
	}
	public Odeme(long siparisID, long odemeYontemiID, long personelID, double bahsis, double araToplam, String odemeNotu, long odemeZamani) {
		this.siparisID = siparisID;
		this.odemeYontemiID = odemeYontemiID;
		this.personelID = personelID;
		if(bahsis > 0) {
			this.bahsis = bahsis;
		}
		else {
			this.bahsis = 0;
		}
		this.toplam = araToplam + this.bahsis;
		this.odemeNotu = odemeNotu;
		this.odemeZamani = odemeZamani;
	}
	public long getSiparisID() {
		return siparisID;
	}
	public long getOdemeYontemiID() {
		return odemeYontemiID;
	}
	public long getPersonelID() {
		return personelID;
	}
	public double getBahsis() {
		return bahsis;
	}
	public double getToplam() {
		return toplam;
	}
	public String getOdemeNotu() {
		return odemeNotu;
	}
	public long getOdemeZamani() {
		return odemeZamani;
	}
	public long getId() {
		return id;
	}
}