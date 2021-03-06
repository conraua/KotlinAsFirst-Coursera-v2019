@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var s = 0.0
    v.forEach { s += (it * it) }
    return sqrt(s)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = when {
    list.count() != 0 -> list.sum() / list.count()
    else -> 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in 0 until list.count()) {
        list[i] -= m
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0 until a.count()) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var s = 0
    var i = 0.0
    p.forEach {
        s += it * Math.pow(x.toDouble(), i).toInt()
        i += 1.0
    }
    return s
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    val list1 = list.toList()
    for (i in 1 until list.count()) {
        list[i] = 0
        for (j in 0..i) {
            list[i] += list1[j]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var list = listOf<Int>()
    var num = n
    while (num > 1 && num % 2 == 0) {
        num /= 2
        list += 2
    }
    var i = 3
    while (num > 1 && i <= kotlin.math.ceil(sqrt(n.toDouble()))) {
        while (num % i == 0) {
            num /= i
            list += i
        }
        i += 2
    }
    if (num != 1) {
        list += num
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = listOf<Int>()
    var num = n
    do {
        list += (num % base)
        num /= base
    } while (num / base != 0)
    if (num != 0) {
        list += (num % base)
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var list = convert(n, base)
    var str: String = ""
    list.forEach { str += if (it > 9) (it + 87).toChar() else (it + 48).toChar() }
    return str
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var num = 0
    var i = 1.0
    digits.forEach { num += it * base.toDouble().pow(digits.count() - i).toInt(); i++ }
    return num
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var list = listOf<Int>()
    str.forEach { list += if (it.toInt() >= 97) it.toInt() - 87 else it.toInt() - 48 }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var str: String = ""
    var num = n
    var n1 = n
    var digitCount = n.toString().length
    while (digitCount > 0 && num > 0) {
        for (i in 1 until digitCount) {
            num /= 10
        }
        when (digitCount) {
            4 -> {
                str += when (num) {
                    3 -> "MMM"
                    2 -> "MM"
                    else -> "M"
                }
            }
            3 -> {
                str += when (num) {
                    9 -> "CM"
                    8 -> "DCCC"
                    7 -> "DCC"
                    6 -> "DC"
                    5 -> "D"
                    4 -> "CD"
                    3 -> "CCC"
                    2 -> "CC"
                    else -> "C"
                }
            }
            2 -> {
                str += when (num) {
                    9 -> "XC"
                    8 -> "LXXX"
                    7 -> "LXX"
                    6 -> "LX"
                    5 -> "L"
                    4 -> "XL"
                    3 -> "XXX"
                    2 -> "XX"
                    else -> "X"
                }
            }
            1 -> {
                str += when (num) {
                    9 -> "IX"
                    8 -> "VIII"
                    7 -> "VII"
                    6 -> "VI"
                    5 -> "V"
                    4 -> "IV"
                    3 -> "III"
                    2 -> "II"
                    else -> "I"
                }
            }
        }
        n1 -= num * 10.0.pow(digitCount - 1).toInt()
        digitCount = n1.toString().length
        num = n1
    }
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var digitsList = n.toString().toList().reversed()
    while (digitsList.count() < 6) {
        digitsList += '0'
    }
    digitsList = digitsList.reversed()
    var str: String = ""
    if (digitsList[0] != '0' || digitsList[1] != '0' || digitsList[2] != '0') {
        str += when (digitsList[0]) {
            '9' -> "девятьсот "
            '8' -> "восемьсот "
            '7' -> "семьсот "
            '6' -> "шестьсот "
            '5' -> "пятьсот "
            '4' -> "четыреста "
            '3' -> "триста "
            '2' -> "двести "
            '1' -> "сто "
            else -> ""
        }
        if (digitsList[1] != '1' || digitsList[2] == '0') {
            str += when (digitsList[1]) {
                '9' -> "девяносто "
                '8' -> "восемьдесят "
                '7' -> "семьдесят "
                '6' -> "шестьдесят "
                '5' -> "пятьдесят "
                '4' -> "сорок "
                '3' -> "тридцать "
                '2' -> "двадцать "
                '1' -> "десять "
                else -> ""
            }
            str += when (digitsList[2]) {
                '9' -> "девять тысяч"
                '8' -> "восемь тысяч"
                '7' -> "семь тысяч"
                '6' -> "шесть тысяч"
                '5' -> "пять тысяч"
                '4' -> "четыре тысячи"
                '3' -> "три тысячи"
                '2' -> "две тысячи"
                '1' -> "одна тысяча"
                else -> "тысяч"
            }
        } else {
            str += when (digitsList[2]) {
                '9' -> "девятнадцать тысяч"
                '8' -> "восемнадцать тысяч"
                '7' -> "семнадцать тысяч"
                '6' -> "шестнадцать тысяч"
                '5' -> "пятнадцать тысяч"
                '4' -> "четырнадцать тысяч"
                '3' -> "тринадцать тысяч"
                '2' -> "двенадцать тысяч"
                '1' -> "одиннадцать тысяч"
                else -> ""
            }
        }
    }

    if ((digitsList[0] != '0' || digitsList[1] != '0' || digitsList[2] != '0') &&
        (digitsList[3] != '0' || digitsList[4] != '0' || digitsList[5] != '0')) {
        str += " "
    }

    str += when (digitsList[3]) {
        '9' -> "девятьсот "
        '8' -> "восемьсот "
        '7' -> "семьсот "
        '6' -> "шестьсот "
        '5' -> "пятьсот "
        '4' -> "четыреста "
        '3' -> "триста "
        '2' -> "двести "
        '1' -> "сто "
        else -> ""
    }
    if (digitsList[4] != '1' || digitsList[5] == '0') {
        str += when (digitsList[4]) {
            '9' -> "девяносто "
            '8' -> "восемьдесят "
            '7' -> "семьдесят "
            '6' -> "шестьдесят "
            '5' -> "пятьдесят "
            '4' -> "сорок "
            '3' -> "тридцать "
            '2' -> "двадцать "
            '1' -> "десять "
            else -> ""
        }
        str += when (digitsList[5]) {
            '9' -> "девять"
            '8' -> "восемь"
            '7' -> "семь"
            '6' -> "шесть"
            '5' -> "пять"
            '4' -> "четыре"
            '3' -> "три"
            '2' -> "два"
            '1' -> "один"
            else -> ""
        }
    } else {
        str += when (digitsList[5]) {
            '9' -> "девятнадцать"
            '8' -> "восемнадцать"
            '7' -> "семнадцать"
            '6' -> "шестнадцать"
            '5' -> "пятнадцать"
            '4' -> "четырнадцать"
            '3' -> "тринадцать"
            '2' -> "двенадцать"
            '1' -> "одиннадцать"
            else -> ""
        }
    }
    return str.trim()
}