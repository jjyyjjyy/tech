package me.jy.sensor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 敏感词过滤
 *
 * @author jy
 */
@Slf4j
public class SensitiveWordFilter {

    private final Set<String> sensitiveWords;
    private final SensitiveWordNode root = new SensitiveWordNode();
    private final SensitiveWordDecorator decorator;

    public SensitiveWordFilter(SensitiveWordInitializer initializer, SensitiveWordDecorator decorator) {
        this.sensitiveWords = initializer.load();
        this.decorator = decorator;
        log.debug("sensitive worlds: {}", sensitiveWords);
        buildTree();
    }

    private void buildTree() {
        if (this.sensitiveWords == null || this.sensitiveWords.isEmpty()) {
            return;
        }
        for (String sensitiveWord : sensitiveWords) {
            char[] chars = sensitiveWord.toCharArray();
            SensitiveWordNode prefixNode = root;
            for (char word : chars) {
                SensitiveWordNode nextWordNode = prefixNode.getNextWordNode(word);
                if (nextWordNode == null) {
                    nextWordNode = new SensitiveWordNode().setWord(word);
                    prefixNode.putNextNode(nextWordNode);
                }
                prefixNode = nextWordNode;
            }
            // 单词最后一个字设置为end
            prefixNode.setEnd(true);
        }
    }

    public String decorate(String originString) {
        if (originString == null || originString.length() == 0) {
            return originString;
        }
        List<Range> ranges = new ArrayList<>();
        char[] chars = originString.toCharArray();
        int start = 0;
        SensitiveWordNode prefixNode = root;
        for (int i = 0; i < chars.length; i++) {
            SensitiveWordNode nextWordNode = prefixNode.getNextWordNode(chars[i]);
            // 没有后缀, 则从i开始重新检测
            if (nextWordNode == null) {
                start = i;
                prefixNode = Optional.ofNullable(root.getNextWordNode(chars[i])).orElse(root);
                continue;
            }
            // 为敏感词最后一个字
            if (nextWordNode.isEnd()) {
                // 处理该敏感词
//                Arrays.fill(chars, start, i + 1, '*');
                ranges.add(new Range(start, i));
                // 重置
                start = i + 1;
                prefixNode = root;
            } else {
                // 继续检测下一个字
                prefixNode = nextWordNode;
            }
        }
        return decorator.decorate(chars, ranges);
    }

    @Data
    private static class SensitiveWordNode {

        /**
         * 字
         */
        private char word;

        /**
         * 是否为单词的最后一个字
         */
        private boolean end;

        /**
         * 后缀节点
         */
        private Map<Character, SensitiveWordNode> nextNode = new HashMap<>(4);

        public SensitiveWordNode getNextWordNode(char word) {
            return nextNode.get(word);
        }

        public void putNextNode(SensitiveWordNode node) {
            nextNode.put(node.getWord(), node);
        }
    }
}
