package api.controller

import api.model.Gun
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
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import org.apache.catalina.manager.StatusTransformer.setContentType
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.util.StreamUtils
import java.io.IOException
import javax.servlet.http.HttpServletResponse

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
	@RequestMapping("/findall")
	fun findAll() = repository.findAll()
    @RequestMapping("/getAllGuns")
    fun getAllGuns() = repository.getAll()
    @RequestMapping("/getAllSea")
    fun getAllSea() = seaRepo.getAll()

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