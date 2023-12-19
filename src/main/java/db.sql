-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 04-Dez-2023 às 16:12
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `farmacia`
--
CREATE DATABASE IF NOT EXISTS `farmacia` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `farmacia`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `ID_fornecedor` int(11) NOT NULL,
  `Nome_fornecedor` varchar(50) NOT NULL,
  `Endereco` varchar(100) NOT NULL,
  `Telefone` varchar(11) NOT NULL,
  `CNPJ` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`ID_fornecedor`, `Nome_fornecedor`, `Endereco`, `Telefone`, `CNPJ`) VALUES
(2, 'px', 'www', '125151', '15227'),
(3, 'salada INC', 'rua linacio freitas', '4061757', '1513'),
(4, 'dsa', 'das', 'dsa', 'dsa'),
(5, 'baixada', 'Rua onzes mish', '4654564', '156456456');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `ID_produto` int(11) NOT NULL,
  `Nome_produto` varchar(50) NOT NULL,
  `Preco_produto` double NOT NULL,
  `Receita_produto` tinyint(1) NOT NULL,
  `Descricao_produto` varchar(200) NOT NULL,
  `ID_fornecedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`ID_produto`, `Nome_produto`, `Preco_produto`, `Receita_produto`, `Descricao_produto`, `ID_fornecedor`) VALUES
(2, 'pasta de dente 2', 6, 0, 'pasta', 2),
(3, 'sabonete', 3, 0, 'efeffefef', 3);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`ID_fornecedor`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`ID_produto`),
  ADD KEY `ID_fornecedor_fk` (`ID_fornecedor`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `ID_fornecedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `ID_produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `ID_fornecedor_fk` FOREIGN KEY (`ID_fornecedor`) REFERENCES `fornecedor` (`ID_fornecedor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


