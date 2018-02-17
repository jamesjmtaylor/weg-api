package api.repo

import org.springframework.data.repository.CrudRepository

import api.model.Gun
import org.springframework.stereotype.Repository

@Repository
interface GunRepository : CrudRepository<Gun, Long> {
	fun findByName(name: String): Iterable<Gun>
    fun findById(id: Long): Gun
}