#include <iostream>
#include <vector>


int main() {

    const std::vector<char> values(std::istream_iterator<char>{std::cin}, {});
    const std::size_t len      = values.size();
    const std::size_t offset_a = 1;
    const std::size_t offset_b = len / 2;

    int sum_1 = 0;
    int sum_2 = 0;

    for (std::size_t i = 0; i < len; i++) {

      if (values[i] == values[(i + offset_a) % len]) {
        sum_1 += values[i] - '0';
      }

      if (values[i] == values[(i + offset_b) % len]) {
        sum_2 += values[i] - '0';
      }

    }

    std::cout << sum_1 << '\n';
    std::cout << sum_2 << '\n';

}
