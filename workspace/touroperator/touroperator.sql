-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Авг 24 2015 г., 17:09
-- Версия сервера: 5.5.45
-- Версия PHP: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `touroperator`
--

-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `client_id` bigint(20) NOT NULL,
  `client_login` varchar(20) NOT NULL,
  `client_hash` varchar(128) NOT NULL,
  `client_salt` varchar(18) NOT NULL,
  `client_email` text NOT NULL,
  `client_type` enum('ADMIN','USER') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `client`
--

INSERT INTO `client` (`client_id`, `client_login`, `client_hash`, `client_salt`, `client_email`, `client_type`) VALUES
(6, 'Westerte', '»сл|.S”TЁIПФsiz_ҐЦН•KоЄLsvе?Њ,ШЫюQa{[“h–ЅЊ–ћ“Trар6ЧpБѓЯ/%…/5Y@Ф†ўHЖ–Ш‡а(ГбҐ¦УтЁЈqЊмBCБB¤†БZяэMЏИбЂЖd‰Ж‚ЭFм$јbЅit=', 'bГХаЩ.эё,TЉ$q\r©', 'westerte@mail.ru', 'USER'),
(7, 'Westerte1', 'ь%yўМY>ДхЂqЄПN=ї\\SмЂюЦxS‡Малz‰sГБ‘џGњпыE­Ќє^:ЯaЌЧТю\ZауfЙ%6*5*l»хП„7ЇчE9Va·ђb ’Бg7b!Є…ђEVзdQ….Т\nУы|w\\ЬыСЁХЊк?в&њс“fїlTSС†н', 'AtкP*П;¶8T{z', 'westerte@mail.ru', 'USER'),
(8, 'Westerte2', 'ъЦ°ЋЫСЌPOЮе�зK0AЧI)ђHшwS\\ж–›Л¶ЃЪє«ЌЏмРzqаюЄH¤cґ}ЉJ\0€Uв·ІµОs‹/RwќгЅZ(дBЗBЎ2їZrс85›РT±›7им$E:!Z…F}ВмДEмR:щuИФ;Я>Ќ(Юи"”4', '“бИ_цA4ѕСћPЦ', 'westerte@mail.ru', 'USER'),
(9, 'Westete3', 'лЊьJТр3I4nЁСЕ0dтт}YгЫoАбdY—tЎcHG# ЏБBјdZ…(рйЋG«�ЅfЋЊО‚Тїш7V$‰ЃЬЩікa«YµЉ|UЫЕу№…cЃй|•ђqМhЧЭыkNFatь™ЏЎ‡	ь}— o,ЧBлэ~E2vВL', 'зG&®ь-CJj¦БмW57', 'westerte@mail.ru', 'USER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `client_login_index` (`client_login`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `client`
--
ALTER TABLE `client`
  MODIFY `client_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
