package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Gun(
        @Lob @Type(type = "org.hibernate.type.TextType")
		val description: String,
		val groupIconUrl: String,
		val individualIcon: String,
		val name: String,
        val penetration: Int,
        val photoUrl: String,
        val range: Int,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","",0,"",0)
}