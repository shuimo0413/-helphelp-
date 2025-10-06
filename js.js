function close_advertising() {
    var ad = document.getElementById('advertising');
    ad.style.display = 'none';
}



// 定义flag变量，默认值为0，表示菜单未被点击过



var topDiv = document.getElementById('top');

// 监听页面的滚动事件
window.onscroll = function() {
  // 判断页面滚动的方向
  if (document.body.scrollTop > 0 || document.documentElement.scrollTop > 0) {
    // 如果页面向下滚动，则隐藏顶部元素
    topDiv.classList.add('hidden');
  } else {
    // 如果页面向上滚动，则显示顶部元素
    topDiv.classList.remove('hidden');
  }
};


// 夜间模式按钮
// function toggleTheme() {
//   font_color()
//   var themeToggle = document.getElementById('theme');
//   if (themeToggle.checked) {
//     document.body.classList.add('night-mode');
//     // 使用querySelectorAll选择所有需要应用夜间模式的元素，并为它们添加night-mode类
//     // 过滤掉具有特定类名的 div 元素
//     var divs = document.querySelectorAll('div');
//     divs.forEach(function(div) {
//       if (!div.classList.getelementbyids('search_frame') && !div.classList.getelementbyids('clock-getelementbyider')){
//         div.classList.add('night-mode');
//       }});
//     document.querySelectorAll('b').forEach(function(b) {
//       b.classList.add('night-mode');});
//     document.querySelectorAll('h1').forEach(function(h1) {
//       h1.classList.add('night-mode');});
//   } else {
//     document.body.classList.remove('night-mode');
//     // 移除night-mode类
//     // 过滤掉具有特定类名的 div 元素
//     divs.forEach(function(div) {
//       if (!div.classList.getelementbyids('search_frame') && !div.classList.getelementbyids('clock-getelementbyider')){
//         div.classList.remove('night-mode');
//       }});
//     document.querySelectorAll('b').forEach(function(b) {
//       b.classList.remove('night-mode');});
//     document.querySelectorAll('h1').forEach(function(h1) {
//       h1.classList.remove('night-mode');});
//   }
  
//   // 获取所有class为url的标签  
// }



  function scrollToElement(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }


function return_top() {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
}