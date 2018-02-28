package api.controller

import api.model.*
import api.repo.AirRepository
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable

import api.repo.GunRepository
import api.repo.LandRepository
import api.repo.SeaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod

import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.util.StreamUtils
import java.io.IOException


@RestController
class WebController {

	@Autowired
	lateinit var repository: GunRepository
    @Autowired
    lateinit var landRepo: LandRepository
    @Autowired
    lateinit var seaRepo: SeaRepository
    @Autowired
    lateinit var airRepo: AirRepository

	@RequestMapping("/save")
	fun save(): String {
//		repository.save(Gun("test","","","",0,0,""))
		return "Stub!"
	}

    //MARK: - JSON fetching endpoints
    //NOTE: In a production environment values in the database should be pre-trimmed & lowercased.
	@RequestMapping("/findall")
	fun findAll() = repository.findAll()
    @RequestMapping("/getAllGuns")
    fun getAllGuns() = repository.getAll()

    @RequestMapping("/getAllLand")
    fun getAllLand() = landRepo.getAll()
    @RequestMapping("/getAllLandCombined")
    fun getAllLandCombined(): List<LandCombined> {
        val guns =  repository.getAll()
        val gunMap = guns.associateBy({it.name.toLowerCase()},{it})
        val land = landRepo.getAll()
        var landCombinedList = ArrayList<LandCombined>()
        for (l in land){
            val lc = LandCombined(l.name,l.groupIconUrl,l.individualIconUrl,l.photoUrl,
                    gunMap.get(l.primaryWeapon?.toLowerCase()),
                    gunMap.get(l.secondaryWeapon?.toLowerCase()),
                    gunMap.get(l.atgm?.toLowerCase()),
                    l.armor,l.speed,l.auto,l.weight,l.description)
            lc.id = l.id
            landCombinedList.add(lc)
        }
        return landCombinedList
    }

    @RequestMapping("/getAllSea")
    fun getAllSea() = seaRepo.getAll()
    @RequestMapping("/getAllSeaCombined")
    fun getAllSeaCombined(): List<SeaCombined> {
        val guns =  repository.getAll()
        val gunMap = guns.associateBy({it.name.toLowerCase()},{it})
        val sea = seaRepo.getAll()
        var seaCombinedList = ArrayList<SeaCombined>()
        for (s in sea){
            val sc = SeaCombined(s.name,s.individualIconUrl,s.photoUrl,
                    gunMap.get(s.gun?.toLowerCase()),
                    gunMap.get(s.sam?.toLowerCase()),
                    gunMap.get(s.asm?.toLowerCase()),
                    gunMap.get(s.torpedo?.toLowerCase()),
                    s.transports,s.qty,s.dive,s.speed,s.auto,s.tonnage,s.description)
            sc.id = s.id
            seaCombinedList.add(sc)
        }
        return seaCombinedList
    }

    @RequestMapping("/getAllAir")
    fun getAllAir() = airRepo.getAll()
    @RequestMapping("/getAllAirCombined")
    fun getAllAirCombined(): List<AirCombined> {
        val guns =  repository.getAll()
        val gunMap = guns.associateBy({it.name.toLowerCase()},{it})
        val air = airRepo.getAll()
        var airCombinedList = ArrayList<AirCombined>()
        for (a in air){
            val ac = AirCombined(a.name,a.groupIconUrl,a.individualIconUrl,a.photoUrl,
                    gunMap.get(a.gun?.toLowerCase()),
                    gunMap.get(a.agm?.toLowerCase()),
                    gunMap.get(a.aam?.toLowerCase()),
                    gunMap.get(a.asm?.toLowerCase()),
                    a.speed,a.auto,a.ceiling,a.weight,a.description)
            ac.id = a.id
            airCombinedList.add(ac)
        }
        return airCombinedList
    }

    //TODO: Re-use combination logic better (currently uses 3 redundant gun dictionaries & 3 redundant loops, one for each endpoint
    @RequestMapping("/getAllCombined")
    fun getAllCombined(): Combined {
        val guns = getAllGuns()
        val land = getAllLandCombined()
        val sea = getAllSeaCombined()
        val air = getAllAirCombined()
        return Combined(guns,land,sea,air)
    }

	@RequestMapping("/findbyid/{id}")
	fun findById(@PathVariable id: Long) = repository.findById(id)
    @RequestMapping("/findnamebyid/{id}")
    fun findNameByid(@PathVariable id: Long) = repository.findNameById(id)

    //MARK: - Image fetching endpoints
    @RequestMapping(value = "/photo/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_JPEG_VALUE))
    @Throws(IOException::class)
    fun getPhoto(@PathVariable id: String): ResponseEntity<ByteArray> {
        val imgFile = ClassPathResource("images/photo/" + id + ".jpg")
        val bytes = StreamUtils.copyToByteArray(imgFile.inputStream)

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes)
    }
    @RequestMapping(value = "/group/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getGroup(@PathVariable id: String): ResponseEntity<ByteArray> {
        val imgFile = ClassPathResource("images/group/" + id + ".png")
        val bytes = StreamUtils.copyToByteArray(imgFile.inputStream)

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes)
    }
    @RequestMapping(value = "/individual/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getIndividual(@PathVariable id: String): ResponseEntity<ByteArray> {
        val imgFile = ClassPathResource("images/individual/" + id + ".png")
        val bytes = StreamUtils.copyToByteArray(imgFile.inputStream)

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes)
    }
}