package pilote

import cars.Voiture
import exceptions._ // pour les exceptions personnalisées

case class Course(nom: String, lieu: String, gain: Double)

case class Pilote(
                   nom: String,
                   prenom: String,
                   age: Int,
                   voiture: Voiture,
                   experience: Int,
                   var palmares: Map[Course, Int] // Classement par course
                 ) {

  // Règle 1 — Vérification de l'âge
  if (age < 18) throw new InvalidAgeException("Le pilote doit avoir au moins 18 ans")

  //  Règle 3 — Ajout d'un classement, avec validation entre 1 et 12
  def ajouterClassement(course: Course, rang: Int): Unit = {
    if (rang < 1 || rang > 12)
      throw new RankRaceException("Le rang doit être entre 1 et 12")
    else {
      palmares += (course -> rang)
    }
  }

  def dejaChampion: Boolean =
    palmares.values.exists(_ == 1)

  def nombreDeVictoire: Int =
    palmares.values.count(_ == 1)

  def nombreDeFoisEnPodium: Int =
    palmares.values.count(classement => classement <= 3)

  def totalDesGains: Double =
    palmares.collect {
      case (course, classement) if classement == 1 => course.gain
      case (course, classement) if classement == 2 => course.gain * 0.6
      case (course, classement) if classement == 3 => course.gain * 0.3
    }.sum

  // Affichage des informations de palmarès
  def informationPalmares: String = {
    if (palmares.isEmpty) "Aucune course."
    else palmares.map { case (c, rank) =>
      // Formater correctement la sortie
      s"${c.nom} (${c.lieu}) : ${rank}ᵉ place"
    }.mkString("\n") // Utiliser mkString pour ne pas découper en caractères
  }
}
