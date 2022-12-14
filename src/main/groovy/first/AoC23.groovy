package first

import groovy.transform.Immutable

class AoC23 implements Runnable {
    def input = getClass().getResource("/day12_input.txt").readLines()

    Coordinate start
    Coordinate end
    Integer height = input.size()
    Integer width = input.first().length()

    Map<Coordinate, Character> nodes = [:]
    Map<Coordinate, Integer> distance = [:]
    List<Coordinate> pending = []

    void run() {
        input.eachWithIndex{ line, y ->
            line.getChars().eachWithIndex{ val, x ->
                if (val == ('S' as char)) {
                    start = new Coordinate(x, y)
                    nodes[start] = 'a' as char
                } else if (val == ('E' as char)) {
                    end = new Coordinate(x, y)
                    nodes[end] = 'z' as char
                } else {
                    nodes[new Coordinate(x, y)] = val
                }
            }
        }

        distance[start] = 0
        pending << start

        def currentDistance = 0

        while(pending.size() > 0) {
            currentDistance += 1
            Set<Coordinate> next = pending.collectMany { it.getAccessibleNeighbours() }.toSet()
            print "$currentDistance: $next -> "
            next.removeAll(distance.keySet())
            println next
            next.each {{
                distance[it] = currentDistance
                if(nodes[it] == ('E' as char)) {
                    end = it
                }
            } }
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

