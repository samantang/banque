Objectifs
:
Ce
projet
constitue
un
cas
réel
de
développement
d’application
JEE
avec
les
composants
et
fonctionnalités
typiques
nécessaires
dans
ces
applications.
Il
vous
permettra
de
mettre
en
pratique
l’ensemble
des
notions
et
technologies
présentées
en
cours
et
en
TP
dans
le
cadre
des
modules
EC
09
et
EC
14
et
en
particulier
:
Les
Servlets/JSP,
les
EJB,
éventuellement
les
Services
Web,
etc.
Il
est
également
possible
d’utiliser
des
standards
et
technologies
non
abordés
en
cours
telles
que
les
composants
graphiques
JSF
ou
des
APIs
complémentaires
à
JEE.
Evaluation
:
Ce
projet
sera
votre
principale
évaluation
pour
les
deux
modules
EC
09
et
EC
14.
Il
est
à
réaliser
de
préférence
en
binôme
pour
qu’il
puisse
y
avoir
des
échanges
et
discussions
autours
des
choix
conceptuels
et
techniques
mais
peut
également
être
réalisé
individuellement
pour
ceux
qui
le
souhaitent.
Les
binômes
doivent
être
préalablement
validés
par
le
responsable
des
modules.
Durée
:
Les
solutions
proposées
sont
à
rendre
au
plus
tard
le
30
avril.
Tout
retard
doit
être
signalé
au
responsable
des
modules
avant
cette
date
en
présentant
le
motif
et
les
éventuelles
difficultés.
Un
délai
supplémentaire
de
quelques
jours
pourra
alors
être
accordé
selon
la
situation.
Sujet
:
Il
s’agit
de
mettre
en
place
une
application
WEB/JEE
pour
la
gestion
des
anciens
de
la
formation
CCI
Tours.
Conformément
à
l’architecture
générale
d’application
JEE.
La
solution
à
proposer
devra
comporter
essentiellement
trois
parties
:
-­‐ une
partie
base
de
données
pour
le
stockage
de
informations
relatives
aux
personnes
ayant
été
inscrites
au
Master
CCI,
-­‐ une
partie
interface
Web
qui
permettra
d’interagir
les
différents
modules
et
composants
de
l’application
et
de
manipuler
(visualiser,
saisir,
etc.)
les
informations
à
travers
ces
modules,
-­‐ et
une
partie
coeur
de
l’application
avec
les
composants
EJB
nécessaires
pour
les
différentes
fonctionnalités
requise
dans
l’application
ainsi
que
pour
l’interaction
entre
l’interface
et
la
base
de
données.
Le
travail
attendu
pour
chacune
de
ces
parties
est
détaillé
ci-­‐après.
1-­‐
Base
de
données
:
Aucun
schéma
de
la
base
n’est
préalablement
imposé.
Cependant,
les
principales
informations
sur
les
personnes
inscrites
dans
le
CCI
Tours
doivent
figurer
dans
les
solutions
à
proposer.
Il
faudra
par
exemple,
en
plus
de
l’identification
de
la
personne
avec
son
nom,
prénom,
etc.
disposer
de
l’année
de
l’inscription
en
CCI
pour
pouvoir
gérer
les
promotions.
Il
faudra
également
prévoir
le
moyen
de
gérer
le
devenir
des
personnes
après
l’obtention
du
diplôme
CCI
et
de
tracer
ainsi
l’évolution
de
leurs
carrières.
Cette
information,
étant
difficile
à
avoir
pour
tous
les
anciens,
doit
être
optionnelle
dans
le
schéma
de
la
base.
Il
est
possible
que
des
anciens
CCI
participent
activement
dans
la
formation
en
tant
que
chargés
de
cours,
conférenciers
invités,
maitres
de
stages,
etc.
Un
statut
particulier
de
membre
«
actif
»
peut
alors
être
accordé
à
toute
personne
participant
à
ce
genre
d’activité.
La
prise
en
compte
de
cette
spécificité
dans
le
schéma
de
la
base
de
données
et
par
la
suite
dans
les
différentes
parties
de
l’application
n’est
pas
nécessaire
et
donnera
lieu
à
un
bonus
dans
la
note
finale.
L’alimentation
de
la
base
pourra
être
faite
à
partir
des
fichiers
des
différentes
promotions
des
inscrits
en
CCI.
Il
n’est
pas
exigé
de
prévoir
des
programmes
d’alimentation
automatique
de
la
base
à
partir
de
fichier
Excel
ou
XML
parce
que
cela
dépend
de
la
structure
de
ces
fichiers
et
une
solution
générique
est
difficile
à
mettre
en
place.
Cependant,
ceux
qui
souhaitent
proposer
un
tel
module
dans
leurs
solutions
peuvent
contacter
le
responsable
des
modules
pour
avoir
la
structure
des
fichiers
d’inscrits
CCI
disponibles.
Une
interaction
avec
le
responsable
pour
valider
le
schéma
de
la
base
de
données
proposé
est
possible
mais
n’est
pas
obligatoire.
2-­‐
Interface
Web
:
Comme
pour
la
base
de
données,
vous
avez
le
libre
choix
pour
proposer
l’interface
de
votre
application.
Cependant,
votre
interface
devra
reposer
sur
les
technologies
Web
Java
vues
en
cours
ou
non
:
Servlet,
JSP,
JSF,
…
Ces
éléments
d’interface
nécessitent
le
déploiement
dans
des
conteneurs
Web.
Vous
pouvez
pour
cela
utiliser
le
conteneur
de
votre
choix.
Cependant
l’utilisation
du
serveur
Apache
Tomcat
manipulé
dans
les
différents
TPs
est
recommandée.
L’utilisation
d’outils
d’aide
à
la
conception
d’interface
tels
que
RichFaces
dans
eclipse
et
JBoss
est
autorisée
et
recommandée
pour
définir
des
interfaces
conviviales.
L’interface
de
l’application
doit
permettre
de
se
connecter
à
l’application
en
tant
qu’administrateur
pour
manipuler
les
informations
dans
la
base
de
données,
créer/modifier/supprimer
des
comptes
etc.
Elle
doit
également
permettre
de
se
connecter
en
mode
ancienCCI
qui
doit
lors
de
sa
première
connexion
passer
par
une
phase
d’inscription
où
il
renseignera
outre
nom,
prénom,
mot
de
passe
choisi,
etc.
sa
fonction
et
son
employeur
actuels
et
éventuellement
ses
précédentes
fonctions
avec
les
périodes
et
les
employeurs
correspondants.
Un
troisième
mode
de
connexion
pour
les
visiteurs
doit
aussi
être
prévu.
Ce
mode
sera
celui
par
défaut
au
lancement
de
l’application
et
permettra
d’accéder
à
des
informations
générales
à
but
«
publicitaire
»
sur
la
formation
CCI
avec
par
exemple
le
nombre
d’inscrits
par
promotion,
le
taux
de
réussite,
les
parcours
typiques
des
anciens,
les
évènements
à
venir
(dates
et
dossier
pour
candidater
au
CCI,
conférences,
etc.).
La
définition
des
éléments
d’interface
doit
être
conforme
aux
principes
JEE
en
ayant
le
minimum
possible
de
couplage
entre
ces
éléments
et
les
composants
de
l’application.
Dans
ce
cadre
l’utilisation
de
solutions
qui
reposent
sur
les
patrons
de
conceptions
tels
que
MVC
est
fortement
encouragée
et
sera
bien
considérée
pour
la
note
globale
du
projet.
3
–
Le
coeur
de
l’application
:
Il
s’agit
ici
de
développer
les
différents
composants
EJB
pour
assurer
les
fonctionnalités
de
l’application
et
pour
gérer
l’interaction
avec
l’interface
et
avec
la
base
de
données.
En
particulier,
les
EJB
Sessions
doivent
être
implémentés
pour
gérer
les
connexions
à
l’application
et
les
EJB
Entités
doivent
assurer
la
communication
avec
la
base
de
données.
Il
s’agit
ici
d’utiliser
les
versions
récentes
EJB3
et
de
manipuler
les
composants
à
travers
les
annotations
du
code
java.
Une
partie
messagerie
peut-­‐être
intégrée
à
l’application
mais
n’est
pas
obligatoire.
La
mise
en
place
de
cette
partie
peut
reposer
sur
l’utilisation
des
EJB
MDB
(Message
Driven
Beans).
Le
déploiement
de
ces
composants
nécessite
l’utilisation
d’un
serveur
d’application
tel
que
JBoss
manipulé
dans
les
différents
TPs
mais
d’autres
serveurs
tels
que
GlassFish
peuvent
également
être
utilisés.
Le
développement
de
ces
composants
doit
être
conforme
aux
règles
de
développement
JEE
en
ayant
le
minimum
possible
de
couplage
entre
les
composants
et
en
faisant
appels
aux
patrons
de
conception
tels
que
DAO,
Singleton,
etc.
L’utilisation
des
patrons
sera
un
point
positif
dans
la
note
globale
du
projet.
Travail
à
rendre
:
Le
travail
attendu
devra
comporter
deux
trois
parties
:
1-­‐ Les
codes
sources
de
votre
projet
documenté
et
fonctionnel
avec
éventuellement
la
génération
des
jar
exécutables.
2-­‐ Un
rapport
d’une
dizaine
de
pages
maximum
expliquant
et
motivant
les
différents
choix
de
conception,
l’architecture
de
la
solution
et
l’implémentation
des
différentes
parties
de
l’application.
3-­‐ Une
courte
présentation
power
point
(une
dizaine
de
diapositives)
qui
présente
votre
application.
Il
n’est
pas
exclu
que
vous
soyez
convoqués
pour
effectuer
cette
présentation
devant
un
mini-­‐jury
et/ou
de
faire
une
démo
de
votre
projet
dans
la
salle
des
cours
du
CCI.
