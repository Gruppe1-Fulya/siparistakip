package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonelDB extends JpaRepository<Personel, Long> {
	Personel findByPersonelSifreHashed(String personelSifreHashed);
}