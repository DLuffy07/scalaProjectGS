package competition

import pilote._
import cars._
import java.time.LocalDate
import scala.util.Random
import scala.util.Try
import exceptions._

case class Course(
                   listePilotes: ListPilotes,
                   champion: Option[Pilote],
                   date: LocalDate,
                   circuit: Circuit,
                   typeVoiture: Class[_ <: Voiture],
                   gain: Double
                 ) {

  def podiumRandom(): List[(Pilote, Int)] = {
    val all = listePilotes.pilotes.filter(p => typeVoiture.isInstance(p.voiture))
    val shuffled = Random.shuffle(toScalaList(all))
    shuffled.take(3).zipWithIndex.map { case (p, i) => (p, i + 1) }
  }

  def podiumCalcule(): List[(Pilote, Int)] = {
    val filtered = toScalaList(listePilotes.pilotes)
      .filter(p => typeVoiture.isInstance(p.voiture))

    val ranked = filtered.sortBy(p =>
      -(
        p.experience * 2 +
          (p.voiture.vitesse / 10) +
          (100.0 / p.voiture.acceleration) -
          (circuit.difficulte * 1.5)
        )
    )

    ranked.take(3).zipWithIndex.map { case (p, i) => (p, i + 1) }
  }

  private def toScalaList(myList: mylist.MyList[Pilote]): List[Pilote] = {
    var result = List.empty[Pilote]
    myList.foreach(p => result = result :+ p)
    result
  }

  def verifieTypeUnique(): Try[Unit] = Try {
    val voitures = toScalaList(listePilotes.pilotes).map(_.voiture.getClass).distinct
    if (voitures.size > 1)
      throw new TypeCarException("Tous les véhicules doivent être du même type dans une course.")
  }
}
