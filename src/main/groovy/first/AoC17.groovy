package first

class AoC17 implements Runnable {
    Map<String, Integer> directories = ['/': 0]

    void run() {
        def input = getClass().getResource("/day9_input.txt").readLines()

        def head = [x: 0, y: 0]
        def tail = [x: 0, y: 0]
        def tailHistory = [:]

        input.each { line ->
            def parts = line.split(" ")
            (parts[1] as Integer).times {
                switch (parts[0]) {
                    case "R":
                        head.x += 1
                        break
                    case "L":
                        head.x -= 1
                        break
                    case "U":
                        head.y += 1
                        break
                    case "D":
                        head.y -= 1
                        break
                }

                if (head.x == tail.x) {
                    if (head.y - tail.y == 2) {
                        tail.y += 1
                    } else if (head.y - tail.y == -2) {
                        tail.y -= 1
                    }
                } else if (head.y == tail.y) {
                    if (head.x - tail.x == 2) {
                        tail.x += 1
                    } else if (head.x - tail.x == -2) {
                        tail.x -= 1
                    }
                } else if ((head.x - tail.x).abs() > 1 || (head.y - tail.y).abs() > 1) {
                    tail.x += head.x > tail.x ? 1 : -1
                    tail.y += head.y > tail.y ? 1 : -1
                }

                tailHistory["${tail.x}:${tail.y}"] = true
            }
        }

        println tailHistory.size()

    }

}

