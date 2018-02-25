package api.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Air(
        val name: String,
        val groupIconUrl: String,
        val individualIconUrl: String,
        val photoUrl: String,
        val gun: String,
        val agm: String,
        val aam: String,
        val asm: String,
        val speed: Int,
        val auto: Int,
        val ceiling: Int,
        val weight: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		val id: Long = -1) {
	
	private constructor() : this("", "","","","", "", "","",0, 0, 0, 0,"")
}
class AirCombined(
        val name: String,
        val groupIconUrl: String,
        val individualIconUrl: String,
        val photoUrl: String,
        val gun: Gun?,
        val agm: Gun?,
        val aam: Gun?,
        val asm: Gun?,
        val speed: Int,
        val auto: Int,
        val ceiling: Int,
        val weight: Int,
        @Lob @Type(type = "org.hibernate.type.TextType")
        val description: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {

    private constructor() : this("", "","","",null, null, null,null,0, 0, 0, 0,"")
}