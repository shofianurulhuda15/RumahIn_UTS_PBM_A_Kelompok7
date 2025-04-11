package com.example.rumahin.util;

import com.example.rumahin.model.House;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    public static List<House> generateDummyHouses() {
        List<House> houses = new ArrayList<>();

        houses.add(new House(
                1,
                "Rumah Minimalis Darussalam",
                "Jl. Teuku Nyak Arief, Darussalam, Banda Aceh",
                "Rumah minimalis 2 lantai dengan fasilitas lengkap, dekat dengan Unsyiah dan UIN Ar-Raniry. Cocok untuk mahasiswa atau keluarga kecil.",
                15000000,
                3,
                2,
                90,
                "https://picsum.photos/id/1031/300/200",
                "Ahmad Rizky",
                "081234567890",
                "Rumah"
        ));

        houses.add(new House(
                2,
                "Kost Putri Syiah Kuala",
                "Jl. T. Chik Ditiro, Syiah Kuala, Banda Aceh",
                "Kost khusus putri dengan fasilitas AC, kamar mandi dalam, dapur bersama, dan wifi gratis. Lingkungan aman dan nyaman.",
                800000,
                1,
                1,
                16,
                "https://picsum.photos/id/1048/300/200",
                "Ibu Fatimah",
                "082345678901",
                "Kost"
        ));

        houses.add(new House(
                3,
                "Kontrakan Keluarga Ulee Kareng",
                "Jl. Seulawah, Ulee Kareng, Banda Aceh",
                "Rumah kontrakan untuk keluarga dengan 2 kamar tidur, ruang tamu, dapur, dan halaman parkir. Akses mudah ke pusat kota.",
                12000000,
                2,
                1,
                70,
                "https://picsum.photos/id/1062/300/200",
                "Pak Hasan",
                "083456789012",
                "Kontrakan"
        ));

        houses.add(new House(
                4,
                "Rumah Modern Lampineung",
                "Jl. Tgk. Daud Beureueh, Lampineung, Banda Aceh",
                "Rumah modern dengan desain kekinian, full furnished, lingkungan tenang dan strategis dekat dengan pusat kota.",
                20000000,
                4,
                3,
                120,
                "https://picsum.photos/id/116/300/200",
                "Zainal Abidin",
                "084567890123",
                "Rumah"
        ));

        houses.add(new House(
                5,
                "Kost Putra Prada",
                "Jl. Prada Utama, Syiah Kuala, Banda Aceh",
                "Kost khusus putra dengan fasilitas lengkap: AC, kamar mandi dalam, wifi, dan tempat parkir motor. Dekat kampus Unsyiah.",
                750000,
                1,
                1,
                12,
                "https://picsum.photos/id/155/300/200",
                "Pak Ridwan",
                "085678901234",
                "Kost"
        ));

        houses.add(new House(
                6,
                "Kontrakan Tahunan Kuta Alam",
                "Jl. Cut Nyak Dhien, Kuta Alam, Banda Aceh",
                "Kontrakan tahunan dengan 3 kamar tidur, ruang keluarga luas, dan halaman depan yang asri. Cocok untuk keluarga sedang.",
                18000000,
                3,
                2,
                85,
                "https://picsum.photos/id/163/300/200",
                "Ibu Mariam",
                "086789012345",
                "Kontrakan"
        ));

        houses.add(new House(
                7,
                "Apartment Peunayong",
                "Jl. T. Panglima Polem, Peunayong, Banda Aceh",
                "Apartment modern di kawasan sibuk Peunayong, akses mudah ke berbagai fasilitas publik dan pusat perbelanjaan.",
                22000000,
                2,
                1,
                60,
                "https://picsum.photos/id/164/300/200",
                "Henry Lie",
                "087890123456",
                "Apartment"
        ));

        houses.add(new House(
                8,
                "Kost Executive Batoh",
                "Jl. T. Iskandar, Batoh, Banda Aceh",
                "Kost exclusive dengan fasilitas bintang lima: AC, kamar mandi dalam, TV kabel, lemari, kasur spring bed, dan laundry service.",
                1200000,
                1,
                1,
                18,
                "https://picsum.photos/id/177/300/200",
                "Ibu Rahmah",
                "088901234567",
                "Kost"
        ));

        houses.add(new House(
                9,
                "Rumah Idaman Lampaseh",
                "Jl. Sultan Iskandar Muda, Lampaseh, Banda Aceh",
                "Rumah mewah dengan arsitektur modern, swimming pool pribadi, dan taman yang luas. Lokasi strategis dan aman.",
                30000000,
                5,
                4,
                200,
                "https://picsum.photos/id/188/300/200",
                "Dr. Rahman",
                "089012345678",
                "Rumah"
        ));

        houses.add(new House(
                10,
                "Kontrakan Ekonomis Lambaro",
                "Jl. Banda Aceh-Medan, Lambaro, Aceh Besar",
                "Kontrakan dengan harga terjangkau, cocok untuk keluarga kecil atau mahasiswa berbagi. Lokasi dekat dengan jalur transportasi umum.",
                8000000,
                2,
                1,
                50,
                "https://picsum.photos/id/237/300/200",
                "Pak Akmal",
                "081234567891",
                "Kontrakan"
        ));

        return houses;
    }

    public static List<String> generateCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Semua");
        categories.add("Rumah");
        categories.add("Kost");
        categories.add("Kontrakan");
        categories.add("Apartment");
        return categories;
    }
}