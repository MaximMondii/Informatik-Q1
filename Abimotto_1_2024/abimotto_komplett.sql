-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 30. Okt 2018 um 16:42
-- Server-Version: 10.1.16-MariaDB
-- PHP-Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `abimotto`
--
CREATE DATABASE IF NOT EXISTS `abimotto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `abimotto`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `motto`
--

CREATE TABLE `motto` (
  `MottoID` int(150) NOT NULL,
  `Motto` varchar(200) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `motto`
--

INSERT INTO `motto` (`MottoID`, `Motto`) VALUES
(1, 'Pommes mit mABI, bitte!'),
(2, 'Abiene - Abi verleiht Fl?gel!'),
(3, 'ABItours'),
(5, 'ABITAL - 13 Jahre Klassenkampf'),
(6, 'Abikalypse 2010 ... und nach uns die Sintflut'),
(4, 'ABI total');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `ID` int(150) NOT NULL,
  `Vorname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`ID`, `Vorname`, `Name`) VALUES
(1, 'Axel', 'M?ller'),
(2, 'Peter', 'Bond'),
(3, 'Karl', 'Kraus'),
(4, 'Anna', 'Neumann'),
(5, 'Vera', 'Heymann');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vorschlag`
--

CREATE TABLE `vorschlag` (
  `ID` int(200) NOT NULL,
  `MottoID` int(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `vorschlag`
--

INSERT INTO `vorschlag` (`ID`, `MottoID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 5),
(5, 6);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `motto`
--
ALTER TABLE `motto`
  ADD PRIMARY KEY (`MottoID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `vorschlag`
--
ALTER TABLE `vorschlag`
  ADD PRIMARY KEY (`ID`,`MottoID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `motto`
--
ALTER TABLE `motto`
  MODIFY `MottoID` int(150) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(150) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
