package api.repo

import org.springframework.data.repository.CrudRepository

import api.model.Sea
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SeaRepository : CrudRepository<Sea, Long> {
    @Query("select s from Sea s where s.id = :id")
    fun findById(@Param("id") id: Long): Sea
    @Query("select s from Sea s")
    fun getAll(): List<Sea>
}