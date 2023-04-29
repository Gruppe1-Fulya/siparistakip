# SiparisTakip

<p>Uygulamayı çalıştırdıktan sonra ödeme yöntemleri, kategoriler, izin seviyeleri ve kullanıcılar gibi 
<br>önceden tanımlanmış değişkenleri veritabanına eklemek için alttaki Python scriptini kullanabilirsiniz. </p>

```shell
stdbinit.py
```

```python
import requests
import hashlib

masterKey = 'JavaMudavimleri'
baseURL = 'http://localhost:8080/'

odemeYontemleri = ['Nakit', 'Kredi Kartı', 'Yemek Kartı', 'Hediye Çeki', 'Puan', 'PayPal', 'Mobil Ödeme', 'Kripto Para']
for odemeYontemi in odemeYontemleri:
    print(requests.post(baseURL+'odemeYontemiEkle?masterKey='+masterKey+'&odemeYontemiAdi='+odemeYontemi).text)

kategoriler = ['Ana Yemek', 'Ara Sıcak', 'İçecek', 'Meze', 'Salata', 'Sıcak Başlangıç', 'Tatlı']
for kategori in kategoriler:
    print(requests.post(baseURL+'urunTuruEkle?masterKey='+masterKey+'&urunTuruAdi='+kategori).text)

izinSeviyeleri = [('Garson', '1'), ('Kasiyer', '2')]
for izinSeviye in izinSeviyeleri:
    print(requests.post(baseURL+'izinSeviyeEkle?masterKey='+masterKey+'&izinSeviyeAdi='+izinSeviye[0]+'&izinSeviyesi='+izinSeviye[1]).text)

kullanicilar = [('kerem', '321', 'Kasiyer'), ('yasin', '123', 'Garson')]
for kullanici in kullanicilar:
    sifreHashed = hashlib.sha256(kullanici[1].encode('utf-8')).hexdigest()
    print(requests.post(baseURL+'personelEkle?masterKey='+masterKey+'&personelAdi='+kullanici[0]+'&sifreHashed='+sifreHashed+'&izinSeviyeAdi='+kullanici[2]).text)
```
