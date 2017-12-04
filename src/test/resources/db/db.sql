DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS food_trucks;

CREATE TABLE IF NOT EXISTS "public"."authors" (
  "id"   SERIAL,
  "name" TEXT,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "public"."books" (
  "id"     SERIAL,
  "book"   TEXT,
  "author" SERIAL,
  PRIMARY KEY ("id"),
  CONSTRAINT "book_auth_fkey" FOREIGN KEY ("author") REFERENCES "public"."authors" ("id") ON DELETE SET NULL
);

INSERT INTO "public"."authors"("name") VALUES('Jack London') RETURNING "id", "name";
INSERT INTO "public"."authors"("name") VALUES('Honore de Balzac') RETURNING "id", "name";
INSERT INTO "public"."authors"("name") VALUES('Lion Feuchtwanger') RETURNING "id", "name";
INSERT INTO "public"."authors"("name") VALUES('Emile Zola') RETURNING "id", "name";
INSERT INTO "public"."authors"("name") VALUES('Truman Capote') RETURNING "id", "name";

INSERT INTO "public"."books"("book", "author") VALUES('the flying sparrow', '1') RETURNING "id", "book", "author";
INSERT INTO "public"."books"("book", "author") VALUES('the flying monkey', '2') RETURNING "id", "book", "author";
INSERT INTO "public"."books"("book", "author") VALUES('the flying pig', '3') RETURNING "id", "book", "author";
INSERT INTO "public"."books"("book", "author") VALUES('the flying donkey', '4') RETURNING "id", "book", "author";
INSERT INTO "public"."books"("book", "author") VALUES('the flying eagle', '5') RETURNING "id", "book", "author";