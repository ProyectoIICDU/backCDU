-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-02-2019 a las 14:31:17
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reservascdu_bd`
--
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio_deportivo`
--

CREATE TABLE `espacio_deportivo` (
  `idEspacio` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ubicacion` enum('CDU','Diamante') COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `estado` enum('En Servicio','Mantenimiento') COLLATE utf8_unicode_ci NOT NULL,
  `foto` mediumblob NOT NULL,
  `tipofoto` enum('image/jpeg','image/jpg','image/png') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `espacio_deportivo`
--

INSERT INTO `espacio_deportivo` (`idEspacio`, `nombre`, `ubicacion`, `descripcion`, `estado`, `foto`, `tipofoto`) VALUES
(103, 'Sala Tenis de mesa', 'CDU', 'Están limpiando la sala', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg'),
(108, 'Cancha de voleibol', 'CDU', 'Ninguna', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg'),
(154, 'Cancha de futbol', 'Diamante', 'NA', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `espacio_deportivo`
--
ALTER TABLE `espacio_deportivo`
  ADD PRIMARY KEY (`idEspacio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `espacio_deportivo`
--
ALTER TABLE `espacio_deportivo`
  MODIFY `idEspacio` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
