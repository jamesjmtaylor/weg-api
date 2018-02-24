package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Gun(
        val name: String,
		val groupIconUrl: String,
		val individualIconUrl: String,
        val photoUrl: String,
        val range: Int,
        val penetration: Int,
        val altitude: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","",0,0, 0, "")
}