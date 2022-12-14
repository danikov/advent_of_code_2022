package first

class AoC20 implements Runnable {
    void run() {
        def input = getClass().getResource("/day10_input.txt").readLines()

        Integer cycle = 0
        Integer signal = 1

        input.each {
            def parts = it.split(" ")

            if (parts[0] == "noop") {
                print getPixel(signal, cycle)
                cycle += 1
            } else {
                print getPixel(signal, cycle)
                cycle += 1
                print getPixel(signal, cycle)
                cycle += 1
                signal += parts[1] as Integer
            }
        }

    }

    def getPixel(signal, cycle) {
        def modCycle = cycle % 40
        def newline = modCycle == 0 ? "\n" : ""
        if (signal - 1 <= modCycle && modCycle <= signal + 1) {
            return newline + "#"
        } else {
            return newline + "."
        }
    }

}

