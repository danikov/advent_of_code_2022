package first

import groovy.transform.Canonical

class AoC29 implements Runnable {
    def input = getClass().getResource("/day15_input.txt").readLines()

    void run() {
        def ranges = input.collect { new Sensor(it)}
                .findResults {it.getRangeAt(2000000)}

        def combined = combine(ranges)
        println combined*.size
        println combined*.size.sum()
    }

    @Canonical
    class Sensor {
        int x
        int y
        int radius

        Sensor(String inputLine) {
            def parts = inputLine =~ /Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)/
            Integer sX = parts[0][1] as Integer, sY = parts[0][2] as Integer,
                    bX = parts[0][3] as Integer, bY = parts[0][4] as Integer
            radius = Math.abs(sX - bX) + Math.abs(sY - bY)
            x = sX - radius
            y = sY - radius
        }

        def getRangeAt(int height) {
            if (height < y || height > y + radius + radius) {
                return null
            }
            def distanceX = radius - Math.abs((y + radius) - height)

            return new Range(x + radius - distanceX, x + radius + distanceX)
        }
    }

    @Canonical
    class Range {
        int start
        int end

        def canCombine(Range other) {
            return this.start <= other.end + 1 || other.start <= this.end + 1
        }

        def getSize() {
            end - start
        }
    }

    List<Range> combine(List<Range> ranges) {
        if (ranges == null) {
            return []
        }
        List<Range> sortedRanges = ranges.sort { it.start }
        List<Range> newRanges = []
        while (sortedRanges.size() > 0) {
            Set<Range> uncheckedRanges = [sortedRanges.pop()]
            List<Range> checkedRanges = []
            while (uncheckedRanges.size() > 0) {
                Range range = uncheckedRanges.first()
                uncheckedRanges.remove(range)
                uncheckedRanges.addAll(sortedRanges.findAll { range.canCombine(it) })
                sortedRanges.removeAll(uncheckedRanges)
                checkedRanges << range
            }
            newRanges << new Range(checkedRanges*.start.min(), checkedRanges*.end.max())
        }
        newRanges
    }

}

