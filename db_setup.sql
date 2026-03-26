-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 26 mars 2026 à 07:49
-- Version du serveur : 10.11.14-MariaDB-0+deb12u2
-- Version de PHP : 8.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd_sdzee`
--

-- --------------------------------------------------------

--
-- Structure de la table `Dinosaure`
--

CREATE TABLE `Dinosaure` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `espece` varchar(100) NOT NULL,
  `ere` varchar(50) DEFAULT NULL,
  `regime_alimentaire` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `Dinosaure`
--

INSERT INTO `Dinosaure` (`id`, `nom`, `espece`, `ere`, `regime_alimentaire`) VALUES
(3, 'Petit-Pied', 'Apatosaurus', 'Jurassique', 'Herbivore'),
(5, 'Spino', 'Spinosaurus', 'Crétacé', 'Carnivore'),
(6, 'Stegui', 'Stegosaurus', 'Jurassique', 'Herbivore'),
(7, 'Bruto', 'Brachiosaurus', 'Jurassique', 'Herbivore'),
(8, 'Anky', 'Ankylosaurus', 'Crétacé', 'Herbivore'),
(9, 'Pachy', 'Pachycephalosaurus', 'Crétacé', 'Herbivore'),
(10, 'Iguano', 'Iguanodon', 'Crétacé', 'Herbivore'),
(11, 'Allo', 'Allosaurus', 'Jurassique', 'Carnivore'),
(12, 'Carno', 'Carnotaurus', 'Crétacé', 'Carnivore'),
(13, 'Dilofo', 'Dilophosaurus', 'Jurassique', 'Carnivore'),
(14, 'Para', 'Parasaurolophus', 'Crétacé', 'Herbivore'),
(15, 'Giga', 'Giganotosaurus', 'Crétacé', 'Carnivore'),
(16, 'Bary', 'Baryonyx', 'Crétacé', 'Carnivore'),
(17, 'Theri', 'Therizinosaurus', 'Crétacé', 'Herbivore'),
(18, 'Galli', 'Gallimimus', 'Crétacé', 'Omnivore'),
(19, 'Deino', 'Deinonychus', 'Crétacé', 'Carnivore'),
(20, 'Argento', 'Argentinosaurus', 'Crétacé', 'Herbivore');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `mot_de_passe` char(255) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `date_inscription` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `email`, `mot_de_passe`, `nom`, `date_inscription`) VALUES
(61, 'test@test.com', 'f4f263e439cf40925e6a412387a9472a6773c2580212a4fb50d224d3a817de17', 'test', '2026-03-23 09:28:53');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Dinosaure`
--
ALTER TABLE `Dinosaure`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Dinosaure`
--
ALTER TABLE `Dinosaure`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
