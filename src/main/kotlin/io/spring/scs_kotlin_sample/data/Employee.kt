package io.spring.scs_kotlin_sample.data

import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var active: Boolean,
    var rank: Int,
    var salary: Double,
    @Enumerated(EnumType.STRING)
    var type: Type,
    var birthdate: LocalDate
)