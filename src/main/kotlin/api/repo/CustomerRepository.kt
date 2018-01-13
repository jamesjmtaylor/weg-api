package api.repo

import org.springframework.data.repository.CrudRepository

import api.model.Customer
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {
	fun findByLastName(lastName: String): Iterable<Customer>
}