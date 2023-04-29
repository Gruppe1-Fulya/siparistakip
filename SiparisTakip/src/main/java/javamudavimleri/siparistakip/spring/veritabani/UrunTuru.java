package javamudavimleri.siparistakip.spring.veritabani;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "URUNTURU")
public class UrunTuru {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String urunTuruAdi;
	public UrunTuru() {}
	public UrunTuru(long id) {
		this.id = id;
	}
	public UrunTuru(String urunTuruAdi) {
		this.setUrunTuruAdi(urunTuruAdi);
	}
	public String getUrunTuruAdi() {
		return urunTuruAdi;
	}
	public void setUrunTuruAdi(String urunTuruAdi) {
		this.urunTuruAdi = urunTuruAdi;
	}
	public long getId() {
		return id;
	}
}