package api.controller

import api.model.Gun
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable

import api.repo.GunRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
class WebController {

	@Autowired
	lateinit var repository: GunRepository

	@RequestMapping("/save")
	fun save(): String {
//		repository.save(Gun("Jack", "Smith"))
//		repository.save(Gun("Adam", "Johnson"))
//		repository.save(Gun("Kim", "Smith"))
//		repository.save(Gun("David", "Williams"))
//		repository.save(Gun("Peter", "Davis"))
//
		repository.save(Gun("","","","test",0,"",0))
		return "Stub!"
	}

	@RequestMapping("/findall")
	fun findAll() = repository.findAll()

	@RequestMapping("/findbyid/{id}")
	fun findById(@PathVariable id: Long)
			= repository.findOne(id)

	@RequestMapping("findbylastname/{lastName}")
	fun findByName(@PathVariable name: String)
			= repository.findByName(name)
	
}