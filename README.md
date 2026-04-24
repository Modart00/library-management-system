# 📚 Library Management System (v2)

Java ile geliştirilmiş, veritabanı destekli kütüphane yönetim sistemi.

## 🚀 Features

### 📦 Ürün Yönetimi
- Kitap, DVD ve Dergi ekleme
- Ürünleri detaylı listeleme (type + bilgiler)
- Ürün durumunu görüntüleme (Müsait / Ödünçte)

### 👤 Üye Yönetimi
- Üye ekleme
- Üyeleri listeleme

### 🔄 Ödünç Sistemi
- Ürün ödünç verme
- Ürün iade alma
- Aynı ürünün birden fazla kişiye verilmesini engelleme
- Aktif ödünçleri görüntüleme

### 🖥️ Arayüz
- Console menu (App)
- Basit GUI (Swing)

---

## 🧠 Concepts Used

- Object-Oriented Programming (OOP)
  - Inheritance
  - Encapsulation
  - Polymorphism
- DAO Pattern
- JDBC (Database bağlantısı)
- SQL (MySQL)
- Collections (ArrayList)
- Layered Architecture (app → service → dao → model)

---

## 🏗️ Project Structure

app → Console uygulaması (menu sistemi)
ui → Swing GUI
model → Veri modelleri (Book, DVD, Magazine, Member, LibraryItem)
service → İş mantığı (Library)
dao → Veritabanı işlemleri
util → Database bağlantısı



---

## 🗄️ Database

Kullanılan tablolar:

- `items`
- `books`
- `magazine`
- `dvds`
- `members`
- `loans`

### 🔑 Mantık

- `items` → ana tablo (type içerir)
- Alt tablolar (`books`, `dvds`, `magazine`) → detay bilgileri
- `loans` → ödünç sistemi
  - `return_date NULL` → ürün ödünçte
  - `return_date dolu` → iade edilmiş

---

## ▶️ How to Run

1. MySQL’de database oluştur:
```sql
CREATE DATABASE library;
