-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 14 Des 2016 pada 13.32
-- Versi Server: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crud_ar`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `kd` varchar(20) NOT NULL,
  `nm` varchar(100) NOT NULL,
  `gaji` int(11) NOT NULL,
  `deskripsi` text NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`kd`, `nm`, `gaji`, `deskripsi`, `status`) VALUES
('789', 'Listiani', 200, 'abc', 'active'),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('002', 'aa', 200, 'aa', ''),
('003', 'Hanna', 500000, 'Kerja Bagus', 'active'),
('004', 'Sancai', 30000, 'Kasir', 'active'),
('009', 'aaa', 800, 'aa', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `sandi` varchar(20) NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `nama`, `sandi`, `email`) VALUES
(1, 'listiani', 'listiani', 'listiani@listiani'),
(0, 'admin', 'admin', 'admin'),
(2, 'admin', 'admin', 'admin'),
(3, 'hrd', 'hrd', 'hrd');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
