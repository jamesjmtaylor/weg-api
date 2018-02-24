package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Sea(
        val name: String,
        val individualIconUrl: String,
        val photoUrl: String,
        val gun: String,
        val sam: String,
        val asm: String,
        val torpedo: String,
        val transports: String,
        val qty: Int,
        val dive: Int,
        val speed: Int,
        val auto: Int,
        val tonnage: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","","", "", "","",0, 0, 0, 0,0,"")
}