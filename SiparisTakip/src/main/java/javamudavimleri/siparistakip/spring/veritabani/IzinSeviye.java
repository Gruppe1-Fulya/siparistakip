package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "IZINSEVIYE")
public class IzinSeviye {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String izinSeviyeAdi;
	public IzinSeviye() {}
	public IzinSeviye(String izinSeviyeAdi) {
		this.setIzinSeviyeAdi(izinSeviyeAdi);
	}
	public String getIzinSeviyeAdi() {
		return izinSeviyeAdi;
	}
	public void setIzinSeviyeAdi(String izinSeviyeAdi) {
		this.izinSeviyeAdi = izinSeviyeAdi;
	}
	public Long getId() {
		return id;
	}
}