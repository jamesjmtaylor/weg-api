package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Gun(
        val name: String,
        val photoUrl: String,
		val groupIconUrl: String,
		val individualIcon: String,
        val penetration: Int,
        val range: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","",0,0,"")
}