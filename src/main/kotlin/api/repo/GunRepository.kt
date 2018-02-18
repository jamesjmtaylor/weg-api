package api.repo

import org.springframework.data.repository.CrudRepository

import api.model.Gun
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GunRepository : CrudRepository<Gun, Long> {
    @Query("select g from Gun g where g.id = :id")
    fun findById(@Param("id") id: Long): Gun
    @Query("select trim(name) from Gun g where g.id = :id")
    fun findNameById(@Param("id") id: Long): String
    @Query("select g from Gun g")
    fun getAll(): List<Gun>
}