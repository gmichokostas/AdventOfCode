tree = '#'

def positions_for_step(step, line_size) = 0.step(by: step).lazy.map { _1 % line_size }

# part one
input     = File.open('input.txt').readlines.map(&:chomp)
line_size = input.first.size
positions = positions_for_step(3, line_size)

input.zip(positions).select do |row, idx|
  row[idx] == tree
end.count

# part two
slopes = [[1, 1], [3, 1], [5, 1], [7, 1], [1, 2]]

result = slopes.map do |step, row|
  positions_for_step(step, line_size).then do |positions|
    input[(0..) % row].zip(positions).select do |r, i|
      r[i] == tree
    end.count
  end
end

p result.reduce(1, :*)
