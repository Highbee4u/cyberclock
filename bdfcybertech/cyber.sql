-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 15, 2010 at 12:31 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cyber`
--

-- --------------------------------------------------------

--
-- Table structure for table `clerk`
--

CREATE TABLE `clerk` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `clerk`
--


-- --------------------------------------------------------

--
-- Table structure for table `email`
--

CREATE TABLE `email` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(225) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `email`
--


-- --------------------------------------------------------

--
-- Table structure for table `ticketting`
--

CREATE TABLE `ticketting` (
  `id` int(11) NOT NULL auto_increment,
  `time` varchar(50) NOT NULL,
  `qty` varchar(11) NOT NULL,
  `date` varchar(225) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ticketting`
--


-- --------------------------------------------------------

--
-- Table structure for table `unused_tickets`
--

CREATE TABLE `unused_tickets` (
  `id` int(11) NOT NULL auto_increment,
  `pin` varchar(225) NOT NULL,
  `date_created` varchar(225) NOT NULL,
  `date_expire` varchar(225) NOT NULL,
  `mins_rem` int(4) NOT NULL,
  `active` int(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `unused_tickets`
--


-- --------------------------------------------------------

--
-- Table structure for table `using_tickets`
--

CREATE TABLE `using_tickets` (
  `id` int(11) NOT NULL auto_increment,
  `user` varchar(225) NOT NULL,
  `mins_rem` int(4) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `using_tickets`
--

