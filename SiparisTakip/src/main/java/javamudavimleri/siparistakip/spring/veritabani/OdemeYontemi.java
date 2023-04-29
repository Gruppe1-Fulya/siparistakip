package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ODEMEYONTEMI")
public class OdemeYontemi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String odemeYontemiAdi;
	public OdemeYontemi() {}
	public OdemeYontemi(long id) {
		this.id = id;
	}
	public OdemeYontemi(String odemeYontemiAdi) {
		this.setOdemeYontemiAdi(odemeYontemiAdi);
	}
	public String getOdemeYontemiAdi() {
		return odemeYontemiAdi;
	}
	public void setOdemeYontemiAdi(String odemeYontemiAdi) {
		this.odemeYontemiAdi = odemeYontemiAdi;
	}
	public long getId() {
		return id;
	}
}