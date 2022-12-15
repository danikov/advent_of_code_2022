package first

import groovy.transform.Canonical

class AoC28 implements Runnable {
    def input = getClass().getResource("/day14_input.txt").readLines()

    def AIR = '.'
    def ROCK = '#'
    def SAND = 'o'

    Coordinate min = new Coordinate(500, 0), max = new Coordinate(500, 0)
    Integer atRest = 0

    def structure = [].withEagerDefault {(0..1000).collect{ AIR } }

    void run() {
        input.each { line ->
            def path = line.split(' -> ').collect { item ->
                def itemParts = item.split(",")
                Integer x = Integer.valueOf(itemParts[0]), y = Integer.valueOf(itemParts[1])
                min.x = Math.min(x, min.x)
                min.y = Math.min(y, min.y)
                max.x = Math.max(x, max.x)
                max.y = Math.max(y, max.y)
                new Coordinate(x, y)
            }
            def previous = path[0]
            (1..<path.size()).each { i ->
                def current = path[i]
                (previous.x..current.x).each { x ->
                    (previous.y..current.y).each { y ->
                        structure[y][x] = ROCK
                    }
                }
                previous = current
            }
        }

        max.y = max.y + 2
        structure[max.y - 1] = (0..1000).collect { AIR }
        structure[max.y] = (0..1000).collect { ROCK }


        println structure.subList(min.y, max.y +1).collect {
            it?.subList(min.x, max.x + 1)?.join('') ?: '' }.join('\n')
        println ''

        placeSand()

        println structure.subList(min.y, max.y +1).collect {
            it?.join('') ?: '' }.join('\n')
        println atRest
    }

    Boolean placeSand() {
        Integer x = 500, y = 0
        def previous = []
        while (true) {
            if (structure[y + 1][x] == AIR) {
                previous << new Coordinate(x, y)
                y += 1
            } else if (structure[y + 1][x - 1] == AIR) {
                previous << new Coordinate(x, y)
                y += 1
                x -= 1
            } else if (structure[y + 1][x + 1] == AIR) {
                previous << new Coordinate(x, y)
                y += 1
                x += 1
            } else {
                structure[y][x] = SAND
                atRest += 1
                println atRest
                if(previous.size() == 0) {
                    return
                }
                x = previous.last().x
                y = previous.last().y
                previous.removeLast()
            }
        }
    }

    @Canonical
    class Coordinate {
        Integer x
        Integer y

        @Override
        String toString() {
            return "$x,$y"
        }
    }

}

