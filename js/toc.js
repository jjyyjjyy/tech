var toctitle = document.getElementById('toctitle');
var path = window.location.pathname;
if (toctitle != null) {
  var oldtoc = toctitle.nextElementSibling;
  var newtoc = document.createElement('div');
  newtoc.setAttribute('id', 'tocbot');
  newtoc.setAttribute('class', 'js-toc desktop-toc');
  oldtoc.setAttribute('class', 'mobile-toc');
  oldtoc.parentNode.appendChild(newtoc);
  tocbot.init({
    contentSelector: '#content',
    headingSelector: 'h1, h2, h3, h4, h5',
    positionFixedSelector: 'body',
    fixedSidebarOffset: 60,
    smoothScroll: false,
    orderedList: false
  });
  if (!path.endsWith("index.html") && !path.endsWith("/")) {
    var link = document.createElement("a");
    if (document.getElementById('index-link')) {
      indexLinkElement = document.querySelector('#index-link > p > a');
      linkHref = indexLinkElement.getAttribute("href");
      link.setAttribute("href", linkHref);
    } else {
      link.setAttribute("href", "index.html");
    }
    link.innerHTML = "<span><i class=\"fa fa-chevron-left\" aria-hidden=\"true\"></i></span> Back to index";
    var block = document.createElement("div");
    block.setAttribute('class', 'back-action');
    block.appendChild(link);
    var toc = document.getElementById('toc');
    var next = document.getElementById('toctitle').nextElementSibling;
    toc.insertBefore(block, next);
  }
}
