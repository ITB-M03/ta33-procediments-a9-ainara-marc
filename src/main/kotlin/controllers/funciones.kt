package controllers

import org.example.controllers.BILLETE
import org.example.controllers.ZONAS
import java.text.DecimalFormat
import java.util.*


fun abrirScanner(): Scanner{
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    return scanner
}
fun cerrarScanner(scanner: Scanner){
    scanner.close()
}



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
    if (numBillete == 4321)
    while (numBillete < 1 || numBillete > 5){
        println("Has d'introduir un numero Enter positiu valid (1-5)")
        numBillete = scanner.nextInt()
    }
    return numBillete
}

fun pedirBillete(numBillete : Int) : BILLETE{
    var billete = BILLETE.CASUAL

    when(numBillete){
        1 -> billete = BILLETE.SENCILLO
        2 -> billete = BILLETE.CASUAL
        3 -> billete = BILLETE.USUAL
        4 -> billete = BILLETE.FAMILIAR
        5 -> billete = BILLETE.JOVEN
        4321 -> cerrarProgramaConCodigo()
    }
    return billete
}

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

fun billetePrecio(billete: BILLETE, zona: ZONAS): Pair<String, Double>{
    var precio = billete.precio * zona.multiplicador
    val opcion = "${billete.nombre} ${zona.nombre}"
    println("Ha escolli l'opcio:  ${billete.nombre}, ${zona.nombre}" )
    println("El preu del billet es ${DecimalFormat("0.00").format(precio)}€")
    return Pair(opcion, precio)
}

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

fun sinCodigoMenuCompleto (num : Int, scanner : Scanner) : Boolean {
    var codigoSecreto = false
    if (menuBillet(scanner) == 4321) codigoSecreto = true
    return codigoSecreto
}

fun menuComplet(scanner: Scanner, codigo : Boolean) : List<Pair<String, Double>>{
    if (codigo == false) {

    var continuar = true
    var contador = 3
    val historial: MutableList<Pair<String, Double>> = mutableListOf()
    while (continuar && contador > 0) {
        repeat(20){
            print("-")
        }
        print("\n")
        val billete = menuBillet(scanner)
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

}

fun mostrarResultat(historial: List<Pair<String, Double>>){
    println("_____TIQUET_____")
        for (entrada in historial){
            println("${entrada.first} - Preu: ${entrada.second}€")
        }
    println("________________")
    println("Recull el teu tiquet.")
    println("Adeu.")
    }

