package first

import groovy.transform.Canonical

class AoC30 implements Runnable {
    def input = getClass().getResource("/day15_input.txt").readLines()

    void run() {
        def sensors = input.collect { new Sensor(it) }
        BigInteger x = 0
        BigInteger y = (3187704..3187704).find{ y ->
            def combined = combine(sensors.findResults { it.getRangeAt(y) })
            if (combined.size() != 1 || combined[0].start > 0 || combined[0].end < 4000000) {
                x = combined[0].end + 1
                return true
            }
        }

        BigInteger freq = (x * 4000000) + y
        println "x: $x, y: $y, freq: $freq"
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
            return (this.start <= other.end + 1 && this.end + 1 >= other.start) ||
                    (other.start <= this.end + 1 && other.end + 1 >= this.start)
        }

        def getSize() {
            end - start
        }

        @Override
        String toString() {
            return "$start->$end"
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

