Tondeuse
La société Libon a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires. La tondeuse peut être programmée pour parcourir l'intégralité de la surface. La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation. Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante. On assume que la case directement au Nord de la position (x, y) a pour coordonnées (x, y+1).

Pour programmer la tondeuse, on lui fournit une payload d'entrée au format json construit comme suit :

L'objet field correspond aux coordonnées du coin supérieur droit de la pelouse, celles du coin inférieur gauche sont supposées être (0,0)
L'objet mowers permet de piloter toutes les tondeuses qui ont été déployées. Chaque tondeuse est spécifiée comme suit :
start_position ligne donne la position (x,y) initiale de la tondeuse
orientation son orientation (N, S, E ou W).
instructions est une série d'instructions ordonnant à la tondeuse d'explorer la pelouse. Chaque instruction est représenté par un caractère (G, A ou D)
Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa série d'instructions. Lorsqu'une tondeuse achève une série d'instructions, elle communique sa position et son orientation.

OBJECTIF
Concevoir et écrire un programme s'exécutant sur une JVM, et implémentant la spécification ci-dessus sous forme d'un endpoint HTTP et passant le test ci-après

TEST
La payload suivante est fournie en entrée :

{
"field": {
"max_x": 5,
"max_y": 5
},
"mowers": [
{
"id": "mower1",
"start_position": {
"x": 1,
"y": 2
},
"orientation": "N",
"instructions": [
"G",
"A",
"G",
"A",
"G",
"A",
"G",
"A",
"A"
]
},
{
"id": "mower2",
"start_position": {
"x": 3,
"y": 3
},
"orientation": "E",
"instructions": [
"A",
"A",
"D",
"A",
"A",
"D",
"A",
"D",
"D",
"A"
]
}]
}

On attend une payload au format JSON indiquant la position et l'orientation finale de chaque tondeuse.

