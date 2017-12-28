#!/usr/bin/env perl

use strict;
use warnings;
use feature qw(say);
use List::Util qw(max);

sub op {
    my $operation = "+";
    $operation = "-" if ($_[2] eq "dec");
    eval '$_[0]->{$_[1]} ' . $operation . '= $_[3]';
}

my %registers;
my $max = 0;

foreach (<STDIN>) {
    my @instruction = split(" ");

    my $reg       = $instruction[0];
    my $operation = $instruction[1];
    my $cond_reg  = $instruction[4];
    my $value     = $instruction[2];
    my $condition = $instruction[5];
    my $cond_val  = $instruction[6];

    $registers{$reg} = 0 if (!$registers{$reg});
    $registers{$cond_reg} = 0 if (!$registers{$cond_reg});

    if (eval('$registers{$cond_reg} ' . $condition . ' $cond_val')) {
        op(\%registers, $reg, $operation, $value);
    }
    $max = max values %registers if ((max values %registers) > $max);
}

say max values %registers;
say $max;
