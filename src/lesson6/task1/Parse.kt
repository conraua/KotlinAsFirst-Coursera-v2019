@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.*
import kotlin.IllegalStateException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val date = str.split(" ")
    if (date.count() != 3) {
        return ""
    }
    val d = date[0]
    val m = date[1]
    val y = date[2]
    var d1 = 0
    var m1 = -1
    var y1 = 0
    try {
        d1 = date[0].toInt()
        y1 = date[2].toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    when (m) {
        "января" -> m1 = 1
        "февраля" -> m1 = 2
        "марта" -> m1 = 3
        "апреля" -> m1 = 4
        "мая" -> m1 = 5
        "июня" -> m1 = 6
        "июля" -> m1 = 7
        "августа" -> m1 = 8
        "сентября" -> m1 = 9
        "октября" -> m1 = 10
        "ноября" -> m1 = 11
        "декабря" -> m1 = 12
        else -> return ""
    }
    when (m1) {
        1, 3, 5, 7, 8, 10, 12 -> if (d1 !in 1..31) return ""
        else -> if (d1 !in 1..30) return ""
    }
    if (m1 == 2) {
        if ((y1 % 400 == 0) || (y1 % 4 == 0) && (y1 % 100 != 0)) {
            if (d1 !in 1..29) {
                return ""
            }
        } else if (d1 !in 1..28) {
            return ""
        }
    }
    return String.format("%02d.%02d.%d", d1, m1, y1)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val date = digital.split(".")
    if (date.count() != 3) {
        return ""
    }
    val d = date[0]
    val m = date[1]
    val y = date[2]
    var d1 = 0
    var m1 = ""
    var y1 = 0
    try {
        d1 = date[0].toInt()
        y1 = date[2].toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    when (m) {
        "01" -> m1 = "января"
        "02" -> m1 = "февраля"
        "03" -> m1 = "марта"
        "04" -> m1 = "апреля"
        "05" -> m1 = "мая"
        "06" -> m1 = "июня"
        "07" -> m1 = "июля"
        "08" -> m1 = "августа"
        "09" -> m1 = "сентября"
        "10" -> m1 = "октября"
        "11" -> m1 = "ноября"
        "12" -> m1 = "декабря"
        else -> return ""
    }
    when (m.toInt()) {
        1, 3, 5, 7, 8, 10, 12 -> if (d1 !in 1..31) return ""
        else -> if (d1 !in 1..30) return ""
    }
    if (m.toInt() == 2) {
        if ((y1 % 400 == 0) || (y1 % 4 == 0) && (y1 % 100 != 0)) {
            if (d1 !in 1..29) {
                return ""
            }
        } else if (d1 !in 1..28) {
            return ""
        }
    }
    return String.format("%d %s %d", d1, m1, y1)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val isPlus = (phone[0] == '+')
    var list = phone.split(" ")
    var str = ""
    var li = mutableListOf<Int>()
    var ri = mutableListOf<Int>()
    for (i in 0 until list.count()) {
        if (list[i].contains("(")) {
            li.add(i)
        }
        if (list[i].contains(")")) {
            ri.add(i)
        }
    }
    if (li.count() != ri.count()) {
        return ""
    }
    for (i in 0 until li.count()) {
        for (j in 0 until li.count())
            if (li[i] > ri[j]) {
                return ""
            }
    }
    for (i in 0 until list.count()) {
        if ((list[i].contains("+")) && i != 0) {
            return ""
        }
        if (list[i].split(" ").joinToString("") == "()") {
            return ""
        }
    }
    str = list.joinToString("")
    list = str.split("(")
    str = list.joinToString("")
    list = str.split(")")
    str = list.joinToString("")
    list = str.split("-")
    str = list.joinToString("")
    list = str.split("+")
    for (i in list) {
        if (i != "") {
            try {
                i.toBigInteger()
            } catch (e: NumberFormatException) {
                return ""
            }
        }
    }
    str = ""
    if (isPlus) {
        str = "+"
    }
    return str + list.joinToString("")
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var list = jumps.split(" ")
    var max = -1
    for (i in list) {
        try {
            val d = i.toInt()
            if (d > max) {
                max = d
            }
        } catch (e: NumberFormatException) {
            if (!((i == "-") || (i == "%"))) {
                return -1
            }
        }
    }
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var list = jumps.split(" ")
    var max = -1
    var lastHeight = 0
    var map = mutableMapOf<Int, String>()
    for (i in 0 until list.count()) {
        if (i % 2 == 0) {
            try {
                lastHeight = list[i].toInt()
                map[lastHeight] = ""
            } catch (e: NumberFormatException) {
                return -1
            }
        } else {
            for (sym in list[i]) {
                if (!((sym == '+') || (sym == '%') || (sym == '-'))) {
                    return -1
                } else {
                    map[lastHeight] = list[i]
                }
            }
        }
    }
    for ((h, tries) in map) {
        for (sym in tries) {
            if ((sym == '+') && (h > max)) {
                max = h
            }
        }
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val list = expression.split(" ")
    val listNumbers = mutableListOf<Int>()
    val listSigns = mutableListOf<String>()
    var lastSign = ""
    var res = 0
    if (list[0] == "") {
        throw IllegalArgumentException()
    }
    if (list[0][0] == '+') {
        throw IllegalArgumentException()
    }
    for (i in 0 until list.count()) {
        if (i % 2 == 0) {
            try {
                val number = list[i].toInt()
                if ((number < 0) && (lastSign == "-")) {
                    throw IllegalArgumentException()
                }
                listNumbers.add(number)
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException()
            }
        } else {
            if (!((list[i] == "+") || (list[i] == "-"))) {
                throw IllegalArgumentException()
            } else {
                lastSign = list[i]
                listSigns.add(lastSign)
            }
        }
    }
    res = listNumbers[0]
    for (i in 1 until listNumbers.count()) {
        try {
            if (listSigns[i - 1] == "+") {
                res += listNumbers[i]
            } else {
                res -= listNumbers[i]
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            throw IllegalArgumentException()
        }
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var list = str.toLowerCase().split(" ")
    for (i in 0 until (list.count() - 1)) {
        if (list[i] == list[i + 1]) {
            return str.toLowerCase().indexOf(list[i] + " " + list[i])
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val list = description.split("; ")
    val map = mutableMapOf<String, Double>()
    var maxPrice = -1.0
    var maxName = ""
    for (i in list) {
        try {
            var item = i.split(" ")
            if (item.count() == 2) {
                map[item[0]] = item[1].toDouble()
            } else {
                return ""
            }
        } catch (e: NumberFormatException) {
            return ""
        }
    }
    for ((name, price) in map) {
        if (price > maxPrice) {
            maxPrice = price
            maxName = name
        }
    }
    return maxName
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val list = roman.split(" ")
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    var sum = 0
    if (list.count() != 1) {
        return -1
    }
    for (sym in list[0]) {
        when (sym) {
            'M' -> list1.add(1000)
            'D' -> list1.add(500)
            'C' -> list1.add(100)
            'L' -> list1.add(50)
            'X' -> list1.add(10)
            'V' -> list1.add(5)
            'I' -> list1.add(1)
            else -> return -1
        }
    }
    list1.add(0)
    list1.add(0)
    var i = 0
    while (i < (list1.count() - 2)) {
        sum = list1[i]
        while ((i < (list1.count() - 2)) && (list1[i] == list1[i + 1])) {
            sum += list1[i]
            i++
        }
        if (list1[i + 1] > sum) {
            sum *= -1
        }
        list2.add(sum)
        sum = 0
        i++
    }
    return list2.sum()
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val input = commands.split("").toMutableList()
    val braces = mutableListOf<Pair<String, Pair<Int, Int>>>()
    val conveyor = mutableListOf<Int>()
    val stack = Stack<Pair<String, Pair<Int, Int>>>()
    var pos = kotlin.math.floor(cells.toDouble() / 2.0).toInt()
    var iterationCounter = 0
    var commandCounter = 0
    var inputCounter = 0
    var uselessCounter = 0
    var openBraceStack = Stack<Pair<Int, Int>>()
    input.removeIf { String -> String == "" }
    for (command in input) {
        if (!((command == ">") || (command == "<") || (command == "+") ||
                    (command == "-") || (command == "[") || (command == "]") || (command == " "))
        ) {
            throw IllegalArgumentException()
        }
        if (command == "[") {
            stack.push(Pair("[", Pair(commandCounter, -1)))
            braces.add(Pair("[", Pair(commandCounter, -1)))
            openBraceStack.push(Pair(braces.count() - 1, commandCounter))
        } else if (command == "]") {
            if (stack.isEmpty()) {
                throw IllegalArgumentException()
            }
            if (stack.peek().first == "[") {
                stack.pop()
                val lastPos = openBraceStack.pop()
                braces[lastPos.first] = Pair("[", Pair(lastPos.second, commandCounter))
                braces.add(Pair("]", Pair(commandCounter, lastPos.second)))
            } else {
                throw IllegalArgumentException()
            }
        }
        commandCounter++
    }
    if (stack.isNotEmpty()) {
        throw IllegalArgumentException()
    }
    for (i in 0 until cells) {
        conveyor.add(0)
    }
    while ((iterationCounter < limit) && (inputCounter < input.count())) {
        when (input[inputCounter]) {
            ">" -> pos++
            "<" -> pos--
            "+" -> conveyor[pos]++
            "-" -> conveyor[pos]--
            "[" -> if (conveyor[pos] == 0) {
                for ((brace, pos) in braces) {
                    if (pos.first == inputCounter) {
                        inputCounter = pos.second
                        break
                    }
                }
            }
            "]" -> if (conveyor[pos] != 0) {
                for ((brace, pos) in braces) {
                    if (pos.first == inputCounter) {
                        inputCounter = pos.second
                        break
                    }
                }
            }
            " " -> uselessCounter++
            else -> throw IllegalArgumentException()
        }
        inputCounter++
        iterationCounter++
        if ((pos >= conveyor.count()) || (pos < 0)) {
            throw IllegalStateException()
        }
    }
    return conveyor
}
