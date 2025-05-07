package cars

class VoitureSuperSport(
                         nom: String,
                         vitesse: Int,
                         acceleration: Double,
                         moteur: String,
                         prix: Double,
                         nombreDeCourse: Int,
                         marque: String
                       ) extends Voiture(nom, vitesse, acceleration, moteur, prix, nombreDeCourse, marque)
