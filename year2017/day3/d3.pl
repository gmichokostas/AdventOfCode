#!/usr/bin/env perl

use strict;
use warnings;
use List::Util qw(min);

my $input = 289326;
my $max   = 1;
my $side  = 0;

while ($input > $max) {
  $side += 2;
  $max  += $side * 4;
}

my $last_max = ($side - 2 + 1) ** 2;
my $east_val = ($last_max + ($side / 2));
my $dist = abs($east_val - $input) % $side;

my $result = ($side / 2) + min($dist, $side - $dist);
print "$result\n";
