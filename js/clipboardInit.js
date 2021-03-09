function initClipBoard() {
  var pre = document.getElementsByTagName('pre');
  for (var i = 0; i < pre.length; i++) {
    var b = document.createElement('button');
    b.className = 'clipboard';
    b.textContent = 'Copy';
    if (pre[i].childNodes.length === 1 && pre[i].childNodes[0].nodeType === 3) {
      var div = document.createElement('div');
      div.textContent = pre[i].textContent;
      pre[i].textContent = '';
      pre[i].appendChild(div);
    }
    pre[i].appendChild(b);
  }
  const clipboard = new ClipboardJS('.clipboard', {
    target: function (b) {
      return b.parentNode.querySelector("code");
    }
  });
  clipboard.on('success', function (e) {
    console.log(e);
    e.clearSelection();
    e.trigger.textContent = 'Copied';
    setTimeout(function () {
      e.trigger.textContent = 'Copy';
    }, 1000);
  });
}

initClipBoard();
