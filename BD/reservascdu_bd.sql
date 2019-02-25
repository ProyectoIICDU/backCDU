-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-02-2019 a las 14:21:45
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
-- Estructura de tabla para la tabla `deporte`
--

CREATE TABLE `deporte` (
  `idDeporte` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `deporte`
--

INSERT INTO `deporte` (`idDeporte`, `nombre`) VALUES
(10, 'Futbol'),
(11, 'Futbol Sala');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio_deporte`
--

CREATE TABLE `espacio_deporte` (
  `idEspacio` int(10) UNSIGNED NOT NULL,
  `idDeporte` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `espacio_deporte`
--

INSERT INTO `espacio_deporte` (`idEspacio`, `idDeporte`) VALUES
(100, 10),
(101, 11),
(102, 11),
(103, 11),
(104, 10),
(104, 11);

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
(100, 'Cancha de futbol', 'CDU', 'medidas reglamentarias', 'En Servicio', '', ''),
(101, 'Cancha 2', 'CDU', 'cancha con medida x y', 'En Servicio', '', ''),
(102, 'Cancha 3', 'Diamante', 'Cancha de 3 * 4', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg'),
(103, 'Sala Tenis de mesa', 'CDU', 'Están limpiando la sala', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg'),
(104, 'jkfjsldkjf', 'CDU', 'hkjdlfdjslfkdj', 'En Servicio', 0x6461736473616f7064696f736170, 'image/jpeg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `festivos`
--

CREATE TABLE `festivos` (
  `idFestivo` int(10) UNSIGNED NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_espacio`
--

CREATE TABLE `reserva_espacio` (
  `idReserva` int(10) UNSIGNED NOT NULL,
  `idEspacio` int(10) UNSIGNED NOT NULL,
  `idUsuario` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fechaini` datetime NOT NULL,
  `fechafin` datetime NOT NULL,
  `tipo` enum('Academico','Normal','Evento','Seleccionados') COLLATE utf8_unicode_ci NOT NULL,
  `esfija` tinyint(1) NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registradopor` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `modificadopor` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fechahorareg` datetime NOT NULL,
  `fechahoramod` datetime NOT NULL,
  `programa` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `reserva_espacio`
--

INSERT INTO `reserva_espacio` (`idReserva`, `idEspacio`, `idUsuario`, `nombre`, `fechaini`, `fechafin`, `tipo`, `esfija`, `descripcion`, `registradopor`, `modificadopor`, `fechahorareg`, `fechahoramod`, `programa`) VALUES
(557, 103, 2, 'Tania', '2019-02-21 21:30:14', '2019-02-21 22:30:14', 'Normal', 1, 'nA', 'Administrador', 'Administrador', '2019-02-21 21:13:45', '2019-02-21 21:13:45', 'Fisioterapia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(10) UNSIGNED NOT NULL,
  `codigo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nombres` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `estado` enum('Registrado','Activo','Inactivo') COLLATE utf8_unicode_ci NOT NULL,
  `fechaactivacion` date NOT NULL DEFAULT '0000-00-00',
  `activadopor` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `rol` enum('Administrador','Invitado','Secretario') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `codigo`, `login`, `password`, `nombres`, `apellidos`, `estado`, `fechaactivacion`, `activadopor`, `rol`) VALUES
(1, '46102037', 'acerquera', '1234', 'Ariel', 'Cerquera', 'Registrado', '2018-06-01', '', 'Invitado'),
(2, '45', 'admin', 'admin', 'Gustavo', 'Ordoñez', 'Activo', '2018-06-01', '', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `deporte`
--
ALTER TABLE `deporte`
  ADD PRIMARY KEY (`idDeporte`);

--
-- Indices de la tabla `espacio_deporte`
--
ALTER TABLE `espacio_deporte`
  ADD PRIMARY KEY (`idEspacio`,`idDeporte`),
  ADD KEY `idDeporte` (`idDeporte`);

--
-- Indices de la tabla `espacio_deportivo`
--
ALTER TABLE `espacio_deportivo`
  ADD PRIMARY KEY (`idEspacio`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `festivos`
--
ALTER TABLE `festivos`
  ADD PRIMARY KEY (`idFestivo`);

--
-- Indices de la tabla `reserva_espacio`
--
ALTER TABLE `reserva_espacio`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `idEspacio` (`idEspacio`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `deporte`
--
ALTER TABLE `deporte`
  MODIFY `idDeporte` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `espacio_deportivo`
--
ALTER TABLE `espacio_deportivo`
  MODIFY `idEspacio` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- AUTO_INCREMENT de la tabla `festivos`
--
ALTER TABLE `festivos`
  MODIFY `idFestivo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `reserva_espacio`
--
ALTER TABLE `reserva_espacio`
  MODIFY `idReserva` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=558;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `espacio_deporte`
--
ALTER TABLE `espacio_deporte`
  ADD CONSTRAINT `espacio_deporte_ibfk_1` FOREIGN KEY (`idEspacio`) REFERENCES `espacio_deportivo` (`idEspacio`),
  ADD CONSTRAINT `espacio_deporte_ibfk_2` FOREIGN KEY (`idDeporte`) REFERENCES `deporte` (`idDeporte`);

--
-- Filtros para la tabla `reserva_espacio`
--
ALTER TABLE `reserva_espacio`
  ADD CONSTRAINT `reserva_espacio_ibfk_1` FOREIGN KEY (`idEspacio`) REFERENCES `espacio_deportivo` (`idEspacio`),
  ADD CONSTRAINT `reserva_espacio_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
