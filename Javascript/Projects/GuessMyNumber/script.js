'use strict';

const number = Math.trunc(Math.random() * 20) + 1;
let score = 20;
let highScore = 0;
let gameover = false;

const determine = function () {
  const guess = Number(document.querySelector('.guess').value);

  if (guess === number) {
    document.querySelector('.message').textContent = 'CORRECT😊';
    document.querySelector('.number').textContent = number;
    gameover = true;
  } else if (guess > number) {
    document.querySelector('.message').textContent = 'TOO HIGH📈';
    score--;
  } else if (guess < number && guess != 0) {
    document.querySelector('.message').textContent = 'TOO LOW 📉';
    score--;
  } else {
    document.querySelector('.message').textContent = 'NO NUMBER 😢';
  }
};

const calcScore = function () {
  if (score == 0) {
    document.querySelector('.message').textContent = 'GAME OVER! 😭😭';
    document.querySelector('.number').textContent = number;
    gameover = true;
  }
  if (score > highScore && gameover == true) {
    document.querySelector('.message').textContent = 'NEW HIGH SCORE! 🎇🎇';
    highScore = score;
    document.querySelector('.highscore').textContent = score;
    document.querySelector('.number').textContent = number;
  }
};

document.querySelector('.check').addEventListener('click', function () {
  if (!gameover) {
    determine();
    calcScore();
  }
  document.querySelector('.score').textContent = score;
});

document.querySelector('.again').addEventListener('click', function () {
  score = 20;
  gameover = false;
  document.querySelector('.score').textContent = score;
  document.querySelector('.message').textContent = 'Try guessing...';
  document.querySelector('.guess').value = '';
  document.querySelector('.number').textContent = '?';
});
