#!/usr/bin/env perl

use strict;
use warnings;
use List::MoreUtils qw(uniq);

my $counter_1 = 0;
my $counter_2 = 0;

sub sort_string {
  my ($string) = @_;

  return join '', sort split(//, $string);
}

foreach my $line (<STDIN>) {
  my @line = split " ", $line;
  my @uniq_line = uniq @line;

  my @splitted_line = split(" ", $line);
  my @sorted_line = map {sort_string $_} @splitted_line;


  my @uniq_sorted_line = uniq @sorted_line;

  $counter_1++ if $#line == $#uniq_line;
  $counter_2++ if $#sorted_line == $#uniq_sorted_line;
}

print "$counter_1\n";
print "$counter_2\n";
