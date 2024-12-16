package controllers

import org.example.controllers.BILLETE
import org.example.controllers.ZONAS
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FuncionesKtTest {

    @Test /* En esta canción no hace falta hacer test porque su único uso es abrir el scanner */
    fun abrirScanner() {
    }

    @Test /* En esta canción no hace falta hacer test porque su único uso es cerrar el scanner */
    fun cerrarScanner() {
    }

    @Test /* En esta función no hace falta hacer test porque lo único que hace es mostrar el mensaje en caso de añadir el código secreto */
    fun cerrarProgramaConCodigo() {
    }

    @Test /* En esta función no se pueden hacer test ya que le preguntamos al usuario el número de tarjeta que quiere*/
    fun menuBillet() {
    }

    @Test
    fun pedirBilleteSencillo() {
        val expected = pedirBillete(1)
        assertEquals(BILLETE.SENCILLO, expected)
    }

    @Test
    fun pedirBilleteJoven() {
        val expected = pedirBillete(5)
        assertEquals(BILLETE.JOVEN, expected)
    }

    @Test
    fun pedirBilleteUsual(){
        val expected = pedirBillete(3)
        assertEquals(BILLETE.USUAL, expected)
    }


    @Test /* En esta función no se pueden hacer test ya que le preguntamos al usuario el número de zona que quiere*/
    fun menuZona() {
    }

    @Test
    fun billetePrecioSencilloZona1() {
        val billete = BILLETE.SENCILLO
        val zona = ZONAS.ZONA1
        val resultado = billetePrecio(billete, zona)
        val expectedDescription = "Billet Senzill Zona 1"
        val expectedPrice = billete.precio * zona.multiplicador
        assertEquals(Pair(expectedDescription, expectedPrice), resultado)
    }

    @Test
    fun billetePrecioFamiliarZona2() {
        val billete = BILLETE.FAMILIAR
        val zona = ZONAS.ZONA2
        val resultado = billetePrecio(billete, zona)
        val expectedDescription = "TFamiliar Zona 2"
        val expectedPrice = billete.precio * zona.multiplicador
        assertEquals(Pair(expectedDescription, expectedPrice), resultado)
    }

    @Test
    fun billetePrecioJovenZona3() {
        val billete = BILLETE.JOVEN
        val zona = ZONAS.ZONA3
        val resultado = billetePrecio(billete, zona)
        val expectedDescription = "TJOVE Zona 3"
        val expectedPrice = billete.precio * zona.multiplicador
        assertEquals(Pair(expectedDescription, expectedPrice), resultado)
    }

    @Test /* En esta función no se pueden hacer test ya que le preguntamos al usuario el importe a ingresar el precio*/
    fun menuCanvio() {
    }

    @Test
    fun calcularCanvio25EurosConBilleteCorrecto() {
        val dinero = 10.0
        val precioTotal = 25.0
        val resultado = calcularCanvio(dinero, precioTotal)
        val dineroIntroducido = 10.0
        val dineroRestante = 15.0
        assertEquals(Pair(dineroIntroducido, dineroRestante), resultado)
    }

    @Test
    fun calcularCanvio25EurosConBilleteIncorrecto() {
        val dinero = 25.0
        val precioTotal = 25.0
        val resultado = calcularCanvio(dinero, precioTotal)
        val dineroIntroducido = 0.0
        val dineroRestante = 25.0
        assertEquals(Pair(dineroIntroducido, dineroRestante), resultado)
    }

    @Test
    fun calcularCanvio40EurosConBilleteCorrecto() {
        val dinero = 20.0
        val precioTotal = 40.0
        val resultado = calcularCanvio(dinero, precioTotal)
        val dineroIntroducido = 20.0
        val dineroRestante = 20.0
        assertEquals(Pair(dineroIntroducido, dineroRestante), resultado)
    }

    @Test /* En esta función no se pueden hacer test ya que le preguntamos al usuario el billete y la zona*/
    fun menuComplet() {
    }

    @Test /* En esta función no se pueden hacer test ya que no devuelve ningún resultado */
    fun mostrarResultat() {
    }
}