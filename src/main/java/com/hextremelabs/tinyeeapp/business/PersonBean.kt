package com.hextremelabs.tinyeeapp.business

import com.hextremelabs.tinyeeapp.model.Person
import javax.ejb.Stateless
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * @author oladeji
 */
@Stateless
class PersonBean {

  @PersistenceContext
  private lateinit var em: EntityManager

  fun getPerson(id: Long) = em.find(Person::class.java, id)

  fun searchByName(query: String) = namedQuery<Person>("Person.searchByName").setParameter("query", query).resultList

  fun createPerson(person: Person) = person.apply { em.persist(this) }

  fun deletePerson(id: Long) = em.remove(em.find(Person::class.java, id))

  private inline fun <reified T> namedQuery(namedQuery: String) = em.createNamedQuery(namedQuery, T::class.java)
}
