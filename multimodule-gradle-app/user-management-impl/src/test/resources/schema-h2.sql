SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS "address";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "address" (
  "address_id" int(11) NOT NULL AUTO_INCREMENT,
  "city" varchar(45) NOT NULL,
  "street" varchar(45) NOT NULL,
  "house_number" int(11) NOT NULL,
  "flat_number" int(11) DEFAULT NULL,
  PRIMARY KEY ("address_id")
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS "license";
CREATE TABLE "license" (
  "license_id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("license_id"),
  UNIQUE KEY "idlicense_id_UNIQUE" ("license_id")
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS "groupp";
CREATE TABLE "groupp" (
  "group_id" int(11) NOT NULL,
  PRIMARY KEY ("group_id"),
  CONSTRAINT "group_parent_fk" FOREIGN KEY ("group_id") REFERENCES "license" ("license_id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS "permission";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "permission" (
  "permission_id" int(11) NOT NULL AUTO_INCREMENT,
  "object" varchar(45) DEFAULT NULL,
  "action_type" varchar(3) DEFAULT NULL,
  PRIMARY KEY ("permission_id")
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS "lisence_permission";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "lisence_permission" (
  "lisence_id" int(11) NOT NULL,
  "permission_id" int(11) NOT NULL,
  KEY "permission_id_fk_idx" ("permission_id"),
  KEY "group_idfk_idx" ("lisence_id"),
  CONSTRAINT "lisence_idfk" FOREIGN KEY ("lisence_id") REFERENCES "license" ("license_id") ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT "permission_idfk" FOREIGN KEY ("permission_id") REFERENCES "permission" ("permission_id") ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS "user";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "user" (
  "user_id" int(11) NOT NULL,
  "first_name" varchar(45) NOT NULL,
  "last_name" varchar(45) NOT NULL,
  "email" varchar(60) DEFAULT NULL,
  "address_id" int(11) DEFAULT NULL,
  "phone" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("user_id"),
  KEY "address_id_fk_idx" ("address_id"),
  CONSTRAINT "address_id_fk" FOREIGN KEY ("address_id") REFERENCES "address" ("address_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "user_parent_fk" FOREIGN KEY ("user_id") REFERENCES "license" ("license_id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS "user_group";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "user_group" (
  "user_id" int(11) NOT NULL,
  "group_id" int(11) NOT NULL,
  PRIMARY KEY ("user_id","group_id"),
  KEY "group_id_fk_idx" ("group_id"),
  KEY "license_id_fk_idx" ("user_id"),
  CONSTRAINT "group_id_fk" FOREIGN KEY ("group_id") REFERENCES "groupp" ("group_id") ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT "user_id_fk" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;
