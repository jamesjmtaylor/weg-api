package api.repo

import api.model.Air
import org.springframework.data.repository.CrudRepository

import api.model.Sea
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AirRepository : CrudRepository<Air, Long> {
    @Query("select a from Air a where a.id = :id")
    fun findById(@Param("id") id: Long): Air
    @Query("select a from Air a")
    fun getAll(): List<Air>
}