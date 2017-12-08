#include <iostream>
#include <set>
#include <sstream>
#include <string>

int solve() {
    int sum { 0 };
    for (std::string line; std::getline(std::cin, line);) {
        sum += [&] {
            std::istringstream ss {line};
            std::set<std::string> string_set;

            for (std::string word; ss >> word;) {
                if (!string_set.insert(word).second) {
                    return 0;
                }
            }
            return 1;
        }();
    }

    return sum;
}

int main() {
    std::cout << solve() << "\n";
}
