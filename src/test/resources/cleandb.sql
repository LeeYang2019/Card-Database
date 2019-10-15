delete from yugioh_card;
Alter table yugioh_card AUTO_INCREMENT = 1;
INSERT INTO `yugioh_card` VALUES (1,'Dark Magician','Monster','Secret','LOB-EN02', '25'),(2,'Dark Magician Girl','Monster','Secret','MFC-EN00', '50');

delete from collector;
Alter table collector AUTO_INCREMENT = 1;
INSERT INTO `collector` VALUES (1, 'leeyang2019', 'greenbayPackers');