package minimal

class AoC9 implements Runnable {
    void run() {
        def inputSplit =  getClass().getResource("day5_input.txt").text.split("\n\n")

        def stacks = (1..9).collect { i ->
            def stack = new Stack()
            def lineIndex = 1 + ((i - 1) * 4)
            inputSplit[0].readLines()[0..-1].asReversed().each { line ->
                if (line[lineIndex] != " ") {
                    stack.push(line[lineIndex])
                }
            }
            stack
        }

        inputSplit[1].readLines().each { instruction ->
            def parts = instruction.split(' ')
            (parts[1] as Integer).times {
                stacks[(parts[5] as Integer) - 1].push(stacks[(parts[3] as Integer) - 1].pop())
            }
        }

        println stacks.collect() {it.peek()}.join('')
    }
}
