#!/usr/bin/env perl

use strict;
use warnings;
use feature qw(say);

my %programs;
my %prog_weights;

foreach (<STDIN>) {
  my ($name, $weight, @names) = split ' ', s/[^\s\w]//gr;
  $programs{$name}++;
  if (@names) {
    for my $name (@names) {
      $programs{$name}++;
    }
  }
}

# get root
say grep { $programs{$_} eq 1 } keys %programs;
