#include <iostream>
#include <vector>
#include <string>
#include <sstream>


int main() {

    int sum_1 = 0;
    int sum_2 = 0;
    std::string line;
    std::vector< std::vector<int> > matrix;

    while ( getline( std::cin, line ) ) {
      std::istringstream in( line );
      matrix.push_back(
            std::vector<int>(
              std::istream_iterator<int>(in), std::istream_iterator<int>() )
            );
    }

    for (std::size_t i = 0; i < matrix.size(); i++) {
      std::sort(matrix[i].begin(), matrix[i].end());
      sum_1 += matrix[i].back() - matrix[i].front();

      bool found = false;
      while (!matrix[i].empty() && !found) {
        int last = matrix[i].back();
        matrix[i].pop_back();

        for (std::size_t j = 0; j < matrix[i].size(); j++) {
          if (last % matrix[i][j] == 0) {
            sum_2 += last / matrix[i][j];
            found = true;
          }
        }
      }
    }

    std::cout << sum_1 << "\n";
    std::cout << sum_2 << "\n";
}
