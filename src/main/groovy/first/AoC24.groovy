package first

import groovy.transform.Immutable

class AoC24 implements Runnable {
    def input = getClass().getResource("/day12_input.txt").readLines()

    List<Coordinate> starts = []
    Coordinate end
    Integer height = input.size()
    Integer width = input.first().length()

    Map<Coordinate, Character> nodes = [:]
    Map<Coordinate, Integer> distance = [:]
    List<Coordinate> pending = []

    void run() {
        input.eachWithIndex{ line, y ->
            line.getChars().eachWithIndex{ val, x ->
                if (val == ('S' as char) || val == ('a' as char)) {
                    def start = new Coordinate(x, y)
                    nodes[start] = 'a' as char
                    starts << start
                } else if (val == ('E' as char)) {
                    end = new Coordinate(x, y)
                    nodes[end] = 'z' as char
                } else {
                    nodes[new Coordinate(x, y)] = val
                }
            }
        }

        starts.each {distance[it] = 0}
        pending.addAll(starts)

        def currentDistance = 0

        while(pending.size() > 0) {
            currentDistance += 1
            Set<Coordinate> next = []
            Set<Coordinate> candidates = pending.collectMany { it.getAccessibleNeighbours() }.toSet()
            candidates.each {
                if(!distance.containsKey(it) || distance[it] > currentDistance) {
                    distance[it] = currentDistance
                    next << it
                }
            }
            pending.clear()
            pending.addAll(next)
        }

        println distance[end]
    }

    def canAccess(Coordinate origin, Coordinate target) {
        nodes[target] - nodes[origin] <= 1
    }

    @Immutable
    class Coordinate {
        Integer x
        Integer y

        List<Coordinate> getAccessibleNeighbours() {
            def neighbours = []
            if (x > 0) {
                neighbours << new Coordinate(x - 1, y)
            }
            if (x < width - 1) {
                neighbours << new Coordinate(x + 1, y)
            }
            if (y > 0) {
                neighbours << new Coordinate(x, y - 1)
            }
            if (y < height - 1) {
                neighbours << new Coordinate(x, y + 1)
            }
            neighbours.findAll {canAccess(this, it)}
        }

        @Override
        String toString() {
            return "$x,$y"
        }
    }
}

