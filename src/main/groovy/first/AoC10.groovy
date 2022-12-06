package first

class AoC10 implements Runnable {
    void run() {
        def input = getClass().getResource("day5_input.txt").text

        def inputSplit = input.split("\n\n")
        def rawStacks = inputSplit[0]
        def stackLines = rawStacks.readLines()
        stackLines.removeLast()
        stackLines = stackLines.reverse()
        def stackCount = 9
        def stacks = (1..stackCount).collect { i ->
            def stack = new Stack()
            def lineIndex = 1 + ((i-1) *4)
            stackLines.each { line ->
                def item = line[lineIndex]
                if (item != " ") {
                    stack.push(item)
                }
            }
            return stack
        }

        println stacks

        def rawInstructions = inputSplit[1]

        rawInstructions.readLines().each { instruction ->
            // move 2 from 1 to 6
            def parts = instruction.split(' ')
            def move = parts[1] as Integer
            def from = parts[3] as Integer
            def to = parts[5] as Integer

            def temp = new Stack()

            move.times {
                temp.push(stacks[from - 1].pop())
            }

            move.times {
                stacks[to - 1].push(temp.pop())
            }
        }

        println stacks
        println stacks.collect() {it.peek()}.join('')
    }
}
