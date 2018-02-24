package api.repo

import org.springframework.data.repository.CrudRepository

import api.model.Land
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LandRepository : CrudRepository<Land, Long> {
    @Query("select l from Land l where l.id = :id")
    fun findById(@Param("id") id: Long): Land
    @Query("select l from Land l")
    fun getAll(): List<Land>
}