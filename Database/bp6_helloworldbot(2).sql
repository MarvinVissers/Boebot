-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Gegenereerd op: 19 jan 2021 om 11:24
-- Serverversie: 10.1.47-MariaDB-0ubuntu0.18.04.1
-- PHP-versie: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bp6_helloworldbot`
--
CREATE DATABASE IF NOT EXISTS `bp6_helloworldbot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bp6_helloworldbot`;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `api_acces`
--

CREATE TABLE `api_acces` (
  `selector` varchar(150) NOT NULL,
  `token` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `api_acces`
--

INSERT INTO `api_acces` (`selector`, `token`) VALUES
('33e47c5030537594', '$2y$10$lJktVUPx40HNaZV3Y38CAO1JIQnGOs07Do6.kcTVY8p3Ybr17W1Dm'),
('6fdc349677123384', '$2y$10$WiMqcp22RH6V2.ZTfXIVWeIV4lwVdflHCD1r0zBFXqOP9HNO44p.6'),
('ae026dd58cd57fd2', '$2y$10$Iu2UbUJE8VQ6A9lUDlsQ2ec58LyF/8ebk0/G9ChUsEeIgAv2cWS8u'),
('dfc701269623500e', '$2y$10$/ZBahc3zYiV4Xb64z.LKXOwPtlT7o0XFso7CxHn6l4sPlh3uAhJca'),
('fc377ae252db615e', '$2y$10$mxEWYpdajVD/87rqm4PnD.vjnYP78waKk9Cc7ugT3vB.GJ6Suq1Ea');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `boebot`
--

CREATE TABLE `boebot` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `status` enum('Offline','Online') NOT NULL DEFAULT 'Offline'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `boebot`
--

INSERT INTO `boebot` (`id`, `name`, `status`) VALUES
(1, 'ada-rbp-04', 'Offline'),
(2, 'ada-rbp-14', 'Offline');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `grid`
--

CREATE TABLE `grid` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `rows` int(2) NOT NULL,
  `columns` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `grid`
--

INSERT INTO `grid` (`id`, `name`, `rows`, `columns`) VALUES
(1, 'MainGrid', 10, 6);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `boebot` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `obstacle`
--

CREATE TABLE `obstacle` (
  `id` int(11) NOT NULL,
  `gridId` int(11) NOT NULL DEFAULT '1',
  `row1` int(2) NOT NULL,
  `column1` int(2) NOT NULL,
  `row2` int(2) NOT NULL,
  `column2` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `route`
--

CREATE TABLE `route` (
  `id` int(11) NOT NULL,
  `boebot` varchar(20) DEFAULT NULL,
  `row1` int(2) NOT NULL DEFAULT '0',
  `column1` int(2) NOT NULL DEFAULT '0',
  `row2` int(2) NOT NULL,
  `column2` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'Rick', '$2y$10$xRtqrBkC18KR.dCvPzaIkuBh23IAJxy9yvLm0pGibOdsMXcj/sOOe'),
(2, 'Marvin', '$2y$10$cTJmPXdffM624SYSdzc2beg4MybyFb2IVv/7q3Ibomao3nHY6zXoe');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `api_acces`
--
ALTER TABLE `api_acces`
  ADD PRIMARY KEY (`selector`);

--
-- Indexen voor tabel `boebot`
--
ALTER TABLE `boebot`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ipadres` (`name`);

--
-- Indexen voor tabel `grid`
--
ALTER TABLE `grid`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexen voor tabel `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `obstacle`
--
ALTER TABLE `obstacle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `obstacleGrid` (`gridId`);

--
-- Indexen voor tabel `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `boebot`
--
ALTER TABLE `boebot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT voor een tabel `grid`
--
ALTER TABLE `grid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT voor een tabel `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT voor een tabel `obstacle`
--
ALTER TABLE `obstacle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT voor een tabel `route`
--
ALTER TABLE `route`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT voor een tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `obstacle`
--
ALTER TABLE `obstacle`
  ADD CONSTRAINT `obstacleGrid` FOREIGN KEY (`gridId`) REFERENCES `grid` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
