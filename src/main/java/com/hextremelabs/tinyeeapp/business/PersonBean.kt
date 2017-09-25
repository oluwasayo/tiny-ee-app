package com.hextremelabs.tinyeeapp.business

import com.hextremelabs.tinyeeapp.model.Person
import javax.ejb.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * @author oladeji
 */
@Singleton
class PersonBean {

  @PersistenceContext
  private lateinit var em: EntityManager

  fun getPerson(id: Long) = em.find(Person::class.java, id)

  fun searchByName(query: String)
      = em.createNamedQuery("Person.searchByName", Person::class.java).setParameter("query", query).resultList

  fun createPerson(person: Person) = person.apply { em.persist(this) }

  fun deletePerson(id: Long) = em.remove(em.find(Person::class.java, id))
}
