package com.hextremelabs.tinyeeapp.rpc

import com.hextremelabs.tinyeeapp.business.PersonBean
import com.hextremelabs.tinyeeapp.model.Person
import javax.ejb.EJB
import javax.ws.rs.ApplicationPath
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.MediaType.APPLICATION_XML
import javax.ws.rs.core.Response

/**
 * @author oladeji
 */
@Path("person-rest")
class PersonRest {

  @EJB
  private lateinit var personBean: PersonBean

  @GET
  @Path("stuff/{id}")
  @Produces(APPLICATION_JSON, APPLICATION_XML)
  fun getPerson(@PathParam("id") id: Long) = personBean.getPerson(id)

  @GET
  @Path("stuff/search/name")
  @Produces(APPLICATION_JSON, APPLICATION_XML)
  fun searchByName(@QueryParam("query") query: String) = personBean.searchByName(query)

  @POST
  @Path("stuff")
  @Consumes(APPLICATION_JSON, APPLICATION_XML)
  @Produces(APPLICATION_JSON, APPLICATION_XML)
  fun createPerson(person: Person) = personBean.createPerson(person)

  @DELETE
  @Path("stuff/{id}")
  @Produces(APPLICATION_JSON, APPLICATION_XML)
  fun deletePerson(@PathParam("id") id: Long) = personBean.deletePerson(id).let {
    Response.ok("Person $id deleted").build()
  }
}

@ApplicationPath("rpc")
class ApplicationConfig : Application() {

  override fun getClasses() = setOf<Class<*>>(PersonRest::class.java)
}