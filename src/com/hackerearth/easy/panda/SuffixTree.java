package com.hackerearth.easy.panda;

public class SuffixTree {
  static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789\1\2";
  static int lcsLength;
  static int lcsBeginIndex;
  
  public static class Node {
    int begin;
    int end;
    int depth; // distance in characters from root to this node
    Node parent;
    Node[] children;
    Node suffixLink;

    Node(int begin, int end, int depth, Node parent) {
      this.begin = begin;
      this.end = end;
      this.parent = parent;
      this.depth = depth;
      children = new Node[ALPHABET.length()];
    }
  }

  public static Node buildSuffixTree(CharSequence s) {
    int n = s.length();
    byte[] a = new byte[n];
    for (int i = 0; i < n; i++) a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
    Node root = new Node(0, 0, 0, null);
    Node node = root;
    for (int i = 0, tail = 0; i < n; i++, tail++) {
      Node last = null;
      while (tail >= 0) {
        Node ch = node.children[a[i - tail]];
        while (ch != null && tail >= ch.end - ch.begin) {
          tail -= ch.end - ch.begin;
          node = ch;
          ch = ch.children[a[i - tail]];
        }
        if (ch == null) {
          node.children[a[i]] = new Node(i, n, node.depth + node.end - node.begin, node);
          if (last != null) last.suffixLink = node;
          last = null;
        } else {
          byte t = a[ch.begin + tail];
          if (t == a[i]) {
            if (last != null) last.suffixLink = node;
            break;
          } else {
            Node splitNode = new Node(ch.begin, ch.begin + tail, node.depth + node.end - node.begin, node);
            splitNode.children[a[i]] = new Node(i, n, ch.depth + tail, splitNode);
            splitNode.children[t] = ch;
            ch.begin += tail;
            ch.depth += tail;
            ch.parent = splitNode;
            node.children[a[i - tail]] = splitNode;
            if (last != null) last.suffixLink = splitNode;
            last = splitNode;
          }
        }
        if (node == root) {
          --tail;
        } else {
          node = node.suffixLink;
        }
      }
    }
    return root;
  }

  // random test
  public static void main(String[] args) {
	  String s1 = "abacaba";
	  String s2 = "";
	  String s = s1 + '\1' + s2 + '\2';
      Node tree = buildSuffixTree(s);
  	  lcsLength = 0;
  	  lcsBeginIndex = 0;  
  	  
      // find longest common substring
      lcs(tree, s1.length(), s1.length() + s2.length() + 1);
      System.out.println("lcsLength "+ lcsLength+"\n"+"lcsBeginIndex"+lcsBeginIndex);
  }

  // traverse suffix tree to find longest common substring
  public static int lcs(Node node, int i1, int i2) {
    if (node.begin <= i1 && i1 < node.end) {
      return 1;
    }
    if (node.begin <= i2 && i2 < node.end) {
      return 2;
    }
    int mask = 0;
    for (char f = 0; f < ALPHABET.length(); f++) {
      if (node.children[f] != null) {
        mask |= lcs(node.children[f], i1, i2);
      }
    }
    if (mask == 3) {
      int curLength = node.depth + node.end - node.begin;
      if (lcsLength < curLength) {
        lcsLength = curLength;
        lcsBeginIndex = node.begin;
      }
    }
    return mask;
  }
}

