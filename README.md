# SIR_TP3_MONGODB
## Quelles sont les limites d’une base données orientées document (BDOD) ?
Avec une BDR classique, une fois la structure créée, il est seulement possible d'ajouter des données qui correspondent à la structure de la base.
Avec une BDOD, le développeur peut facilement insérer n'importe quel type de données dans n'importe quelle collection, 
ce qui implique un gros risque d'avoir des structures de documents inconsistentes ou obsolètes avec de nombreuses mises à jour.
En plus le langage n'est pas standardisé et l’écriture de requêtes complexes est difficile à mettre en œuvre.

###Que peut on faire comme types de requêtes ?
Chaque structure de données gérée possède des caractéristiques uniques et commandes uniques.
Il est impossible de faire des requêtes sur les valeurs comme on le fait habituellement avec un WHERE dans une BDR, 
une valeur en table n'est accessible que via une clé.
Alors qu'une clé est stockée comme un tableau de bytes, on utilise essentiellement une string comme clé.
les types de données simples : chaînes de caractères, tableaux associatifs, listes, ensembles et ensembles ordonnés.
On peut effectuer des requetes d'ajout de clé, de valeurs de clé, des requetes de selection de valeur de clé
## quelques exemples
#####Strings
C'est le type le plus simple : à une clef, on peut y associer une valeur. Cette valeur peut-être une string de tout type (json, valeur d'une image jpeg), mais aussi un entier. La limite est de 1 Go.
Les principales commandes associées sont SET, GET, INCR, DECR, et GETSET.
```
redis 127.0.0.1:6379> SET compteur 10
 INCR compteur
 INCR compteur
 GET compteur
"12"
 GETSET compteur 123
"12"
 GET compteur
"123"
```
#####Hashes
Une hash permet de stocker dans un même enregistrement plusieurs couples de clef/valeurs. Les commande de hash commencent... par un H comme Hash. 
```
 HSET nosql redis "clef/valeur"
 HSET nosql mongodb document
 HSET nosql riak "fault tolerant"
 HGET nosql redis
HGETALL nosql
1) "redis"
2) "clef/valeur"
3) "mongodb"
4) "document"
5) "riak"
6) "fault tolerant"
 HKEYS nosql
1) "redis"
2) "mongodb"
3) "riak"
 HVALS nosql
1) "clef/valeur"
2) "document"
3) "fault tolerant"
 HLEN nosql

```
#####Lists
Les listes de redis sont des listes liées (linked list). Cela veut dire que même si vous avez des millions d'enregistrement dans une liste, 
cela sera toujours aussi rapide d'insérer un élément en tête ou en queue de liste.
```
 RPUSH messages "Bienvenue dans redis"
 RPUSH messages "Merci pour cette petite introduction sur barreverte.fr"
 RPUSH messages "Non seulement je passe du bon temps, mais je m'amuse en meme temps"
 LRANGE messages 0 3
1) "Bienvenue dans redis"
2) "Merci pour cette petite introduction sur barreverte.fr"
3) "Non seulement je passe du bon temps, mais je m'amuse en meme temps"
```
#####Sets

Les Sets sont des collections d'objets non ordonnées. Les commandes commencent toutes avec un S comme Set, parmi celles-ci on trouve SADD pour ajouter une valeur à un set, SCARD pour obtenir la taille (cardinalité) d'un set, et surtout les commandes SINTER, SUNION, SDIFF qui permettent respectivement d'obtenir l'intersection, l'union et la différences entre 2 sets. 
Ces commandes existent en version "STORE" ; ainsi SINTERSTORE permet de stocker dans un nouveau set l'intersection de 2 autres.
```
 SADD supermarche pommes
 SADD supermarche poires
 SADD supermarche scoubidous
 SADD monFrigo beurre
 SADD monFrigo pommes
 SADD monFrigo TddByExample
 SCARD monFrigo
 SINTER supermarche monFrigo
1) "pommes"
 SUNION supermarche monFrigo
1) "scoubidous"
2) "pommes"
3) "poires"
4) "TddByExample"
5) "beurre"
SDIFF supermarche monFrigo
1) "scoubidous"
2) "poires"
1) "duncan"
```
#####Sorted sets
Les sets triés (Sorted Set) sont similaires à des Sets mais ajoutent une notion de score aux valeurs ajoutées aux Set, ce qui permet de faire des tris. 
```
ZADD savants 1564 "Galilee"
ZADD savants 1643 "Isaac Newton"
ZADD savants 1571 "Johannes Kepler"
ZADD savants 1879 "Albert Einstein"
ZADD savants 1858 "Max Planck"
ZADD savants 1887 "Erwin Schrodinger"
ZRANGE savants 0 -1
1) "duncan"
2) "ghanima"
```
