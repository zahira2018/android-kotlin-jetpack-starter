# android-kotlin-jetpack-starter
Repo ini untuk belajar mengenai android jetpack development

# Berikut ini teknologi yang digunakan :

# Langkah-langkah development
1. Setiap akan melakukan code, pikirkan apa yang harus dikerjakan misalnya bagian login, menu, dll
2. Jika sudah, buat branch baru dari branch terakhir yang paling update dengan kode features/md-00n (contoh : features/md-001)
3. Jika features sudah fix dan siap testing, checkout ke branch master_development, lalu merge branch features yang sudah fix tadi ke master_development
4. jika master_development sudah fix, checkout ke branch master lalu merge branch master_development ke branch master (atau lakukan pull request)
5. APK atau app bundle yang siap release atau delivery ke user adalah hasil build dari branch master

# Berikut ini penjelasan dari setiap kode ticket branch :

master = Digunakan untuk build release version
master_development = Digunakan untuk mengumpulkan semua branch features yang sudah fix (merge features to all master_development)

1. features/md-001
   Implementasi navigation (part of jetpack component)
2. features/md-002
   Implementasi Viewmodel, livedata, detailscreen dari list, hanya saja masih dalam bentuk data hardcode
3. features/md-003
   Setting retrofit, rxjava

