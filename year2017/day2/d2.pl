#!/usr/bin/env perl

use strict;
use warnings;

my $sum_1 = 0;
my $sum_2 = 0;

foreach my $line (<STDIN>) {
  my @row = sort { $a <=> $b } split(" ", $line);
  $sum_1 += $row[-1] - $row[0];

  my $found = 0;
  while (@row and !$found) {
    my $a = pop @row;

    foreach my $b (@row) {
      if ($a % $b == 0) {
        $sum_2 += $a / $b;
        $found = 1;
      }
    }
  }
}

print "$sum_1\n";
print "$sum_2\n";
