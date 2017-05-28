
INSERT INTO "address" VALUES (1,'Minsk','Novaya',1,1),(2,'Minsk','Staraya',10,115),(6,'Minsk','Street1',111,10),(10,'Minsk','Staraya',10,115),(16,'Minsk','INovaya',1,1),(17,'Minsk','INovaya',1,1),(18,'Minsk','INovaya',1,1),(19,'Minsk','INNovaya',1,1),(20,'Minsk','Novaya',2,1),(21,'Minsk','Novaya',2,1),(22,'Minsk','Novaya',2,1),(23,'Minsk','Novaya',2,1),(24,'Minsk','Novaya',2,1),(25,'Minsk','Novaya',2,1),(26,'NY','BakerStreet',2,1),(27,'Minsk','Novaya',2,1),(28,'Minsk','Novaya',0,0),(29,'Minsk','Novaya',1,1),(30,'Minsk','Novaya',1,1),(31,'Minsk','Novaya',1,1),(32,'Minsk','Novaya',1,1);

INSERT INTO "license" VALUES (1,NULL),(2,NULL),(3,NULL),(4,NULL),(5,NULL),(6,NULL),(7,NULL),(8,'text'),(11,'Michael, Martin'),(12,'Mike, Michael'),(13,'New group'),(14,'New group'),(15,'Test group'),(16,'Ivan, Holly'),(17,', '),(18,'Hundos, Linda'),(19,'Hundos, Linda'),(20,'Hundos, Linda'),(21,'Hundos, Linda'),(22,'Hundos, Linda'),(23,'Hundos, Linda'),(24,'Hundos, Linda'),(25,'Hundos, Linda'),(26,'Hundos, Linda'),(27,'Hundos, Linda'),(28,'Hundos, Linda'),(29,'Hundos, Linda'),(30,'Linda1, Hundos1'),(31,'Hundos, Linda'),(32,'Hundos, Linda'),(34,'');

INSERT INTO "groupp" VALUES (6),(7),(14),(15);

INSERT INTO "permission" VALUES (1,'Permission1','000'),(2,'Permission2','111'),(3,'Permission3','010');

INSERT INTO "lisence_permission" VALUES (6,1),(7,1),(7,2),(30,1),(31,1),(32,1),(30,3),(30,2),(2,1),(2,2);

INSERT INTO "user" VALUES (2,'Holly','Ivan','Holly.Ivan@kronos.com',1,'32165478'),(3,'firstNameN','lastNameN','emailN@dot.com',1,'568974'),(5,'firstNameN','lastNameN','emailN@dot.com',6,'12345673'),(8,'Test','Test','Test',2,'Test'),(11,'Martin','Michael','Martin.Machael@kronos.com',10,NULL),(16,'Holly','Ivan','Holly.Ivan@kronos.com',1,NULL),(20,'Linda','Hundos','Linda.Hudson@kronos.com',16,'123456'),(21,'Linda','Hundos','Linda.Hudson@kronos.com',17,'123456'),(22,'Linda','Hundos','Linda.Hudson@kronos.com',18,'123456'),(23,'Linda','Hundos','Linda.Hudson@kronos.com',19,'123456'),(24,'Linda','Hundos','Linda.Hudson@kronos.com',20,'123456'),(25,'Linda','Hundos','Linda.Hudson@kronos.com',21,'123456'),(26,'Linda','Hundos','Linda.Hudson@kronos.com',22,'123456'),(27,'Linda','Hundos','Linda.Hudson@kronos.com',23,'123456'),(28,'Linda','Hundos','Linda.Hudson@kronos.com',24,'123456'),(29,'Linda','Hundos','Linda.Hudson@kronos.com',25,'123456'),(30,'Linda1','Hundos1','Linda@kronos.com',26,'1234567'),(31,'Linda','Hundos','Linda.Hudson@kronos',27,'123456'),(32,'Linda','Hundos','Linda.Hudson@kronos',28,'123456'),(34,'Holly','Ivan','Holly.Ivan@kronos.com',32,NULL);

INSERT INTO "user_group" VALUES (29,6),(30,6),(31,6),(32,6),(30,14),(30,15);

