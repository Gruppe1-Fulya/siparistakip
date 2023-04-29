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
	private long izinSeviyeID;
	private String personelSifreHashed;
	public Personel() {}
	public Personel(long id) {
		this.id = id;
	}
	public Personel(String personelAdi, long izinSeviyeID, String personelSifreHashed) {
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
	public long getIzinSeviyeID() {
		return izinSeviyeID;
	}
	public void setIzinSeviyeID(long izinSeviyeID) {
		this.izinSeviyeID = izinSeviyeID;
	}
	public String getPersonelSifreHashed() {
		return personelSifreHashed;
	}
	public void setPersonelSifreHashed(String personelSifreHashed) {
		this.personelSifreHashed = personelSifreHashed;
	}
	public long getId() {
		return id;
	}
}