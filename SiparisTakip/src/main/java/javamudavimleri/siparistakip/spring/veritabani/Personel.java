package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONEL")
public class Personel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String personelAdi;
	private Long izinSeviyeID;
	private String personelSifreHashed;
	public Personel() {}
	public Personel(String personelAdi, Long izinSeviyeID, String personelSifreHashed) {
		this.setPersonelAdi(personelAdi);
		this.setIzinSeviyeID(izinSeviyeID);
		this.setPersonelSifreHashed(personelSifreHashed);
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public Long getIzinSeviyeID() {
		return izinSeviyeID;
	}
	public void setIzinSeviyeID(Long izinSeviyeID) {
		this.izinSeviyeID = izinSeviyeID;
	}
	public String getPersonelSifreHashed() {
		return personelSifreHashed;
	}
	public void setPersonelSifreHashed(String personelSifreHashed) {
		this.personelSifreHashed = personelSifreHashed;
	}
	public Long getId() {
		return id;
	}
}