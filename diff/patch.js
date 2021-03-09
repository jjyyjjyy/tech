export const patchVNode = (elm, oldVNode, newVNode) => {
  if (sameNode(oldVNode, newVNode)) {
    updateAttrs(elm, oldVNode);
    updateChildren(elm, oldVNode.children, newVNode.children)
  } else {
    elm.appendChild(newVNode);
    elm.removeChild(oldVNode);
  }
};

const setAttr = (el, key, value) => {
  switch (key) {
    case 'style':
      Object.keys(value).forEach(k => el.style[k] = value[k]);
      break;
    case 'value':
      const tag = (el.tag || '').toLowerCase();
      if (tag === 'input' || tag === 'textarea') {
        el.value = value;
      } else {
        el.setAttribute(key, value);
      }
      break;
    default:
      el.setAttribute(key, value);
  }
};

const updateAttrs = (parentElm, oldVNodeAttrs = {}, newVNodeAttrs = {}) => {
  for (let oldKey of oldVNodeAttrs) {
    if (newVNodeAttrs[oldKey]) {
      oldVNodeAttrs[oldKey] !== newVNodeAttrs[oldKey] && setAttr(parentElm, oldKey, newVNodeAttrs[oldKey]);
    } else {
      parentElm.removeAttribute(oldKey)
    }
  }
  for (let newKey of newVNodeAttrs) {
    !oldVNodeAttrs[newKey] && setAttr(parentElm, newKey, newVNodeAttrs[newKey]);
  }
};

const updateChildren = (parentElm, oldVNodeChildren = [], newVNodeChildren = []) => {
  let oldVNodeStartIndex, newVNodeStartIndex = 0;
  let oldVNodeEndIndex = oldVNodeChildren.length - 1, newVNodeEndIndex = newVNodeChildren.length - 1;
  let oldStartVNode = oldVNodeChildren[oldVNodeStartIndex], oldEndVNode = oldVNodeChildren[oldVNodeEndIndex];
  let newStartVNode = newVNodeChildren[newVNodeStartIndex], newEndVNode = newVNodeChildren[newVNodeEndIndex];
  const oldVNodeIndexKeyMap = mapIndexToKey(oldVNodeChildren, oldVNodeStartIndex, oldVNodeEndIndex);

  while (oldVNodeStartIndex <= oldVNodeEndIndex && newVNodeStartIndex <= newVNodeEndIndex) {
    if (!oldStartVNode) {
      oldStartVNode = oldVNodeChildren[++oldVNodeStartIndex]
    } else if (!oldEndVNode) {
      oldEndVNode = oldVNodeChildren[--oldVNodeEndIndex];
    } else if (sameNode(oldStartVNode, newStartVNode)) { // 旧首和新首相同
      patchVNode(oldStartVNode.elm, oldStartVNode, newStartVNode);
      oldStartVNode = oldVNodeChildren[++oldVNodeStartIndex];
      newStartVNode = newVNodeChildren[++newVNodeStartIndex];
    } else if (sameNode(oldEndVNode, newEndVNode)) { // 旧尾和新尾相同
      patchVNode(oldEndVNode.elm, oldEndVNode, newEndVNode);
      oldEndVNode = oldVNodeChildren[--oldVNodeEndIndex];
      newEndVNode = newVNodeChildren[--newVNodeEndIndex];
    } else if (sameNode(oldStartVNode, newEndVNode)) { // 旧首和新尾相同, 将旧首移动到旧尾的后面
      parentElm.insertBefore(oldEndVNode.elm.nextSibling, oldStartVNode.elm);
      oldStartVNode = oldVNodeChildren[++oldVNodeStartIndex];
      newEndVNode = newVNodeChildren[--newVNodeEndIndex];
    } else if (sameNode(oldEndVNode, newStartVNode)) { // 旧尾和新首相同
      parentElm.insertBefore(oldStartVNode.elm, oldEndVNode.elm);
      oldEndVNode = oldVNodeChildren[--oldVNodeEndIndex];
      newEndVNode = newVNodeChildren[++newVNodeStartIndex];
    } else { // 新旧首尾均不同
      const newVNodeKey = newStartVNode.key;
      // 在old children中找到相同key的vnode
      const oldVNodeIndex = oldVNodeIndexKeyMap[newVNodeKey];
      const oldVNodeWithSameKey = oldVNodeChildren[oldVNodeIndex];
      // 找不到, 或者节点改变, 则直接创建新的VNode, 插入到oldStartVNode前一位
      if (!oldVNodeWithSameKey || !sameNode(oldVNodeWithSameKey, newStartVNode)) {
        parentElm.insertBefore(oldStartVNode.elm, newStartVNode.elm);
      } else {
        // 如果找到的vnode相同
        patchVNode(parentElm, oldVNodeWithSameKey, newStartVNode);
        oldVNodeChildren[oldVNodeIndex] = undefined;
        parentElm.insertBefore(oldStartVNode.elm, oldVNodeWithSameKey);
      }
      newStartVNode = newVNodeChildren[++newVNodeStartIndex];
    }
  }

  if (oldVNodeStartIndex > oldVNodeEndIndex) {
    const refNode = newVNodeChildren[newVNodeEndIndex + 1];
    while (newVNodeStartIndex <= newVNodeEndIndex) {
      // 将新node插入到节点树中(refNode后)
      parentElm.insert(refNode, newVNodeChildren[newVNodeStartIndex++]);
    }
  }
  if (newVNodeStartIndex > newVNodeEndIndex) {
    while (oldVNodeStartIndex <= oldVNodeEndIndex) {
      // 删除旧node
      parentElm.removeChild(oldVNodeChildren[oldVNodeStartIndex++]);
    }
  }

};

const sameNode = (n1, n2) => n1.tag === n2.tag && n1.key === n2.key;

const mapIndexToKey = (children, beginIndex, endIndex) => {
  const map = {};
  for (let i = beginIndex; i <= endIndex; i++) {
    map[children[i].key] = i
  }
  return map;
};
