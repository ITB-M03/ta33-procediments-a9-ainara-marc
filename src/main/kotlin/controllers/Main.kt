package org.example.controllers

import controllers.menuCanvio
import controllers.menuComplet
import controllers.mostrarResultat
import java.util.Scanner

enum class BILLETE(val nombre: String, val precio: Double){
    SENCILLO(nombre = "Billet Senzill", precio = 2.40),
    CASUAL(nombre = "TCasual", precio = 11.35),
    USUAL(nombre = "TUsual", precio = 40.0),
    FAMILIAR(nombre = "TFamiliar", precio = 10.0),
    JOVEN(nombre = "TJOVE", precio = 80.0)
}
enum class ZONAS(val nombre: String, val multiplicador: Double){
    ZONA1(nombre = "Zona 1", multiplicador = 1.0),
    ZONA2(nombre = "Zona 2", multiplicador = 1.3125),
    ZONA3(nombre = "Zona 3", multiplicador = 1.8443)
}
fun main() {
    val scanner = Scanner(System.`in`)
    val historial = menuComplet(scanner)
    menuCanvio(historial, scanner)
    mostrarResultat(historial)

}