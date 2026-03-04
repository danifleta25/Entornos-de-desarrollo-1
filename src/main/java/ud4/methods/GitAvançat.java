package ud4.methods;

public @interface GitAvançat {

    /*
    Recolocar cada rama sobre el commit inicial

    // Rama canvis
    git checkout canvis
    git reset --hard cca01c4 // es el codigo del commit inicial donde esta el main
    echo "__Canvis__: >> README.md
    git comit -am "Canvis"

        // Rama canviC
    git checkout canvi/C
    git reset --hard cca01c4 // es el codigo del commit inicial donde esta el main
    echo "__Canvi C__: >> README.md
    git comit -am "Canvi C"

           // Rama canviB
    git checkout canvi/B
    git reset --hard cca01c4 // es el codigo del commit inicial donde esta el main
    echo "__Canvi B__: >> README.md
    git comit -am "Canvi B"

            // Rama canviA
    git checkout canvi/A
    git reset --hard cca01c4 // es el codigo del commit inicial donde esta el main
    echo "__Canvi A__: >> README.md
    git comit -am "Canvi A"


    //Modifica el mensaje del commit canviA por Canvi A

    git checkout canvi/A
    git commit --amend -m "Canvi A"


    // Copia els commits canviA, canvib y canvi c dentro de la rama canvis
    // Cherry pick

    git checkout canvis
    git cherry-pick 74e873b // hash A
    git cherry-pick dc48c81 // hash b
    git cherry-pick 1c6a633 // hash c

    // Eliminar ramas canvi/a, canvi/b, canvi/c

    git checkout main
    git branch -d canvi/A
    git branch -d canvi/B
    git branch -d canvi/C


    // Fusiona la rama canvis con la rama main en un solo commit
    // Crea una etiqueta anotada con el nombre GitAvançat en este commit

    git checkout main
    git merge --squash canvis
    git commit -m "Canvis A, B y C"

    git tag -a GitAvançat -m "Estat final despres de l'exercici de Git avançat"

    git show GitAvançat

    git lga

    // Eliminar branca canvis

    git checkout main
    git branch -d canvis // Para forzar eliminacion sino funciona usa D MAYUSCULA






     */


}
