package org.roda_project.commons_ip2.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 * 
 * @param <T>
 *          generic object.
 */
public class Tree<T> {
  /**
   * root of the node {@link T}.
   */
  private final T root;

  public Tree<T> getParent() {
    return parent;
  }

  /**
   * parent of the node {@link Tree<T>}.
   */
  private Tree<T> parent;
  /**
   * children nodes {@link ArrayList}.
   */
  private final ArrayList<Tree<T>> childs;

  /**
   * Constructor of new Tree.
   * 
   * @param root
   *          root of the new Tree {@link T}
   */
  public Tree(final T root) {
    this.root = root;
    this.childs = new ArrayList<>();
  }

  /**
   * Add a new child to the Tree.
   * 
   * @param child
   *          {@link T}
   */
  public Tree<T> addChild(final T child, final T parentNode) {
    final Tree<T> childTree;
    if (!checkIfExistChild(child, parentNode)) {
      childTree = new Tree<>(child);
      childTree.parent = this;
      this.childs.add(childTree);

    } else {
      childTree = getChild(child, parentNode);
    }
    return childTree;
  }

  /**
   * Get the root of this tree.
   * 
   * @return {@link T}
   */
  public T getRoot() {
    return root;
  }

  /**
   * Check if is the first (root) element of the tree.
   * 
   * @return true if the parent is null or false if isn't.
   */
  public boolean isRoot() {
    return parent == null;
  }

  /**
   * Check if is leaf or not.
   * 
   * @return true if does not have childs.
   */
  public boolean isLeaf() {
    return childs.isEmpty();
  }

  public List<Tree<T>> getChilds() {
    return this.childs;
  }

  /**
   * Get the level of the node in the tree.
   * 
   * @return the level.
   */
  public int getLevel() {
    if (isRoot()) {
      return 0;
    } else {
      return parent.getLevel() + 1;
    }
  }

  /**
   * Check if exist {@link T} in {@link ArrayList}.
   * 
   * @param child
   *          {@link T}.
   * @return a flag if exist or not.
   */
  public boolean checkIfExistChild(final T child, final T parentNode) {
    boolean exist = false;
    for (Tree<T> children : childs) {
      if (children.getRoot().equals(child) && root.equals(parentNode)) {
        exist = true;
        break;
      }
    }
    return exist;
  }

  /**
   * Get node equal to child given.
   * 
   * @param child
   *          {@link T}
   * @return {@link Tree<T>}.
   */
  public Tree<T> getChild(final T child, final T parentNode) {
    for (Tree<T> children : childs) {
      if (children.getRoot().equals(child) && root.equals(parentNode)) {
        return children;
      }
    }
    return null;
  }
}
