Java Socket Tabanlı Web Sunucusu (Web Server)
Bu proje, Java programlama dili ve yerleşik Socket mimarisi kullanılarak herhangi bir 3. parti kütüphane veya harici bağımlılık (Spring, Express, Tomcat vb.) kullanılmadan geliştirilmiş hafif bir HTTP web sunucusudur.

📌 Proje Özellikleri
Port: 1989 üzerinden gelen HTTP isteklerini dinler.
Teknoloji: Sadece yerleşik Java standart kütüphaneleri (java.net.ServerSocket, java.net.Socket, java.io.*) kullanılmıştır.
İçerik: İstemci bağlandığında H1 boyutunda Ad-Soyad, H2 boyutunda Öğrenci Numarası ve CSS ile biçimlendirilmiş kısa bir biyografi içeren HTML çıktısı sunar.
Çalışma Mimarisi: HTTP/1.1 200 OK yanıt başlıklarını (Header) ve UTF-8 HTML gövdesini (Body) TCP soket seviyesinde iletir.

🛠️ Kurulum ve Çalıştırma
Projeyi Klonlayın:
git clone https://github.com/Salihabinici/java-web-server-odevi.git

Çalıştırma:
Projeyi IntelliJ IDEA veya herhangi bir Java IDE'sinde açın.
SimpleWebServer.java sınıfını çalıştırın (Run).

Test Etme:
Herhangi bir web tarayıcısını açın.
Adres çubuğuna şu adresi yazıp Enter'a basın: http://localhost:1989

🧠 Teknik Mimari ve Kavramlar
1. Socket, Port ve IP Nedir?
IP Adresi (Internet Protocol): Ağ üzerindeki bir cihazın mantıksal adresidir. Bu uygulamada lokal testler için 127.0.0.1 (localhost) adresi kullanılmıştır.
Port: Sunucu üzerinde çalışan spesifik servislerin iletişim kapısıdır. Bu projede istemci bağlantıları 1989 portu üzerinden kabul edilmektedir.
Socket (Soket): Ağ üzerindeki iki cihaz (istemci ve sunucu) arasındaki çift yönlü veri akışını sağlayan mantıksal uç noktadır.

2. Kullanılan Sınıflar ve Çalışma Mantığı
ServerSocket: Belirtilen portu (1989) dinleyen ve gelen bağlantı isteklerini kabul eden (accept()) ana sunucu yapısıdır.
Socket: Sunucu ile tarayıcı (istemci) arasındaki aktif iletişim kanalını temsil eder.
InputStream: İstemciden (tarayıcıdan) gelen HTTP istek başlıklarını okumak için kullanılmıştır.
OutputStream: Sunucunun tarayıcıya ekranda gösterilecek HTTP yanıtını ve HTML kodlarını bayt formatında yazmasını (write()) sağlar.

📐 SOLID Prensipleri Derlemesi
Bu projede kod okunabilirliği ve modülerlik adına aşağıdaki SOLID ilkelerine dikkat edilmiştir:
Single Responsibility Principle (SRP - Tek Sorumluluk Prensibi):
Sunucunun ağ dinleme ve port kabul etme döngüsü ile istemciye yanıt oluşturup gönderme süreçleri tek bir karmaşık yapıda toplamak yerine mantıksal adımlara ayrılmıştır.
Open/Closed Principle (OCP - Açık/Kapalı Prensibi):
Sunucunun temeli HTTP başlığı üretecek şekilde tasarlanmıştır. İlerleyen süreçte sunucu kodunun çekirdeğini değiştirmeden dinamik dosya okuma veya farklı HTML yanıtları dönme yeteneği eklenebilir.
Dependency Inversion Principle (DIP - Bağımlılıkların Tersine Çevrilmesi):
Somut bağımlılıklar yerine Java'nın soyut I/O akış sınıfları (InputStream, OutputStream) tercih edilerek veri transferi soyutlaştırılmıştır.

