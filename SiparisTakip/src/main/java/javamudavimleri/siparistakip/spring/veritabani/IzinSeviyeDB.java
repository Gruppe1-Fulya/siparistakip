package javamudavimleri.siparistakip.spring.veritabani;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IzinSeviyeDB extends JpaRepository<IzinSeviye, Long> {
	IzinSeviye findByIzinSeviyeAdi(String izinSeviyeAdi);
}