#!/usr/bin/env perl

use strict;
use warnings;
use feature qw(say);

my $score      = 0;
my $level      = 0;
my $ignore     = 0;
my $in_garbage = 0;
my $char_count = 0;

foreach my $string (<STDIN>) {
    foreach my $char (split //, $string) {

        if ($in_garbage && !$ignore && $char ne '!' && $char ne '>') {
            $char_count++;
        } elsif ($ignore) {
            $ignore = 0;
        } elsif ($char eq '<') {
            $in_garbage = 1;
        } elsif ($char eq '>') {
            $in_garbage = 0;
        } elsif ($char eq '!') {
            $ignore = 1;
        } elsif ($char eq '{' && !$in_garbage) {
            $level += 1;
        } elsif ($char eq '}' && !$in_garbage) {
            $score += $level--;
        }
    }
}

say $score;
say $char_count;
