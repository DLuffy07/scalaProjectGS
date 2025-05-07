package cars

import exceptions._

class VoitureSport(
                    nom: String,
                    vitesse: Int,
                    acceleration: Double,
                    moteur: String,
                    prix: Double,
                    nombreDeCourse: Int,
                    marque: String
                  ) extends Voiture(nom, vitesse, acceleration, moteur, prix, nombreDeCourse, marque) {

  if (vitesse < 0 || vitesse > 330)
    throw new SpeedRangeException(s"Vitesse invalide ($vitesse km/h) pour VoitureSport. Elle doit être entre 0 et 330.")
}
