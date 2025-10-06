var topDiv = document.getElementById('top');
var pageHeight = topDiv.offsetHeight;

// 监听页面的滚动事件
window.onscroll = function() {
  // 判断页面滚动的方向
  if (window.pageYOffset > pageHeight) {
    // 如果页面向下滚动超过顶部元素的高度，则隐藏顶部元素
    topDiv.classList.add('hidden');
  } else {
    // 如果页面向上滚动，则显示顶部元素
    topDiv.classList.remove('hidden');
  }
};
