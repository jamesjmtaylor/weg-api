package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Land(
        val name: String,
        val groupIconUrl: String,
        val individualIcon: String,
        val photoUrl: String,
        val primaryWeapon: String,
        val secondaryWeapon: String,
        val atgm: String,
        val armor: Int,
        val speed: Int,
        val auto: Int,
        val weight: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","","", "", "",0,0, 0, 0, "")
}