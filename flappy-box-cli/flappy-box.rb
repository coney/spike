#!/usr/bin/env ruby

require "curses"
require 'io/console'
include Curses


def init
  init_screen
  nl
  noecho
end


def draw pipes
  pipes.each do |pipe|
    1.upto(20) do |i|
      if i < pipe[:y] - 1 || i > pipe[:y] + 1
        setpos i, pipe[:x]
        addstr "■"
      end
    end
  end
end

bird = {
    x: 10,
    y: 10
}

def draw_bird bird
  setpos bird[:y], bird[:x]
  addstr "*"
end

def check_bird bird, pipe
  return bird[:x] == pipe[:x] && (bird[:y] > pipe[:y] + 1 || bird[:y] < pipe[:y] - 1)
end



pipes = [{
             x: 40,
             y: 10
         }, {
             x: 80,
             y: 10
         }]

# 加速度吧
a = 0.1

Thread.new {
  loop do
    bird[:y] += a
    a += 0.03
    sleep 0.1
  end
}

Thread.new {
init

  loop do
    clear
    draw pipes
    draw_bird bird
    refresh

    if check_bird bird, pipes.first
      close_screen
      p "hehe"
      sleep 0.5
      `say holy shit`
      exit
    end

    pipes.each do |pipe|
      pipe[:x] -= 1
    end
    if pipes.first[:x] <= 0
      pipes = [pipes.last, {x: 80, y: 2 + rand(15)}]
    end

    sleep 0.1
  end

}


loop do
  key = STDIN.getch
  if key == 'q'
    exit
  end
  if key == " "
    bird[:y] -= 1
    a = 0.1
    Thread.new {
      `afplay jump.wav`
    }
  end
end

