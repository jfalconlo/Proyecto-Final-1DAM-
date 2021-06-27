-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2021 a las 22:08:47
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoprogramacion`
--
CREATE DATABASE IF NOT EXISTS `proyectoprogramacion` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `proyectoprogramacion`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id` int(5) NOT NULL,
  `matricula` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fechaRevision` date NOT NULL,
  `fechaNextRevision` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `mantenimiento`
--

INSERT INTO `mantenimiento` (`id`, `matricula`, `tipo`, `descripcion`, `fechaRevision`, `fechaNextRevision`) VALUES
(12, 'H1995AB', 'Pre-ITV', 'Todo Correcto. En la proxima revision Cambiar los neumaticos', '2021-05-25', '2021-05-25'),
(13, 'H1995AB', 'Neumaticos', 'Cambio de Neumaticos Realizado con Exito.l', '2021-05-29', '2021-05-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `DOCUMENTACION` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `NOMBRE` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `TIPO` enum('Empresa','Usuario') COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`DOCUMENTACION`, `NOMBRE`, `TIPO`) VALUES
('49081033E', 'Juan', 'Empresa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `propietario` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `marca` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `modelo` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `matricula` varchar(7) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`propietario`, `marca`, `modelo`, `matricula`) VALUES
('49081033E', 'Opel', 'Corsa', 'H1995AB');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `matricula` (`matricula`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`DOCUMENTACION`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`matricula`) USING BTREE,
  ADD KEY `propietario` (`propietario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `mantenimiento_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`PROPIETARIO`) REFERENCES `propietario` (`DOCUMENTACION`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vehiculo_ibfk_2` FOREIGN KEY (`propietario`) REFERENCES `propietario` (`DOCUMENTACION`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
