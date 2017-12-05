#!/usr/bin/env perl

use strict;
use warnings;

my @input = split(//, <STDIN>);
pop(@input);

my $len = $#input;
my $sum_1 = 0;
my $sum_2 = 0;
my @list = (@input, @input);

for my $n (0..$len) {
  my $offset = $n + int(($len+1) / 2);

  if ($list[$n] == $list[$n+1]) {
    $sum_1 += $list[$n];
  }

  if ($list[$n] == $list[$offset]) {
    $sum_2 += $list[$n];
  }
}


print "$sum_1\n";
print "$sum_2\n";
