package pilote

import mylist._

class ListPilotes(val pilotes: MyList[Pilote]) {

  def filtrerParTypeVoiture[T <: cars.Voiture](clazz: Class[T]): MyList[Pilote] =
    pilotes.filter(p => clazz.isInstance(p.voiture))

  def ajouterPilote(pilote: Pilote): ListPilotes =
    new ListPilotes(pilotes.add(pilote))

  def supprimerPilote(pilote: Pilote): ListPilotes =
    new ListPilotes(pilotes.filter(_ != pilote))
}
