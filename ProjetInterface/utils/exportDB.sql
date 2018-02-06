-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 23, 2018 at 12:33 PM
-- Server version: 10.1.26-MariaDB-0+deb9u1
-- PHP Version: 7.0.27-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `produits`
--

-- --------------------------------------------------------

--
-- Table structure for table `Categorie`
--

CREATE TABLE `Categorie` (
  `codeCategorie` int(11) NOT NULL,
  `nomCategorie` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Categorie`
--

INSERT INTO `Categorie` (`codeCategorie`, `nomCategorie`) VALUES
(1, 'Alcools'),
(2, 'Gâteaux salés'),
(3, 'Laitages'),
(4, 'Oeufs');

-- --------------------------------------------------------

--
-- Table structure for table `Produit`
--

CREATE TABLE `Produit` (
  `codeProduit` int(11) NOT NULL,
  `nomProduit` varchar(50) DEFAULT NULL,
  `prixProduit` float DEFAULT NULL,
  `codeCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Produit`
--

INSERT INTO `Produit` (`codeProduit`, `nomProduit`, `prixProduit`, `codeCategorie`) VALUES
(1, 'Vodka Byson', 14.5, 1),
(2, 'Eku 28', 5, 1),
(3, 'Mini-Pizzas', 0.9, 2),
(4, 'Noix de cajou', 2.1, 2);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_produit`
-- (See below for the actual view)
--
CREATE TABLE `v_produit` (
`codeCategorie` int(11)
,`nomCategorie` varchar(50)
,`codeProduit` int(11)
,`nomProduit` varchar(50)
,`prixProduit` float
);

-- --------------------------------------------------------

--
-- Structure for view `v_produit`
--
DROP TABLE IF EXISTS `v_produit`;

CREATE ALGORITHM=UNDEFINED DEFINER=`b2a`@`localhost` SQL SECURITY DEFINER VIEW `v_produit`  AS  select `Categorie`.`codeCategorie` AS `codeCategorie`,`Categorie`.`nomCategorie` AS `nomCategorie`,`Produit`.`codeProduit` AS `codeProduit`,`Produit`.`nomProduit` AS `nomProduit`,`Produit`.`prixProduit` AS `prixProduit` from (`Categorie` join `Produit` on((`Categorie`.`codeCategorie` = `Produit`.`codeCategorie`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Categorie`
--
ALTER TABLE `Categorie`
  ADD PRIMARY KEY (`codeCategorie`);

--
-- Indexes for table `Produit`
--
ALTER TABLE `Produit`
  ADD PRIMARY KEY (`codeProduit`),
  ADD KEY `FK_Produit_codeCategorie` (`codeCategorie`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Categorie`
--
ALTER TABLE `Categorie`
  MODIFY `codeCategorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Produit`
--
ALTER TABLE `Produit`
  MODIFY `codeProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Produit`
--
ALTER TABLE `Produit`
  ADD CONSTRAINT `FK_Produit_codeCategorie` FOREIGN KEY (`codeCategorie`) REFERENCES `Categorie` (`codeCategorie`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
