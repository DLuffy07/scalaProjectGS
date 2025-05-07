

import cars._
import pilote.{Course => CoursePalmares, _}
import competition.{Course => CourseCompetition, _}  // Correctement importer CourseCompetition
import exceptions._
import mylist._
import java.time.LocalDate

object Main extends App {

  println("=== Simulation des Courses ===")

  // 1. Instancier un ensemble de circuits, voitures et pilotes

  // Création des circuits
  val silverstone = Circuit("Silverstone", "UK", 5.9, 7)
  val monaco = Circuit("Monaco", "Monaco", 3.3, 10)

  // Création des voitures
  val ferrari = new VoitureSport("488 GTB", 330, 3.0, "V8", 210000, 10, "Ferrari")
  val clio = new VoitureClassic("Clio", 180, 10.0, "Essence", 15000, 4, "Renault")
  val bugatti = new VoitureSuperSport("Chiron", 420, 2.4, "W16", 2500000, 5, "Bugatti")

  // Création des pilotes
  val pilote1 = Pilote("Jean", "Dupont", 30, ferrari, 8, Map())
  val pilote2 = Pilote("Luc", "Martin", 25, clio, 5, Map())
  val pilote3 = Pilote("Alice", "Moreau", 29, bugatti, 10, Map())
  val pilote4 = Pilote("Sophie", "Durand", 26, ferrari, 7, Map())

  // Liste de pilotes
  val listePilotes = new ListPilotes(
    Cons(pilote1, Cons(pilote2, Cons(pilote3, Cons(pilote4, Empty))))
  )

  // 2. Vérification des règles d’âge, type de voiture et vitesse
  // Les règles sont gérées dans les constructeurs des classes

  // 3. Crée et simule deux courses

  // Création d'une course (VoitureSport)
  val courseSilverstone = CourseCompetition(
    listePilotes = listePilotes,
    champion = None,
    date = LocalDate.now(),
    circuit = silverstone,
    typeVoiture = classOf[VoitureSport],
    gain = 500000
  )

  // Création d'une autre course (VoitureSuperSport)
  val courseMonaco = CourseCompetition(
    listePilotes = listePilotes,
    champion = None,
    date = LocalDate.now(),
    circuit = monaco,
    typeVoiture = classOf[VoitureSuperSport],
    gain = 1000000
  )

  // 4. Afficher les résultats des courses et les informations sur les pilotes

  println("=== Course 1 : Podium Aléatoire ===")
  val podiumSilverstone = courseSilverstone.podiumRandom()
  podiumSilverstone.foreach { case (p, place) =>
    println(s"$place - ${p.prenom} ${p.nom} (${p.voiture.nom})")
    // Mise à jour du palmarès avec conversion de CourseCompetition en CoursePalmares
    val coursePalmares = CoursePalmares(courseSilverstone.circuit.nom, courseSilverstone.circuit.lieu, courseSilverstone.gain)
    p.ajouterClassement(coursePalmares, place)
  }

  println("\n=== Course 2 : Podium Calculé ===")
  val podiumMonaco = courseMonaco.podiumCalcule()
  podiumMonaco.foreach { case (p, place) =>
    println(s"$place - ${p.prenom} ${p.nom} (${p.voiture.nom})")
    // Mise à jour du palmarès avec conversion de CourseCompetition en CoursePalmares
    val coursePalmares = CoursePalmares(courseMonaco.circuit.nom, courseMonaco.circuit.lieu, courseMonaco.gain)
    p.ajouterClassement(coursePalmares, place)
  }

  println("\nInformations sur les pilotes :")
  println(pilote1.informationPalmares)
  println(pilote2.informationPalmares)
  println(pilote3.informationPalmares)
  println(pilote4.informationPalmares)
}
