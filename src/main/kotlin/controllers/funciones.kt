package controllers

import org.example.controllers.BILLETE
import org.example.controllers.ZONAS
import java.text.DecimalFormat
import java.util.*

/**
 * Función para abrir el scanner
 * @author Ainara y Marc
 * @since 1.0
 */
fun abrirScanner(): Scanner{
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    return scanner
}

/**
 * Función para cerrar el scanner
 * @author Ainara y Marc
 * @since 1.0
 */
fun cerrarScanner(scanner: Scanner){
    scanner.close()
}

/**
 * Función para cerrar el programa con el código secreto
 * @author Ainara Valdivieso
 * @since 1.0
 */
fun cerrarProgramaConCodigo(num : Int){
    println("Codi secrete correcte, programa tancat")
}

/**
 * Programam para mostrar el menu de selección del billete
 * @author Ainara y Marc
 * @since 1.0
 * @param scanner para preguntar al usuario que tipo de billete desea (o el código secreto ;) )
 * @return el número de billete que desea el usuario
 */

fun menuBillet(scanner: Scanner): Int {
    println("Quin billet desitja adquirir")
    println(
        "1 - ${BILLETE.SENCILLO.nombre}\n" +
                "2 - ${BILLETE.CASUAL.nombre}\n" +
                "3 - ${BILLETE.USUAL.nombre}\n" +
                "4 - ${BILLETE.FAMILIAR.nombre}\n" +
                "5 - ${BILLETE.JOVEN.nombre}"
    )
    var numBillete = scanner.nextInt()
    if (numBillete == 4321) {
        cerrarProgramaConCodigo(numBillete)
        return -1
    }
    while (numBillete < 1 || numBillete > 5){
        println("Has d'introduir un numero Enter positiu valid (1-5)")
        numBillete = scanner.nextInt()
    }
    return numBillete
}

/**
 * Función para marcar el billete pedido a la máquina
 * @author Ainara y Marc
 * @since 1.0
 * @param numBillete número del billete escogido del menú
 * @return el billete escogido
 */
fun pedirBillete(numBillete : Int) : BILLETE{
    var billete = BILLETE.CASUAL

    when(numBillete){
        1 -> billete = BILLETE.SENCILLO
        2 -> billete = BILLETE.CASUAL
        3 -> billete = BILLETE.USUAL
        4 -> billete = BILLETE.FAMILIAR
        5 -> billete = BILLETE.JOVEN
    }
    return billete
}

/**
 * Función para pedir al usuario al zona que desea para la tarjeta
 * @author Ainara y Marc
 * @since 1.0
 * @param scanner para pedir al usuario el número de la zona
 * @return la zona que desea el usuarui con el múltiplicador de su precio base
 */
fun menuZona(scanner: Scanner): ZONAS{
    println("Quina zona desitja adquirir")
    println("1 - ${ZONAS.ZONA1}\n2 - ${ZONAS.ZONA2}\n3 - ${ZONAS.ZONA3}")
    var numZonas = scanner.nextInt()
    var zona = ZONAS.ZONA1
    while (numZonas < 1 || numZonas > 3){
        println("Has d'introduir un numero Enter positiu valid (1-3)")
        numZonas = scanner.nextInt()
    }
    when (numZonas){
        1 -> zona = ZONAS.ZONA1
        2 -> zona = ZONAS.ZONA2
        3 -> zona = ZONAS.ZONA3
    }
    return zona
}

/**
 * Función para calcular el precio del billete según la zona
 * @author Ainara y Marc
 * @since 1.0
 *
 * @param billete el billete escogido por el usuario
 * @param zona la zona escogida por el usuario con su multiplicador del precio
 * @return la opción escogida conjuntamente con el precio total
 */

