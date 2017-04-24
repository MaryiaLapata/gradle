
INSERT INTO "address" VALUES (1,'Minsk','Novaya',1,1),(2,'Minsk','Staraya',10,115),(3,'Minsk','Street1',111,10),(4,'Minsk','Street1',111,10),(5,'Minsk','Street1',111,10),(6,'Minsk','Street1',111,10),(7,'Minsk','Street1',111,10);

INSERT INTO "license" VALUES (1, ''),(2, ''),(3, ''),(4, ''),(5, ''),(6, ''),(7, ''),(8, '');

INSERT INTO "group" VALUES (6),(7);


INSERT INTO "permission" VALUES (1,'Permission1','000'),(2,'Permission2','111');


INSERT INTO "group_permission" VALUES (1,1,1),(2,2,1),(3,2,2);


INSERT INTO "user" VALUES (1,'Martin','Michael','Martin.Machael@kronos.com',2,NULL),(2,'Holly','Ivan','Holly.Ivan@kronos.com',1,NULL),(3,'firstNameN','lastNameN','emailN@dot.com',1,NULL),(4,'firstNameN','lastNameN','emailN@dot.com',3,NULL),(5,'firstNameN','lastNameN','emailN@dot.com',6,'12345673');

INSERT INTO "user_group" VALUES (1,1,1),(2,2,1),(3,2,2);

INSERT INTO "user_permission" VALUES (1,1,1),(2,2,1),(3,2,2);
