package first

class AoC18 implements Runnable {
    void run() {
        def input = getClass().getResource("/day9_input.txt").readLines()

        def knots = [].withDefault {[x: 0, y: 0]}
        def tailHistory = [:]

        input.each { line ->
            def parts = line.split(" ")
            (parts[1] as Integer).times {
                switch (parts[0]) {
                    case "R":
                        knots[0].x += 1
                        break
                    case "L":
                        knots[0].x -= 1
                        break
                    case "U":
                        knots[0].y += 1
                        break
                    case "D":
                        knots[0].y -= 1
                        break
                }

                (1..9).each {
                    def prev = knots[it - 1]
                    def curr = knots[it]
                    if (prev.x == curr.x) {
                        if (prev.y - curr.y == 2) {
                            curr.y += 1
                        } else if (prev.y - curr.y == -2) {
                            curr.y -= 1
                        }
                    } else if (prev.y == curr.y) {
                        if (prev.x - curr.x == 2) {
                            curr.x += 1
                        } else if (prev.x - curr.x == -2) {
                            curr.x -= 1
                        }
                    } else if ((prev.x - curr.x).abs() > 1 || (prev.y - curr.y).abs() > 1) {
                        curr.x += prev.x > curr.x ? 1 : -1
                        curr.y += prev.y > curr.y ? 1 : -1
                    }
                }

                tailHistory["${knots[9].x}:${knots[9].y}"] = true
            }
        }

        println tailHistory.size()

    }

}

