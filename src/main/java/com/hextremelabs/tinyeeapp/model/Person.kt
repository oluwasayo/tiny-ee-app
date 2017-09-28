package com.hextremelabs.tinyeeapp.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.ManyToMany
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

/**
 * @author oladeji
 */
@Entity
@Table(name = "person", indexes = arrayOf(Index(name = "INDEX_person_name", columnList = "name"), Index(name = "INDEX_person_dateOfBirth", columnList = "date_of_birth")), uniqueConstraints = arrayOf(UniqueConstraint(name = "UK_person_email", columnNames = arrayOf("email"))))
@NamedQueries(NamedQuery(name = "Person.searchByName", query = "SELECT p FROM Person p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:query), '%') ORDER BY p.dateOfBirth DESC"))
@XmlRootElement
class Person(

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", nullable = false)
  @get:XmlElement(name = "id")
  var id: Long? = null,

  @NotNull
  @Column(name = "name", nullable = false)
  @XmlElement
  var name: String,

  @NotNull
  @Column(name = "email", nullable = false)
  @get:XmlElement(name = "email")
  var email: String,

  @Temporal(TemporalType.DATE)
  @Column(name = "date_of_birth")
  @get:XmlElement(name = "dateOfBirth")
  var dateOfBirth: Date,

  @Column(name = "income", scale = 2, precision = 15)
  @get:XmlElement(name = "income")
  var income: BigDecimal,

  @ManyToMany(fetch = EAGER)
  @get:XmlElement(name = "children")
  var children: List<Person>? = null
) : Serializable {
  companion object {
    private const val serialVersionUID = 1L
  }
}
