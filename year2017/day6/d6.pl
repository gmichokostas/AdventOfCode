#!/usr/bin/env perl

use strict;
use warnings;
use feature qw(say);

my @banks = split(" ", <STDIN>);

sub maxindex {
  my $arr_ref = shift;
  my $max_index = 0;
  for my $index (0.. scalar(@$arr_ref) - 1) {
    if (($arr_ref->[$index] > $arr_ref->[$max_index]) &&
        $arr_ref->[$index] != $arr_ref->[$max_index]) {
      $max_index = $index;
    }
  }

  return $max_index;
}

my $redistribution = 0;
my %seen_arrays = ("@banks" => [1, 0]);

while ($seen_arrays{"@banks"}->[0] <= 1) {
  my $idx = maxindex(\@banks);
  my $blocks = $banks[$idx];

  $banks[$idx] = 0;
  $redistribution++;

  while ($blocks != 0) {
    $blocks--;
    $idx++;
    $idx = 0 if ($idx > $#banks);
    $banks[$idx] += 1;
  }

  $seen_arrays{"@banks"}->[0] += 1;
  for my $key (keys %seen_arrays) {
    $seen_arrays{$key}->[1] += 1;
  }

}

say $redistribution;
say $seen_arrays{"@banks"}->[1]-1; # print circles
