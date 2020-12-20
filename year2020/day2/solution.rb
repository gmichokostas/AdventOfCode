# part 1
def part_1_rules(input)
  appearances = input[:pass].count(input[:char])
  lowest, highest = input[:policy]

  (lowest..highest).include? appearances
end

# NOTE: (pass[first] == char) ^ (pass[sec] == char) found on internet

def part_2_rules(input)
  first, sec = input[:policy].map { _1 - 1 }
  pass = input[:pass]
  char = input[:char]

  (pass[first] == char && pass[sec] != char) ||
    (pass[sec] == char && pass[first] != char)
end

def input(str)
  str.split.then do |policy, char, pass|
    {
      policy: policy.split('-').map(&:to_i),
      char:   char.gsub(/:/, ''),
      pass:   pass
    }
  end
end

valid_passwords = 0

File.readlines('year2020/day2/input.txt').each do |line|
  valid_passwords += 1 if input(line).then(&method(:part_2_rules))
end

p valid_passwords
