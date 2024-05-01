package Lista2ZJP

import kotlin.math.pow
/**
 *
 * @author Jakub Smoliński.
 * @param Wielomian(arrayOf())
 * @return stopień
 * @return wielomian :String
 * @return W(x) :Int
 * @return W(x) + Q(x)
 * @return W(x) - Q(x)
 * @return W(x) * Q(x)
 * @throws IllegalArgumentException jeśli lista współczynników jest pusta
 */

class Wielomian(wspolczynniki: Array<Int>) {
    private var wspolczynniki: Array<Int>

    init {
        if (wspolczynniki.isEmpty()) {
            throw IllegalArgumentException("Lista współczynników nie może być pusta.")
        }
        this.wspolczynniki = wspolczynniki.copyOf()
    }

    fun stopien(): Int {
        return wspolczynniki.size - 1
    }
    /**
     * Metoda zwracająca wielomian w postaci tekstowej
     *
     * @return wielomian: String
     */
    override fun toString(): String {
        val builder = StringBuilder("W(x) = ")
        for (i in wspolczynniki.indices) {
            val wspolczynnik = wspolczynniki[i]
            val potega = wspolczynniki.size - 1 - i
            if (wspolczynnik == 0) {
                continue
            }
            if (i > 0) {
                builder.append(" + ")
            }
            if (potega == 0) {
                builder.append(wspolczynnik)
            } else {
                builder.append("${wspolczynnik}x^$potega")
            }
        }
        return builder.toString()
    }
    /**
     * Metoda zwracająca wartość wielomianu dla danego x
     *
     * @return wynik W(x)
     */
    operator fun invoke(x: Int): Int {
        var result = 0
        for (i in wspolczynniki.indices) {
            val potega = wspolczynniki.size - 1 - i
            result += wspolczynniki[i] * x.toDouble().pow(potega).toInt()
        }
        return result
    }
    /**
     * Metoda zwracająca wynik dodawania/odejmowania/mnożenia wielomianu
     *
     * @return wynik działania
     */
    //Operatory dodawania/odejmowania/mnożenia zostały napisane przy pomocy kodu
    // https://kt.academy/article/kfde-operators , zostały przekształcone tak, aby działały na
    // wielomianach.
    operator fun plus(drugi: Wielomian): Wielomian {
        val max = maxOf(this.stopien(), drugi.stopien())
        val wynikdodawania = Array(max + 1) { 0 }
        for (i in 0..max) {
            val pierwszy = if (i < this.wspolczynniki.size) this.wspolczynniki[i] else 0
            val drugi = if (i < drugi.wspolczynniki.size) drugi.wspolczynniki[i] else 0
            wynikdodawania[i] = pierwszy + drugi
        }
        return Wielomian(wynikdodawania)
    }

    operator fun minus(drugi: Wielomian): Wielomian {
        val max = maxOf(this.stopien(), drugi.stopien())
        val wynikodejmowania = Array(max + 1) { 0 }
        for (i in 0..max) {
            val pierwszy = if (i < this.wspolczynniki.size) this.wspolczynniki[i] else 0
            val drugi = if (i < drugi.wspolczynniki.size) drugi.wspolczynniki[i] else 0
            wynikodejmowania[i] = pierwszy - drugi
        }
        return Wielomian(wynikodejmowania)
    }

    operator fun times(drugi: Wielomian): Wielomian {
        val max = this.stopien() + drugi.stopien()
        val wynikmnozenia = Array(max + 1) { 0 }
        for (i in this.wspolczynniki.indices) {
            for (j in drugi.wspolczynniki.indices) {
                wynikmnozenia[i + j] += this.wspolczynniki[i] * drugi.wspolczynniki[j]
            }
        }
        return Wielomian(wynikmnozenia)
    }

    operator fun plusAssign(drugi: Wielomian) {
        val result = this + drugi
        this.wspolczynniki = result.wspolczynniki.copyOf()
    }

    operator fun minusAssign(drugi: Wielomian) {
        val result = this - drugi
        this.wspolczynniki = result.wspolczynniki.copyOf()
    }

    operator fun timesAssign(drugi: Wielomian) {
        val result = this * drugi
        this.wspolczynniki = result.wspolczynniki.copyOf()
    }
}


fun main(){
    val wielomian1 = Wielomian(arrayOf(3, 4, 2,8))
    val wielomian2 = Wielomian(arrayOf(7, 10, 4, 3))
    val builderW1 = wielomian1.toString()
    val builderW2 = wielomian2.toString()
    println("Wielomian 1: $builderW1")
    println("Wielomian 2: $builderW2")

    val w = Wielomian(arrayOf(1, 2, 3))
    val builderw = w.toString()
    val x = 2
    val wynik = w(x)
    println("Wartość wielomianu $builderw dla x=$x wynosi $wynik")

    val wynikDodawania = wielomian1 + wielomian2
    val wynikOdejmowania = wielomian1 - wielomian2
    val wynikMnozenia = wielomian1 * wielomian2
    println("Wynik dodawania: $wynikDodawania")
    println("Wynik odejmowania: $wynikOdejmowania")
    println("Wynik mnożenia: $wynikMnozenia")
}
