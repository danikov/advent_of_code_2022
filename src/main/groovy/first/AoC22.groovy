package first

class AoC22 implements Runnable {
    void run() {
        def input = getClass().getResource("/day11_input.txt").readLines()

        def inspections = (0..7).collect { 0 as BigInteger }

        def items = input.findAll { it.startsWith("  Starting items: ") }
                *.substring("  Starting items: ".length())
                .collect { it.split(", ").collect { it as Long } }

        def commonDivisor = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19

        def operation = [
                { Long it -> it * 11 },
                { Long it -> it * it },
                { Long it -> it + 1 },
                { Long it -> it + 2 },
                { Long it -> it * 13 },
                { Long it -> it + 5 },
                { Long it -> it + 6 },
                { Long it -> it + 7 },
        ]

        def choice = [
                { Long it -> it % 2 == 0 ? 4 : 7 },
                { Long it -> it % 7 == 0 ? 3 : 6 },
                { Long it -> it % 13 == 0 ? 4 : 0 },
                { Long it -> it % 3 == 0 ? 6 : 5 },
                { Long it -> it % 19 == 0 ? 1 : 7 },
                { Long it -> it % 17 == 0 ? 2 : 0 },
                { Long it -> it % 11 == 0 ? 2 : 5 },
                { Long it -> it % 5 == 0 ? 1 : 3 },

        ]
        10000.times {
            println "Round $it"
            (0..7).each { monkey ->
                items[monkey].each { item ->
                    Long worry = operation[monkey].call(item) % commonDivisor
                    items[choice[monkey].call(worry)] << worry
                    inspections[monkey] += 1
                }
                items[monkey].clear()
            }
        }

        println inspections
        println inspections.sort()[-1] * inspections.sort()[-2]

    }
}

