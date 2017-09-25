package com.hextremelabs.tinyeeapp.ws

import com.hextremelabs.tinyeeapp.business.PersonBean
import com.hextremelabs.tinyeeapp.model.Person

import javax.ejb.EJB
import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService

/**
 * @author oladeji
 */
@WebService(name = "Person")
class PersonSoap {

  @EJB
  private lateinit var personBean: PersonBean

  @WebMethod(operationName = "getPerson")
  fun getPerson(@WebParam(name = "id") id: Long) = personBean.getPerson(id)

  @WebMethod(operationName = "searchByName")
  fun searchByName(@WebParam(name = "query") query: String) = personBean.searchByName(query)

  @WebMethod(operationName = "createPerson")
  fun createPerson(@WebParam(name = "person") person: Person) = personBean.createPerson(person)

  @WebMethod(operationName = "deletePerson")
  fun deletePerson(@WebParam(name = "id") id: Long) = "Person $id deleted".also { personBean.deletePerson(id) }
}
