/*=================
  credit to  Gregory Loshakov
  Dribbble: https://dribbble.com/shots/5776408-Zara-Shop-Website
 */
  const addBtn = document.getElementById('add');
  const subBtn = document.getElementById('sub');
  const input = document.querySelector('.card__count');
  
  addBtn.addEventListener('click', () => {
    input.value = parseInt(input.value) + 1
  })
  
  subBtn.addEventListener('click', () => {
    input.value = parseInt(input.value) - 1
  })
  
  