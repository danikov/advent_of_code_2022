package first

class AoC19 implements Runnable {
    void run() {
        def input = getClass().getResource("/day10_input.txt").readLines()

        Integer startCycle = 1
        Integer endCycle = 1
        Integer signal = 1
        List<Integer> interestedCycles = [20, 60, 100, 140, 180, 220]
        List<Integer> interestedValues = []

        input.each {
            def parts = it.split(" ")
            startCycle = endCycle

            if(parts[0] == "noop") {
                endCycle = startCycle + 1
            } else {
                endCycle = startCycle + 2
            }

            interestedCycles
                    .findAll{it >= startCycle && it < endCycle}
                    .each {interestedValues << it * signal}

            if(parts[0] == "addx") {
                signal += parts[1] as Integer
            }

            println "$it : $signal : $endCycle"
        }

        println interestedValues
        println interestedValues.sum()
    }

}

