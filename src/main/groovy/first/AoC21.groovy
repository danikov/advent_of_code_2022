package first

class AoC21 implements Runnable {
    void run() {
        def input = getClass().getResource("/day11_input.txt").readLines()

        def inspections = (0..7).collect{0}

        def items = input.findAll { it.startsWith("  Starting items: ") }
                *.substring("  Starting items: ".length())
                .collect { it.split(", ").collect { it as Integer } }

        def operation = [
                { Integer it -> it * 11 },
                { Integer it -> it * it },
                { Integer it -> it + 1 },
                { Integer it -> it + 2 },
                { Integer it -> it * 13 },
                { Integer it -> it + 5 },
                { Integer it -> it + 6 },
                { Integer it -> it + 7 },
        ]

        def choice = [
                { Integer it -> it % 2 == 0? 4 : 7 },
                { Integer it -> it % 7 == 0 ? 3 : 6 },
                { Integer it -> it % 13 == 0 ? 4 : 0 },
                { Integer it -> it % 3 == 0 ? 6 : 5 },
                { Integer it -> it % 19 == 0 ? 1 : 7 },
                { Integer it -> it % 17 == 0 ? 2 : 0 },
                { Integer it -> it % 11 == 0 ? 2 : 5 },
                { Integer it -> it % 5 == 0 ? 1 : 3 },

        ]
        20.times {
            (0..7).each { monkey ->
                items[monkey].each { item ->
                    Integer worry = operation[monkey].call(item) / 3
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

