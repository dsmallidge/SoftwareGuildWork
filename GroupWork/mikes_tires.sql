INSERT INTO `authorities` (`username`, `authority`) VALUES
('mike', 'ROLE_ADMIN'),
('mike', 'ROLE_USER'),
('sarah', 'ROLE_USER');

INSERT INTO `BlogPost` (`BlogId`, `BlogTitle`, `Category`, `UserName`, `BlogEntry`, `Approved`, `StartDate`, `EndDate`) VALUES
(1, 'Happy Monday', 'Promotions', 'sarah', '<p>Mikes Tire''s is happy to announce that for this week only&nbsp;(5/2/16 to 5/7/16). The deal is&nbsp;if you buy 3 tires, the 4th tire is free. You just have to pay for mounting and balancing.</p>', 'Y', '2016-05-02', '2016-05-09'),
(2, 'A Day In The Life', 'General', 'mike', '<p>Well I got my laptop and we downloaded Netbeans today. It was suprising how heavy the laptop is. The wireless mouse is awesome. Tomorrow we actually get to do some Java stuff. Stay Tuned....Glenn Kasel</p>', 'Y', '2016-02-15', '2016-02-15'),
(3, 'Week 12', 'General', 'mike', '<p>Well I am now on my 12th week as an apprentice here at Mike''s Tires. I got a raise last week from $9.00 per hour to $9.50. My back still hurts from lifting really heavy tires last Friday to put them on the storage rack. Rumor has it that there is a huge tire sale coming soon.</p>', 'Y', '2016-05-02', '2016-05-30');

INSERT INTO `BlogTag` (`BlogId`, `TagId`) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO `StaticPage` (`PageId`, `PageName`, `PageContent`) VALUES
(2, 'Mudding Fun', '<p><img alt=''Mudding truck'' src=''http://i48.photobucket.com/albums/f233/SuperMann1987_8/F150onCleats.jpg'' style=''float:left; height:225px; width:300px'' /></p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p>Studies show that tires like these don&#39;t get very good mileage on the highway, but they can get you through your favorite mud hole.&nbsp;</p>  <p>&nbsp;</p> '),
(3, 'Flat or no Flat?', '<p>See, this tire is only flat on the bottom. It is good to go!!</p>  <p>&nbsp;</p>  <p><img alt='''' src=''http://www.mowersarea.com/wp-content/uploads/2015/01/riding-mower-tire-chains.jpg'' style=''height:167px; width:300px'' /></p> '),
(4, 'Under Pressure', '<p>Checking the air pressure in your tires regularly is the most important thing you can do for your tires. It helps you get better mileage and aids in giving your tires a longer life. And it just may keep you off of the side of the road with a flat tire. Don&#39;t forget to check the pressure in your spare as well as you surely want that to have air in it when and if, you get a flat.</p>  <p>&nbsp;</p>  <p><img alt='''' src=''http://3.bp.blogspot.com/-6tDmkbu6xBA/VU5bzfhzBoI/AAAAAAAALHQ/yoIZaBR4xCE/s1600/072.JPG'' style=''height:201px; width:300px'' /></p> ');

-- --------------------------------------------------------

INSERT INTO `Tag` (`TagId`, `Tag`) VALUES
(1, 'Sale'),
(2, 'Apprentice Day 1'),
(3, 'Apprentice');



