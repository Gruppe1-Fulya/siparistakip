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
	private int izinSeviyesi;
	public IzinSeviye() {}
	public IzinSeviye(String izinSeviyeAdi, int izinSeviyesi) {
		this.setIzinSeviyeAdi(izinSeviyeAdi);
		this.setIzinSeviyesi(izinSeviyesi);
	}
	public String getIzinSeviyeAdi() {
		return izinSeviyeAdi;
	}
	public void setIzinSeviyeAdi(String izinSeviyeAdi) {
		this.izinSeviyeAdi = izinSeviyeAdi;
	}
	public int getIzinSeviyesi() {
		return izinSeviyesi;
	}
	public void setIzinSeviyesi(int izinSeviyesi) {
		this.izinSeviyesi = izinSeviyesi;
	}
	public Long getId() {
		return id;
	}
}