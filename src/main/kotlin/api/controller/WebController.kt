package api.controller

import api.model.Gun
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable

import api.repo.GunRepository
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

	@RequestMapping("/save")
	fun save(): String {
		repository.save(Gun("","","","test",0,"",0))
		return "Stub!"
	}
    //TODO: Find out why 'name' has a really long space between the last character and the closing quote.
	@RequestMapping("/findall")
	fun findAll() = repository.findAll()
    //TODO: Implement id fetch
	@RequestMapping("/findbyid/{id}")
	fun findById(@PathVariable id: Long)
			= repository.findById(id)

    @RequestMapping(value = "/photo/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_JPEG_VALUE))
    @Throws(IOException::class)
    fun getImage(@PathVariable id: String): ResponseEntity<ByteArray> {
        val imgFile = ClassPathResource("images/photo/" + id + ".jpg")
        val bytes = StreamUtils.copyToByteArray(imgFile.inputStream)

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes)
    }
}