fun billetePrecio(billete: BILLETE, zona: ZONAS): Pair<String, Double>{
    var precio = billete.precio * zona.multiplicador
    val opcion = "${billete.nombre} ${zona.nombre}"
    println("Ha escolli l'opcio:  ${billete.nombre}, ${zona.nombre}" )
    println("El preu del billet es ${DecimalFormat("0.00").format(precio)}€")
    return Pair(opcion, precio)
}

/**
 * Función para mostrar el importe a la hora de pagar
 * @author Ainara y Marc
 * @since 1.0
 *
 * @param historial el cojunto de las opciones escogidas con el precio total
 * @param scanner para preguntar al usuario el importe que va a añadir al precio a pagar
 */

fun menuCanvio(historial: List<Pair<String, Double>>, scanner: Scanner){
    var precioTotal = historial.sumOf { it.second }
    var dineroIntroducido = 0.0
    println("Has comprat ${historial.size}, ha de pagar ${DecimalFormat("0.00").format(precioTotal)}€")
    while (precioTotal>0) {
        println("Introdueix monedes o bitllets vàlids de EURO")
        val dinero = scanner.nextDouble()
        val calculoCanvio = calcularCanvio(dinero, precioTotal)
        dineroIntroducido += calculoCanvio.first
        precioTotal = calculoCanvio.second
        if (precioTotal>0) println("Haa introduït $dineroIntroducido€, li resta per pagar ${DecimalFormat("0.00").format(precioTotal)}€")
        else println("Haa introduït $dineroIntroducido€, li resta per pagar 0€")
    }
    println("Recull el seu billet i el seu canvi ${DecimalFormat("0.00").format(Math.abs(precioTotal))}€")
}

/**
 * Función para realizar el importe del pagamiento incluyendo el cambio si es
 * el caso de que añade más importe
 * @author Ainara y Marc
 * @since 1.0
 *
 * @param dinero el dinero añadido por el usuario
 * @param precioTotal el precio total de la compra del usuario
 * @return el dinero introducido por el usuario y el restante
 */
fun calcularCanvio(dinero: Double, precioTotal: Double): Pair<Double, Double>{
    var dineroIntroducido = 0.0
    var dineroRestante = precioTotal
    val dineroValido = arrayOf(50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05)
    if (dinero in dineroValido){
        dineroIntroducido = dinero
        dineroRestante-= dinero
    }
    return Pair(dineroIntroducido, dineroRestante)
}

/**
 * Funció para ejecutar to.do el menú al usuario en un sola función dentro del main
 * y preguntar si quiere comprar más de un billete (solo 3)
 * @author Ainara y Marc
 * @since 1.0
 *
 * @param scanner para añadirlo a las funciones en donde el usuario requiere de poner información
 * @return el precio del billete y la zona
 */

fun menuComplet(scanner: Scanner) : List<Pair<String, Double>>{
    var continuar = true
    var contador = 3
    val historial: MutableList<Pair<String, Double>> = mutableListOf()
    while (continuar && contador > 0) {
        repeat(20){
            print("-")
        }
        print("\n")
        val billete = menuBillet(scanner)
        if (billete == -1) return emptyList()
        val pedirBillete = pedirBillete(billete)
        val zona = menuZona(scanner)
        historial.add(billetePrecio(pedirBillete, zona))
        println("Vols seguir comprant [S][N]")
        scanner.nextLine()
        val preguntaContinuar = scanner.nextLine()
        if (preguntaContinuar.uppercase() == "N") continuar = false
        contador--
    }
    return historial
}

/**
 * Función para mostrar el tiquet final con toda la información de la compra
 * @author Ainara y Marc
 * @since 1.0
 *
 * @param historial el billete y la zona escogida de los billetes
 */

fun mostrarResultat(historial: List<Pair<String, Double>>){
    println("_____TIQUET_____")
    for (entrada in historial){
        println("${entrada.first} - Preu: ${entrada.second}€")
    }
    println("________________")
    println("Recull el teu tiquet.")
    println("Adeu.")
}