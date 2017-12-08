#!/usr/bin/env perl

use strict;
use warnings;

my @jumps;
foreach (<STDIN>) {
  chomp;
  push @jumps, $_;
}

my $steps = 0;
my $index = 0;
my $part2 = 1;

while ( $index >= 0 && defined $jumps[$index]) {
  ++$steps;
  if ($part2 && $jumps[$index] >= 3) {
    $index += $jumps[$index]--;
  } else {
    $index += $jumps[$index]++;
  }
}

print "$steps\n";
